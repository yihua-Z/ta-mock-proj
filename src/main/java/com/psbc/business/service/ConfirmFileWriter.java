package com.psbc.business.service;

import com.psbc.pojo.AccountApplication;
import com.psbc.pojo.AccountConfirmation;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfirmFileWriter {

    private String targetPath;
    private String FileName;

    public void Writer(AccountConfirmation accountConfirmation, AccountApplication accountApplication) {


    }
}
