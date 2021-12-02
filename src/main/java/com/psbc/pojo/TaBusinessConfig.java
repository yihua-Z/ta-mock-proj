package com.psbc.pojo;

import lombok.Data;

/**
 * ta_business_config
 * @author 
 */
@Data
public class TaBusinessConfig implements DatabaseModel {
    /**
     * TA代码
     */
    private String tacode;

    /**
     * 业务方向（‘1’-单向，‘2’-双向）
     */
    private Object direction;

    /**
     * 申请业务代码
     */
    private String applybusinesscode;

    /**
     * 延迟处理天数
     */
    private Integer settlementdays;

    /**
     * 处理状态（‘1’-成功，‘2’-失败）
     */
    private Object processstatus;

    /**
     * 确认业务代码(可以是多个，以逗号分隔)
     */
    private String confimbusinesscodes;

    private static final long serialVersionUID = 1L;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new TaBusinessConfig();
    }
}