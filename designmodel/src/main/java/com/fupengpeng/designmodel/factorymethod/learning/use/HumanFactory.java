package com.fupengpeng.designmodel.factorymethod.learning.use;

import com.fupengpeng.designmodel.factorymethod.learning.ClassUtils;
import com.fupengpeng.designmodel.factorymethod.learning.interf.Human;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by fupengpeng on 2017/5/31 0031.
 * 定义类的工厂方法
 *
 *     3.写个一工厂，提供一个方法用于根据传入的实现类来生产出一个实现类对象
 *
 */

public class HumanFactory {


    //根据传入的类，创建其对象
    public static Human createHuman(Class c){
        Human human = null;

        try {
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return human;
    }


    //根据接口的实现类来随机生产类对象
    public static Human createHuman(){

        Human human = null;

        //查找接口的实现类，生成一个接口实现类的列表
        List<Class> concreateHumanList = ClassUtils.getAllClassByInterface(Human.class);

        //进行随机的生产
        Random random = new Random();
        int rand = random.nextInt(concreateHumanList.size());

        human = createHuman(concreateHumanList.get(rand));

        return human;
    }


    /*
    这个在类初始化很消耗资源的情况比较实用，比如你要连接硬
件，或者是为了初始化一个类需要准备比较多条件（参数），通过以下这种方式可以很好的减少项目的复杂程度
     */
    private static HashMap<String,Human> humans = new HashMap<String,Human>();
    //根据传入的类，创建其对象
    public static Human createHumanSaveResources(Class c){
        Human human = null;

        try {
            // 如果 MAP 中有，则直接从取出，不用初始化了
            if(humans.containsKey(c.getSimpleName())){
                human = humans.get(c.getSimpleName());
            }else{
                human = (Human)Class.forName(c.getName()).newInstance();
// 放到 MAP 中
                humans.put(c.getSimpleName(), human);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return human;
    }


}
