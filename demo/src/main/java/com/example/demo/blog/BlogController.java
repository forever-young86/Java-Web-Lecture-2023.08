package com.example.demo.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
//	private BlogDao bDao = new BlogDao(); =>스프링의 방식이 아니다!
	@Autowired private BlogDao bDao; //객체를 만들지 않았지만 스프링에서 dependency injection 해서 (객체를 생성해서) bDao를 사용하게 함
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Blog> list = bDao.getBlogList("title", ""); //모든 값 불러오기 (보여줄화면)
		model.addAttribute("blogList",list); //출력 파라미터
		model.addAttribute("menu", "blog");
		return "blog/list"; //blog에 있는 list.jsp를 불러온다
	}
	
	@GetMapping("/write")
	public String writeForm(Model model) {
		model.addAttribute("menu", "blog");
		return "blog/write"; 
	}
	
	@PostMapping("/write")
//	public String writeProc(String penName, String title, String content) { //받아올내용
	public String writeProc(Blog blog) { //위처럼 길게 쓰지않고 + 아래처럼 객체를 만들지않고, 바로 Blog blog로 가져올수있다
//		Blog blog = new Blog(penName, title, content); //객체를 만들어 불러옴
		bDao.insertBlog(blog);
		return "redirect:/blog/list"; //list화면으로 redirect한다
	}
	
	@GetMapping("/detail/{bid}")
	public String detail(@PathVariable int bid, String option, Model model) {
		if(option == null || option.equals(""))
			bDao.increaseViewCount(bid); //DNI option이 설정되어 있으면 (update시) 조회수가 증가되지 않음
		Blog blog = bDao.getBlog(bid); // bid로 데이터를 가져옴
		model.addAttribute("blog", blog);
		model.addAttribute("menu", "blog");
		return "blog/detail";
	}

	@GetMapping("/update/{bid}")
	public String updateForm(@PathVariable int bid, Model model) {
		Blog blog = bDao.getBlog(bid); //수정을 위해선 기존에 갖고 있던 내용을 가져와서 다시 수정하여 보낸다
		model.addAttribute("blog", blog);
		model.addAttribute("menu", "blog");
		return "blog/update";
	}
	
	@PostMapping("/update")
	public String updateProc(Blog blog) {
//  public String updateProc(int bid, String penName, String title, String content){
//		Blog blog = new Blog(bid, penName, title, content);
		bDao.updateBlog(blog);
		return "redirect:/blog/detail/" + blog.getBid() + "?option=DNI"; //DNI : DoNotIncrease
	}
	
	@GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid, Model model) {
		Blog blog = bDao.getBlog(bid);
		model.addAttribute("bid", bid);
		model.addAttribute("menu", "blog");
		return "blog/delete";
	}
	
	@GetMapping("/deleteConfirm/{bid}")
	public String deleteConfirm(@PathVariable int bid) {
		bDao.deleteBlog(bid);
		return "redirect:/blog/list";
	}
	
}
