package com.psbc.business.processor;

import com.psbc.exceptions.ApplyException;
import com.psbc.exceptions.ConfirmExpectationException;
import com.psbc.exceptions.ProcessingException;
import com.psbc.pojo.*;

import java.lang.Exception;
import java.util.LinkedList;
import java.util.List;



abstract class BiDirectionProcessor implements Processor {
    // 包装主方法，主要做状态更新
    public List<DatabaseModel> process(final TransactionApplication apply) throws ConfirmExpectationException {
        List<DatabaseModel> confirmations = null;
        ApplyException applyException=null;
        updateRecordStatus(apply, "1"); 
        try{
            confirmations = doProcess(apply);
        }catch(ConfirmExpectationException e){
            // 确认期望内容异常
            // TODO: 打印log
            registerException(e);
            updateRecordStatus(apply, "3");   
            throw e;
        }
        ////
        // 更新库表
        updateRepository(apply, confirmations, applyException);
        updateRecordStatus(apply, "2");
        return confirmations;
    }

    // 主方法：实现不同业务的具体业务逻辑的主流程
    // 确认记录结果作为List返回：方便一条申请记可能对应多条确认记录
    private List<DatabaseModel> doProcess(final TransactionApplication apply) throws ConfirmExpectationException {
        final List<DatabaseModel> confirmList = new LinkedList<>();

        
        TransactionExpectation confirmExpect = null;
        TransactionConfirmation confirmation = null;
        ApplyException applyException=null;
        // 判断申请记录是否有异常；如果有，抓取异常
        try{
            validateApply(apply);
        }catch(ApplyException e){
            applyException = e;
        }
        
        // 写入异常表（此时只可能为业务异常，必会有确认记录生成）
        if(applyException != null){
            registerException(applyException);
        }
        // 申请记录无异常，进行正常的业务流程
        else {
            confirmExpect = getExpectation(apply);
            // 如果期望条目已更新
            final boolean isExpectationUpdated = "1".equals(confirmExpect.getStatus());
            if(isExpectationUpdated){
                validateConfirmExpectation(confirmExpect); // 会异常
            }
        }
        // 由确认期望条目转为确认条目
        transformObject(confirmExpect, confirmation);
        // 根据申请记录，生成对应的确认记录
        generateConfirm(apply, confirmation, applyException);
        confirmList.add(confirmation);
        for(String businessCode : getExtraConfirmationBusiness(apply)){
            confirmList.addAll(callOtherProcessor(businessCode));
        }
        // 可能包含跨天的确认记录，写入文件时，要判断确认时间
        return confirmList;
    }

    // 获取申请记录对应的确认期望记录
    private TransactionExpectation getExpectation(TransactionApplication apply){
        // 直接用辅助部分的代码实现
        return null;
    }

    // 获取额外的确认业务（主要是单向业务）
    // 返回业务代码
    private List<String> getExtraConfirmationBusiness(TransactionApplication apply){
        // 可暂时用配置文件实现
        return null;
    }

    // 用于期望确认对象转型为确认对象
    private void transformObject(TransactionExpectation originObj, TransactionConfirmation targetObj){
        // 直接用辅助部分的代码实现
    }

    // 登记异常
    private void registerException(ProcessingException exception){
        // 直接用辅助部分的代码实现
    }

    // 调取单向业务处理器
    private List<DatabaseModel> callOtherProcessor(String businessCode){
        // 利用反射实现
        return null;
    }

    // 更新记录状态（至‘processingError’）
    private void updateRecordStatus(TransactionApplication apply, String status){
        
    }


    // 判断申请记录的业务合法性，不同业务需具体实现;
    // 若合法，不做任何返回；若不合法，抛对应异常
    abstract void validateApply(TransactionApplication apply) throws ApplyException;

    // 判断所获期望是否合理，不同业务需具体实现
    abstract void validateConfirmExpectation(TransactionExpectation expect) throws ConfirmExpectationException;

    // 根据业务逻辑更新对应库表
    abstract void updateRepository(TransactionApplication apply, List<DatabaseModel> confirm, ApplyException applyException);
    
    // 生成确认记录，不同业务需具体实现
    abstract void generateConfirm(TransactionApplication apply, TransactionConfirmation confirm, ApplyException applyException);




}