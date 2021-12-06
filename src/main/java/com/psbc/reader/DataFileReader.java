package com.psbc.reader;

import com.psbc.pojo.TableModel;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.StringProcessor;
import com.psbc.utils.helper.XMLParser;

import java.io.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.psbc.utils.Reflector.getFileObjectFrom;

public class DataFileReader extends TAFileReader {

    // 配置目录
    private static final String configPath = ".\\src\\main\\resources\\xml\\file_configs\\";
    // 文件类型码所在行
    private static final int fileTypeCodePosition = 6;
    // 在数据文件中字段个数所在行
    private static final int fieldNumPosition = 9;
    // 文件对应的业务代码(文件的字段中第一个3-byte的字段;加判断防止其它字符也为3-byte)
    //private String businessCode;
    private List<String> fields;

    public DataFileReader(File file) {
        super.file = file;
        this.fields = new LinkedList<>();
    }

    /**
     * 读取数据文件，返回记录列表
     */
    // TODO:判断文件确为申请类文件
    @Override
    public List<TableModel> read() {

        List<TableModel> res = new LinkedList<>();
        // 该数据文件的文件类型对应的类
        TableModel fileClass = null;
        // 文件中的字段数
        int fieldSize = 0;
        // 文件中的记录数
        int recordSize = 0;

        boolean isBusinessCodeFound = false;
        // 读文件
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"))) {
            int lineIndex = 0;
            String str;
            while ((str = bf.readLine()) != null) {
                // 忽略索引文件中的表头内容
//                System.out.println(str);
                if (lineIndex < fileTypeCodePosition) {
                    lineIndex++;
                    continue;
                }
                // 第6行：文件类型
                if (lineIndex == fileTypeCodePosition) {
                    fileClass = getFileObjectFrom("File" + str.trim());
                }
                // 第9行：字段个数
                if (lineIndex == fieldNumPosition) {
                    fieldSize = Integer.parseInt(StringProcessor.removeAllBlanks(str));
                }
                // 从第10行到第10+fieldSize行：字段内容部分
                if (lineIndex > fieldNumPosition && lineIndex < fieldNumPosition + fieldSize + 2) {
                    // 判断业务代码字段(第一个长度为3 bytes的字段，防止其它字段也为3 bytes)
                    if (!isBusinessCodeFound &&
                            StringProcessor.cnLength(StringProcessor.removeAllBlanks(str)) == 3) {
                        //businessCode = StringProcessor.removeAllBlanks(str);
                        isBusinessCodeFound = true;
                    }
                    fields.add(str);
                }
                // 第10+fieldSize+1行：记录个数
                if (lineIndex == fieldNumPosition + fieldSize + 1) {
                    recordSize = Integer.parseInt(StringProcessor.removeAllBlanks(str));
                }
                // 从第10+fieldSize+2行到第10+fieldSize+2+recordSize行：每条记录内容

                if (lineIndex >= fieldNumPosition + fieldSize + 2 && lineIndex < fieldSize + recordSize + 11) {
                    res.add(convertRecordToObject(str, fields, Objects.requireNonNull(fileClass)));
                }

                lineIndex++;
            }
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param record
     * @param fields
     * @param clazz
     * @return
     */
    private TableModel convertRecordToObject(String record, List<String> fields, TableModel clazz) {
        TableModel res = clazz.newInstanceWithoutArgs();
        StringBuilder sb = new StringBuilder(record);
        // 注意类名和配置文件的文件名的一致性
        XMLNode configNode = XMLParser.parseXml(configPath + clazz.getClass().getSimpleName() + ".xml");
        int fieldsIndex = 0;
        try {
            // 一一匹配配置文件中的字段和fields中的字段
            for (XMLNode node : Objects.requireNonNull(configNode).getChildrenNodes()) {
                String fieldName = node.getAttributeValue("fieldName");
                Field field = res.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                if (fieldName.equals(fields.get(fieldsIndex))) {
                    int len = Integer.parseInt(node.getAttributeValue("length"));
                    field.set(res, StringProcessor.removeAllBlanks(StringProcessor.substringInByte(sb, len)));
                }
                fieldsIndex++;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}