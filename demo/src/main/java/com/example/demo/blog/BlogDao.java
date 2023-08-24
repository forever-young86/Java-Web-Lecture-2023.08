package com.example.demo.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogDao { //application.properties에 있어서 정보가 노출되지 않음
	 @Value("${spring.datasource.url}") private String url;
	 @Value("${spring.datasource.username}") private String username;
	 @Value("${spring.datasource.password}") private String password;
	
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String username = "hmuser";
//	private String password = "hmpass";
	
	public Blog getBlog(int bid) {
		String sql = "select * from blog where bid = ?";
		Blog blog = null;
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bid = rs.getInt(1);
				String penname = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				String modTime = rs.getString(5);
				int viewCount = rs.getInt(6);
				int isDeleted = rs.getInt(7);
				blog = new Blog(bid, penname, title, content, LocalDateTime.parse(modTime.substring(0,19).replace(" ", "T")), viewCount, isDeleted);
			}
			rs.close(); pstmt.close(); conn.close();
			/* String type을 localDateTime type으로 바꿀때 .parse를 사용
			 * 오라클  2023-08-23 14:18:40.964000000 를 loacalDatetime 2023-08-23 14:18:40 으로 변경하려면
			 * modTime.substring(0,19).replace(" ", "T") (0번째부터 19자리까지만, 블랭크를 T로 변경)을 사용한다. */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blog;
	}

	public void insertBlog(Blog blog) {
		String sql = "insert into blog(penName, title, content) values (?, ?, ?)";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blog.getPenName());
			pstmt.setString(2, blog.getTitle());
			pstmt.setString(3, blog.getContent());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Blog> getBlogList(String field, String query){
		String sql = "select * from blog where " + field + " like ? and isDeleted=0 " //""안에서 엔터쳐서 이어서 작성가능 사이에 스페이스 무조건 있어야함! (없으면 에러!)
				+ " order by modTime desc";
		List<Blog> list = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int bid = rs.getInt(1);
				String penname = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				String modTime = rs.getString(5);
				int viewCount = rs.getInt(6);
				int isDeleted = rs.getInt(7);
				Blog blog = new Blog(bid, penname, title, content, 
							LocalDateTime.parse(modTime.substring(0,19).replace(" ", "T")), 
							viewCount, isDeleted);
				list.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateBlog(Blog blog) {
		String sql = "update blog set penName=?, title=?, content=?, modTime=current_timestamp "
					+ "where bid = ?";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blog.getPenName());
			pstmt.setString(2, blog.getTitle());
			pstmt.setString(3, blog.getContent());
			pstmt.setInt(4, blog.getBid());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBlog(int bid) {
		String sql = "update blog set isDeleted=1 where bid=?"; //필드 isDeleted를 사용
		
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void increaseViewCount(int bid) {
		String sql = "update blog set viewCount=viewCount+1 where bid=?";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
