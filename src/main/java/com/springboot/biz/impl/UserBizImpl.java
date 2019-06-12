package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.springboot.biz.UserBiz;
import com.springboot.dao.UserDao;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@Service
@Transactional
public class UserBizImpl implements UserBiz {

	@Resource
	private UserDao userDao;
	Map<String, Object> map = null;

	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public User findInfoUser(int id) {
		map = AppUtils.getMap("id", id);
		return userDao.findInfoUser(map);
	}

	public User findInfoUser(Map<String, Object> map) {
		return userDao.findInfoUser(map);
	}

	public List<User> findListUser(Map<String, Object> map) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		if (!StringUtils.isEmpty(map.get("page")))
			map.put("page", ((int) map.get("page") - 1) * 15);
		return userDao.findListUser(map);
	}

	public int getPages(Map<String, Object> map, int size) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		int count = userDao.getCount(map);
		return (int) Math.ceil((double) count / size);
	}

}
