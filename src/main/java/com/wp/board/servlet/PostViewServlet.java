package com.wp.board.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.board.dao.PostDAO;
import com.wp.board.domain.Post;

@WebServlet({"/notice/view", "/free/view"})
public class PostViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String p_id = request.getParameter("p_id");

        if (p_id != null) {
            PostDAO dao = (PostDAO) getServletContext().getAttribute("post_dao");
            Post post = dao.getPost(Long.parseLong(p_id));

            if (post == null) {
                response.sendRedirect("/error");
                return;
            }

            request.setAttribute("boardType", post.getBoardType());
            request.setAttribute("post", post);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detailView.jsp");
            requestDispatcher.forward(request, response);
        } else {
           response.sendRedirect("/");
        }
    }
}
