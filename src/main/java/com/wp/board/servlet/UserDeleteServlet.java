package com.wp.board.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.wp.board.dao.UserDAO;
import com.wp.board.domain.User;

@WebServlet("/admin/um/delete")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String id = request.getParameter("id");
        String nickname = request.getParameter("nickname");

        if (id != null) {
            UserDAO dao = (UserDAO) getServletContext().getAttribute("user_dao");
            User user = dao.getUserById(id);

            if (user == null) {
                response.sendRedirect("/error");
                return;
            }

            HttpSession session = request.getSession();

            int result = dao.deleteUser(id, nickname);

            if (result > 0) {
                response.sendRedirect("/admin/um");
            } else {
                session.setAttribute("deleteMessage", "사용자 삭제에 실패했습니다. 다시 시도해주세요.");
            }
        } else {
            response.sendRedirect("/");
        }
    }
}
