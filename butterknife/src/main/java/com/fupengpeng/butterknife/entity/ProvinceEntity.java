package com.fupengpeng.butterknife.entity;

import java.util.List;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/3/20 0020 10:58
 */

public class ProvinceEntity {

    public String Name;
    public List<CityEntity> City;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<CityEntity> getCity() {
        return City;
    }

    public void setCity(List<CityEntity> city) {
        City = city;
    }
}
