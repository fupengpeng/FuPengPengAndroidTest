package com.fupengpeng.listmenu.model;

import java.util.List;

public class ProvinceModel extends BaseModel{

	private String gisBd09Lat;
	private String gisBd09Lng;
	private String gisGcj02Lat;
	private String gisGcj02Lng;
	private String pinYin;
	private List<CityModel> cityList;


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

	public ProvinceModel() {
		super();
	}

	public ProvinceModel(String name, List<CityModel> cityList) {
		super();
		this.cityList = cityList;
	}


	public List<CityModel> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityModel> cityList) {
		this.cityList = cityList;
	}

	@Override
	public String toString() {
		return "ProvinceModel{" +
				"gisBd09Lat='" + gisBd09Lat + '\'' +
				", gisBd09Lng='" + gisBd09Lng + '\'' +
				", gisGcj02Lat='" + gisGcj02Lat + '\'' +
				", gisGcj02Lng='" + gisGcj02Lng + '\'' +
				", pinYin='" + pinYin + '\'' +
				", cityList=" + cityList +
				'}';
	}
}
