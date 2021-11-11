package com.psbc.launch;

import com.psbc.exceptions.BusinessFileMappingException;
import com.psbc.exceptions.ConfirmationTypeNotFoundException;
import com.psbc.pojo.TableModel;
import com.psbc.reader.DataFileReader;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.ConfirmationGenerator;
import com.psbc.utils.FieldsDifference;
import com.psbc.utils.concurrent.MultiCompletable;
import com.psbc.utils.concurrent.MultiThread;
import com.psbc.utils.helper.XMLParser;
import com.psbc.writer.DataFileWriter;
import com.psbc.writer.IndexFileWriter;
import com.psbc.writer.TAFileWriter;
import com.psbc.utils.random.Random;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import static com.psbc.utils.Reflector.getRandomInstanceFrom;
import static com.psbc.utils.StringProcessor.*;

/**
 * 该类用于读取申请文件、生成对应的确认文件的启动类
 */
public class ConfirmationGenerationLaunch implements MultiCompletable {

    private static final String businessConfigPath = ".\\src\\main\\resources\\zhang\\xml\\business_configs\\";
    private static final String randomConfig = ".\\src\\main\\resources\\zhang\\xml\\random_configs\\data_dictionary_random.xml";
    private static final String fieldConfigPath = ".\\src\\main\\resources\\zhang\\xml\\file_configs\\";
    private static List<XMLNode> batches;

    public static void main(String[] args) {
        final String bootPath = "./src/main/resources/zhang/xml/boot/bootForConfirmationGeneration.xml";
        XMLNode bootNode = XMLParser.parseXml(bootPath);
        batches = new ArrayList<>(Objects.requireNonNull(bootNode).getChildrenNodes());
        int threadSize = Integer.parseInt(batches.get(0).getAttributeValue("threadSize"));
        MultiThread threads = new MultiThread(threadSize, new ConfirmationGenerationLaunch());
        threads.execute();
    }

    @Override
    public void singleProcess(int batchIndex) {
        // 跳过<environment>节点
        if(batchIndex == 0) return;

        XMLNode xmlNode = batches.get(batchIndex);
        String targetPath = xmlNode.getAttributeValue("targetPath");
        String fileConfigPath = xmlNode.getAttributeValue("fileConfigPath");

        // 读取生成文件的配置，初始化索引文件(需等待加入数据文件的文件名后才可写索引文件)
        IndexFileWriter indexWriter = new IndexFileWriter(fileConfigPath, targetPath);

        for(XMLNode file : xmlNode.getChildrenNodes()){
                // 001
                String applicationBusinessCode = file.getAttributeValue("applicationBusinessCode");
                // .../OFD_Tom_Jack_20200304_01.txt
                String applicationFilePath = file.getAttributeValue("applicationFilePath");

                String businessApplyConfig = businessConfigPath + applicationBusinessCode + ".xml";
            String confirmationBusinessCode = null;
            try {
                confirmationBusinessCode = getConfirmationBusinessCodeFrom(applicationBusinessCode);
            } catch (ConfirmationTypeNotFoundException e) {
                e.printStackTrace();
            }
            String businessConfirmConfig = businessConfigPath + confirmationBusinessCode + ".xml";
            DataFileReader applyReader = new DataFileReader(new File(applicationFilePath));
            List<TableModel> applyRecords = applyReader.read();

            // 获得申请业务和确认业务的字段的差集，随机赋值这些差集
            List<String> differences = FieldsDifference.process(businessApplyConfig,businessConfirmConfig);
            XMLNode randConfig = XMLParser.parseXml(randomConfig);
            List<XMLNode> childrenNodes = Objects.requireNonNull(randConfig).getChildrenNodes();

            List<Map<String,String>> randomValues = new LinkedList<>();  //Map<fieldName, fieldValue>

            // 每个申请对象（即每条申请记录的Bean）
            int size = applyRecords.size();
            int index = 0;
            while(index < size){
                Field[] fields = applyRecords.get(index).getClass().getDeclaredFields();
                Map<String, String> randomValue = new HashMap<>();
                // 确认文件中每一个需要随机内容的字段
                for(String attrName : differences){
                    for(XMLNode node : childrenNodes){
                        if(attrName.equals(node.getAttributeValue("fieldName"))){
                            Map<String,String> diffNodeAttr = node.getAttributes();
                            //System.out.println(attrName + ":" + diffNodeAttr.get("randomType"));
                            Random randomClazz = null;
                            try {
                                if(diffNodeAttr.get("randomType").split(",").length > 1){
                                    randomClazz = getRandomInstanceFrom(diffNodeAttr.get("randomType").split(",")[1]);
                                } else {
                                    randomClazz = getRandomInstanceFrom(diffNodeAttr.get("randomType"));
                                }
                            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                                e.printStackTrace();
                            }
                            // 放申请文件中的原值
                            if(diffNodeAttr.get("randomType").contains("KeepSame")){
                                // 001业务中不存在TAAccountID
                                if("001".equals(applicationBusinessCode)){
                                    randomValue.put(attrName, randomClazz.next(diffNodeAttr));
                                }
                                else {
                                    String mapper = node.getAttributeValue("mapper");
                                    for (Field field : fields) {
                                        String name = field.getName();
                                        if (mapper.equals(name)) {
                                            field.setAccessible(true);
                                            try {
                                                //System.out.println("aPPLY:"+applyRecords.get(index));
                                                randomValue.put(attrName, (String) field.get(applyRecords.get(index)));
                                            } catch (Exception e) {
                                                System.out.println("获取原值失败！");
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            else {
                                randomValue.put(attrName, randomClazz.next(diffNodeAttr));
                            }
                            break;
                        }
                    }
                }
                randomValues.add(randomValue);
                index++;
            }
            ConfirmationGenerator confirGenerator = new ConfirmationGenerator(applicationBusinessCode, applyRecords, randomValues);
            List<TableModel> confirmations = confirGenerator.generate();

            // 读取待写入数据文件中的字符段配置
            XMLNode attrNode = null;
            try {
                attrNode = XMLParser.parseXml(fieldConfigPath + getFileTypeFrom(confirmationBusinessCode) + ".xml");
            } catch (BusinessFileMappingException e) {
                e.printStackTrace();
            }
            TAFileWriter dataWriter = new DataFileWriter(fileConfigPath, targetPath, attrNode, confirmations);
            indexWriter.collect(dataWriter.write());
        }
        //对于生成确认文件，暂不写索引文件
        //indexWriter.write();
    }

    @Override
    public int getTasksSize() {
        return batches.size();
    }
    // 注意：对于每一个差集中的非必填字段，如果申请文件中该字段为空，则在回复文件中同样为空
}