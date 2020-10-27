package com.community.my.api.model;

public class SpellDTO {
	private String id;
    private String name;
    private String key;
    private RiotImageDTO image;
	String getId() {
		return id;
	}
	void setId(String id) {
		this.id = id;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getKey() {
		return key;
	}
	void setKey(String key) {
		this.key = key;
	}
	RiotImageDTO getImage() {
		return image;
	}
	void setImage(RiotImageDTO image) {
		this.image = image;
	}
    
}
