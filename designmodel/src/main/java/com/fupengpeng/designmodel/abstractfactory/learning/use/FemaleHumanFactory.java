package com.fupengpeng.designmodel.abstractfactory.learning.use;

import com.fupengpeng.designmodel.abstractfactory.learning.interf.Human;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 */

public class FemaleHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createYellowHuman() {
        return (Human) super.createHuman(HumanEnum.YellowFemaleHuman);
    }

    @Override
    public Human createWhiteHuman() {
        return (Human) super.createHuman(HumanEnum.WhiteFemaleHuman);
    }

    @Override
    public Human createBlackHuman() {
        return (Human) super.createHuman(HumanEnum.BlackFemaleHuman);
    }
}
