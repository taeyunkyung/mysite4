package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVo> galleryList = galleryService.getList();
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam("file") MultipartFile file,
					  @RequestParam("content") String content,
					  HttpSession session) {
		System.out.println("GalleryController.add()");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		GalleryVo galleryVo = new GalleryVo(authUser.getNo(), content);
		galleryService.add(file, galleryVo);		
				
		return "redirect:/gallery/list";
	}
	
	@ResponseBody
	@RequestMapping("/view")
	public GalleryVo view(@RequestParam("no") int no) {
		System.out.println("GalleryController.view()");
		
		return galleryService.view(no);		
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@RequestBody GalleryVo galleryVo) {
		System.out.println("GalleryController.remove()");
		
		return galleryService.remove(galleryVo);		
	}
	
}
