package com.psbc.utils.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 该类根据从指定doc中提取的所有文件表格形成的excel文件中生成每个表格所对应的xml配置文件；
 * 注意1：如果excel中的表格格式存在某些未知问题，生成的xml的内容可能不会完全正确，需手动修改。
 * 注意2：xml的格式按照Schema文件“schema.xsd”中定义的格式生成
 * 注意3：需要手动填写<file>中的属性fileCode的内容（因为无法自动读取）
 */
public class XMLWriter {
    private static final String XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    private static final String ROOTATTRI = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"schema.xsd\"";
    private static final String[] fileAttrs = {
            //"name=",
            "type=",
            //"fileCode=",
    };
    private static final String[] fieldAttrs = {
            "fieldName=",       // order
            "fieldType=",       // order
            "length=",          // order
            "description=",     // order
            "required=",        // skip one col [暂时忽略类似“Y 9”中的“9”]
            "placeholder=",     // [需判断type]
            "precision=",       // [需判断type和length]
            "align=",           // [需判断type]
            "caseSensitive=",   // [可不写]
    };
    // 缓存从cell中读取的原始数据，以便其它attribute利用
    private static String[] fieldAttrsRowValues;

    //业务数据表头（及单业务文件表头）
    private static final String[] allowedTableHeader1 = {"ID","字段名","类型","长度","描述","备注","是否必需"};
    //多业务文件表头
    private static final String[] allowedTableHeader2 = {"ID","字段名","类型","长度","描述","备注"};
    //数据字典文件表头
    private static final String[] allowedTableHeader3 = {"ID","字段名","类型","长度","描述"};

    // 处理后可以直接写入的数据
    private static final String[] fileAttrsProcessedValues = new String[1]; //目前只包含是否定长

    private static final List<String[]> processedFieldAttrsList = new LinkedList<>();

    // 文件是否定长
    private static boolean isLengthSolid = true;

    public static void main(String[] args) throws IOException {
        final String excelPath = "./src/main/resources/zhang/excel/中邮table.xlsx";
        final String targetPath = "./src/main/resources/zhang/target/xmlTarget/";
        final String[] header = allowedTableHeader1;
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelPath));
        int numberOfSheets = workbook.getNumberOfSheets();
        int sheetNum = 0;
        //each sheet
        NextSheet:
        for (int i = 0; i < numberOfSheets; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> iterator = sheet.iterator();
            int rowIndex = 0;
            processedFieldAttrsList.clear();
            isLengthSolid = true;
            //each row
            NextRow:
            while (iterator.hasNext()) {
                fieldAttrsRowValues = new String[header.length];
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int colIndex = 0;
                int fieldRowValueIndex = 0;
                //each col
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //判断表头以确定该页为业务数据表

                    if (rowIndex == 0) {
                        if (!removeAllBlanks(cell.getStringCellValue()).equals(header[colIndex]) ||
                                //(colIndex == header.length - 1 && cellIterator.hasNext()) ||
                                (colIndex < header.length - 1 && !cellIterator.hasNext())) {
                            continue NextSheet;
                        }
                    } else {
                        //跳过ID段和备注段
                        if (colIndex == 0 || colIndex == header.length - 2) {
                            //如果ID为空，直接跳到下一行
                            if (colIndex == 0 && isBlankIDCell(cell)) {
                                continue NextRow;
                            }
                            colIndex++;
                            continue;
                        }
                        fieldAttrsRowValues[fieldRowValueIndex++] = getCellContent(cell);
                    }
                    colIndex++;
                }
                if(rowIndex > 0)
                    processRowValueOfFieldAttrs();
                rowIndex++;
            }
            processRowValueOfFileAttrs();
            //形成数据
            String result = generateXMLString();
            //写入文件
            String targetFile = targetPath + sheetNum + ".xml";
            writerXMLFile(targetFile, result);
            sheetNum++;
        }
        System.out.println("完成");
    }

    private static String generateXMLString(){
        //注意值为空的或长度为0的
        StringBuilder sb = new StringBuilder();
        sb.append(XMLHeader);
        sb.append("<file ");
        //sb.append(ROOTATTRI);
        int fileAttrLength = fileAttrsProcessedValues.length;
        for(int i = 0; i < fileAttrLength; ++i){
            sb.append(" ");
            sb.append(fileAttrs[i]);
            sb.append(fileAttrsProcessedValues[i]);
        }
        sb.append(" ");
        sb.append("fileCode=\"\"");
        sb.append(">\n");
        for (String[] strings : processedFieldAttrsList) {
            sb.append("\t");
            sb.append("<field");
            int attrsLength = strings.length;
            for (int j = 0; j < attrsLength; ++j) {
                if (strings[j] != null && !"".equals(removeAllBlanks(strings[j]))) {
                    if (!"\"null\"".equals(removeAllBlanks(strings[j]))) {
                        //防止excel中整数带“.0”, 对精度而言
                        if(!"\".0\"\"".equals(strings[j])){
                            sb.append(" ");
                            sb.append(fieldAttrs[j]);
                            sb.append(strings[j]);
                        }
                    }
                }
            }
            sb.append("/>\n");
        }
        sb.append("</file>");
        return sb.toString();
    }

    private static void writerXMLFile(String targetFile, String content) throws IOException {
        FileWriter fw = new FileWriter(targetFile);
        fw.write(content);
        fw.close();
    }

    private static String getCellContent(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return "\"" + cell.getStringCellValue() + "\"";
            case Cell.CELL_TYPE_BOOLEAN:
                return "\"" + cell.getBooleanCellValue() + "\"";
            case Cell.CELL_TYPE_NUMERIC:
                return "\"" + cell.getNumericCellValue() + "\"";
        }
        return null;
    }

    private static void processRowValueOfFieldAttrs(){
        String[] fieldAttrsProcessedValues = new String[9];
        //fieldName
        fieldAttrsProcessedValues[0] = removeAllBlanks(fieldAttrsRowValues[0]);
        //fieldType
        fieldAttrsProcessedValues[1] = removeAllBlanks(fieldAttrsRowValues[1]);
        //length
        String[] lengths = processFieldLengthAttr(removeAllBlanks(fieldAttrsRowValues[2]));
        fieldAttrsProcessedValues[2] = lengths[0];
        //description
        fieldAttrsProcessedValues[3] = removeAllBlanks(fieldAttrsRowValues[3]);
        //required
        fieldAttrsProcessedValues[4] = processFieldRequiredAttr(removeAllBlanks(fieldAttrsRowValues[4]));
        //placeholder
        //数字左补零右对齐(A,N),字符右补空格左对齐(C,TEXT[不补])
        final boolean isStringType = "\"A\"".equals(fieldAttrsProcessedValues[1]) || "\"N\"".equals(fieldAttrsProcessedValues[1]);
        fieldAttrsProcessedValues[5] = isStringType ?
                "\"0\"" : "\"C\"".equals(fieldAttrsProcessedValues[1]) ? "\"space\"" : "\"\"";
        //precision；如果长度为0，则不写这个属性
        fieldAttrsProcessedValues[6] = !"\"N\"".equals(fieldAttrsProcessedValues[1]) && lengths[1] == null?
                "\"null\"" : "\""+lengths[1]+"\"";
        //align
        fieldAttrsProcessedValues[7] = isStringType ?
                "\"right\"" : "\"left\"";
        //caseSensitive; 目前来说所有的都不区分大小写
        fieldAttrsProcessedValues[8] = "\"insensitive\"";
        //是否定长
        if("TEXT".equals(fieldAttrsProcessedValues[1])){
            isLengthSolid = false;
        }
        //给当前file的当前fieldList加入当前field
        processedFieldAttrsList.add(fieldAttrsProcessedValues);
    }

    private static void processRowValueOfFileAttrs(){
        fileAttrsProcessedValues[0] = isLengthSolid ? "\"solidLength\"" : "\"variaLength\"";
    }

    public static String[] processFieldLengthAttr(String rowValue){
        String[][] chineseToNum = {{"一","1"},{"两","2"},{"三","3"},{"四","4"},{"五","5"},{"六","6"},{"七","7"},{"八","8"},{"九","9"},{"十","10"}};
        //考虑小数和不定长: 9（六|6位小数）； 5，4； ”TEXT"
        String[] res = new String[2];
        if(rowValue != null && (rowValue.contains("(") || rowValue.contains("（"))){
            int index = rowValue.contains("(") ? rowValue.indexOf("(") : rowValue.indexOf("（");
            res[0] = removeAllBlanks(rowValue.substring(0, index)) + "\"";
            String decimalPart = rowValue.substring(index);
            for (String[] strings : chineseToNum) {
                for (int j = 0; j < 2; ++j) {
                    if (decimalPart.contains(strings[j])) {
                        res[1] = strings[1];
                        break;
                    }
                }
            }
        } else if(rowValue != null && rowValue.contains(".")){
            int index = rowValue.indexOf(".");
            res[0] = removeAllBlanks(rowValue.substring(0, index)) + "\"";
            String decimalPart = removeAllBlanks(rowValue.substring(index));
            res[1] = decimalPart;
        } else if(rowValue != null && rowValue.contains("TEXT")){
            res[0] = "-1";
        } else if(rowValue != null){
            res[0] = rowValue;
            res[1] = null;
        }
        return res;
    }

    // 暂忽略可能含有的数字部分
    private static String processFieldRequiredAttr(String rowValue){
        if(rowValue == null) return null;
        return rowValue.contains("Y") ? "\"true\"" : rowValue.contains("N") ? "\"false\"" : "\"unknown\"";
    }

    private static boolean isBlankIDCell(Cell cell){
        String content = getCellContent(cell);
        return content == null || "".equals(removeAllBlanks(content));
    }

    private static String removeAllBlanks(String str){
        return str == null ? null : str.replaceAll("\\s*", "");
    }
}