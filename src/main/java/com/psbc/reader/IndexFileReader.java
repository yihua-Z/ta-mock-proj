package com.psbc.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class IndexFileReader extends TAFileReader{

    // 在索引文件中有关数据文件名内容开始的行数
    private static final int DataNamesStartLine = 6;

    public IndexFileReader(File file){
        super.file = file;
    }

    /**
     * 先读索引文件，获取索引文件中的数据文件名
     * @return 这些文件名的列表
     */
    @Override
    public List<String> read() {
        return getDataFileNames();
    }

    private List<String> getDataFileNames(){
        List<String> res = new LinkedList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            int lineIndex = 0;
            String str;
            while ((str = bf.readLine()) != null) {
                // 忽略索引文件中的表头内容
                if(lineIndex++ < DataNamesStartLine) {
                    continue;
                }
                res.add(str);
            }
            // 移除索引文件结束符
            res.remove(res.size() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}