package Servlets.DeleteServlet;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.RemoveUser;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Delete_Servlet", value = "/DeleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = (String) request.getSession().getAttribute("username");
        try {
            UserById ubi = new UserById(new BaseConnector());
            int id = ubi.getIdByUsername(userName);
            RemoveUser removeUser = new RemoveUser(new BaseConnector());
            removeUser.removeById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("username", null);
        request.getSession().invalidate();
        response.sendRedirect("/");
    }
}
