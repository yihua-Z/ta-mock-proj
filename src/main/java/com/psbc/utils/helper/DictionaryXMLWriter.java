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

import static com.psbc.utils.StringProcessor.removeAllBlanks;

public class DictionaryXMLWriter {
    private static final String XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    private static final String[] fieldAttrs = {
            "fieldName=",
            "length=",
            "precision=",
            "randomType=",
            "randomRange=",
    };

    // 缓存从cell中读取的原始数据，以便其它attribute利用
    // "name","type","length","generateType","range"
    private static String[] fieldAttrsRowValues = new String[5];

    private static final List<String[]> processedFieldAttrsList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        final String xmlPath = "./src/main/resources/zhang/dataDict.xlsx";
        final String targetPath = "./src/main/resources/zhang/";
        final String[] header = {"ID","Name","Type","Length","Description","GenerateType","Range"};
        Workbook workbook = new XSSFWorkbook(new FileInputStream(xmlPath));
        int numberOfSheets = workbook.getNumberOfSheets();
        int sheetNum = 0;
        //each sheet
        NextSheet:
        for (int i = 0; i < numberOfSheets; ++i) {
            processedFieldAttrsList.clear();
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> iterator = sheet.iterator();
            int rowIndex = 0;
            NextRow:
            while (iterator.hasNext()){
                fieldAttrsRowValues = new String[5];
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int colIndex = 0;
                int fieldRowValueIndex = 0;
                // each col
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    //判断表头以确定该页为数据字典表
                    if (rowIndex == 0) {
                        if (!removeAllBlanks(cell.getStringCellValue()).equals(header[colIndex]) ||
                                (colIndex == header.length - 1 && cellIterator.hasNext()) ||
                                (colIndex < header.length - 1 && !cellIterator.hasNext())) {
                            continue NextSheet;
                        }
                    } else {
                        //跳过ID段和Description段
                        if (colIndex == 0 || colIndex == 4) {
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
            //形成数据
            String result = generateXMLString();
            //写入文件
            String targetFile = targetPath + sheetNum + ".xml";
            writerXMLFile(targetFile, result);
            sheetNum++;
        }
        System.out.println("写入完成。");
    }

    private static void writerXMLFile(String targetFile, String content) throws IOException {
        FileWriter fw = new FileWriter(targetFile);
        fw.write(content);
        fw.close();
    }

    private static String generateXMLString(){
        //注意值为空的或长度为0的
        StringBuilder sb = new StringBuilder();
        sb.append(XMLHeader);
        sb.append("<fields>\n");
        for (String[] strings : processedFieldAttrsList) {
            sb.append("\t");
            sb.append("<field");
            int attrsLength = strings.length;
            for (int j = 0; j < attrsLength; ++j) {
                if (strings[j] != null && !"".equals(removeAllBlanks(strings[j]))) {
                    if (!"\"null\"".equals(removeAllBlanks(strings[j]))) {
                        //1.防止excel中整数带“.0”, 对精度而言
                        //2.
                        if(!"\".0\"\"".equals(strings[j]) &&
                                !"\"不适用\"".equals(strings[j]) && !"-1".equals(strings[j])){
                            sb.append(" ");
                            sb.append(fieldAttrs[j]);
                            sb.append(strings[j]);
                        }
                    }
                }
            }
            sb.append("/>\n");
        }
        sb.append("</fields>");
        return sb.toString();
    }

    private static void processRowValueOfFieldAttrs(){
        //row: {"name","type","length","generateType","range"}
        //res: {"fieldName","length","precision","randomType","randomRange"}
        String[] fieldAttrsProcessedValues = new String[5];
        // "fieldName"
        fieldAttrsProcessedValues[0] = removeAllBlanks(fieldAttrsRowValues[0]);
        // "length"
        String[] lengths = processFieldLengthAttr(removeAllBlanks(fieldAttrsRowValues[2]));
        fieldAttrsProcessedValues[1] = lengths[0];
        // "precision"
        fieldAttrsProcessedValues[2] = !"\"N\"".equals(removeAllBlanks(fieldAttrsRowValues[1])) && lengths[1] == null?
                "\"null\"" : "\""+lengths[1]+"\"";
        // "randomType"
        fieldAttrsProcessedValues[3] = removeAllBlanks(fieldAttrsRowValues[3]);
        // "randomRange"
        fieldAttrsProcessedValues[4] = "不适用".equals(removeAllBlanks(fieldAttrsRowValues[4])) ?
                "" : removeAllBlanks(fieldAttrsRowValues[4]);
        //给当前file的当前fieldList加入当前field
        processedFieldAttrsList.add(fieldAttrsProcessedValues);
    }

    private static String[] processFieldLengthAttr(String rowValue){
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

    private static boolean isBlankIDCell(Cell cell){
        String content = getCellContent(cell);
        return content == null || "".equals(removeAllBlanks(content));
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
}
