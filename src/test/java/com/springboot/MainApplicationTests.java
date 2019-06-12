package com.springboot;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.biz.UserBiz;
import com.springboot.util.AppUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

	@Resource
	private UserBiz userBiz;

	private Map<String, Object> map = null;

	@Test
	public void contextLoads() {
		map = AppUtils.getMap("name", null);
		int pages = userBiz.getPages(map, 2);
		System.out.println(pages);
	}

}
