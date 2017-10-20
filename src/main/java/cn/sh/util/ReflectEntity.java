package cn.sh.util;

import java.math.BigDecimal;
import java.util.Date;


public class ReflectEntity {
	
	public static String name="静态变量";
	
	static {
		System.out.println("静态代码块");
	}
	
	@MyAnnotation(name="field",value="2017-01-01")
	public Date birthday;
	
	private Integer id;
	
	private String title;
	
	private BigDecimal money;
	
	private float percent;
	

	public ReflectEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReflectEntity(Integer id, String title, BigDecimal money, float percent) {
		super();
		this.id = id;
		this.title = title;
		this.money = money;
		this.percent = percent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	private void print() {
		System.out.println("自己的打印方法");
	}
	
	

}
