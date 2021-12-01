package com.psbc.business.processor;

import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.mapper.AcctShareDao;
import com.psbc.mapper.FundParaConfigDao;
import com.psbc.mapper.TransactionConfirmationDao;
import com.psbc.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static com.psbc.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.getFullNowDateTime;

/**
 * @author Huilin Tong
 * @date 2021年11月30日 14:23
 */
public class Processor024 extends BiDirectionProcessor {

    //  赎回必输字段校验，
    //  金额、日期等等
    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

        RepositoryFactory factory = SpringContextUtil.getBean(RepositoryFactory.class);

        TransactionApplication transactionApplication = (TransactionApplication) apply;

        String transactiondate = transactionApplication.getTransactiondate();
        String transactiontime = transactionApplication.getTransactiontime();

//        赎回份数
        BigDecimal applicationvol = transactionApplication.getApplicationvol();

//      查找当前赎回申请 的持仓表记录
        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = factory.getAcctShareDao().selectByPrimaryKey(acctShareKey);

//      查找当前赎回的对应产品参数
//        FundParaConfig fundParaConfig=new FundParaConfig();
//        copyFields(transactionApplication,fundParaConfig);
//        fundParaConfig = factory.getFundParaConfigDao().selectByUnionCode(fundParaConfig);
//        当前净值
//        BigDecimal nav = fundParaConfig.getNav();

//        理财产品总份数（含冻结）
        BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//        理财产品冻结总份数
        BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();

//        理财产品实际可用总份数
        BigDecimal shareVol = totalvolofdistributorinta.subtract(totalfrozenvol);


//        a = -1,表示 shareAmount 小于 applyAmount；
//        a =  0,表示 shareAmount 等于 applyAmount；
//        a =  1,表示 shareAmount 大于 applyAmount；
        if (shareVol.compareTo(applicationvol) < 0) {
//            超过当前额度，赎回异常
            throw new ApplyException();
        }
//        @TODO 判断的日期应该从Holiday表中获取交易日
        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {
            throw new ApplyException();
        }
    }

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {
        RepositoryFactory factory = SpringContextUtil.getBean(RepositoryFactory.class);

        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) expect;

        String transactiondate = transactionConfirmation.getTransactiondate();
        String transactiontime = transactionConfirmation.getTransactiontime();
//        赎回份数
        BigDecimal applicationvol = transactionConfirmation.getConfirmedvol();

        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {
            throw new ConfirmExpectationException();
        }

        //      查找当前赎期望 的持仓表记录
        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionConfirmation, acctShareKey);
        AcctShare acctShare = factory.getAcctShareDao().selectByPrimaryKey(acctShareKey);

        //        理财产品总份数（含冻结）
        BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        //        理财产品冻结总份数
        BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();

        //        理财产品实际可用总份数
        BigDecimal shareVol = totalvolofdistributorinta.subtract(totalfrozenvol);

        if (shareVol.compareTo(applicationvol) < 0) {
//            超过当前额度，赎回异常
            throw new ConfirmExpectationException();
        }

//        读取期望配置表（获取失败信息）
//        获取交易日期配置表，获取延迟确认天数

//        获取工作日日历表

//        读取产品表（获取个人额度，产品额度）
//        读取账户信息表（获取个人已购额度）
    }

    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {

        RepositoryFactory factory = SpringContextUtil.getBean(RepositoryFactory.class);
        TransactionConfirmationDao transactionConfirmationDao = factory.getTransactionConfirmationDao();
        AcctShareDao acctShareDao = factory.getAcctShareDao();
        FundParaConfigDao fundParaConfigDao = factory.getFundParaConfigDao();

        TransactionApplication transactionApplication = (TransactionApplication) apply;
        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) confirm;

        FundParaConfig fundParaConfig = new FundParaConfig();

        fundParaConfig.setFundcode(transactionApplication.getFundcode());
        fundParaConfig.setTacode(transactionApplication.getTacode());
        fundParaConfig.setDistributorcode(transactionApplication.getDistributorcode());

        FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);
        BigDecimal nav = paraConfig.getNav();

        paraConfig.setFundmaxbala(paraConfig.getFundmaxbala().add((nav.multiply(transactionApplication.getApplicationvol()))));


        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionConfirmation, acctShareKey);
        AcctShare acctShare = factory.getAcctShareDao().selectByPrimaryKey(acctShareKey);

        BigDecimal shareTotalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        acctShare.setTotalvolofdistributorinta(shareTotalvolofdistributorinta.subtract(transactionApplication.getApplicationvol()));

        copyFields(transactionApplication, transactionConfirmation);

        //生成交易确认成功记录（124）
        transactionConfirmationDao.insert(transactionConfirmation);
        //更新或新增账户持仓表
        acctShareDao.updateByPrimaryKey(acctShare);
        //更新产品已售额度等
        fundParaConfigDao.updateByPrimaryKey(paraConfig);
    }

    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {
        RepositoryFactory factory = SpringContextUtil.getBean(RepositoryFactory.class);
    }
}
