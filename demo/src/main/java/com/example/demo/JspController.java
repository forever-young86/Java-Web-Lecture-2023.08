package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller //컨트롤러 만들때 제일 먼저 import 해야함
@RequestMapping("/jsp")
public class JspController {

	@GetMapping("/sample")
	public String sample(Model model) {
		model.addAttribute("menu", "ai");
		return "jsp/sample";
		}
	
	@GetMapping("/schedule")
	public String schedule(Model model) {
		model.addAttribute("menu", "schedule");
		return "jsp/schedule";
		}
		
	@GetMapping("/elOperator")
	public String elOperator() {
		return "jsp/el1_operator";
	}
	
	@GetMapping("/elScope")
	public String elScope(HttpSession session, Model model) {
		session.setAttribute("sName", "sName"); //session = 글로벌변수 (모든페이지에서 불러올수 있음)
		model.addAttribute("mName","mName"); //model = 지역변수
		session.setAttribute("name", "sName");
		model.addAttribute("name","mName");
		return "jsp/el2_scope";
	}
	
}
