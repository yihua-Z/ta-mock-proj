package com.psbc.pojo;

import lombok.Data;

/**
 * ta_property
 * @author 
 */
@Data
public class TaProperty implements DatabaseModel {
    /**
     * TA代码
     */
    private String TACode;

    /**
     * TA账户前缀
     */
    private String accountPrefix;

    /**
     * TA账户序号
     */
    private Integer accountIndex;

    private static final long serialVersionUID = 1L;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new TaProperty();
    }
}