package com.springboot.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.bean.MyRealm;
import com.springboot.bean.RolesFilter;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Resource
	private MyRealm myRealm;

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager(myRealm);
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(manager);
		Map<String, Filter> filtersMap = new HashMap<String, Filter>();
		RolesFilter myFilter = new RolesFilter();
		filtersMap.put("MyFilter", myFilter);
		factoryBean.setFilters(filtersMap);
		
		Map<String, String> roleMap = new HashMap<>();
		roleMap.put("/user/**", "anon");
		roleMap.put("/main1/**", "MyFilter[role1]");
		roleMap.put("/main2/**", "MyFilter[role2]");
		factoryBean.setFilterChainDefinitionMap(roleMap);
		
		// 配置跳转的登录页面
		factoryBean.setLoginUrl("/user/loginview");
		// 设置未授权提示页面
		factoryBean.setUnauthorizedUrl("/failure");
		return factoryBean;
	}

	@Bean
	public ShiroDialect shiroDialect() {
		ShiroDialect dialect = new ShiroDialect();
		return dialect;
	}
}
