package com.psbc.business.processor;

import com.nlf.calendar.util.HolidayUtil;
import com.psbc.business.service.RepositoryFactory;
import com.psbc.business.service.SpringContextUtil;
import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.mapper.AcctShareDao;
import com.psbc.mapper.FundParaConfigDao;
import com.psbc.mapper.TransactionConfirmationDao;
import com.psbc.pojo.*;
import com.psbc.reader.xmlModel.XMLNode;
import com.psbc.utils.helper.XMLParser;
import org.apache.log4j.Logger;

import java.lang.Exception;
import java.math.BigDecimal;
import java.util.List;

import static com.psbc.service.ObjectProcessor.copyFields;
import static com.psbc.utils.DateAndTimeUtil.getFullNowDateTime;
import static com.psbc.utils.DateAndTimeUtil.getNextTransactionDay;

/**
 * @author Huilin Tong
 * @date 2021年11月30日 14:23
 */
public class Processor024 extends BiDirectionProcessor {
    private static final Logger logger = Logger.getLogger(Processor024.class);

    public static RepositoryFactory FactoryDao() {
        RepositoryFactory repositoryFactory = SpringContextUtil.getBean(RepositoryFactory.class);
        return repositoryFactory;
    }

    //  赎回必输字段校验，
    //  金额、日期等等
    @Override
    void validateApply(ApplicationModel apply) throws ApplyException {

        TransactionApplication transactionApplication = (TransactionApplication) apply;
//        @TODO 必要字段存在不为空
//        根据对应业务的XML解析是否required
//        XMLNode xmlNode= XMLParser.parseXml(".\\src\\main\\resources\\xml\\business_configs\\024.xml");

        String transactiondate = transactionApplication.getTransactiondate();
        String transactiontime = transactionApplication.getTransactiontime();

//        赎回份数
        BigDecimal applicationvol = transactionApplication.getApplicationvol();

//      查找当前赎回申请 的持仓表记录
        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionApplication, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

//        查找当前赎回的对应产品参数
        FundParaConfig fundParaConfig = new FundParaConfig();
        copyFields(transactionApplication, fundParaConfig);
        fundParaConfig = FactoryDao().getFundParaConfigDao().selectByUnionCode(fundParaConfig);
//        当前净值
        BigDecimal nav = fundParaConfig.getNav();

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
            logger.error("赎回超过当前额度");
            throw new ApplyException();
        }
//
        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {
            logger.error("日期错误");
            throw new ApplyException();
        }


    }

    @Override
    void validateConfirmExpectation(ExpectationModel expect) throws ConfirmExpectationException {

        //        读取期望配置表（获取失败信息）
        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) expect;

        String transactiondate = transactionConfirmation.getTransactiondate();
        String transactiontime = transactionConfirmation.getTransactiontime();
        //        赎回份数
        BigDecimal applicationvol = transactionConfirmation.getConfirmedvol();
        //  @TODO 赎回最小份数的判断

        if (Double.valueOf(transactiondate + transactiontime) > Double.valueOf(getFullNowDateTime())) {
            throw new ConfirmExpectationException();
        }
        //        获取交易日期配置表，获取延迟确认天数
        //        获取工作日日历表
        if(HolidayUtil.getHoliday(transactiondate)!=null){
            logger.error("期望交易日期为非工作日");
            throw new ConfirmExpectationException();
        }

        //      查找当前赎期望的持仓表记录
        //      读取产品表（获取个人额度，产品额度）
        //      读取账户信息表（获取个人已购额度）
        AcctShareKey acctShareKey = new AcctShareKey();
        copyFields(transactionConfirmation, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

        //        理财产品总份数（含冻结）
        BigDecimal totalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        //        理财产品冻结总份数
        BigDecimal totalfrozenvol = acctShare.getTotalfrozenvol();

        //        理财产品实际可用总份数
        BigDecimal shareVol = totalvolofdistributorinta.subtract(totalfrozenvol);

        if (shareVol.compareTo(applicationvol) < 0) {
//            超过当前额度，赎回异常
            logger.error("赎回超过当前额度");
            throw new ConfirmExpectationException();
        }


    }

    @Override
    void updateRepository(ApplicationModel apply, List<ConfirmationModel> confirm, ApplyException applyException) {

//        TransactionApplicationDao transactionApplicationDao = FactoryDao().getTransactionApplicationDao();
        TransactionConfirmationDao transactionConfirmationDao = FactoryDao().getTransactionConfirmationDao();

//        transactionApplicationDao.insert((TransactionApplication) apply);
        for (ConfirmationModel c : confirm
        ) {
            try {
                //生成交易确认成功记录（124）
                transactionConfirmationDao.insert((TransactionConfirmation) c);
            } catch (Exception e) {
                logger.error(e);
            }
        }


    }

    @Override
    void generateConfirm(ApplicationModel apply, ConfirmationModel confirm, ApplyException applyException) {

        AcctShareDao acctShareDao = FactoryDao().getAcctShareDao();
        FundParaConfigDao fundParaConfigDao = FactoryDao().getFundParaConfigDao();

        TransactionApplication transactionApplication = (TransactionApplication) apply;

        FundParaConfig fundParaConfig = new FundParaConfig();
        fundParaConfig.setFundcode(transactionApplication.getFundcode());
        fundParaConfig.setTacode(transactionApplication.getTacode());
        fundParaConfig.setDistributorcode(transactionApplication.getDistributorcode());

        FundParaConfig paraConfig = fundParaConfigDao.selectByUnionCode(fundParaConfig);
        BigDecimal nav = paraConfig.getNav();
        //        产品额度更新
        paraConfig.setFundmaxbala(paraConfig.getFundmaxbala().add((nav.multiply(transactionApplication.getApplicationvol()))));

        AcctShareKey acctShareKey = new AcctShareKey();

        TransactionConfirmation transactionConfirmation = (TransactionConfirmation) confirm;
        String transactiondate = transactionConfirmation.getTransactiondate();

        copyFields(transactionConfirmation, acctShareKey);
        AcctShare acctShare = FactoryDao().getAcctShareDao().selectByPrimaryKey(acctShareKey);

        BigDecimal shareTotalvolofdistributorinta = acctShare.getTotalvolofdistributorinta();
        BigDecimal totalamountofdistributorinta = acctShare.getTotalamountofdistributorinta();

        //      持仓份额更新
        acctShare.setTotalvolofdistributorinta(shareTotalvolofdistributorinta.subtract(transactionApplication.getApplicationvol()));
        //      持仓金额更新
        acctShare.setTotalfrozenamount(totalamountofdistributorinta.subtract(transactionApplication.getApplicationvol().multiply(nav)));

        copyFields(transactionApplication, transactionConfirmation);

        //      获得交易日日期
        transactionConfirmation.setTransactiontime(getNextTransactionDay(transactiondate));

        try {
            //更新或新增账户持仓表
            acctShareDao.updateByPrimaryKey(acctShare);
            //更新产品已售额度等
            fundParaConfigDao.updateByPrimaryKey(paraConfig);
        } catch (Exception e) {
            logger.error(e);
        }


    }
}
