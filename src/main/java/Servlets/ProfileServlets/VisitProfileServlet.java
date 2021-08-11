package Servlets.ProfileServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.BlockUser;
import Manage.HelperClasses.FriendAddition;
import Manage.HelperClasses.User;
import Manage.HelperClasses.UserById;
import Manage.ManageUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "Visit_Profile_Servlet", value = "/visitProfile")
public class VisitProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        String profileName = request.getParameter("profileName");
        session.setAttribute("username",userName);
        session.setAttribute("profileName",profileName);
        System.out.println(userName +" "+  profileName);
        boolean isBlocked = false, isFriend = false;
        int user_id=0;
        int profile_id=0;
        User profileUser = null;
        String profileMail = "";
        int courseNum=0;
        try {
            UserById userById = new UserById(new BaseConnector());
            user_id = userById.getIdByUsername(userName);
            profile_id = userById.getIdByUsername(profileName);
            BlockUser blockUser = new BlockUser(new BaseConnector());
            isBlocked = blockUser.isBlocked(user_id,profile_id);
            FriendAddition friendAddition = new FriendAddition(new BaseConnector());
            isFriend = friendAddition.isFriend(user_id,profile_id);
            profileUser = userById.getUser(profile_id);
            ManageUser manageUser = new ManageUser(new BaseConnector());
            ArrayList<String > userInfo = manageUser.getUserInfo(profile_id);
            profileMail = userInfo.get(4);
            if (userInfo.get(5) == null)
                session.setAttribute("profileFaculty", "არაა მითითებული");
            else session.setAttribute("profileFaculty", userInfo.get(5));

            if (userInfo.get(6) == null)
                session.setAttribute("profileCourse", "არაა მითითებული");
            else session.setAttribute("profileCourse", userInfo.get(6));

            if (userInfo.get(7) == null)
                session.setAttribute("profileSex", "თავს შევიკავებ");
            else {
                String sex = userInfo.get(7);
                switch (sex) {
                    case "no": session.setAttribute("profileSex", "თავს შევიკავებ"); break;
                    case "male": session.setAttribute("profileSex", "მამრობითი"); break;
                    case "female": session.setAttribute("profileSex", "მდედრობითი"); break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        session.setAttribute("profileFirstName",profileUser.getUserFirstName());
        session.setAttribute("profileLastName",profileUser.getUserLastName());
        session.setAttribute("profileMail",profileMail);

        if(isBlocked) {
            request.getRequestDispatcher("/JSPs/ProfilePages/blockedProfile.jsp").forward(request, response);
        }else if(isFriend){
            request.getRequestDispatcher("/JSPs/ProfilePages/friendProfile.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/JSPs/ProfilePages/unFriendProfile.jsp").forward(request,response);
        }
    }
}
