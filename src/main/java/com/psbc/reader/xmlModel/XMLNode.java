package com.psbc.reader.xmlModel;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public class XMLNode {

    private Map<String, String> attributes;  // map for 'key - value'
    private List<XMLNode> childrenNodes;
    private XMLNode next;

    public XMLNode(){
        attributes = new HashMap<>();
        childrenNodes = new LinkedList<>();
        next = null;
    }
    public XMLNode(Map<String, String> attrs){
        this();
        attributes = attrs;
    }
    public XMLNode(Map<String, String> attrs, List<XMLNode> children){
        attributes = attrs;
        childrenNodes = children;
        next = null;
    }

    /**
     * 向当前节点增加子节点
     */
    public void addChild(XMLNode child){
        childrenNodes.add(child);
    }

    /**
     * 返回当前节点的子节点的个数
     * @return 子节点个数
     */
    public int getChildrenSize(){
        return childrenNodes.size();
    }

    /**
     * 获取第i个子节点
     */
    public XMLNode getChild(int i){
        if(i < 0 || i >= childrenNodes.size()){
            return null;
        }
        return childrenNodes.get(i);
    }

    /**
     * 返回子节点列表
     */
    public List<XMLNode> getChildrenNodes(){
        return childrenNodes;
    }
    /**
     * 向当前节点增加属性
     * @param attrName attrValue
     */
    public void addAttribute(String attrName, String attrValue){
        attributes.put(attrName, attrValue);
    }

    /**
     * 获取当前节点的某一属性值
     */
    public String getAttributeValue(String attrName) {
        return attributes.get(attrName);
    }

    /**
     * 返回当前节点的属性个数
     */
    public int getAttributeSize(){
        return attributes.size();
    }

    /**
     * 追加兄弟节点
     */
    public void addNextXMLObject(XMLNode obj){
        next = obj;
    }
}