package com.fupengpeng.listmenu.model;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/3/20 0020 14:43
 */

public class BaseModel {

    private String id;
    private String name;

    public BaseModel(){}
    public BaseModel(String id , String name){
        this.id = id;
        this.name = name;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "BaseModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
