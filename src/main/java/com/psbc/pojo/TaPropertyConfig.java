package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * ta_property_config
 * @author 
 */
@Data

public class TaPropertyConfig implements Serializable {
    /**
     * TA代码
     */
    private String tacode;

    /**
     * TA账户前缀
     */
    private String accountprefix;

    /**
     * TA账户序号
     */
    private Integer accountindex;

    private static final long serialVersionUID = 1L;
}