package com.psbc.writer;

import com.psbc.reader.xmlModel.XMLNode;

import java.util.*;

import static com.psbc.utils.StringProcessor.flushString;

public abstract class TAFileWriter implements Writer {

    // 生成文件的字符集
    public static final String GBK_CHAR_SET = "GBK";

    // ！！！均为包内可见，非protected, 防止包外继承！！！
    // 解析索引文件和数据文件的文件配置
    XMLNode configXML;

    // 生成文件的目录
    String targetPath;

    // 从配置文件中读取；保持顺序
    // file fields <value of 'fieldName', map of the rest fields<name,value>>
    Map<String, Map<String, String>> fields;

    /**
     * 读取指定配置文件，赋值索引文件和数据文件的对应属性
     */
    void setFieldsFromXML(){
        for (XMLNode kid : configXML.getChildrenNodes()) {
            //XML第二层：<file> index 或 data
            Map<String,String> attributes = kid.getAttributes();
            for (String name : attributes.keySet()) {
                String value = attributes.get(name);
                // 搜索属性name为‘index’或‘data’的节点
                // 注意：在子类中只解析其中对应的一个; 子类名必须包含xml中的<file name="xxx">的xxx
                if ("name".equals(name) &&
                        this.getClass().getCanonicalName().toLowerCase(Locale.ROOT).contains(value)) {
                    setFields(kid.getChildrenNodes());
                }
            }
        }
    }
    private void setFields(List<XMLNode> childrenNodes){
        for(XMLNode kid : childrenNodes){
            StringBuilder fieldName = new StringBuilder();
            Map<String, String> content = getFieldContent(kid, fieldName);
            fields.put(fieldName.toString(), content);
        }
    }

    /**
     * 读取XMLNode中的键值对
     * @param node 每一个xml节点
     * @param keySb 暂时存放”fieldName“的值, 作为配置map的key
     * @return 配置map的value
     */
    private Map<String,String> getFieldContent(XMLNode node, StringBuilder keySb) {
        Map<String,String> res = new HashMap<>();
        Map<String,String> attributes = node.getAttributes();
        for (String key : attributes.keySet()) {
            String value = attributes.get(key);
            if ("fieldName".equals(key)) {
                keySb.append(value);
            } else {
                res.put(key, value);
            }
        }
        return res;
    }

    /**
     * 生成 file header
     * @param fields 头文件中每个字段
     * @param contentArray 字段具体内容（hardcoded）
     * @param sb 用于暂时存放写入文件时不连续的字段
     * @return file header的具体内容
     */
    String generateFileHeader(Map<String, Map<String, String>> fields, String[] contentArray, StringBuilder... sb){
        StringBuilder res = new StringBuilder();
        Iterator<String> fieldIterator = fields.keySet().iterator();
        // 有序的fields
        int contentIndex = 0;
        while(fieldIterator.hasNext()){
            String fieldName = fieldIterator.next();
            Map<String, String> attrPair = fields.get(fieldName);
            Iterator<String> iterator = attrPair.keySet().iterator();
            String placeholder = "";
            int length = -1;
            String align = "";
            // 每个field的属性键值对(属性无序)
            while(iterator.hasNext()){
                String attrName = iterator.next();
                String attrValue = attrPair.get(attrName);
                if("length".equals(attrName)){
                    length = Integer.parseInt(attrValue);
                } else if("align".equals(attrName)){
                    placeholder = "left".equals(attrValue) ? " " : "0";
                    align = attrValue;
                }
            }
            String str = flushString(placeholder, length, contentArray[contentIndex], align);
            if("terminator".equals(fieldName)){
                sb[sb.length - 1].append(str);
            } else if("recordNum".equals(fieldName)){
                sb[sb.length - 2].append(str);
            } else {
                res.append(str);
                res.append("\n");
            }
            contentIndex++;
        }
        return res.toString();
    }

    /**
     * Index File Writer中需具体实现
     */
    abstract void collect(String name);
}