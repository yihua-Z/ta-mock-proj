package com.psbc.launch;

import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.ExcelReader;
import com.psbc.utils.helper.XMLParser;
import com.psbc.writer.DataFileWriter;
import com.psbc.writer.IndexFileWriter;
import com.psbc.writer.TAFileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.psbc.utils.Reflector.getFileObjectFrom;

/**
 * 该类用于从格式化的Excel中读取记录并生成对应的TA文件的启动类
 */
public class ExcelToTAFileLaunch {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // 读取boot.xml (该文件的具体作用请看boot.xml中的注解)
        final String bootPath = "./src/main/resources/zhang/xml/bootForExcelToFile.xml";
        XMLNode bootNode = XMLParser.parseXml(bootPath);

        // 多次文件传输的list (“多个数据文件 + 一个对应的索引文件” 视为 “一次传输”)
        List<XMLNode> batches = Objects.requireNonNull(bootNode).getChildrenNodes();

        for(XMLNode batch : batches){
            String targetPath = batch.getAttributeValue("targetPath");
            String fileConfigPath = batch.getAttributeValue("fileConfigPath");

            // 读取生成文件的配置，初始化索引文件(需等待加入数据文件的文件名后才可写索引文件)
            IndexFileWriter indexWriter = new IndexFileWriter(fileConfigPath, targetPath);

            // 遍历传输的文件类型
            for(XMLNode file : batch.getChildrenNodes()){
                String fileClass = file.getAttributeValue("class");
                String fieldConfigPath = file.getAttributeValue("fieldConfigPath");
                String dataPath = file.getAttributeValue("dataPath");

                // 读取待写入数据文件中的字符段配置
                XMLNode attrNode = XMLParser.parseXml(fieldConfigPath);

                // 确定该文件的类型
                TableModel tableModel = getFileObjectFrom(fileClass);
                // 读取待写入数据文件中的每条数据记录
                ExcelReader excelReader = new ExcelReader(tableModel);
                List<TableModel> readResult = excelReader.readExcel(dataPath);
                TAFileWriter dataWriter = new DataFileWriter(fileConfigPath, targetPath, attrNode, readResult);
                indexWriter.collect(dataWriter.write());
            }
            // 写索引文件
            indexWriter.write();
        }
    }
}