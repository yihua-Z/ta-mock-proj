package com.psbc.utils.random;

import java.util.Map;

/**
 * 随机产生地址
 */
public class AddressRandom implements Random {

    private String[] roadNames = {"重庆大厦","黑龙江路","十梅庵街","遵义路","湘潭街","瑞金广场","仙山街" +
            "仙山东路","仙山西大厦","白沙河路","赵红广场","机场路","民航街","长城南路","流亭立交桥","虹桥广场","长城大厦","礼阳路","风岗街"};
    private String[] provinceNames = {"黑龙江","辽宁省","吉林省","河北省","山西省","陕西省","山东省","江苏省","浙江省","河南省","湖北省","安徽省" +
            "湖南省","江西省","云南省","海南省","四川省","台湾省","贵州省","广东省","甘肃省","青海省","福建省"};

    /**
     * 产生一个伪随机地址，无需参数
     */
    @Override
    public String next(Map<String,String> attrs) {
        return provinceNames[IntegerRandom.next(0, provinceNames.length - 1)] +
                roadNames[IntegerRandom.next(0, roadNames.length - 1)] +
                IntegerRandom.next(10, 100) + "号";
    }


}
