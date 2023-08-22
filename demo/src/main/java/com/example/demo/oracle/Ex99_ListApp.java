package com.example.demo.oracle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex99_ListApp {

	public static void main(String[] args) {
		Customer c1 = new Customer ();
		c1.setCustId(101);
		c1.setName("박찬호");
		c1.setAddr("미국 엘에이");
		c1.setPhone("010-2345-6789");
		
		Customer c2 = new Customer (102, "박인비", "대한민국 서울", "010-3456-7890");
	
		
		List<Customer> list = new ArrayList<>(); //list 타입에 Customer 타입을 넣음
		list.add(c1); list.add(c2); //list에 위에서 만든 객체 c1, c2를 넣음
		System.out.println(list.get(0)); System.out.println(list.get(1));
		System.out.println();
		
		for (Customer c : list) //리스트에서 요소하나를 꺼내면 Customer 타입
			System.out.println(c);
		System.out.println();
		
		list.forEach(x -> System.out.println(x)); //람다함수하여 리스트를 불러옴
		
	}

}
