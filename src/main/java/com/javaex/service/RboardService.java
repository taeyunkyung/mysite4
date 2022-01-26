package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {

	@Autowired
	private RboardDao rboardDao;

	public List<RboardVo> rboardList() {
		System.out.println("RboardService.rboardList()");

		List<RboardVo> rboardList = rboardDao.getList();
		return rboardList;
	}

	public RboardVo read(int no) {
		System.out.println("RboardService.read(): readOne, hit");

		RboardVo rboardVo = rboardDao.readOne(no);
		rboardDao.hit(no);

		return rboardVo;
	}

	// 첫글쓰기
	public int insertFirst(RboardVo rboardVo) {
		System.out.println("RboardService.insertFirst()");

		int count = rboardDao.insertFirst(rboardVo);		
		return count;
	}

	// 댓글쓰기-1
	public RboardVo replySet(int no) {
		System.out.println("RboardService.replySet()");
		
		RboardVo rboardVo = rboardDao.replySet(no);
		return rboardVo;
	}

	// 댓글쓰기-2
	public int reply(RboardVo rboardVo) {
		System.out.println("RboardService.reply(): orderUpdate, insertReply");
		
		rboardDao.orderUpdate(rboardVo);
		int count = rboardDao.insertReply(rboardVo);

		return count;
	}

	public int delete(int no) {
		System.out.println("RboardService.deleteUpdate()");
		
		int count = rboardDao.deleteUpdate(no);
		return count;
	}
	
	public RboardVo modifyForm(int no) {
		System.out.println("RboardService.modifyForm()");
		
		RboardVo rboardVo = rboardDao.readOne(no);
		return rboardVo;
	}
	
	public int modify(RboardVo rboardVo) {
		System.out.println("RboardService.modify()");
		
		int count = rboardDao.update(rboardVo);
		return count;
	}
}
