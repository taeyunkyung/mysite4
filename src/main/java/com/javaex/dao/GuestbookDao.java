package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// 등록
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookDao.insert()");

		int count = sqlSession.insert("guestbook.insert", guestbookVo);

		System.out.println(count + "건 등록되었습니다.(GuestbookDao)");
		return count;
	}
	
	// addReturn //
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookDao.insertSelectKey()");		
		
		return sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
	}
	
	public GuestbookVo selectGuest(int no) {
		System.out.println("mysite4/GuestbookDao.selectGuest()");
		
		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectByNo", no);
		return guestbookVo;
	}
	// addReturn //

	// 삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("mysite4/GuestbookDao.delete()");

		int count = sqlSession.delete("guestbook.delete", guestbookVo);

		System.out.println(count + "건 삭제되었습니다.(GuestbookDao)");
		return count;
	}

	// 리스트 가져오기
	public List<GuestbookVo> getList() {
		System.out.println("mysite4/GuestbookDao.getList()");

		List<GuestbookVo> guestList = sqlSession.selectList("guestbook.selectList");
		return guestList;
	}
}
