package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DemoController {

	//localhost:8080/demo/index
		@GetMapping("/index")
		@ResponseBody
		public String index() {
			return "<h1>Hello World!!!</h1>";
		}
		//localhost:8080/demo/hello
		@GetMapping("/hello")
		public String hello() {
			return "hello";
		}
		
		@GetMapping("/login")
		public String login() {
			return "02.login";
		}
		
		@PostMapping("/login")   	//REST API : 반응하여 결과가 나옴
		//@ResponseBody 
		public String loginProc(HttpServletRequest req, Model model) {
			String uid = req.getParameter("uid");	//"uid" 가 input tag의 name값 
			String pwd = req.getParameter("pwd");
			//return "<h1>uid=" + uid + ", pwd="+ pwd + "</h1>" ;
			
			model.addAttribute("uid",uid);
			model.addAttribute("pwd",pwd);
			return "03.loginResult";
		}
}
