package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("mysite4/UserController>loginForm");
		
		return "user/loginForm";		
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@RequestParam("id") String id,
						@RequestParam("password") String password,
						HttpSession session) {
		System.out.println("mysite4/UserController>login");
		
		UserVo authUser = userService.login(id, password);
		if(authUser == null) {
			System.out.println("로그인 실패");
			return "redirect:/user/loginForm?result=fail";
			
		} else {
			session.setAttribute("authUser", authUser);			
			return "redirect:/main";
		}		
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("mysite4/UserController>logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("mysite4/UserController>joinForm");
		
		return "user/joinForm";
	}
	
	@ResponseBody
	@RequestMapping("/availability")
	public boolean availability(@RequestParam("keyword") String keyword) {
		System.out.println("mysite4/UserController>availability");
		
		boolean availability = userService.availability(keyword);
		return availability;
	}
	
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("mysite4/UserController>join");
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session,
							 Model model) {
		System.out.println("mysite4/UserController>modifyForm");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		UserVo userVo = userService.modifyForm(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo,
						 HttpSession session) {
		System.out.println("mysite4/UserController>modify");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		userVo.setNo(no);
		userService.modify(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/main";
	}
}
