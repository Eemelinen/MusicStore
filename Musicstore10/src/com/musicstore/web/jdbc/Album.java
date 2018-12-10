package com.musicstore.web.jdbc;

public class Album {
	
	private long albumId;
	private String albumTitle;
	private long artistId;
	
	public Album(String albumTitle, long artistId) {
		super();
		this.albumTitle = albumTitle;
		this.artistId = artistId;
	}

	public Album(long albumId, String albumTitle, long artistId) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.artistId = artistId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumTitle=" + albumTitle + ", artistId=" + artistId + "]";
	}
}
