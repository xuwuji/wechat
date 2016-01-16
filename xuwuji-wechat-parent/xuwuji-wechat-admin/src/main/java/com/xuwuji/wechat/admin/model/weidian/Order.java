package com.xuwuji.wechat.admin.model.weidian;

public class Order {

	private String img_url;
	private String order_id;
	private String express_id;
	private String city;
	private String province;
	private String address;
	private String time;
	private String status;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getExpress_id() {
		return express_id;
	}

	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Order [img_url=" + img_url + ", order_id=" + order_id + ", express_id=" + express_id + ", city=" + city
				+ ", province=" + province + ", address=" + address + ", time=" + time + ", status=" + status + "]";
	}

}
