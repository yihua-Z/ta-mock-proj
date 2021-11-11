package com.psbc.utils;

import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;

import java.util.LinkedList;
import java.util.List;

public class FieldsDifference {

    /**
     * @param application 申请业务字段的配置文件路径
     * @param confirmation 确认业务字段的配置文件路径
     * @return 确认业务字段中独有的字段名
     */
    public static List<String> process(String application, String confirmation){
        XMLNode applyNode = XMLParser.parseXml(application);
        XMLNode confiNode = XMLParser.parseXml(confirmation);
        return compareXML(applyNode, confiNode);
    }

    /**
     * 返回第二个配置文件中的独有字段名
     * @param one 第一个配置文件
     * @param two 第二个配置文件
     * <p>限制：xml格式必须和业务字段的配置文件一致</p>
     */
    private static List<String> compareXML(XMLNode one, XMLNode two){
        List<String> namesInOne = new LinkedList<>();
        for(XMLNode node : one.getChildrenNodes()) {
            namesInOne.add(node.getAttributeValue("fieldName"));
        }
        List<String> namesInTwo = new LinkedList<>();
        for(XMLNode node : two.getChildrenNodes()) {
            namesInTwo.add(node.getAttributeValue("fieldName"));
        }
        namesInTwo.removeAll(namesInOne);
        return namesInTwo;
    }
}