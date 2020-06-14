package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete")
public class ServletDelete extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UserService userService = UserService.getInstance();
        userService.deleteUser(id);
        List<User> list = userService.getAllUsers();
        req.setAttribute("list", list);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
