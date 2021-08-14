package Servlets.SaveleServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.SaveleList;
import Manage.HelperClasses.User;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Savele_Request_Servlet", value = "/SaveleRequests")
public class SaveleRequestServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        int id = -1;
        List<User> requesters = new ArrayList<>();
        try {
            UserById userById = new UserById(new BaseConnector());
            id = userById.getIdByUsername(username);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            SaveleList saveleList = new SaveleList(new BaseConnector());
            requesters = saveleList.getSaveleRequestersList(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("requesters", requesters);
        session.setAttribute("username", username);

        req.getRequestDispatcher("JSPs/SavelePages/SaveleRequests.jsp").forward(req, resp);
    }
}
