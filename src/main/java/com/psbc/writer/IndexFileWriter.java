package com.psbc.writer;

import com.psbc.utils.helper.XMLParser;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class IndexFileWriter extends TAFileWriter{

    // temporarily hardcoded
    private String filePrefix = "OFI";

    // 用于存放多个数据文件的文件名
    private List<String> dataFileNames = new ArrayList<>();

    public IndexFileWriter(String configFile, String target) {
        // 如："./src/main/java/com/psbc/zhang/config/xml/file_configs.xml"
        configXML = XMLParser.parseXml(configFile);
        // 如："./src/main/resources/zhang/"
        targetPath = target;
        fields = new LinkedHashMap<>();
        setFieldsFromXML();
    }

    /**
     * 向数据文件名列表中加入新生成的数据文件名
     * @param name 数据文件名
     */
    @Override
    public void collect(String name){
        dataFileNames.add(name);
    }

    /**
     * 写索引文件, 必须在完成生成数据文件后执行
     */
    @Override
    public String write() {
        if(dataFileNames == null || dataFileNames.size() < 1) {
            System.out.println("无数据文件，索引文件写入失败。");
            return null;
        }
        // hard-code content: indexIdentifier, version, creator, receiver, date, terminator
        String[] contentArray = {"OFDCFIDX","21","Tom","Jack",
                new SimpleDateFormat("yyyyMMdd").format(new Date()),
                dataFileNames.size()+"","OFDCFEND"};

        String indexFileName = String.format("%s_%s_%s_%s.TXT",
                contentArray[0], contentArray[2], contentArray[3], contentArray[4]);
        // 存放填充后的terminator
        StringBuilder term = new StringBuilder();
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(targetPath + indexFileName), GBK_CHAR_SET))) {

            out.write(generateFileHeader(fields, contentArray, term));
            for (String dataFileName : dataFileNames) {
                out.write(dataFileName);
                out.write("\n");
            }
            out.write(term.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("索引文件已生成。");
        return indexFileName;
    }
}