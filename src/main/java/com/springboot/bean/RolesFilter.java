package com.springboot.bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RolesFilter extends AuthorizationFilter {

	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mapper) {
		// 当前请求需要的权限
		Subject subject = this.getSubject(request, response);
		// 当前用户包含的权限
		String[] roles = (String[]) mapper;
		if (roles == null || roles.length == 0)
			return true;
		for (int i = 0; i < roles.length; i++)
			if (subject.hasRole(roles[i]))
				return true;
		return false;
	}

}
