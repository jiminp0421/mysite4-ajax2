package com.javaex.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService { //지금은 비즈니스로직으로만 이해하면돼.
	
	@Autowired 
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("userService.join");
		
		userDao.insert(userVo); //db관련된일
		
		return 1;
		
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("userService.login");
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}
	
	public UserVo modifyGet(int no) {
		System.out.println("/user/modifyget");
		UserVo vo = userDao.selectUser(no);
		return vo;
	}
	
	//회원정보수정
	public int modify(UserVo userVo) {
		System.out.println("userService.modify");
		
		int authUser = userDao.updateUser(userVo);
		
		return authUser;
	}
	
	//아이디체크(ajax)
	public boolean checkId(String id) {
		UserVo userVo = userDao.selectUser2(id);
		boolean result = true;
		
		if(userVo == null) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}

}

