package com.psbc.utils.helper;

import com.psbc.utils.StringProcessor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.psbc.utils.helper.ExcelReader.convertCellValueToString;
import static com.psbc.utils.helper.ExcelReader.getWorkbook;

public class SQLWriter {

    private static Map<String,String> typeMapper = new HashMap<String,String>(){
        {
            put("A","VARCHAR");put("C","VARCHAR");put("N","DECIMAL");put("TEXT","TEXT");
        }
    };

    private static Map<String,String> decimalMapper = new HashMap<String,String>(){
        {
            put("һ","1");put("��","2");put("��","3");put("��","4");put("��","5");
            put("��","6");put("��","7");put("��","8");put("��","9");put("ʮ","10");
        }
    };

    private static String primaryKey = "\tprimary key (),\n";
    private static String foreignKey = "\tforeign key (����ֶ�) references ����(����),\n";
    private static String uniqueIndex = "\tunique key (a,b)";

    public static void main(String[] args) throws Exception {
        String sPath = "./src/main/resources/zhang/sql.txt";
        String dataDic = "./src/main/resources/zhang/excel/dataDict.xlsx";
        String targetPath = "./src/main/resources/zhang/target/sql/sql";

        String content = getCreateSql(sPath, dataDic);
        FileWriter fw = new FileWriter(targetPath);
        fw.write(content);
        fw.close();
    }

    public static String getCreateSql(String structurePath, String dataDic) {

        Map<String, String[]> attrMap = parseExcel(dataDic);

        StringBuilder res = new StringBuilder("create table if not exists ");
        List<String> fields = getFields(structurePath);
        // table name
        res.append(fields.get(0) + " (\n");
        // attribute name

        // type

        // �Ƿ�Ϊ��

        // comment
        for (int i = 1; i < fields.size(); ++i) {

            res.append("\t");
            //��name
            System.out.println(":" + fields.get(i) + ":");
            res.append(fields.get(i));

            int tabNum = 8;
            int nameLen = fields.get(i).length();
            tabNum = (tabNum * 8 - nameLen) / 8;
            for(int j = 0; j < tabNum; j++){
                res.append("\t");
            }

            if(attrMap.containsKey(fields.get(i))){
                // type
                String rowType = StringProcessor.removeAllBlanks(attrMap.get(fields.get(i))[0]);
                String databaseType = typeMapper.get(rowType);
                res.append(databaseType);
                String[] lens = XMLWriter.processFieldLengthAttr(StringProcessor.removeAllBlanks(attrMap.get(fields.get(i))[1]));
                if("A".equals(rowType)){
                    res.append("(" + lens[0] + ")");

                } else if("N".equals(rowType)){
                    res.append("(" + lens[0]);
                    if(lens[1]!=null){
                        res.append(","+lens[1]);
                    }
                    res.append(")");

                } else if("C".equals(rowType)){
                    res.append("(" + lens[0] + ")");

                } else if("TEXT".equals(rowType)){
                    // do nothing
                }
                res.append("\tnot null comment \"" + StringProcessor.removeAllBlanks(attrMap.get(fields.get(i))[2]) + "\"");
                res.append(",\n");
            } else {
                // ������¼ӵ��ֶ�
                // do nothing
                res.append(",\n");
            }
        }
        res.append(primaryKey);
        res.append(foreignKey);
        res.append(uniqueIndex);
        res.append("\n)ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        return res.toString();
    }

    private static List<String> getFields(String txtPath){
        List<String> res = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(txtPath))){
            String line = null;
            while((line = reader.readLine()) != null){
                res.add(line);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    private static Map<String, String[]> parseExcel(String path){
        Map<String, String[]> res = new HashMap<>();
        try(FileInputStream inputStream = new FileInputStream(path)){
            String fileType = path.substring(path.lastIndexOf(".") + 1);
            Workbook workbook = getWorkbook(inputStream, fileType);
            Sheet sheet = workbook.getSheetAt(0);

            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            int rowEnd = sheet.getPhysicalNumberOfRows();
            // ÿһ������
            for (int rowNum = firstRowNum + 1; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                int cellLength = firstRow.getLastCellNum();
                // ÿһ��
                String key = null;
                String[] content = new String[3];
                for(int colNum = 1; colNum < cellLength - 2; ++colNum){
                    Cell cell = row.getCell(colNum);
                    String val = StringProcessor.removeAllBlanks(convertCellValueToString(cell));
                    // ID,Name,Type,Length,Description,
                    if(colNum == 1){
                        key = val;
                    } else if(colNum >= 2){
                        content[colNum - 2] = val;
                    }
                }
                res.put(key,content);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
}