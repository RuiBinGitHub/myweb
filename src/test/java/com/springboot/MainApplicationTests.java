package com.springboot;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.biz.UserBiz;
import com.springboot.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

	@Resource
	private UserBiz userBiz;

	@Resource
	private UserController userController;
	
	public Map<String, Object> map = null;

	@Test
	public void contextLoads() {
		System.out.println(userController.sendMail("919732942@qq.com"));
	}

}
