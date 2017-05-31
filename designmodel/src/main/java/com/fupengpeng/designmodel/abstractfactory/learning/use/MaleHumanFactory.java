package com.fupengpeng.designmodel.abstractfactory.learning.use;

import com.fupengpeng.designmodel.abstractfactory.learning.interf.Human;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 * 男性创建工厂，只创建男性
 */

public class MaleHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createYellowHuman() {
        return (Human) super.createHuman(HumanEnum.YellowMaleHuman);
    }

    @Override
    public Human createWhiteHuman() {
        return (Human) super.createHuman(HumanEnum.WhiteMaleHuman);
    }

    @Override
    public Human createBlackHuman() {
        return (Human) super.createHuman(HumanEnum.BlackMaleHuman);
    }
}
