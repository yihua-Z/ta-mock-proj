package com.psbc.business.processor;

import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.mapper.AcctShareDao;
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
//      @TODO   fundParaConfig 应增加联合字段查询
        FundParaConfig fundParaConfig = factory.getFundParaConfigDao().selectByPrimaryKey(1);

//        当前净值
//        BigDecimal nav = fundParaConfig.getNav();

//        理财产品总份数（含冻结）
        BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
//        理财产品冻结总份数
        BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();

//        理财产品实际可用总份数
        BigDecimal shareVol = totalvolofdistributorinta.subtract(totalfrozenvol);


//        a = -1,表示 applyAmount 小于 shareAmount；
//        a =  0,表示 applyAmount 等于 shareAmount；
//        a =  1,表示 applyAmount 大于 shareAmount；
        if (applicationvol.compareTo(shareVol) < 0) {
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

    }

    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {

    }

    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {

    }
}
