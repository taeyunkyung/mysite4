package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping("/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rboardService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String replyList(Model model) {
		System.out.println("mysite4/RboardController");
		
		List<RboardVo> rboardList = rboardService.rboardList();
		model.addAttribute("rboardList", rboardList);
		
		return "rboard/list";
	}
	
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam("no") int no,
			Model model) { 
		System.out.println("mysite4/RboardController>read");
		
		RboardVo rboardVo = rboardService.read(no);
		model.addAttribute("readVo", rboardVo);
		
		return "rboard/read";
	}
	
	@RequestMapping(value = "/writeFirst", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeFirst() {
		System.out.println("mysite4/RboardController>writeFirst");
		
		return "rboard/writeFirst";		
	}
	
	@RequestMapping(value = "/first", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute RboardVo rboardVo) {
		System.out.println("mysite4/RboardController>first");
		
		rboardService.insertFirst(rboardVo);
		
		return "redirect:/rboard";		
	}	

	@RequestMapping(value = "/writeReply", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeReply(@RequestParam("no") int no,
			Model model) {
		System.out.println("mysite4/RboardController>writeReply");
		
		RboardVo rboardVo = rboardService.replySet(no);
		model.addAttribute("replyVo", rboardVo);
		
		return "rboard/writeReply";		
	}
	
	@RequestMapping(value = "/reply", method = { RequestMethod.GET, RequestMethod.POST })
	public String reply(@ModelAttribute RboardVo rboardVo) {
		System.out.println("mysite4/RbaordController>reply");
		
		rboardService.reply(rboardVo);
		
		return "redirect:/rboard";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("mysite4/RboardController>delete");
		
		rboardService.delete(no);
		
		return "redirect:/rboard";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no,
			Model model) {
		System.out.println("mysite4/RboardController>modifyForm");
		
		RboardVo rboardVo = rboardService.modifyForm(no);
		model.addAttribute("readVo", rboardVo);
		
		return "rboard/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute RboardVo rboardVo) {
		System.out.println("mysite4/RboardController>modify");
		
		rboardService.modify(rboardVo);
		
		return "redirect:/rboard";
	}
}
