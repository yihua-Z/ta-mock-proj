package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * timed_application
 * @author 
 */
@Data
public class TimedApplication implements Serializable {
    /**
     * 定时任务ID
     */
    private Integer appsheetserialno;

    /**
     * 任务名字（即包名+类名+方法名）
     */
    private String tacode;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 处理日期
     */
    private String transactiondate;

    /**
     * 是否完成标志（每日由脚本完成更新）
     */
    private Object completness;

    private static final long serialVersionUID = 1L;
}