package com.fupengpeng.designmodel.strategy.instance.context;

import com.fupengpeng.designmodel.strategy.instance.impl.RegularMembers001;
import com.fupengpeng.designmodel.strategy.instance.impl.RegularMembers002;
import com.fupengpeng.designmodel.strategy.instance.impl.RegularMembers003;
import com.fupengpeng.designmodel.strategy.instance.impl.RegularMembers004;
import com.fupengpeng.designmodel.strategy.instance.impl.RegularMembers005;
import com.fupengpeng.designmodel.strategy.instance.impl.RegularMembers006;
import com.fupengpeng.designmodel.strategy.instance.interf.IReturnCalculate;

/**
 * Created by fupengpeng on 2017/5/27 0027.
 * 策略容器
 */

public class Customer {
    private Double totalAmount = 0D;//客户在本商店消费的总额
    private Double amount = 0D;//客户单次消费金额
    private IReturnCalculate iReturnCalculate = new RegularMembers001();//每个客户都有一个计算价格的策略，初始都是普通计算，即原价

    //客户购买商品，就会增加它的总额
    public void buy(Double amount){
        this.amount = amount;
        totalAmount += amount;
        if (totalAmount > 50000) {//钻石会员计算方式
            iReturnCalculate = new RegularMembers006();
        }else if (totalAmount > 20000) {//铂金会员
            iReturnCalculate = new RegularMembers005();
        }else if (totalAmount > 10000) {//金牌会员
            iReturnCalculate = new RegularMembers004();
        }else if (totalAmount > 3000) {//银牌会员
            iReturnCalculate = new RegularMembers003();
        }else if (totalAmount > 0) {//铜牌会员
            iReturnCalculate = new RegularMembers002();
        }else {//普通会员
            iReturnCalculate = new RegularMembers001();
        }
    }
    //计算客户最终要付的钱
    public Double calLastAmount(){
        return iReturnCalculate.returnCalculate(amount);
    }
}
