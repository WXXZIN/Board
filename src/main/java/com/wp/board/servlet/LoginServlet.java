package com.wp.board.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.board.dao.UserDAO;
import com.wp.board.domain.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDAO dao = (UserDAO) getServletContext().getAttribute("user_dao");

		String id = request.getParameter("id");
	    String password = request.getParameter("pw");

	    User user = dao.getUserById(id);

		HttpSession session = request.getSession();

		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("nickname", user.getNickname());
			response.sendRedirect("/");
		} else {
			session.setAttribute("loginMessage", "로그인에 실패했습니다. 다시 시도해주세요.");
			response.sendRedirect("/login");
		}
	}
}
