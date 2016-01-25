package com.xuwuji.wechat.admin.model.weidian;

/**
 * Model for order of weidian
 * 
 * Use builder pattern to create a new object
 * 
 * No setter method in this class, so it is immutable
 * 
 * Everytime building an instance, the builder returns a new instance
 * 
 * @author wuxu
 *
 *         Jan 19, 2016
 */
public class Order {

	private String img_url;
	private String order_id;
	private String express_id;
	private String city;
	private String province;
	private String address;
	private String time;
	private String status;

	private Order(Builder builder) {
		this.img_url = builder.img_url;
		this.order_id = builder.order_id;
		this.express_id = builder.express_id;
		this.city = builder.city;
		this.province = builder.province;
		this.address = builder.address;
		this.time = builder.time;
		this.status = builder.status;
	}

	/**
	 * can do the check in the adding attribute process and do corresponding
	 * action, like throw exception and catch it in the logical service
	 * 
	 */
	public static class Builder {

		private String img_url;
		private String order_id;
		private String express_id;
		private String city;
		private String province;
		private String address;
		private String time;
		private String status;

		public Builder() {

		}

		public Builder img(String img_url) {
			this.img_url = img_url;
			return this;
		}

		public Builder orderId(String order_id) {
			this.order_id = order_id;
			return this;
		}

		public Builder expressId(String express_id) {
			this.express_id = express_id;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder province(String province) {
			this.province = province;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder time(String time) {
			this.time = time;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

	public String getImg_url() {
		return img_url;
	}

	public String getOrder_id() {
		return order_id;
	}

	public String getExpress_id() {
		return express_id;
	}

	public String getCity() {
		return city;
	}

	public String getProvince() {
		return province;
	}

	public String getAddress() {
		return address;
	}

	public String getTime() {
		return time;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Order [img_url=" + img_url + ", order_id=" + order_id + ", express_id=" + express_id + ", city=" + city
				+ ", province=" + province + ", address=" + address + ", time=" + time + ", status=" + status + "]";
	}

}
