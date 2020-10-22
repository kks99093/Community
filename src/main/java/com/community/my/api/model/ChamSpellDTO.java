package com.community.my.api.model;


public class ChamSpellDTO {
    private String id;
    private int key;
    private String name;
    private RiotImageDTO image;
    	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RiotImageDTO getImage() {
		return image;
	}
	public void setImage(RiotImageDTO image) {
		this.image = image;
	}
    

}
