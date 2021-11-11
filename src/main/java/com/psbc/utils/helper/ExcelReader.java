package com.psbc.utils.helper;

import com.psbc.exceptions.FileTypeException;
import com.psbc.pojo.TableModel;
import com.psbc.utils.StringProcessor;
import lombok.Data;
import lombok.NonNull;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.apache.poi.ss.usermodel.Cell.*;

@Data
public class ExcelReader {

    private static Logger logger = Logger.getLogger(ExcelReader.class.getName()); // 日志打印类

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private TableModel table;

    public ExcelReader(TableModel table){
        this.table = table;
    }
    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException, FileTypeException
     */
    public static Workbook getWorkbook(@NonNull InputStream inputStream, @NonNull String fileType) throws IOException, FileTypeException {
        Workbook workbook;
        if (XLS.equalsIgnoreCase(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (XLSX.equalsIgnoreCase(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new FileTypeException();
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     *
     * @param fileName 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public List<TableModel> readExcel(@NonNull String fileName) {

        List<TableModel> tableModels = new LinkedList<>();

        try (FileInputStream inputStream = new FileInputStream(fileName)){

            // 获取Excel工作簿
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            Workbook workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            tableModels = parseExcel(workbook, table);

        } catch (FileNotFoundException e) {
            logger.warning("指定Excel文件不存在！");
            e.printStackTrace();
        } catch (IOException e) {
            logger.warning("解析Excel失败，文件名：" + fileName + " 错误信息：" + e.getMessage());
            e.printStackTrace();
        } catch (FileTypeException e) {
            logger.warning("文件类型不匹配！");
            e.printStackTrace();
        } finally {
            return tableModels;
        }
    }

    /**
     * 解析Excel数据
     *
     * @param  workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<TableModel> parseExcel(Workbook workbook,TableModel table) {
        List<TableModel> resultDataList = new LinkedList<>();
        // 解析sheet
        int sheetSize = workbook.getNumberOfSheets();
        // 每一个Sheet
        for (int sheetNum = 0; sheetNum < sheetSize; sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 校验sheet是否合法
            if (sheet == null) { continue; }
            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (firstRow == null) {
                logger.warning("解析Excel失败：表头为空，在第一行没有读取到任何数据！");
            }
            // 解析每一行的数据，构造数据对象（除去两行表头）
            int rowStart = firstRowNum + 2;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            // 每一行
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) { continue; }
                TableModel rd = convertRowToData(row, Objects.requireNonNull(firstRow), table);
                if (rd == null) {
                    logger.warning("第" + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                resultDataList.add(rd);
            }
        }
        return resultDataList;
    }

    /**
     * 将单元格内容转换为字符串
     */
    public static String convertCellValueToString(Cell cell) {
        return getString(cell);
    }

    public static String getString(@NonNull Cell cell) {
        String returnValue = null;
        switch (cell.getCellType()) {
            case CELL_TYPE_NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case CELL_TYPE_STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case CELL_TYPE_BOOLEAN:   //布尔
                boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue + "";
                break;
            case CELL_TYPE_BLANK:     // 空值
                break;
            case CELL_TYPE_FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case CELL_TYPE_ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static TableModel convertRowToData(Row row, Row firstRow, TableModel table) {
        TableModel newTable = table.newInstanceWithoutArgs();
        Cell cell;
        Cell firstCell;
        Field field;
        int CellLength = firstRow.getLastCellNum();
        for (int cellNum = 0; cellNum < CellLength; cellNum++) {
            firstCell = firstRow.getCell(cellNum);
            cell = row.getCell(cellNum);

            String headName = StringProcessor.removeAllBlanks(convertCellValueToString(firstCell));
            String name = StringProcessor.removeAllBlanks(convertCellValueToString(cell));

            try {
                field = table.getClass().getDeclaredField(headName);
                field.setAccessible(true);
                field.set(newTable, name == null ? "" : name);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return newTable;
    }
}