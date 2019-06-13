package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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
	@Resource
	private JavaMailSender sender;

	private Map<String, Object> map = null;

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

	@Async
	public void sendMail(String mail, String code) {
		try {
			System.out.println(mail + code);
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("2770471187@qq.com");
			helper.setTo(mail);
			StringBuffer text = new StringBuffer("【个人网站】");
			text.append("您正在使用邮箱进行校验，效验码：<a href='#'>" + code + "</a>。");
			text.append("有效时间10分钟，超时请重新获取。(如非本人操作，请忽略该信息)");
			text.append("<p style='color:#999999'>该信息为系统自动发件，请勿回复!</p>");
			helper.setSubject("信息验证");
			helper.setText(text.toString(), true);
			sender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
