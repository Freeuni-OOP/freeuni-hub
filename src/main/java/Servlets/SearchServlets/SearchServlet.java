package Servlets.SearchServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.Search;
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

@WebServlet(name = "Search_Servlet", value = "/Search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username= request.getParameter("username");
        String curUser = request.getParameter("curUser");
        int id=0;
        try {
            UserById ubi = new UserById(new BaseConnector());
            id=ubi.getIdByUsername(curUser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        ArrayList<User> all = new ArrayList<>();
        System.out.println(id);
        try {
             all = new Search(new BaseConnector()).searchSimilarUsers(username,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("searchList",all);
        session.setAttribute("username",curUser);
        request.getRequestDispatcher("/JSPs/SearchPages/Search.jsp").forward(request,response);
    }
}
