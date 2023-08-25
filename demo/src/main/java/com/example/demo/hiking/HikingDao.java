package com.example.demo.hiking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HikingDao {

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "hmuser";
	private String password = "hmpass";
	
	public Hiking getHiking(int mtid) {
		String sql = "select * from hiking where mtid = ?";
		Hiking hiking = null;
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				mtid = rs.getInt(1);
				String mtName = rs.getString(2);
				String location = rs.getString(3);
				int altitude = rs.getInt(4);
				int viewCount = rs.getInt(5);
				String modTime = rs.getString(6);
				int isDeleted = rs.getInt(7);
				hiking = new Hiking(mtid, mtName, location, altitude, viewCount, LocalDateTime.parse(modTime.substring(0,19).replace(" ", "T")), isDeleted);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hiking;
	}
	
	public void insertHiking(Hiking hiking) {
		String sql = "insert into hiking(mtName, location, altitude) values (?, ?, ?)";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hiking.getMtName());
			pstmt.setString(2, hiking.getLocation());
			pstmt.setInt(3, hiking.getAltitude());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public List<Hiking> getHikingList(String field, String query){
		String sql = "select * from hiking where " + field + " like ? and isDeleted=0 "
				+ " order by modTime desc";
		List<Hiking> list = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int mtid = rs.getInt(1);
				String mtName = rs.getString(2);
				String location = rs.getString(3);
				int altitude = rs.getInt(4);
				int viewCount = rs.getInt(5);
				String modTime = rs.getString(6);
				int isDeleted = rs.getInt(7);
				Hiking hiking = new Hiking(mtid, mtName, location, altitude, viewCount, LocalDateTime.parse(modTime.substring(0,19).replace(" ", "T")), isDeleted);
				list.add(hiking);
			}
		} catch (Exception e) {
			e.printStackTrace();
	}
		return list;
	}
	
	public void updateHiking(Hiking hiking) {
		String sql = "update hiking set mtName=?, location=?, altitude=?, modTime=current_timestamp "
					+ "where mtid = ?";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hiking.getMtName());
			pstmt.setString(2, hiking.getLocation());
			pstmt.setInt(3, hiking.getAltitude());
			pstmt.setInt(4, hiking.getmtid());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteHiking(int mtid) {
		String sql = "update hiking set isDeleted=1 where mtid=?";
		
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtid);
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void increaseViewCount(int mtid) {
		String sql = "update hiking set viewCount=viewCount+1 where mtid=?";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mtid);
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
