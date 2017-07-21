package com.github.fly_springmvc.domain;

public class DemoDomain {

	private Long id;
	private String name;
	// jackson对对象和json做转换时一定需要空构造
	public DemoDomain() {
		super();
	}
	public DemoDomain(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
