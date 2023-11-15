package controller;

import model.AddCommentLogic;
import model.Board;
import model.FindCommentLogic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        FindCommentLogic fcl = new FindCommentLogic();
        List<Board> list = fcl.executeFindComment();

        HttpSession session = request.getSession();
        session.setAttribute("listAttribute", list);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/main.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String comment = request.getParameter("comment");

        Board bo = new Board();
        bo.setName(name);
        bo.setComment(comment);

        AddCommentLogic acl = new AddCommentLogic();
        acl.executeAddComment(bo);

        FindCommentLogic fcl = new FindCommentLogic();
        List<Board> list = fcl.executeFindComment();

        HttpSession session = request.getSession();
        session.setAttribute("listAttribute", list);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main.jsp");
        rd.forward(request, response);
    }
}
