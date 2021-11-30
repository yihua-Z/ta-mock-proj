package com.psbc.business.service;

import com.psbc.pojo.TableModel;
import com.psbc.reader.DataFileReader;

import java.io.File;
import java.util.List;

public class CommonProcessUtils {
    public static List<TableModel> readRecords(String applicationFilePath) {

        DataFileReader applyReader = new DataFileReader(new File(applicationFilePath));
        List<TableModel> applyRecords = applyReader.read();
        return applyRecords;
    }
}
