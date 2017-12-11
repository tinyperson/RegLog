package com.reglog.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.reglog.domain.User;
import com.reglog.service.UserService;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkcode = request.getParameter("checkcode");

		String checkcode_session = (String) request.getSession().getAttribute(
				"checkcode_session");

		if (checkcode == null || !checkcode.equalsIgnoreCase(checkcode_session)) {
			// 不正确 转发
			request.setAttribute("message", "验证码不正确");
			request.getRequestDispatcher("/regist.jsp").forward(request,
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

		UserService us = new UserService();

		int result = us.regist(user);

		switch (result) {
		case UserService.USERNAME_EXIST:
			request.setAttribute("message", "用户名已经存在");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			break;
		case UserService.EMAIL_EXIST:
			request.setAttribute("message", "邮箱已经存在");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			break;
		case UserService.SUCCESS:
			// response.sendRedirect("/login.jsp");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			break;

		default:
			break;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
