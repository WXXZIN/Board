package com.wp.board.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.board.dao.UserDAO;
import com.wp.board.domain.User;

@WebServlet("/admin/um")
public class UserListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String loggedInUser = (String) session.getAttribute("nickname");

        if (!"Admin".equals(loggedInUser)) {
            session.setAttribute("errorMessage", "관리자만 접근 가능합니다.");
            response.sendRedirect("/login");
            return;
        }

        UserDAO dao = (UserDAO) getServletContext().getAttribute("user_dao");

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int pageSize = 10;
        int startIndex = (currentPage - 1) * pageSize;

        int totalUserCount = dao.getTotalCount();
        List<User> userList = dao.getUserList(startIndex, pageSize);

        int totalPages = (totalUserCount / pageSize) + (totalUserCount % pageSize > 0 ? 1 : 0);

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("userList", userList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userManage.jsp");
        requestDispatcher.forward(request, response);
    }
}
