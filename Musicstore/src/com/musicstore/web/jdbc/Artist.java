package com.musicstore.web.jdbc;

public class Artist {
	
	private long id;
	private String name;
	
	public Artist(String name) {
		super();
		this.name = name;
	}

	public Artist(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + "]";
	}

}
