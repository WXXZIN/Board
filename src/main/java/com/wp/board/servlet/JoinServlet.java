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

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/join.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDAO dao = (UserDAO) getServletContext().getAttribute("user_dao");

		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		String nickname = request.getParameter("nickname");

		User user = new User();
	    user.setId(id);
	    user.setNickname(nickname);

		boolean isIdExist = dao.isIdExist(id);
		boolean isNicknameExist = dao.isNicknameExist(nickname);

		HttpSession session = request.getSession();

		if (isIdExist) {
			session.setAttribute("joinMessage", "이미 사용 중인 아이디입니다. 다른 아이디를 선택해주세요.");
			response.sendRedirect("/join");
		} else if (isNicknameExist) {
			session.setAttribute("joinMessage", "이미 사용 중인 닉네임입니다. 다른 닉네임을 선택해주세요.");
			response.sendRedirect("/join");
		} else {
			user.setPassword(password);

			int result = dao.registerUser(user);

			if (result > 0) {
				response.sendRedirect("/login");
			} else {
				session.setAttribute("joinMessage", "회원가입에 실패했습니다. 다시 시도해주세요.");
				response.sendRedirect("/join");
			}
		}
	}
}
