package com.psbc.utils.random;

import java.util.Map;

/**
 * 随机产生一个名字（包括人名，产品名等）
 */
public class NameRandom implements Random {

    private static String[] names = {"肇修","绝尘","光昭","江静","初丹","顾引","秋杉","疏林","凯风",
            "安南","温恭","雨来","水玲","如铁","平辉","健峰","怜蕾","溪舟","海蓝","沛萍","月素"};
    @Override
    public String next(Map<String,String> attrs) {
        return names[IntegerRandom.next(names.length)];
    }
}
