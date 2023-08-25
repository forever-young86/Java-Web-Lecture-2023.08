package com.example.demo.hiking;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface HikingDaoOracle {
	@Select("select * from hiking where mtid =#{mtid}")
	Hiking getHiking(int mtid);
	
	@Select("select * from hiking where ${field} like #{query} and isDeleted=0"
			+ " order by modTime desc")
	List<Hiking> getHikingList(String field, String query);
	
	@Insert("insert into hiking(mtName, location, altitude)"
			+ " values (#{mtName}, #{location}, #{altitude, jdbcType=VARCHAR})")
	void insertHiking(Hiking hiking);
	
	@Update("update hiking set mtName=#{mtName}, location=#{location}, altitude=#{altitude, jdbcType=VARCHAR},"
			+ " modTime=current_timestamp where mtid=#{mtid}")
	void updateHiking (Hiking hiking);
	
	@Update("update hiking set isDeleted=1 where mtid=#{mtid}")
	void deleteHiking(int mtid);
	
	@Update("update hiking set viewCount=viewCount+1 where mtid=#{mtid}")
	void increaseViewCount(int mtid);


	
}
