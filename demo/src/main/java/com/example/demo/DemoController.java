package com.example.demo;

import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		public String loginProc(HttpServletRequest req, Model model) { //입력한 파라미터를 받는것 : HttpServletRequest req
			String uid = req.getParameter("uid");	//"uid" 가 input tag의 name값 
			String pwd = req.getParameter("pwd");
			//return "<h1>uid=" + uid + ", pwd="+ pwd + "</h1>" ;
			
			model.addAttribute("uid",uid);
			model.addAttribute("pwd",pwd);
			return "03.loginResult";
		}
		
		@GetMapping("/getParam")
		@ResponseBody //return 값이 화면에 표시됨
		public String getParam(HttpServletRequest req) {
			String a_ = req.getParameter("a");
			String b_ = req.getParameter("b");
			String op = req.getParameter("op");
			int a = Integer.parseInt(a_);
			int b = Integer.parseInt(b_);
			int result = 0;
			String oper = "";
			
			switch(op) {
			case "add": result = a + b; oper = "+";
				break;
			case "sub": result = a - b; oper = "-";
				break;
			case "mul": result = a * b; oper = "*";
				break;
			case "div": result = (int) a / b; oper = "/";
				break;
			default:
				result = 0;
			}
			return "<h1>" + a + oper + b + "=" + result + "</h1>";
		}
		@GetMapping("/calc")
		public String calcForm() {
			return "04.calcForm"; //파일이름을 넣는다-->파일이 나타남
		}
		
		@PostMapping("/calc")
		public String calcProc(int a, int b, String op, Model model) {
			/**String a_ = req.getParameter("a");
			String b_ = req.getParameter("b");
			String op = req.getParameter("op");
			int a = Integer.parseInt(a_);
			int b = Integer.parseInt(b_); 를 (int a, int b, String op) 한줄로! **/ 
			
			int result = 0;
			String oper = "";
			
			switch(op) {
			case "add": result = a + b; oper = "+";
				break;
			case "sub": result = a - b; oper = "-";
				break;
			case "mul": result = a * b; oper = "*";
				break;
			case "div": result = (int) a / b; oper = "/";
				break;
			default:
				result = 0;
			}
			model.addAttribute("a",a);
			model.addAttribute("b",b);
			model.addAttribute("oper",oper);
			model.addAttribute("result",result);

			return "05.calcResult";
		}
		
		@GetMapping("/write")
		public String writeForm() {
			return "06.writeForm";
		}
		
		@PostMapping("/write")
		public String writeProc(HttpServletRequest req, Model model) {
			String title = req.getParameter("title");
			String[] languages = req.getParameterValues("language"); //체크박스를 받을때, 여러개 받을수 있기때문에 array로 간다
			String content = req.getParameter("content");
			
			String joinLanguages = (languages == null) ? "" : String.join(", ", languages);
			//(languages == null) ? "" => languages == null 일때, "" 아무값도 안나옴
			Board board = new Board(title, joinLanguages, content.replace("\n", "<br>"));
			//content.replace("\n", "<br>") => content 내용이 길때 자동 줄바꿈 
			//System.out.println(board);
			model.addAttribute("board", board);
			return "07.writeResult";
		}
		
		@GetMapping("/calculator")
		public String calculatorForm(String eval, Model model) {
			model.addAttribute("eval",eval);
			return "08.calculator";
		}
		
		@PostMapping("/calculator")
//HttpServletRequest => 사용자로부터 들어온 요청을 서버에서 생성하여 내용을 파라미터로 get, post로 전달
		public String calculatorProc(HttpServletRequest req, HttpSession session, Model model) {
			String num_ = req.getParameter("num");
			String op_ = req.getParameter("op");
			Object obj = session.getAttribute("stack");
			Stack<String> stack = (obj == null) ? new Stack<>() : (Stack) obj;
					//obj가 null일때 참이면 stack 은 빈칸, 거짓이면 stack 에 obj 들어감
			if (num_ != null) { //num 이 null 이 아닐때
				 if (stack.isEmpty()) { //stack 에 비어있으면
					 	stack.push(num_); //num (입력값) 넣기
				 } else {
					 String element = stack.pop(); //null아니면, 입력된 숫자 보이기
					 //연산자 기호를 눌렀을때, stack에 기호 넣기
					 if(element.equals("/") || element.equals("*") || element.equals("-") ||element.equals("+")){
						 stack.push(element);
						 stack.push(num_);
					 } else {
						 num_ = element + num_;
						 stack.push(num_);
					 }
				}
				 session.setAttribute("stack", stack);
				 model.addAttribute("eval", getEval(stack));
			} else if (op_ !=null && !op_.equals("=")) {
				if (op_.equals("C")) {
					if (stack.isEmpty())
						;
					else {
						String s = stack.pop();
						if (s.length() > 1) {
							s = s.substring(0, s.length() -1);
							stack.push(s);
						}
					}
				}else
					stack.push(op_);
				session.setAttribute("stack", stack);
				model.addAttribute("eval",getEval(stack));
				
				} else {
					int result = 0;
					int num2 = Integer.parseInt(stack.pop());
					String op = stack.pop();
					int num1 = Integer.parseInt(stack.pop());
					switch(op) {
					case "+" : result = num1 + num2; break;
					case "-" : result = num1 - num2; break;
					case "*" : result = num1 * num2; break;
					case "/" : result = (int) num1 / num2; break;
					default: result = 0;
					}
					session.removeAttribute("stack");
					model.addAttribute("eval",result);
				} 
			return "08.calculator";
			
			}
			String getEval(Stack<String>stack) {
				String eval = "";
				for (String s : stack)
					eval += s + " ";
				return eval;
		}
		
		
  }
