package com.xuwuji.wechat.admin.util;

public enum Price {

	CHEAP("0-500"), MEDIUM("500-1000"), DEAR(">1000");

	private String message;

	public String getMessage() {
		return this.message;
	}

	private Price(String message) {
		this.message = message;
	}

}
