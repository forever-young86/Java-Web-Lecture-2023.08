package com.example.demo.oracle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDao {

		private String host;
		private String user;
		private String password;
		private String database;
		private String port;
		
		public BookDao() {
			try {
				Properties props = new Properties();
				String filename ="D:/JavaWeb/demo/src/main/java/com/example/demo/oracle/oracle.properties";
				InputStream is = new FileInputStream(filename);
				props.load(is);
				is.close();
				
				this.host=props.getProperty("host");
				this.user=props.getProperty("user");
				this.password=props.getProperty("password");
				this.database=props.getProperty("database");
				this.port=props.getProperty("port");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Connection myConnection() {
			Connection conn = null;
			try {
				String connStr = "jdbc:oracle:thin:@" + host + ":" + port + ":" + database;
				conn = DriverManager.getConnection(connStr, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public Book getBook(int bookId) { //-->파라메터가 있으므로 2.에서 PreparedStatement 사용
			Connection conn = myConnection(); //1.connection 열기
			String sql = "select * from book where bookid = ?";
			Book book = null;
			try { //2. try 블럭에서 statement 만들기
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bookId); //-->첫번째 파라메터 타입이 Int, ? 자리가 1번째, 파라메터명
				ResultSet rs = pstmt.executeQuery(); //3. ResultSet => executeQuery() : select 문장이용시, 리턴값없음
				while (rs.next()) { //select 문은 while을 사용하여 Result를 얻는다
					bookId = rs.getInt(1);
					String bookname = rs.getString(2);
					String publisher = rs.getString(3);
					int price = rs.getInt(4);
					book = new Book (bookId, bookname, publisher, price); //book 이라는 객체에 데이터를 불러온다
				}
				rs.close(); pstmt.close(); conn.close(); //4.열었던것 다 닫기
			} catch (Exception e) {
				e.printStackTrace();
			}
			return book; //데이터 불러옴!
		}
		
		public List<Book> getBookList(){
			Connection conn = myConnection();
			String sql = "select * from book order by bookid"; //SQL 구문안에 넣어 조건 정렬한다
			List<Book> list = new ArrayList<>(); //데이터를 넣을 리스트를 만든다
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					int bookId = rs.getInt(1);
					String bookname = rs.getString(2);
					String publisher = rs.getString(3);
					int price = rs.getInt(4);
					Book book = new Book(bookId, bookname, publisher, price);
					list.add(book);
				}
				rs.close(); stmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public void insertBook (Book b) {
			Connection conn = myConnection();
			 String sql = "insert into book values (?, ?, ?, ?)";
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, b.getBookid());
				 pstmt.setString(2, b.getBookname());
				 pstmt.setString(3, b.getPublisher());
				 pstmt.setInt(4, b.getPrice());
				 pstmt.executeUpdate();
				 
				 pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void updateBook (Book b) {
			Connection conn = myConnection();
			 String sql = "update book set bookname=?, publisher=?, price=? where bookid=?"; //bookid가 primary key여서 조건에 넣음
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, b.getBookname());
				 pstmt.setString(2, b.getPublisher());
				 pstmt.setInt(3, b.getPrice());
				 pstmt.setInt(4, b.getBookid());
				 pstmt.executeUpdate();
				 
				 pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void deleteBook (int bookId) {
			Connection conn = myConnection();
			 String sql = "delete from book where bookid=?";
			 try {
				 PreparedStatement pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, bookId);
				 pstmt.executeUpdate();
				 
				 pstmt.close(); conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}