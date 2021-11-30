package com.psbc.utils;


import com.psbc.exceptions.BusinessFileMappingException;
import com.psbc.exceptions.ConfirmationTypeNotFoundException;
import com.psbc.exceptions.EmptyStringException;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

public class StringProcessor {

    /**
     * 从文件类名获得文件序号
     */
    public static String getFileNoFromFileClazzName(@NonNull final String className){
        return className.substring(4,6);
    }
    public static String removeAllBlanks(String str) {
        if(str == null || str.trim().length() < 1){
            //throw new EmptyStringException();
            return str == null ? null : str.trim();
        }
        return str.replaceAll("\\s*","");
    }
    public static boolean isBlankString(String str) {
        //try {
        return str == null || "".equals(removeAllBlanks(str));
        //} catch (EmptyStringException e){
        //    return true;
        //}
    }
    // 首字母小写（前提：原首字母必须为大写）
    public static String lowerFirstCase(String str) throws EmptyStringException{
        if(isBlankString(str)) throw new EmptyStringException();
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars).trim();
    }

    // 首字母大写（前提：原首字母必须为小写）
    public static String upperFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars).trim();
    }

    // 包含汉字的字符串长度（单位byte）
    public static int cnLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static String substringInByte(StringBuilder s, int length){
        int sum = 0;
        for(int i = 0; i < s.length(); ++i){
            int code = s.charAt(i);
            if(19968 <= code && code < 40869) {
                sum += 2;
            } else {
                sum ++;
            }
            if(sum >= length){
                String res = s.substring(0, i + 1);
                s.delete(0, res.length());
                return res;
            }
        }
        return "";
    }

    /**
     * 填充字符串
     * @param placeholder 待填充的字符
     * @param length 填充后的长度
     * @param string 要格式化的字符串
     * @param align 左右对齐方式
     */
    public static String flushString(String placeholder, int length, String string, String align) {
        // 长度足够的或"TEXT"类型不需要补（注意：无内容的补空格）
        if (string.length() >= length || "".equals(placeholder)) {
            return string;
        }
        StringBuilder cs = new StringBuilder();
        int len = cnLength(string);
        if(len < 1){
            for (int i = 0; i < length - len; i++) {
                cs.append(" ");
            }
        } else {
            for (int i = 0; i < length - len; i++) {
                cs.append(placeholder);
            }
        }
        return "left".equals(align) ? string + cs : cs + string;
    }

    /**
     * 填充数字的精确度
     * @param: l 填充后的长度
     * @param: string 待填充的数字字符串
     */
    private static String flushPrecision(int l, String string) {
        StringBuilder cs = new StringBuilder(string);
        cs.append(".");
        for (int i = 0; i < l; i++) {
            cs.append("0");
        }
        return cs.toString();
    }

    /**
     * 根据业务类型获取对应的文件类型
     * @param businessType 业务类型
     * @return FileType
     */
    public static String getFileTypeFrom(String businessType) throws BusinessFileMappingException {
        final String mappingFile = ".\\src\\main\\resources\\zhang\\xml\\business_file_mapping\\business_file_mapping.xml";
        XMLNode xmlNode = XMLParser.parseXml(mappingFile);
        List<XMLNode> childrenNodes = Objects.requireNonNull(xmlNode).getChildrenNodes();
        for(XMLNode node : childrenNodes){
            String[] businesses = node.getAttributeValue("business").split(",");
            if(businesses.length > 0){
                for(String business : businesses){
                    if(business.equals(businessType)){
                        return node.getAttributeValue("file");
                    }
                }
            }
        }
        throw new BusinessFileMappingException();
    }

    /**
     * 根据申请业务类型确定对应的确认业务类型
     */
    public static String getConfirmationBusinessCodeFrom(String applicationCode) throws ConfirmationTypeNotFoundException {
        XMLNode xmlNode = XMLParser.parseXml(".\\src\\main\\resources\\zhang\\xml\\application_confirmation_mapping\\application_confirmation_mapping.xml");
        for(XMLNode node : Objects.requireNonNull(xmlNode).getChildrenNodes()){
            String[] applications = node.getAttributeValue("application").split(",");
            for(String app : applications){
                if(app.equals(applicationCode)){
                    return node.getAttributeValue("confirmation");
                }
            }
        }
        throw new ConfirmationTypeNotFoundException();
    }
}