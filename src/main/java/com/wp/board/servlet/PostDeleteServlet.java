package com.wp.board.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.board.dao.PostDAO;
import com.wp.board.domain.Post;

@WebServlet({"/notice/delete", "/free/delete"})
public class PostDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String p_id = request.getParameter("p_id");

        if (p_id != null) {
            PostDAO dao = (PostDAO) getServletContext().getAttribute("post_dao");
            Post post = dao.getPost(Long.parseLong(p_id));

            if (post == null) {
                response.sendRedirect("/error");
                return;
            }

            HttpSession session = request.getSession();
            String loggedInUser = (String) session.getAttribute("nickname");
            String boardType = post.getBoardType();

            if (!post.getWriter().equals(loggedInUser) && !"Admin".equals(loggedInUser)) {
                session.setAttribute("deleteMessage", "이 게시글을 삭제할 권한이 없습니다.");
                response.sendRedirect("/" + boardType + "/view?p_id=" + p_id);
            } else {
                int result = dao.deletePost(Long.parseLong(p_id));

                if (result > 0) {
                    response.sendRedirect("/" + boardType);
                } else {
                    session.setAttribute("deleteMessage", "게시글 삭제에 실패했습니다. 다시 시도해주세요.");
                    response.sendRedirect("/" + boardType + "/view?p_id=" + p_id);
                }
            }
        } else {
            response.sendRedirect("/");
        }
    }
}
