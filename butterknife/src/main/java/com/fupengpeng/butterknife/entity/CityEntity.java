package com.fupengpeng.butterknife.entity;

import java.util.List;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/3/20 0020 10:58
 */

public class CityEntity {
    public String Name;
    public List<AreaEntity> Area;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<AreaEntity> getArea() {
        return Area;
    }

    public void setArea(List<AreaEntity> area) {
        Area = area;
    }
}
