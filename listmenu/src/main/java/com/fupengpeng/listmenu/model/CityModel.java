package com.fupengpeng.listmenu.model;

import java.util.List;

public class CityModel extends BaseModel{

	private String gisBd09Lat;
	private String gisBd09Lng;
	private String gisGcj02Lat;
	private String gisGcj02Lng;
	private String pinYin;
	private List<DistrictModel> districtList;

	private List<DistrictModel> cityList;

	public List<DistrictModel> getCityList() {
		return cityList;
	}

	public void setCityList(List<DistrictModel> cityList) {
		this.cityList = cityList;
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

	public CityModel() {
		super();
	}

	public CityModel(String name, List<DistrictModel> districtList,List<DistrictModel> cityList) {
		super();
		this.districtList = districtList;
		this.cityList = cityList;
	}


	public List<DistrictModel> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<DistrictModel> districtList) {
		this.districtList = districtList;
	}

	@Override
	public String toString() {
		return "CityModel{" +
				"gisBd09Lat='" + gisBd09Lat + '\'' +
				", gisBd09Lng='" + gisBd09Lng + '\'' +
				", gisGcj02Lat='" + gisGcj02Lat + '\'' +
				", gisGcj02Lng='" + gisGcj02Lng + '\'' +
				", pinYin='" + pinYin + '\'' +
				", districtList=" + districtList +
				", cityList=" + cityList +
				'}';
	}
}
