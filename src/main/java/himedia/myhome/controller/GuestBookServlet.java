package himedia.myhome.controller;

import java.io.IOException;
import java.util.List;

import himedia.myhome.dao.GuestBookDao;
import himedia.myhome.vo.GuestBookVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Guestbook", urlPatterns = "/gb")
public class GuestBookServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("a");
        if ("deleteform".equals(actionName)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
            rd.forward(req, resp);
        } else {
            GuestBookDao dao = new GuestBookDao(dbuser, dbpass);
            List<GuestBookVo> list = dao.getlist();
            req.setAttribute("list", list);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("a");

        if ("add".equals(actionName)) {
            String name = req.getParameter("name");
            String password = req.getParameter("pass");
            String content = req.getParameter("content");

            GuestBookVo vo = new GuestBookVo();
            vo.setName(name);
            vo.setPassword(password);
            vo.setContent(content);
            GuestBookDao dao = new GuestBookDao(dbuser, dbpass);
            dao.insert(name, password, content);
            resp.sendRedirect(req.getContextPath() + "/gb");
        } else if ("delete".equals(actionName)) {
            int no = Integer.parseInt(req.getParameter("no"));
            String password = req.getParameter("pass");
            GuestBookDao dao = new GuestBookDao(dbuser, dbpass);
            boolean success = dao.delete(no, password);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/gb");
            } else {
                req.setAttribute("error", "비밀번호가 일치하지 않습니다.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
                rd.forward(req, resp);
            }
        }
    }
}
