package com.example.demo.hiking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HikingServiceOracleImpl implements HikingService {
	@Autowired private HikingDaoOracle hikingDao;
	
	@Override
	public Hiking getHiking(int mtid) {
		Hiking hiking = hikingDao.getHiking(mtid);
		return null;
	}

	@Override
	public List<Hiking> getHikingList(String field, String query) {
		query = "%" + query + "%";
		List<Hiking> list = hikingDao.getHikingList(field, query);
		return list;
	}

	@Override
	public void insertHiking(Hiking hiking) {
		hikingDao.insertHiking(hiking);
		
	}

	@Override
	public void updateHiking(Hiking hiking) {
		hikingDao.updateHiking(hiking);
		
	}

	@Override
	public void deleteHiking(int mtid) {
		hikingDao.deleteHiking(mtid);
		
	}

	@Override
	public void increaseViewCount(int mtid) {
		hikingDao.increaseViewCount(mtid);
		
	}


}
