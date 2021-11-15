package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * account_info
 * @author 
 */
@Data
public class AccountInfo implements Serializable {
    /**
     * 投资人理财账号
     */
    private String taaccountid;

    /**
     * 投资人户名
     */
    private String name;

    /**
     * 投资人性别
     */
    private String sex;

    /**
     * 投资者国籍
     */
    private String nationality;

    /**
     * 行业
     */
    private String vocation;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 投资人电话号码
     */
    private String telno;

    /**
     * 个人证件类型及机构证件类型
     */
    private String certificatetype;

    /**
     * 投资人证件号码
     */
    private String certificateno;

    /**
     * 证件有效日期
     */
    private String certvaliddate;

    /**
     * 投资人理财交易账号
     */
    private String transactionaccountid;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 网点号码
     */
    private String branchcode;

    /**
     * 账户状态
     */
    private String accountstatus;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * TA客户编号
     */
    private String customerno;

    private static final long serialVersionUID = 1L;
}