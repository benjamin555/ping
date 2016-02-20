package com.kpoda.ping;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author 陈嘉镇
* @version 创建时间：2016-2-19 下午11:00:53
*/
public class ChargeSuccessServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4354828611240280894L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/chargeSuccess.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	

}
