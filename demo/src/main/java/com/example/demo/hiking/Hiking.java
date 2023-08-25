package com.example.demo.hiking;

import java.time.LocalDateTime;


public class Hiking {

	private int mtid;
	private String mtName;
	private String location;
	private int altitude;
	private int viewCount;
	private LocalDateTime modTime;
	private int isDeleted;
	

	public Hiking() {
	}

	//insert용
	public Hiking(String mtName, String location, int altitude) {
		this.mtName = mtName;
		this.location = location;
		this.altitude = altitude;
	}
	

	//update용	
	public Hiking(int mtid, String mtName, String location, int altitude) {
		this.mtid = mtid;
		this.mtName = mtName;
		this.location = location;
		this.altitude = altitude;
	}
	

	public Hiking(int mtid, String mtName, String location, int altitude, int viewCount, LocalDateTime modTime,
			int isDeleted) {
		this.mtid = mtid;
		this.mtName = mtName;
		this.location = location;
		this.altitude = altitude;
		this.viewCount = viewCount;
		this.modTime = modTime;
		this.isDeleted = isDeleted;
	}


	@Override
	public String toString() {
		return "hiking [mtid=" + mtid + ", mtName=" + mtName + ", location=" + location + ", altitude="
				+ altitude + ", viewCount=" + viewCount + ", modTime=" + modTime.toString().substring(2, 16).replace("T"," ") + ", isDeleted=" + isDeleted
				+ "]";
	}

	public int getmtid() {
		return mtid;
	}

	public void setmtid(int mtid) {
		this.mtid = mtid;
	}

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public LocalDateTime getmodTime() {
		return modTime;
	}

	public void setmodTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

	
}
