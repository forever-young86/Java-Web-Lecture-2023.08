package com.example.demo.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {
//	private BlogDao bDao = new BlogDao(); =>스프링의 방식이 아니다!
//	@Autowired private BlogDao bDao; //객체를 만들지 않았지만 스프링에서 dependency injection 해서 (객체를 생성해서) bDao를 사용하게 함
//	@Autowired private BlogDaoOracle bDao; //BlogDaoMyBatis는 클래스가 아닌 인터페이스 타입의 bDao를 찾아서 객체를 생성해서 사용한다
	@Autowired private BlogService blogService;  //BlogService는 인터페이스 blogService는 스프링이 넣어주는 BlogServiceOracleImpl 구현객체
	
	@GetMapping("/list")
	public String list(@RequestParam(name="f", defaultValue="title") String field,  //@RequestParam f 가 들어가면 field값 불러온다, 없으면 title 불러옴
						@RequestParam(name="q", defaultValue="") String query, //q라고 하는 네임을 보고, 없으면 빈스트링을 보여줌 (*list.jsp #17)
						Model model) {
		List<Blog> list = blogService.getBlogList(field, query); //(보여줄화면)
		model.addAttribute("blogList",list); //출력 파라미터
		model.addAttribute("menu", "blog");
		model.addAttribute("field", field);
		model.addAttribute("query", query);
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
		blogService.insertBlog(blog);
		return "redirect:/blog/list"; //list화면으로 redirect한다
	}
	
	@GetMapping("/detail/{bid}")
	public String detail(@PathVariable int bid, String option, Model model) {
		if(option == null || option.equals(""))
			blogService.increaseViewCount(bid); //DNI option이 설정되어 있으면 (update시) 조회수가 증가되지 않음
		Blog blog = blogService.getBlog(bid); // bid로 데이터를 가져옴
		model.addAttribute("blog", blog);
		model.addAttribute("menu", "blog");
		return "blog/detail";
	}

	@GetMapping("/update/{bid}")
	public String updateForm(@PathVariable int bid, Model model) {
		Blog blog = blogService.getBlog(bid); //수정을 위해선 기존에 갖고 있던 내용을 가져와서 다시 수정하여 보낸다
		model.addAttribute("blog", blog);
		model.addAttribute("menu", "blog");
		return "blog/update";
	}
	
	@PostMapping("/update")
	public String updateProc(Blog blog) {
//  public String updateProc(int bid, String penName, String title, String content){
//		Blog blog = new Blog(bid, penName, title, content);
		blogService.updateBlog(blog);
		return "redirect:/blog/detail/" + blog.getBid() + "?option=DNI"; //DNI : DoNotIncrease
	}
	
	@GetMapping("/delete/{bid}")
	public String delete(@PathVariable int bid, Model model) {
		Blog blog = blogService.getBlog(bid);
		model.addAttribute("bid", bid);
		model.addAttribute("menu", "blog");
		return "blog/delete";
	}
	
	@GetMapping("/deleteConfirm/{bid}")
	public String deleteConfirm(@PathVariable int bid) {
		blogService.deleteBlog(bid);
		return "redirect:/blog/list";
	}
	
}
