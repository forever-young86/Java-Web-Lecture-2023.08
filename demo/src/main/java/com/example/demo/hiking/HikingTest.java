package com.example.demo.hiking;

import java.util.List;

public class HikingTest {

	public static void main(String[] args) {

		HikingDao hDao = new HikingDao();
		
//		Hiking hiking = new Hiking("북한산", "서울", 650);
//		hDao.insertHiking(hiking);
		
//		Hiking h = hDao.getHiking(1);
//		System.out.println(h);
		
//		Hiking hiking = hDao.getHiking(2);
//		hiking.setAltitude(1914);
//		hDao.updateHiking(hiking);
//		System.out.println(hiking);
		
//		hDao.deleteHiking(1);
		
//		hDao.increaseViewCount(1); hDao.increaseViewCount(1);
		
		List<Hiking> list = hDao.getHikingList("mtid", "");
		for(Hiking h : list)
			System.out.println(h);
	}

}
