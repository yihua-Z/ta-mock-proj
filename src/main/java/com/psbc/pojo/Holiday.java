package com.psbc.pojo;

import lombok.Data;

/**
 * holiday
 * @author 
 */
@Data
public class Holiday implements DatabaseModel {
    /**
     * TA代码
     */
    private String tacode;

    /**
     * 具体日期
     */
    private String day;

    /**
     * 这个日期是否为假期
     */
    private Boolean isholiday;

    /**
     * 下一假期日
     */
    private String nextholiday;

    /**
     * 日期类型（0-银行工作日，1-债券交易日）
     */
    private Object daytype;

    private static final long serialVersionUID = 1L;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new Holiday();
    }
}