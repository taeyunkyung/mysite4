package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> list() {
		System.out.println("mysite4/GuestbookService.list()");
		
		List<GuestbookVo> guestList = guestbookDao.getList();
		return guestList;
	}
	
	public int add(GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookService.add()");
		
		int count = guestbookDao.insert(guestbookVo);
		System.out.println("(-> GuestbookService)");
		
		return count;
	}
	
	public GuestbookVo addReturnVo(GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookService.addReturnVo()");
		
		guestbookDao.insertSelectKey(guestbookVo);
		int no = guestbookVo.getNo();		
		
		return guestbookDao.selectGuest(no);
	}
	
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookService.delete()");
		
		int count = guestbookDao.delete(guestbookVo);
		System.out.println("(-> GuestbookService)");
		
		return count;
	}

}
