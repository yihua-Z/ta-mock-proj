package com.psbc.writer;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public interface Writer {
    // 返回文件名
    String write();
}