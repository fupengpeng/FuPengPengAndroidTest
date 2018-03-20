package com.fupengpeng.listmenu.entity;

import java.util.List;

/**
 * @author fupengpeng
 * @description 市
 * @data 2018/3/19 0019 16:36
 */

public class City {
    private String id;
    private String name;
    private String gisBd09Lat;
    private String gisBd09Lng;
    private String gisGcj02Lat;
    private String gisGcj02Lng;
    private String pinYin;
    private List<Cunty> cityList;

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

    public String getGisBd09Lat() {
        return gisBd09Lat;
    }

    public void setGisBd09Lat(String gisBd09Lat) {
        this.gisBd09Lat = gisBd09Lat;
    }

    public String getGisBd09Lng() {
        return gisBd09Lng;
    }

    public void setGisBd09Lng(String gisBd09Lng) {
        this.gisBd09Lng = gisBd09Lng;
    }

    public String getGisGcj02Lat() {
        return gisGcj02Lat;
    }

    public void setGisGcj02Lat(String gisGcj02Lat) {
        this.gisGcj02Lat = gisGcj02Lat;
    }

    public String getGisGcj02Lng() {
        return gisGcj02Lng;
    }

    public void setGisGcj02Lng(String gisGcj02Lng) {
        this.gisGcj02Lng = gisGcj02Lng;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public List<Cunty> getCityList() {
        return cityList;
    }

    public void setCityList(List<Cunty> cityList) {
        this.cityList = cityList;
    }
}
