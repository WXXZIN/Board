package com.wp.board.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.board.dao.PostDAO;
import com.wp.board.domain.Post;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO dao = (PostDAO) getServletContext().getAttribute("post_dao");

        List<Post> noticeList = dao.getPostList(0, 5, "notice", "title", "");
        request.setAttribute("noticeList", noticeList);

        List<Post> freeList = dao.getPostList(0, 5, "free", "title", "");
        request.setAttribute("freeList", freeList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        requestDispatcher.forward(request, response);
    }
}
