package com.jackie.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jackie.domain.User;
import com.jackie.service.UserServiceI;
/**
 * @webservlet是由servlet3.0提供的注解，目的是将一个继承了httpservlet的普通的java类标注为一个servlet
 * userservlet使用了@webservlet标注后，就不需要在web.xml中配置了
 * @author Administrator
 *
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//处理业务逻辑的userService
	private UserServiceI userServiceI;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取所有的用户信息
		List<User> lstUsers = userServiceI.getAllUser();
		req.setAttribute("lstUsers", lstUsers);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	@Override
	public void init() throws ServletException {
		// 在servlet初始化时获取spring上下文对象（ApplicationContext）
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//从ApplicationContext中获取userService
		userServiceI = (UserServiceI) ac.getBean("userServiceI");
	}
	

}
