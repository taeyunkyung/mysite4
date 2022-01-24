package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 가입
	public int insert(UserVo userVo) {
		System.out.println("mysite4/UserDao.insert()");

		int count = sqlSession.insert("user.insert", userVo);

		System.out.println(count + "건 등록되었습니다.(UserDao)");
		return count;
	}

	// 회원정보(1명)가져오기: 로그인
	public UserVo getUser(String id, String password) {
		System.out.println("mysite4/UserDao.getUser(id, pw)");

		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("id", id);
		userMap.put("password", password);

		UserVo user = sqlSession.selectOne("user.getUserLogin", userMap);
		return user;
	}

	// 회원정보(1명)가져오기: 회원정보수정
	public UserVo getUser(int no) {
		System.out.println("mysite4/UserDao.getUser(no)");

		UserVo user = sqlSession.selectOne("user.getUserUpdate", no);
		return user;
	}

	// 수정
	public int update(UserVo userVo) {
		System.out.println("mysite4/UserDao.update()");

		int count = sqlSession.update("user.update", userVo);

		System.out.println(count + "건 수정되었습니다.(UserDao)");
		return count;
	}
}
