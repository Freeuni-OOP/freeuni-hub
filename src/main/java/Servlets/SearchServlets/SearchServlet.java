package Servlets.SearchServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.Search;
import Manage.HelperClasses.User;

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
        HttpSession session = request.getSession();
        ArrayList<User> all = new ArrayList<>();
        try {
             all = new Search(new BaseConnector()).searchSimilarUsers(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(username);
        session.setAttribute("searchList",all);
        for(int i=0;i<all.size();i++){
            System.out.println(all.get(i).getUserName());
        }

        request.getRequestDispatcher("/JSPs/SearchPages/Search.jsp").forward(request,response);
    }
}
