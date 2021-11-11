package com.psbc.utils.helper;

import com.psbc.utils.StringProcessor;
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

public class ReturnCodeXMLWriter {
    private static final String XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    private static final String[] fieldAttrs = {
            "code=",
            "description=",
    };

    // “return code”, "description"
    private static String[] fieldAttrsRowValues = new String[2];

    private static final List<String[]> processedFieldAttrsList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        final String xmlPath = "./src/main/resources/zhang/excel/returnCode.xlsx";
        final String targetPath = "./src/main/resources/zhang/xml/return_code/";
        final String[] header = {"错误码", "错误陈述"};
        Workbook workbook = new XSSFWorkbook(new FileInputStream(xmlPath));
        int numberOfSheets = workbook.getNumberOfSheets();
        int sheetNum = 0;
        //each sheet
        NextSheet:
        for (int i = 0; i < numberOfSheets; i++) {
            processedFieldAttrsList.clear();
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> iterator = sheet.iterator();
            int rowIndex = 0;
            NextRow:
            while (iterator.hasNext()) {
                fieldAttrsRowValues = new String[2];
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int fieldRowValueIndex = 0;
                // each col
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (rowIndex > 0) {
                        fieldAttrsRowValues[fieldRowValueIndex++] = getCellContent(cell);
                    }
                }
                if (rowIndex > 0)
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
        System.out.println("写入完成");
    }

    private static void writerXMLFile(String targetFile, String content) throws IOException {
        FileWriter fw = new FileWriter(targetFile);
        fw.write(content);
        fw.close();
    }

    private static String generateXMLString() {
        StringBuilder sb = new StringBuilder();
        sb.append(XMLHeader);
        sb.append("<returnCodes>\n");
        for(String[] strings : processedFieldAttrsList){
            sb.append("\t");
            sb.append("<returnCode");
            int attrsLength = strings.length;
            for (int j = 0; j < attrsLength; ++j) {
                sb.append(" ");
                sb.append(fieldAttrs[j]);
                sb.append(strings[j]);
            }
            sb.append("/>\n");
        }
        sb.append("</returnCodes>");
        return sb.toString();
    }

    private static void processRowValueOfFieldAttrs() {
        String[] fieldAttrsProcessedValues = new String[2];
        fieldAttrsProcessedValues[0] = fieldAttrsRowValues[0];
        fieldAttrsProcessedValues[1] = fieldAttrsRowValues[1];
        processedFieldAttrsList.add(fieldAttrsProcessedValues);
    }

    private static String getCellContent(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return "\"" + StringProcessor.removeAllBlanks(cell.getStringCellValue()) + "\"";
            case Cell.CELL_TYPE_BOOLEAN:
                return "\"" + cell.getBooleanCellValue() + "\"";
            case Cell.CELL_TYPE_NUMERIC:
                return "\"" + cell.getNumericCellValue() + "\"";
        }
        return null;
    }
}