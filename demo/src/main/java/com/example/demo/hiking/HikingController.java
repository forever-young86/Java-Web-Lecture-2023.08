package com.example.demo.hiking;

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
@RequestMapping("/hiking")

public class HikingController {
//	@Autowired private HikingDaoOracle hDao;
	@Autowired private HikingService hikingService;
	
	@GetMapping("/list")
	public String list(@RequestParam(name="f", defaultValue="mtName") String field,
			@RequestParam(name="q", defaultValue="") String query,
			Model model) {
		List<Hiking> list = hikingService.getHikingList(field, query);
		model.addAttribute("hikingList",list);
		model.addAttribute("menu", "hiking");
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		return "hiking/list";
}

	@GetMapping("/write")
	public String writeForm(Model model) {
		model.addAttribute("menu", "hiking");
		return "hiking/write"; 
	}
	

	@PostMapping("/write")
	public String writeProc(Hiking hiking) {
		hikingService.insertHiking(hiking);
		return "redirect:/hiking/list";
	}
	
	@GetMapping("/detail/{mtid}")
	public String detail(@PathVariable int mtid, String option, Model model) {
		if(option == null || option.equals(""))
			hikingService.increaseViewCount(mtid);
		Hiking hiking = hikingService.getHiking(mtid);
		model.addAttribute("hiking", hiking);
		model.addAttribute("menu", "hiking");
		return "hiking/detail";
	}

	@GetMapping("/update/{mtid}")
	public String updateForm(@PathVariable int mtid, Model model) {
		Hiking hiking = hikingService.getHiking(mtid);
		model.addAttribute("hiking", hiking);
		model.addAttribute("menu", "hiking");
		return "hiking/update";
	}
	
	@PostMapping("/update")
	public String updateProc(Hiking hiking) {
		hikingService.updateHiking(hiking);
		return "redirect:/hiking/detail/" + hiking.getmtid() + "?option=DNI";
	}
	
	@GetMapping("/delete/{mtid}")
	public String delete(@PathVariable int mtid, Model model) {
		Hiking hiking = hikingService.getHiking(mtid);
		model.addAttribute("mtid", mtid);
		model.addAttribute("menu", "hiking");
		return "hiking/delete";
	}
	
	@GetMapping("/deleteConfirm/{mtid}")
	public String deleteConfirm(@PathVariable int mtid) {
		hikingService.deleteHiking(mtid);
		return "redirect:/hiking/list";
	}
	
}
