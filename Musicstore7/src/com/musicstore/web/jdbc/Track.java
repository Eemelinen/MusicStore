package com.musicstore.web.jdbc;

public class Track {
	
	private long trackId;
	private String trackName;
	private long albumId;
	
	public Track(long trackId, String trackName, long albumId) {
		super();
		this.trackId = trackId;
		this.trackName = trackName;
		this.albumId = albumId;
	}

	public Track(String trackName, long albumId) {
		super();
		this.trackName = trackName;
		this.albumId = albumId;
	}

	public long getTrackId() {
		return trackId;
	}

	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", trackName=" + trackName + ", albumId=" + albumId + "]";
	}
}
