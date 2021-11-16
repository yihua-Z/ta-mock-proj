package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_expectation
 * @author 
 */
@Data
public class AccountExpectation implements Serializable {
    /**
     * 申请单编号
     */
    private String appsheetserialno;

    /**
     * 交易处理返回代码
     */
    private String returncode;

    /**
     * 出错详细信息
     */
    private String errordetail;

    /**
     * 条目状态
     */
    private String status;

    /**
     * 交易确认日期
     */
    private String transactioncfmdate;

    /**
     * TA确认交易流水号
     */
    private String taserialno;

    /**
     * 是否注册登记人发起业务标志
     */
    private String fromtaflag;

    /**
     * 交易所在地区编号
     */
    private String regioncode;

    /**
     * 操作（清算）网点编号
     */
    private String netno;

    /**
     * 摘要/说明
     */
    private String specification;

    private static final long serialVersionUID = 1L;
}