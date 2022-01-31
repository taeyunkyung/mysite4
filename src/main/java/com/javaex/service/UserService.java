package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserVo login(String id, String password) {
		System.out.println("mysite4/UserService.login()");

		UserVo authUser = userDao.getUser(id, password);
		return authUser;
	}
	
	public boolean availability(String keyword) {
		System.out.println("mysite4/UserService.availability()");
		boolean availability = false;
		
		List<UserVo> usedIdList = userDao.usedIdList(keyword);
		if(usedIdList.isEmpty() == true) {
			availability = true;
		}
		
		return availability;
	}

	public int join(UserVo userVo) {
		System.out.println("mysite4/UserService.join()");

		int count = userDao.insert(userVo);
		System.out.println("(-> UserService)");

		return count;
	}

	public UserVo modifyForm(int no) {
		System.out.println("mysite4/UserService.modifyForm()");

		UserVo userVo = userDao.getUser(no);
		return userVo;
	}

	public int modify(UserVo userVo) {
		System.out.println("mysite4/UserService.modify()");

		int count = userDao.update(userVo);
		System.out.println("(-> UserService)");

		return count;
	}
}
