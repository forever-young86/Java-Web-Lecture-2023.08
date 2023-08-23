package com.example.demo.oracle;

import java.util.List;

public class BookTest {

	public static void main(String[] args) {
		BookDao bDao = new BookDao(); //객체 생성
		
//		Book b1 = bDao.getBook(5); //get을 사용하여 원하는 데이터를 불러온다
//		System.out.println(b1);
		
//		Book b3 = new Book (14, "날아라손오공", "미래출판사", 15000);
//		bDao.insertBook(b3);
		
//		Book b4 = bDao.getBook(14);
//		b4.setBookname("손오공이 난다"); b4.setPrice(60000);
//		bDao.updateBook(b4);
		
		bDao.deleteBook(14);
		
		List<Book> list = bDao.getBookList();
		for(Book b2 : list)
			System.out.println(b2);
	}

}
