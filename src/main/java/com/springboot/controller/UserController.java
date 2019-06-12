package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.UserBiz;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	private UserBiz userBiz;

	private Map<String, Object> map = null;

	/** 用户注册 */
	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	public ModelAndView logon(User user) {

		return null;
	}

	/** 用户登录 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ServletRequest request, String username, String password) {
		try {
			ModelAndView view = new ModelAndView("success");
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			SecurityUtils.getSubject().login(token);
			SavedRequest path = WebUtils.getSavedRequest(request);
			if (!StringUtils.isEmpty(path))
				view.setViewName("redirect:" + path.getRequestUrl());
			return view;
		} catch (IncorrectCredentialsException e) {
			ModelAndView view = new ModelAndView("user/login");
			return view;
		}
	}

	@RequestMapping(value = "/isexitsname")
	public boolean isExistName(String name) {
		map = AppUtils.getMap("username", name);
		if (userBiz.findInfoUser(map) == null)
			return false;
		return true;
	}

	@RequestMapping(value = "/isexitsmail")
	public boolean isExistMail(String mail) {
		map = AppUtils.getMap("emailbox", mail);
		if (userBiz.findInfoUser(map) == null)
			return false;
		return true;
	}

	@RequestMapping(value = "/updatepass", method = RequestMethod.POST)
	public boolean updatePass(String oldpass, String newpass) {
		return false;
	}

	@RequestMapping(value = "/updatemail", method = RequestMethod.POST)
	public boolean updateMail(String mail, String code) {
		return false;
	}
}
