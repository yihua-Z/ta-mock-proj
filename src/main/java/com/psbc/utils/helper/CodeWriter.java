package com.psbc.utils.helper;

import com.psbc.exceptions.EmptyStringException;
import com.psbc.utils.StringProcessor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.psbc.utils.StringProcessor.removeAllBlanks;


/**
 * 该类根据从指定doc中提取的所有文件表格形成的excel文件中生成每个表格所对应的model类；
 * 注意1：如果excel中的表格格式存在某些未知问题，生成的xml的内容可能不会完全正确，需手动修改。
 * 注意2：最终field的类型均为private String
 * 注意3：类中只有fields(无任何方法)
 */
public class CodeWriter {
    //业务数据表头（及单业务文件表头）
    private static final String[] allowedTableHeader1 = {"ID","字段名","类型","长度","描述","备注","是否必需"};
    //多业务文件表头
    private static final String[] allowedTableHeader2 = {"ID","字段名","类型","长度","描述","备注"};
    //{表格中的数据类型，对应的Java数据类型}
    private static final String[][] attrType = {{"C","String"}, {"A","String"}, {"N","String"}, {"TEXT","String"}};
    //读业务表时
    private static String businessCode;
    //读文件表时
    private static String fileCode;

    public static void main(String[] args) throws IOException, EmptyStringException {
        //!!!注意!!!:文件处理前必须转为Excel，且每张表为一个Excel页面
        //原因：原word格式极其混乱，意外不可预测，故不可读。
        String xmlPath = "./src/main/resources/zhang/row.xlsx";
        String targetPath = "./src/main/resources/zhang/target/";
        Workbook workbook = new XSSFWorkbook(new FileInputStream(xmlPath));
        int numberOfSheets = workbook.getNumberOfSheets();
        //XWPFDocument doc = new XWPFDocument(is);
        int ta = 0;
        String[] acceptedHeader = allowedTableHeader2;
        //each sheet
        NextSheet:
        for (int i = 0; i < numberOfSheets; ++i) {
            ta++;
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> iterator = sheet.iterator();
            //存储写入Java文件的值
            List<String> attrNameList = new LinkedList<>();
            List<String> attrTypeList = new LinkedList<>();
            int rowIndex = 0;
            //each row
            NextRow:
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int colIndex = 0;
                //each col
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (colIndex == 0 && (cell.getStringCellValue() == null || "".equals(removeAllBlanks(cell.getStringCellValue())))) {
                        continue NextRow;
                    }
                    //判断表头类型以确定该页为指定表类
                    if (rowIndex == 0) {
                        if (!removeAllBlanks(cell.getStringCellValue()).equals(acceptedHeader[colIndex]) ||
                                (colIndex == acceptedHeader.length - 1 && cellIterator.hasNext()) ||
                                (colIndex < acceptedHeader.length - 1 && !cellIterator.hasNext())) {
                            continue NextSheet;
                        }
                    } else {
                        // 检测第二列和第三列
                        if (colIndex == 1) {
                            // 把每个属性名加入“属性列表”
                            attrNameList.add(removeAllBlanks(getCellContent(cell)));
                            colIndex++;
                            continue;
                        } else if (colIndex == 2) {
                            // 把每个属性对应的类型加入“属性类型列表”
                            attrTypeList.add(getJavaType(removeAllBlanks(getCellContent(cell))));
                            rowIndex++;
                            continue NextRow;
                        }
                    }
                    colIndex++;
                }
                rowIndex++;
            }
            String resultCode = generateCode(ta+"", attrTypeList, attrNameList);
            String des = targetPath + ta + ".java";
            writeJavaFile(des, resultCode);
        }
    }

    private static String getCellContent(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
        }
        return null;
    }

    // 获取对应的attr类型
    private static String getJavaType(String str) {
        if(StringProcessor.isBlankString(str)) return "UNKNOWN";
        for(String[] strs : attrType){
            if(strs[0].equals(str.trim())){
                return strs[1];
            }
        }
        return "";
    }

    // 形成code
    private static String generateCode(String className, List<String> attrTypeList, List<String> attrNameList){
        final String annotation =
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.Data;\n" +
                "import lombok.NoArgsConstructor;\n\n"+
                        "@Data\n@AllArgsConstructor\n@NoArgsConstructor\n";
        final String PRIVATE = "private";
        final String PUBLIC = "public";

        StringBuilder re = new StringBuilder(annotation);
        re.append(PUBLIC);
        re.append(" class ");
        re.append(className);
        re.append(" implements TableModel");
        re.append(" {\n");
        int len = attrNameList.size();
        for(int i = 0; i < len; ++i){
            re.append("\t");
            re.append(PRIVATE).append(" ");
            re.append(attrTypeList.get(i)).append(" ");
            re.append(attrNameList.get(i)).append(";\n");
        }
        re.append("\n");
        re.append("\t@Override\n" +
                  "\tpublic TableModel newInstanceWithoutArgs() { return new (); }\n}");

        return re.toString();
    }

    // 写.java文件
    private static void writeJavaFile(String filepath, String content){
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f));
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.flush();
            write.close();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}