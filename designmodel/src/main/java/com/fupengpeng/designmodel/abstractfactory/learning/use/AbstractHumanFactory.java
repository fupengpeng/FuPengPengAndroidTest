package com.fupengpeng.designmodel.abstractfactory.learning.use;

import com.fupengpeng.designmodel.abstractfactory.learning.interf.HumanFactory;
import com.fupengpeng.designmodel.factorymethod.learning.interf.Human;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 * 创建一个抽象工厂类，根据enum创建一个人类出来
 */

public abstract class AbstractHumanFactory implements HumanFactory {

    protected Human createHuman(HumanEnum humanEnum){
        Human human = null;

        //如果传递进来不是一个Enum中具体的一个Element的话，则不处理
        if (!humanEnum.getValue().equals("")) {
            try {
//直接产生一个实例
                human = (Human)
                        Class.forName(humanEnum.getValue()).newInstance();
            } catch (Exception e) {
//因为使用了enum，这个种异常情况不会产生了，除非你的enum有问题；
                e.printStackTrace();
            }
        }
        return human;
    }

}
