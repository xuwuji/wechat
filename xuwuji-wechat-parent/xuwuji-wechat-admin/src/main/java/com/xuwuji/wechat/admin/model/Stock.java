package com.xuwuji.wechat.admin.model;

public class Stock {

	private String date;
	private Double max;
	private Double min;
	private Double avg;
	private Double open;
	private Double close;

	private Stock(Builder builder) {
		date = builder.date;
		max = builder.max;
		min = builder.min;
		avg = builder.avg;
		open = builder.open;
		close = builder.close;
	}

	public static class Builder {
		private String date;
		private Double max;
		private Double min;
		private Double avg;
		private Double open;
		private Double close;

		public Builder() {

		}

		public Builder date(String date) {
			this.date = date;
			return this;
		}

		public Builder max(Double max) {
			this.max = max;
			return this;
		}

		public Builder min(Double min) {
			this.min = min;
			return this;
		}

		public Builder avg(Double avg) {
			this.avg = avg;
			return this;
		}

		public Builder open(Double open) {
			this.open = open;
			return this;
		}

		public Builder close(Double close) {
			this.close = close;
			return this;
		}

		public Stock build() {
			return new Stock(this);
		}
	}

	public String getDate() {
		return date;
	}

	public Double getMax() {
		return max;
	}

	public Double getMin() {
		return min;
	}

	public Double getAvg() {
		return avg;
	}

	public Double getOpen() {
		return open;
	}

	public Double getClose() {
		return close;
	}

	@Override
	public String toString() {
		return "Stock [date=" + date + ", max=" + max + ", min=" + min + ", avg=" + avg + ", open=" + open + ", close="
				+ close + "]";
	}

}
