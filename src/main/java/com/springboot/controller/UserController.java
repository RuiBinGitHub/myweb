package com.springboot.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		ModelAndView view = new ModelAndView("user/logon");
		/** 验证登录账号 */
		if (isExistName(user.getUsername())) {
			view.addObject("tips", "*账号已经存在，请重新输入！");
			return view;
		}
		/** 验证电子邮箱 */
		if (isExistMail(user.getEmailbox())) {
			view.addObject("tips", "*邮箱已经注册，请重新输入！");
			return view;
		}
		/** 提交数据，完成注册 */
		String date = AppUtils.getDate(null);
		user.setRole("Role2");
		user.setDate(date);
		userBiz.insertUser(user);
		view.setViewName("redirect:/user/completes");
		return view;
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

	/** 退出登录 */
	@RequestMapping(value = "leave", method = RequestMethod.GET)
	public ModelAndView leave() {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/user/login");
		SecurityUtils.getSubject().logout();
		// AppUtils.removeSession("user");
		return view;
	}

	/** 判断账号是否存在 */
	@RequestMapping(value = "/isexitsname", method = RequestMethod.GET)
	public boolean isExistName(String name) {
		map = AppUtils.getMap("username", name);
		if (userBiz.findInfoUser(map) == null)
			return false;
		return true;
	}

	/** 判断邮箱是否存在 */
	@RequestMapping(value = "/isexitsmail", method = RequestMethod.GET)
	public boolean isExistMail(String mail) {
		map = AppUtils.getMap("emailbox", mail);
		if (userBiz.findInfoUser(map) == null)
			return false;
		return true;
	}

	/** 判断账号和邮箱 */
	@RequestMapping(value = "/checknamemail", method = RequestMethod.GET)
	public boolean checkNameMail(String name, String mail) {
		map = AppUtils.getMap("username", name, "emailbox", mail);
		if (userBiz.findInfoUser(map) == null)
			return false;
		else
			return true;
	}

	/** 修改密码 */
	@RequestMapping(value = "/updatepass", method = RequestMethod.POST)
	public boolean updatePass(String oldpass, String newpass) {
		return false;
	}

	/** 修改邮箱 */
	@RequestMapping(value = "/updatemail", method = RequestMethod.POST)
	public boolean updateMail(String mail, String code) {
		return false;
	}

	/** 找回密码 */
	@RequestMapping(value = "/resetpass", method = RequestMethod.POST)
	public ModelAndView resetPass(String username, String password, String emailbox) {
		ModelAndView view = new ModelAndView("user/resetpass");
		map = AppUtils.getMap("username", username, "emailbox", emailbox);
		User user = userBiz.findInfoUser(map);
		if (StringUtils.isEmpty(user)) {
			view.addObject("tips", "*账号和邮箱不匹配！");
			view.addObject("username", username);
			view.addObject("emailbox", emailbox);
			return view;
		}
		view.setViewName("redirect:/success");
		user.setPassword(password);
		userBiz.updateUser(user);
		return view;
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	public String sendMail(String mail) {
		String regs = "\\w+@(\\w+.)+[a-z]{2,3}";
		Pattern pattern = Pattern.compile(regs);
		Matcher matcher = pattern.matcher(mail);
		String code = null;
		if (matcher.matches()) {
			code = 100000 + (int) (Math.random() * 899999) + "";
			userBiz.sendMail(mail, code);
		}
		return code;
	}
}
