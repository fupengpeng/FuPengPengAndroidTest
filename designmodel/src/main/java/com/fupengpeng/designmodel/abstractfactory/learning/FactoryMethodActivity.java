package com.fupengpeng.designmodel.abstractfactory.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fupengpeng.designmodel.R;
import com.fupengpeng.designmodel.abstractfactory.learning.use.FemaleHumanFactory;
import com.fupengpeng.designmodel.abstractfactory.learning.use.MaleHumanFactory;
import com.fupengpeng.designmodel.factorymethod.learning.impl.WhiteHuman;
import com.fupengpeng.designmodel.factorymethod.learning.interf.Human;
import com.fupengpeng.designmodel.factorymethod.learning.use.HumanFactory;

/**
 *
 * 4.在实际中应用工厂生产对象加以使用
 * 抽象工厂方法模式
 *     1.定义一个类型接口
 *     2.抽象类（产品等级）实现这一接口
 *     3.继承抽象类的实体类（区分性别，产品族）
 *     4.写一个枚举类，提供人类类型
 *     5.工厂接口，区分性别
 *     6.实现工厂接口的抽象实现类
 *     7.工厂类创建，两个，继承工厂的抽象类，一个男性生产工厂，一个女性生产工厂
 *     8.在实际中应用工厂生产对象加以使用
 */
public class FactoryMethodActivity extends AppCompatActivity {

    /*
    两个八卦炉，一个造女的，一个造男的，开足马力，一直造到这个世界到现在这个模式为止。
抽象工厂模式讲完了，那我们再思考一些问题：工厂模式有哪些优缺点？先说优点，我这人一般先看
人优点，非常重要的有点就是，工厂模式符合 OCP 原则，也就是开闭原则，怎么说呢，比如就性别的问题，
这个世界上还存在双性人，是男也是女的人，那这个就是要在我们的产品族中增加一类产品，同时再增加
一个工厂就可以解决这个问题，不需要我再来实现了吧，很简单的大家自己画下类图，然后实现下。
那还有没有其他好处呢？抽象工厂模式，还有一个非常大的有点，高内聚，低耦合，在一个较大的项
目组，产品是由一批人定义开发的，但是提供其他成员访问的时候，只有工厂方法和产品的接口，也就是
说只需要提供 Product Interface 和 Concrete Factory 就可以产生自己需要的对象和方法，Java 的高内聚
低耦合的特性表现的一览无遗，哈哈。
     */

    private Human whiteHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_method);

        first();

    }
    private void first() {
        //第一条生产线  男性生产线
        com.fupengpeng.designmodel.abstractfactory.learning.interf.HumanFactory maleHumanFactory =
                new MaleHumanFactory();

        //第二条生产线  男性生产线
        com.fupengpeng.designmodel.abstractfactory.learning.interf.HumanFactory femaleHumanFactory =
                new FemaleHumanFactory();

        //生产线建立完毕，开始生产
        Human maleYellowHuman = (Human) maleHumanFactory.createYellowHuman();
        Human femaleYellowHuman = (Human) femaleHumanFactory.createYellowHuman();


    }
}
