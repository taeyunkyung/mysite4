package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println("ApiGuestbookController.addList()");
		
		return "aguestbook/addList";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestList = guestbookService.list();
		System.out.println(guestList);
		
		return guestList;
	}

	@ResponseBody
	@RequestMapping("/add")
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		
		GuestbookVo guest = guestbookService.addReturnVo(guestbookVo);
		
		return guest;
	}
	
	@ResponseBody
	@RequestMapping("/add2")
	public GuestbookVo add2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add2()");
		System.out.println(guestbookVo);
		
		GuestbookVo guest2 = guestbookService.addReturnVo(guestbookVo);
		return guest2;
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove()");
		System.out.println(guestbookVo);
		
		String result = guestbookService.removeGuest(guestbookVo);
		System.out.println(result);
		return result;
	}

}
