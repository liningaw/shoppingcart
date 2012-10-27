package com.tarena.shoppingcart.bean;

public class Computer {
	private long id;
	private String model;
	private String pic;
	private String prodInfo;
	private double price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProdInfo() {
		return prodInfo;
	}

	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", model=" + model + ", pic=" + pic
				+ ", prodInfo=" + prodInfo + ", price=" + price + "]";
	}
}