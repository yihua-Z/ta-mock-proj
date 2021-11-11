package com.psbc.reader;

import java.util.List;

public interface Reader {
    //index: 读后产生List<String>：数据文件名列表
    //data:  读后产生List<TableModel>
    List<?> read();
}