package com.psbc.writer;

import com.psbc.exceptions.XMLParsingException;
import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.StringProcessor;
import com.psbc.utils.helper.XMLParser;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.psbc.utils.StringProcessor.flushString;

public class DataFileWriter extends TAFileWriter{

    // hardcoded
    private String filePrefix = "OFD";

    // 数据文件中的字段记录
    private XMLNode xmlNode;

    // 数据文件中的记录列表
    private List<TableModel> records;

    public DataFileWriter(String configFile, String target, XMLNode xmlNode, List<TableModel> records){
        this.xmlNode = xmlNode;
        this.records = records;
        targetPath = target;
        fields = new LinkedHashMap<>();
        configXML = XMLParser.parseXml(configFile);
        setFieldsFromXML();
    }

    /**
     * 写数据文件
     */
    @Override
    public String write() {
        // content: dataIdentifier, version, fileCreator, fileReceiver, date, summaryCode, fileType, sender, receiver, terminator
        String[] contentArray = {"OFDCFDAT","21","Tom","Jack",
                new SimpleDateFormat("yyyyMMdd").format(new Date()),
                "001",
                records.get(0).getClass().getSimpleName().substring(4),"王五","李四",
                xmlNode.getChildrenNodes().size()+"",records.size()+"","OFDCFEND"};
        String dataFileName = String.format("%s_%s_%s_%s_%s.TXT",
                this.filePrefix, contentArray[2],
                contentArray[3], contentArray[4],
                contentArray[6]);
        // 存放填充后的terminator
        StringBuilder term = new StringBuilder();
        // 存放填充后的record number
        StringBuilder recordNum = new StringBuilder();
        try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(targetPath + dataFileName), GBK_CHAR_SET))){

            // 写入表头
            out.write(generateFileHeader(fields, contentArray, recordNum, term));

            // 写入每一个字段名
            for (XMLNode node : xmlNode.getChildrenNodes()) {
                out.write(node.getAttributes().get("fieldName"));
                out.write("\n");
            }
            out.write(recordNum.toString());
            out.write("\n");
            // 写入每一个记录
            for (TableModel tableModel : records) {
                //int len = 0;
                StringBuilder sb = new StringBuilder();
                for(XMLNode xn : xmlNode.getChildrenNodes()){
                    Map<String, String> attributes = xn.getAttributes();
                    Field field = tableModel.getClass().getDeclaredField(attributes.get("fieldName"));
                    field.setAccessible(true);
                    Object obj = field.get(tableModel);
                    String cellValues = StringProcessor.removeAllBlanks(obj == null ? "" : obj.toString());
                    String value = processRecord(cellValues, attributes);
                    //len += StringProcessor.cnLength(value);
                    sb.append(value);
                }
                //System.out.println("length:" + len);
                //System.out.println("content:" + sb + ";");
                out.write(sb.toString());
                out.write("\n");
            }
            out.write(term.toString());
        } catch (IOException | NoSuchFieldException | IllegalAccessException | XMLParsingException e) {
            e.printStackTrace();
        }
        System.out.println("A data file completed.");
        return dataFileName;
      }

    /**
     * 处理数据文件中每个record的数据格式
     */
    private String processRecord(String cellValues, Map<String, String> attributes) throws XMLParsingException {
        String type = "";
        String length = "";
        String align = "";
        String precision = "";
        String placeholder = "";

        Set<String> m_keySet = attributes.keySet();
        for (String m_key : m_keySet) {
            String key = m_key.trim();
            switch (key) {
                case "fieldType":
                    type = attributes.get(key);
                    break;
                case "length":
                    length = attributes.get(key);
                    break;
                case "align":
                    align = attributes.get(key);
                    break;
                case "precision":
                    precision = attributes.get(key);
                    break;
                case "placeholder":
                    placeholder = "space".equals(attributes.get(key)) ? " " : "0";
                    break;
            }
        }

        String re = cellValues;
        // 如果是字符型：左对齐，右补空格（TEXT不需要补）
        if ("C".equals(type) || "TEXT".equals(type)) {
            if (!"".equals(length) && !"".equals(align)) {
                re = flushString(placeholder, Integer.parseInt(length), cellValues, align);
            }
            else {
                throw new XMLParsingException();
            }
        }
        // 如果是数字型: type, length, align, precision, placeholder
        else if ("A".equals(type) || "N".equals(type)) {
            if (!"".equals(length) && !"".equals(align)) {
                // 有精确度属性
                if (!"".equals(precision)) {
                    //placeholder, length, content, align
                    // 补小数部分
                    int decimalLen = Integer.parseInt(precision);
                    // 补整数部分
                    if(cellValues.contains(".")){
                        cellValues = cellValues.replace(".","");
                    } else {
                        if(cellValues.length() < Integer.parseInt(length)) {
                            for (int i = 0; i < decimalLen; i++) {
                                cellValues += "0";
                            }
                        }
                    }
                    re = flushString(placeholder, Integer.parseInt(length), cellValues, align);
                    // 删除小数点
                    re = re.replace(".","");
                }
                // 无精确度属性
                else {
                    re = flushString(placeholder, Integer.parseInt(length), cellValues, align);
                }
            } else {
                throw new XMLParsingException();
            }
        }
        return re;
    }

    /**
     * 无需具体实现
     */
    @Override
    void collect(String name) {}
}