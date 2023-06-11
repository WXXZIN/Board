package com.wp.board.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wp.board.dao.PostDAO;
import com.wp.board.domain.Post;

@WebServlet({"/notice/write", "/free/write", "/notice/edit", "/free/edit"})
public class PostWriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String loggedInUser = (String) session.getAttribute("nickname");

        if (loggedInUser == null) {
            response.sendRedirect("/login");
            return;
        }

        String path = request.getRequestURI();
        String boardType = path.contains("free") ? "free" : "notice";
        String p_id = request.getParameter("p_id");

        if (p_id != null) {
            PostDAO dao = (PostDAO) getServletContext().getAttribute("post_dao");
            Post post = dao.getPost(Long.parseLong(p_id));

            if (post == null) {
                response.sendRedirect("/error");
                return;
            }

            if (!post.getWriter().equals(loggedInUser) && !"Admin".equals(loggedInUser)) {
                session.setAttribute("editMessage", "이 게시글을 수정할 권한이 없습니다.");
                response.sendRedirect("/" + boardType + "/view?p_id=" + p_id);
                return;
            } else {
                request.setAttribute("post", post);
                request.setAttribute("editMode", true);
            }
        } else {
            request.setAttribute("editMode", false);
        }

        request.setAttribute("boardType", boardType);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postForm.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PostDAO dao = (PostDAO) getServletContext().getAttribute("post_dao");

        String path = request.getRequestURI();
        String boardType = path.contains("free") ? "free" : "notice";
        String p_id = request.getParameter("p_id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = request.getParameter("writer");

        HttpSession session = request.getSession();

        if ("notice".equals(boardType) && !"Admin".equals(writer)) {
            session.setAttribute("editMessage", "공지사항 게시판은 관리자만 수정할 수 있습니다.");
            return;
        }

        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            session.setAttribute("writeMessage", "제목과 내용은 필수 입력 항목입니다.");

            if (p_id != null) {
                response.sendRedirect("/" + boardType + "/edit?p_id=" + p_id);
            } else {
                response.sendRedirect("/" + boardType + "/write");
            }
            return;
        }

        LocalDateTime regDate = LocalDateTime.now();

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        post.setBoardType(boardType);
        post.setRegDate(regDate);

        if (p_id != null) {
            post.setP_id(Long.parseLong(p_id));

            int result = dao.editPost(post);

            if (result > 0) {
                response.sendRedirect("/" + boardType);
            } else {
                session.setAttribute("editMessage", "글 수정에 실패했습니다. 다시 시도해주세요.");
                response.sendRedirect("/" + boardType + "/edit?p_id=" + p_id);
            }
        } else {
            int result = dao.writePost(post);

            if (result > 0) {
                response.sendRedirect("/" + boardType);
            } else {
                session.setAttribute("writeMessage", "글 작성에 실패했습니다. 다시 시도해주세요.");
                response.sendRedirect("/" + boardType + "/write");
            }
        }
    }
}
