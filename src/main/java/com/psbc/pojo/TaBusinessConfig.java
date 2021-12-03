package com.psbc.pojo;

import java.io.Serializable;
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
     * 确认业务代码(可以是多个，以逗号分隔)
     */
    private String confimbusinesscodes;

    /**
     * 状态（0-未激活(即不会执行)，1-立即执行(但确认日期根据延迟来写)，2-立即执行(确认日期为当天)，3-严格执行(即等到对应日期才执行)）
     */
    private Object status;

    private static final long serialVersionUID = 1L;

    @Override
    public DatabaseModel newInstanceWithoutArgs() {
        return new TaBusinessConfig();
    }
}