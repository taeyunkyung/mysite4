package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<RboardVo> getList() {
		System.out.println("RboardDao.getList()");

		List<RboardVo> rboardList = sqlSession.selectList("rboard.selectList");
		return rboardList;
	}

	// 읽기 //
	public RboardVo readOne(int no) {
		System.out.println("RboardDao.readOne()");

		RboardVo rboardVo = sqlSession.selectOne("rboard.readOne", no);
		return rboardVo;
	}

	public int hit(int no) {
		int count = sqlSession.update("rboard.hit", no);
		System.out.println("조회수+" + count);

		return count;
	}
	// 읽기 //

	// 첫글쓰기
	public int insertFirst(RboardVo rboardVo) {
		System.out.println("RboardDao.insertFirst()");

		int count = sqlSession.insert("rboard.insertFirst", rboardVo);
		System.out.println(count + "건 등록되었습니다.(RboardDao)");

		return count;
	}

	// 댓글쓰기-1 //
	public RboardVo replySet(int no) {
		System.out.println("RboardDao.replySet()");

		RboardVo rboardVo = sqlSession.selectOne("rboard.replySet", no);
		return rboardVo;
	}

	// 댓글쓰기-2 //
	public int orderUpdate(RboardVo rboardVo) {
		int count = sqlSession.update("rboard.orderUpdate", rboardVo);
		System.out.println("orderUpdate+" + count);

		return count;
	}

	public int insertReply(RboardVo rboardVo) {
		System.out.println("RboardDao.insertReply()");

		int count = sqlSession.insert("rboard.insertReply", rboardVo);
		System.out.println(count + "건 등록되었습니다.(RboardDao)");

		return count;
	}
	// 댓글쓰기-2 //

	// 삭제 //
	public int delete(int no) {
		System.out.println("RboardDao.delete()");

		int count = sqlSession.delete("rboard.delete", no);
		System.out.println(count + "건 삭제되었습니다.(RboardDao)");

		return count;
	}
	
	public int deleteUpdate(int no) {
		System.out.println("RboardDao.deleteUpdate(): 임시기능");
		
		int count = sqlSession.update("rboard.deleteUpdate", no);
		System.out.println(count + "건 수정되었습니다.(RboardDao)");
		
		return count;
	}
	// 삭제 //

	public int update(RboardVo rboardVo) {
		System.out.println("RbaordDao.update");

		int count = sqlSession.update("rboard.update", rboardVo);
		System.out.println(count + "건 수정되었습니다.(RboardDao)");

		return count;
	}
}
