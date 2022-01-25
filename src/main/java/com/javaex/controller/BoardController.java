package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardList(Model model) {
		System.out.println("mysite4/BoardController>boardList");
		
		List<BoardVo> boardList = boardService.list();
		model.addAttribute("boardList", boardList);	
		
		return "board/list";
	}

	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam("no") int no,
			Model model) {
		System.out.println("mysite4/BoardController>read");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("readVo", boardVo);
				
		return "board/read";
	}
	
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("mysite4/BoardController>writeForm");
		
		return "board/writeForm";		
	}
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("mysite4/BoardController>write");
		
		boardService.write(boardVo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("mysite4/BoardController>delete");
		
		boardService.delete(no);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no,
			Model model) {
		System.out.println("mysite4/BoardController>modifyForm");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("readVo", boardVo);
		
		return "board/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("mysite4/BoardController>modify");
		
		boardService.modify(boardVo);
		
		return "redirect:/board";
	}
	
}
