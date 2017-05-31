package com.fupengpeng.designmodel.proxy.learning.impl;

import com.fupengpeng.designmodel.proxy.learning.interf.KingWomen;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 代理对象
 */

public class WangPo implements KingWomen {

    private KingWomen kingWomen;

    //构造函数
    public WangPo(){
        this.kingWomen = new PanJinLian();//代理PanJinLian
    }

    public WangPo(KingWomen kingWomen){
        this.kingWomen = kingWomen; //也可以代理KingWomen的其他对象
    }

    @Override
    public void makeEyesWithMan() {
        //使用代理对象调用被代理对象（接口实例--被代理对象）的方法
        this.kingWomen    //被代理对象
                .makeEyesWithMan();    //调用被代理对象的方法
    }

    @Override
    public void happyWithMan() {
        this.kingWomen.happyWithMan();
    }
}
