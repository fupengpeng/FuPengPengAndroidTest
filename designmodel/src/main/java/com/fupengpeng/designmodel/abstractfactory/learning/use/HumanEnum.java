package com.fupengpeng.designmodel.abstractfactory.learning.use;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 * 列举所有的人类型
 */

public enum HumanEnum {
    //世界上所有的人类型都定义出来
    YellowMaleHuman("com.fupengpeng.designmodel.abstractfactory.learning.impl.YellowMaleHuman"),
    YellowFemaleHuman("com.fupengpeng.designmodel.abstractfactory.learning.impl.YellowFemaleHuman"),
    WhiteFemaleHuman("com.fupengpeng.designmodel.abstractfactory.learning.impl.WhiteFemaleHuman"),
    WhiteMaleHuman("com.fupengpeng.designmodel.abstractfactory.learning.impl.WhiteMaleHuman"),
    BlackFemaleHuman("com.fupengpeng.designmodel.abstractfactory.learning.impl.BlackFemaleHuman"),
    BlackMaleHuman("com.fupengpeng.designmodel.abstractfactory.learning.impl.BlackMaleHuman");

    private String value = "";
    //定有构造函数，目的是Data(value)类型的匹配
    HumanEnum(String value) {
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
