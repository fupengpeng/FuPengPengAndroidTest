package com.fupengpeng.listmenu.model;

public class DistrictModel extends BaseModel{


	private String createAccount;
	private String createTime;
	private String gisBd09Lat;
	private String gisBd09Lng;
	private String gisGcj02Lat;
	private String gisGcj02Lng;
	private String modifyAccount;
	private String modifyTime;
	private String orderId;
	private String pinYin;
	private String status;
	private String stubGroupCnt;
	private String zipcode;

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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


	public String getModifyAccount() {
		return modifyAccount;
	}

	public void setModifyAccount(String modifyAccount) {
		this.modifyAccount = modifyAccount;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStubGroupCnt() {
		return stubGroupCnt;
	}

	public void setStubGroupCnt(String stubGroupCnt) {
		this.stubGroupCnt = stubGroupCnt;
	}

	public DistrictModel() {
		super();
	}

	public DistrictModel(String name, String zipcode) {
		super();
		this.zipcode = zipcode;
	}


	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "DistrictModel{" +
				"createAccount='" + createAccount + '\'' +
				", createTime='" + createTime + '\'' +
				", gisBd09Lat='" + gisBd09Lat + '\'' +
				", gisBd09Lng='" + gisBd09Lng + '\'' +
				", gisGcj02Lat='" + gisGcj02Lat + '\'' +
				", gisGcj02Lng='" + gisGcj02Lng + '\'' +
				", modifyAccount='" + modifyAccount + '\'' +
				", modifyTime='" + modifyTime + '\'' +
				", orderId='" + orderId + '\'' +
				", pinYin='" + pinYin + '\'' +
				", status='" + status + '\'' +
				", stubGroupCnt='" + stubGroupCnt + '\'' +
				", zipcode='" + zipcode + '\'' +
				'}';
	}
}
