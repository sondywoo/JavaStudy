package com.java.collection.experiment;

public class Boy implements Comparable<Boy>{
	private Long oid;
	private String name;
	public Boy(Long oid, String name){
		this.oid = oid;
		this.name = name;
	}
	@Override
	public String toString() {
		return "id: " + oid + "\tname: " + name;
	}
	public int compareTo(Boy anotherBoy) {
//		return oid.compareTo(anotherBoy.oid);	// 按ID排序
		return name.compareTo(anotherBoy.name);	// 按Name排序
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
}
