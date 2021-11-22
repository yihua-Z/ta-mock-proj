package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * customer_info
 * @author 
 */
@Data
public class CustomerInfo implements Serializable {
    /**
     * 投资人理财帐号
     */
    private Integer taaccountid;

    /**
     * 投资人户名
     */
    private String name;

    /**
     * 投资人性别；1-男，2-女
     */
    private Object sex;

    /**
     * 投资人国籍（采用GB/T 2659-2000）
     */
    private String nationality;

    /**
     * 投资人行业（采用国标 GB/T4754-2011）
     */
    private String vocation;

    /**
     * 投资人通讯地址
     */
    private String address;

    /**
     * 投资人手机号码
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
     * 证件有效期
     */
    private String certvaliddate;

    /**
     * 销售人代码
     */
    private String distributorcode;

    /**
     * 网点号码
     */
    private String branchcode;

    /**
     * TA代码
     */
    private String tacode;

    /**
     * TA客户编号
     */
    private String customerno;

}