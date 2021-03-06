package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("mysite4/GuestbookController>addList");
		
		List<GuestbookVo> guestList = guestbookService.list();
		model.addAttribute("guestList", guestList);
		
		return "guestbook/addList";
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookController>add");
		
		guestbookService.add(guestbookVo);
		
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {
		System.out.println("mysite4/GuestbookController>deleteForm");
		
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookController>delete");
		
		guestbookService.delete(guestbookVo);
		
		return "redirect:/guestbook";
	}
}
