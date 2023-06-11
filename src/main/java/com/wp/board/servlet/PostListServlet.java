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

@WebServlet({"/notice", "/free"})
public class PostListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO dao = (PostDAO) getServletContext().getAttribute("post_dao");

        String path = request.getRequestURI();
        String boardType = path.contains("free") ? "free" : "notice";

        int currentPage;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } else {
            currentPage = 1;
        }

        String searchType = request.getParameter("searchType");
        String searchKey = request.getParameter("searchKey");

        int pageSize = 5;
        int startIndex = (currentPage - 1) * pageSize;

        int totalPostCount;
        List<Post> postList;

        if (searchType != null && !searchType.isEmpty() && searchKey != null && !searchKey.isEmpty()) {
            totalPostCount = dao.getSearchPostCount(boardType, searchType, searchKey);
            postList = dao.getPostList(startIndex, pageSize, boardType, searchType, searchKey);
        } else {
            totalPostCount = dao.getTotalCount(boardType);
            postList = dao.getPostList(startIndex, pageSize, boardType, "title", "");
        }

        int totalPages = (totalPostCount / pageSize) + (totalPostCount % pageSize > 0 ? 1 : 0);

        request.setAttribute("boardType", boardType);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchKey", searchKey);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("postList", postList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postList.jsp");
        requestDispatcher.forward(request, response);
    }
}
