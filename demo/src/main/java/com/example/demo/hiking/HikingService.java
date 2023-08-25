package com.example.demo.hiking;

import java.util.List;

public interface HikingService {

	Hiking getHiking(int mtid);
	List<Hiking> getHikingList(String field, String query);
	
	void insertHiking(Hiking hiking);
	
	void updateHiking(Hiking hiking);
	
	void deleteHiking(int mtid);
	
	void increaseViewCount(int mtid);
	
}
