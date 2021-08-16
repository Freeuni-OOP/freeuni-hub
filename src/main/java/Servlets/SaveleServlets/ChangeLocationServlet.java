package Servlets.SaveleServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.LocationAddition;
import Manage.HelperClasses.SaveleList;
import Manage.HelperClasses.UserById;
import Manage.ManageUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static StarterManager.Attributes.USER_MANAGER_ATTRIBUTE;

@WebServlet(name = "Change_Location_Servlet", value = "/changeLocation")
public class ChangeLocationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        String profileName = (String) req.getSession().getAttribute("profileName");
        try {
            LocationAddition locationAddition = new LocationAddition(new BaseConnector());
            UserById userById = new UserById(new BaseConnector());
            int requester_id = userById.getIdByUsername(username);
            int receiver_id = userById.getIdByUsername(profileName);
            System.out.println("aqamde");
            if (locationAddition.alreadyRegistered(requester_id) &&
                    locationAddition.alreadyRegistered(receiver_id)) {
                System.out.println("mtavaria aqamde");
                SaveleList saveleList = new SaveleList(new BaseConnector());
                saveleList.addRequest(requester_id, receiver_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE); // get manager
        req.setAttribute("profilePic", um.getProfilePic(username));
        req.getRequestDispatcher("JSPs/PersonalHomePages/HomePage.jsp").forward(req, resp);
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
