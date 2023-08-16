package com.example.demo;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bs")
public class BoostrapeController {
	@Value("${spring.servlet.multipart.location}") private String uploadDir;

	@GetMapping("/login")
	public String login() {
		return "bs/login";
	}
	
	@PostMapping("login1") //파라미터를 받는것 HttpServletRequest
	@ResponseBody
	public String login1(HttpServletRequest req) {
		String email = req.getParameter("email");
		String pswd = req.getParameter("pswd");
		String remember = req.getParameter("remember"); //check면 on, 아니면 null
		
		return "<h2>email: " + email + ", pswd: "+ pswd + ", remember: "+ remember + "</h2>";
	}

	@PostMapping("login2")
	@ResponseBody
	public String login2(String email, String pswd, String remember) {
		return "<h2>email: " + email + ", pswd: "+ pswd + ", remember: "+ remember + "</h2>";
	}

	@GetMapping("/register")
	public String registerForm() {
		return "bs/register";
	}
	
	@PostMapping("/register") //입력값을 확인하기위한
	@ResponseBody
	public String registerProc(MultipartHttpServletRequest req) {
		String id = req.getParameter("id");
		String pswd = req.getParameter("pswd");
		String pswd2 = req.getParameter("pswd2");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String html = String.format("<h2>%s, %s, %s, %s, %s, %s</h2>", id, pswd, pswd2, name, email, gender);
		
		//파일읽기
		MultipartFile filePart = req.getFile("profile");
		if (filePart.getContentType().contains("image")) { //사진을 보냈음
			String filename = filePart.getOriginalFilename();
			html += "<h2>"+ filename + "</h2>"; 
			String profilePath = uploadDir + "filename";
			try {
				filePart.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		return html;
	}
	@PostMapping("/register2") //라우팅을 위한 (콘솔창에 결과값이 나옴)
	public String register2Proc(MultipartHttpServletRequest req) {
		String id = req.getParameter("id");
		String pswd = req.getParameter("pswd");
		String pswd2 = req.getParameter("pswd2");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		MultipartFile filePart = req.getFile("profile");
		if (filePart.getContentType().contains("image")) {
			String filename = filePart.getOriginalFilename();
			String profilePath = uploadDir + "filename";
			try {
				filePart.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		if (!checkId(id)) {
			//alert message 내보내고
			System.out.println("id: " + id + "이/가 이미 존재합니다.");
			//회원가입 화면으로 되돌림
			return "redirect:/bs/register";
			
		}else {
			if (pswd !=null && pswd.equals(pswd2)) {
				//올바른 회원가입
				System.out.println(name + "님 환영합니다.");
				//로그인 화면으로 보내줌
				return "redirect:/bs/login";
			}else {
				//비밀번호 오류에 대한 alert message 
				System.out.println("패스워드를 올바르게 입력하세요.");
				//회원가입 화면으로 되돌림
				return "redirect:/bs/register";

			}
		}
		
	}
	
	@PostMapping("/register3")
	public String register3Proc(MultipartHttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		String pswd = req.getParameter("pswd");
		String pswd2 = req.getParameter("pswd2");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		MultipartFile filePart = req.getFile("profile");
		if (filePart.getContentType().contains("image")) {
			String filename = filePart.getOriginalFilename();
			String profilePath = uploadDir + "filename";
			try {
				filePart.transferTo(new File(profilePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		if (!checkId(id)) {
			model.addAttribute("msg","id: " + id + "이/가 이미 존재합니다.");
			model.addAttribute("url","/demo/bs/register");
			return "bs/alertMsg";
			
		}else {
			if (pswd !=null && pswd.equals(pswd2)) {
				model.addAttribute("msg",name + "님 환영합니다.");
				model.addAttribute("url","/demo/bs/login");
				return "bs/alertMsg";
			}else {
				model.addAttribute("msg","패스워드를 올바르게 입력하세요.");
				model.addAttribute("url","/demo/bs/register");
				return "bs/alertMsg";

			}
		}
		
	}
	
	//id가 DB에 존재하면 false, 없으면 true
	boolean checkId(String id) {
		if (Math.random() < 0.9) //90%확률
			return true;
		return false; //else 생략가능
	}
}
