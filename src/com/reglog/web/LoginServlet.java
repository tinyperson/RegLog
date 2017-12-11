package com.reglog.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.reglog.domain.User;
import com.reglog.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 验证码
		String checkcode = request.getParameter("checkcode");

		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session");

		if (checkcode == null || !checkcode.equalsIgnoreCase(checkcode_session)) {
			// 不正确 转发
			request.setAttribute("message", "验证码不正确");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
			return;
		}

		User user = new User();

		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		// 登录
		UserService us = new UserService();

		User loginUser = us.login(user);

		if (loginUser == null) {
			// 不正确
			request.setAttribute("message", "用户名或密码不正确");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			// 正确
			// 用户信息保存到session中
			request.getSession().setAttribute("loginUser", loginUser);
			// cookie保存中文会出错 http status 500
			// 转码URLEncode

			String username_cookies = loginUser.getUsername();
			String encodeName = URLEncoder.encode(username_cookies, "utf-8");

			// 勾选了记住用户名和密码，就保存到cookie
			if ("on".equals(request.getParameter("remember"))) {
				Cookie usernameCookie = new Cookie("username", encodeName);
				usernameCookie.setMaxAge(60 * 60 * 24);
				usernameCookie.setPath("/");
				response.addCookie(usernameCookie);

				Cookie passwordCookie = new Cookie("password",
						loginUser.getPassword());
				passwordCookie.setMaxAge(60 * 60 * 24);
				passwordCookie.setPath("/");
				response.addCookie(passwordCookie);
			}
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
