package org.zerock.VO;

public class ProductVO {
	String name = "";
	String money = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	public ProductVO(String name, String money) {
		super();
		this.name = name;
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", money=" + money + "]";
	}
	
}
