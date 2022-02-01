package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// 리스트 가져오기
	public List<BoardVo> boardList() {
		System.out.println("mysite4/BoardDao.boardList()");

		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		return boardList;
	}

	// 게시글 1건 가져오기(읽기용)
	public BoardVo read(int no) {
		System.out.println("mysite4/BoardDao.read()");

		BoardVo boardVo = sqlSession.selectOne("board.read", no);
		return boardVo;
	}

	// 조회수 업데이트
	public int hit(int no) {
		System.out.println("mysite4/BoardDao.hit()");

		int count = sqlSession.update("board.updateHit", no);

		System.out.println("조회수+" + count);
		return count;
	}

	// 글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("mysite4/BoardDao.write()");

		int count = sqlSession.insert("board.insert", boardVo);

		System.out.println(count + "건 등록되었습니다.(BoardVo)");
		return count;
	}

	// 게시글 삭제
	public int delete(BoardVo boardVo) {
		System.out.println("mysite4/BoardDao.delete()");

		int count = sqlSession.delete("board.delete", boardVo);

		System.out.println(count + "건 삭제되었습니다.(BoardDao)");
		return count;
	}

	// 게시글 수정
	public int update(BoardVo boardVo) {
		System.out.println("mysite4/BoardDao.update()");

		int count = sqlSession.update("board.update", boardVo);

		System.out.println(count + "건 수정되었습니다.(BoardDao)");
		return count;
	}
}
