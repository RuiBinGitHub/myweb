package com.springboot.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springboot.biz.UserBiz;
import com.springboot.entity.User;

@Component(value = "myRealm")
public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserBiz userBiz;

	/** 执行授权逻辑 */
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) collection.getPrimaryPrincipal();
		info.addRole(user.getRole());
		return info;
	}

	/** 执行认证逻辑 */
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		UsernamePasswordToken tempToken = (UsernamePasswordToken) token;
		String username = tempToken.getUsername();
		String password = new String((char[]) tempToken.getCredentials());
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		User user = userBiz.findInfoUser(map);
		if (StringUtils.isEmpty(user))
			throw new IncorrectCredentialsException();
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, password, "");
		return info;
	}

}
