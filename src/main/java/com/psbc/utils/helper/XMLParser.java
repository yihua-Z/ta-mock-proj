package com.psbc.utils.helper;

import com.psbc.reader.xmlModel.XMLNode;
import lombok.NonNull;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 解析xml文件 JDOMTest.java
 */
public class XMLParser {

    /**
     * 解析xml配置文件，生成对应的 file(1) - field list(n) - attribute list(n) 集合
     * @param path of XML file
     * @return XMLNode
     * <p>由于当xml文件比较大时，JDOM的解析性能严重下降且有可能内存溢出；
     * 故先假设只有<file></file>和<field></field>两层tag</p>
     */
    public static XMLNode parseXml(@NonNull String path) {

        try (FileInputStream in = new FileInputStream(path)) {

            // 解析中文时出现乱码，可以改变编码格式
            // InputStreamReader isr = new InputStreamReader(in, "utf-8");
            // 获取根节点
            Element root = new SAXBuilder().build(in).getRootElement();

            // 获取根节点下的子节点
            List<Element> childList = root.getChildren();
            List<XMLNode> childNodes = new LinkedList<>();

            for (Element child : childList) {
                Map<String, String> attributes = getAllAttributesFromElement(child);
                List<XMLNode> allChildrenFromElement = getAllChildrenFromElement(child);
                childNodes.add(new XMLNode(attributes, allChildrenFromElement));
            }
            return new XMLNode(getAllAttributesFromElement(root), childNodes);

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析并获取当前节点的属性
     */
    private static Map<String,String> getAllAttributesFromElement(@NonNull Element ele){
        List<Attribute> attributes = ele.getAttributes();
        Map<String, String> res = new HashMap<>();
        for(Attribute attr : attributes){
            String name = attr.getName();
            String value = attr.getValue();
            res.put(name, value);

        }
        return res;
    }

    /**
     * 递归获取当前节点的子节点
     */
    private static List<XMLNode> getAllChildrenFromElement(@NonNull Element ele){
        List<XMLNode> res = new LinkedList<>();
        List<Element> children = ele.getChildren();
        for(Element child : children) {
            Map<String, String> attributes = getAllAttributesFromElement(child);
            List<XMLNode> subNodes = getAllChildrenFromElement(child);
            XMLNode obj = new XMLNode(attributes, subNodes);
            res.add(obj);
        }
        return res;
    }
}