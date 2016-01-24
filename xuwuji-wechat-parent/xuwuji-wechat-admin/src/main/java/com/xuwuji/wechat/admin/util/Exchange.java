package com.xuwuji.wechat.admin.util;

public enum Exchange {

	RMB("RMB"), USD("USD"), JPY("JPY"), EUR("EUR"), GBP("GBP");

	private String key;

	private Exchange(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

}
