package com.psbc.utils.random;

import java.util.Map;

public interface Random {
    // 需要读取每个字段的全部属性K-V键值对(由XMLNode生成)的内容来产生该字段的随机内容
    String next(Map<String, String> attrs);
}
