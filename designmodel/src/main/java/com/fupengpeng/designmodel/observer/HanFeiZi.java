package com.fupengpeng.designmodel.observer;

/**
 * Created by fupengpeng on 2017/11/6 0006.
 */

public class HanFeiZi implements IHanFeiZi {

    //韩非子是否在吃饭，作为监控的判断标准
    private boolean isHaveBreakfast = false;
    //韩非子是否在娱乐
    private boolean isHaveFun = false;
    //韩非子要吃饭了
    public void haveBreakfast(){
        System.out.println("韩非子:开始吃饭了...");
        this.isHaveBreakfast =true;
    }
    //韩非子开始娱乐了,古代人没啥娱乐，你能想到的就那么多
    public void haveFun(){
        System.out.println("韩非子:开始娱乐了...");
        this.isHaveFun = true;
    }

    public boolean isHaveBreakfast() {
        return isHaveBreakfast;
    }

    public void setHaveBreakfast(boolean haveBreakfast) {
        isHaveBreakfast = haveBreakfast;
    }

    public boolean isHaveFun() {
        return isHaveFun;
    }

    public void setHaveFun(boolean haveFun) {
        isHaveFun = haveFun;
    }
}
