package com.psbc.utils.random;

import java.util.Map;

/**
 * 随机产生一段文字
 */
public class TextRandom implements Random {

    private final int MAX_LENGTH = 100;

    private final String pool = "香格里拉古城.实际是茶马古道上的“建塘古城”,不太为世人所知.香格里拉新城毗邻而建," +
            "规模不大,道路、建筑规划整齐,完全是崭新和繁荣的现代景象.眼前的古城,基本格局是以大龟山公园为中心,藏民居围山而建." +
            "山顶上号称世界第一大的转经筒在阳光下熠熠生辉外,古城一部分建筑,山顶的寺庙都在施工.据导游说,眼前的景象并非" +
            "茶马古道上赫赫有名的建塘古城原貌,古城始建于唐代676-679年,已有1300多年的历史,鼎盛期是在“茶马互市”的清代," +
            "古城乃沟通滇、川、藏三地之通衢,只是解放后,随滇藏公路与川藏公路的相继修通,建塘的马帮渐渐衰落,上世纪80年代后," +
            "新城崛起,古城渐渐被人遗忘.但古城是迄今中国保存得最好、最大的藏民居群,而且与大理、丽江一样,是茶马古道的枢纽." +
            "当年贺龙率领红二、六军团为策应中央红军的战略转移,由湘赣边转战滇黔乌蒙,再挥师丽江石鼓渡金沙,翻越玉龙雪山后" +
            "到达了中甸（即现在的香格里拉）.当地僧人有感于红军的民族宗教政策,非常高兴,两天内筹集青稞20万斤,红糖若干," +
            "表示敬意.红军在此休整10天后,兵分两路继续北上,前往四川甘孜去与期待着的红四方面军进行会师.\n";

    @Override
    public String next(Map<String,String> attrs) {
        int length = MAX_LENGTH;
        if(attrs.containsKey("length")){
            int val = Integer.parseInt(attrs.get("length"));
            length = val > MAX_LENGTH ? MAX_LENGTH : length;
        }
        int begin = IntegerRandom.next(length);
        return pool.substring(begin, begin + length);
    }
}