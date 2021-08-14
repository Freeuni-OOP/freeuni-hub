package Servlets.PersonalHomeServlets;


import DataBaseConnection.BaseConnector;
import Manage.Configurations.UserConfiguration;
import Manage.HelperClasses.LocationAddition;
import Manage.HelperClasses.LocationID;
import Manage.HelperClasses.UserById;
import Manage.ManageUser;
import StarterManager.Attributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "Profile_Update", value = "/update")
public class ProfileUpdateServlet extends HttpServlet implements Attributes, UserConfiguration {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession(false); // get session
        String username = (String) session.getAttribute("username"); // get username

        BaseConnector bc = (BaseConnector) request.getServletContext().getAttribute(BASE_CONNECTOR_ATTRIBUTE);
        UserById ubi = new UserById(bc);

        int user_id = -1;
        try {
            user_id = ubi.getIdByUsername(username); // get user id
        } catch (SQLException ignored) {
        }


        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE); // get manager
        ArrayList<String> info = new ArrayList<>(); // this is final user info to check
        try {
            info = um.getUserInfo(user_id);
        } catch (SQLException ignored) {
        }

        String curPassword = info.get(3); // get current password to check later


        // get information
        String newUsername = request.getParameter("user_name");
        String sex = request.getParameter("sex");
        String saveleLocation = request.getParameter("saveleLocation");
        String faculty = request.getParameter("faculty");

        int course = -1;
        String possibleCourse = (request.getParameter("course"));
        switch (possibleCourse) {
            case "I":
                course = 1;
                break;
            case "II":
                course = 2;
                break;
            case "III":
                course = 3;
                break;
            case "IV":
                course = 4;
                break;
            default:
                course = 5;
                break;
        }

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatedPassword = request.getParameter("repeatedPassword");


        //-----------------------------------------------------------------------different cases
        for (int i = 0; i < faculty.length(); i++) { // check faculty
            char cur = faculty.charAt(i);
            if ((!Character.isLetter(cur)) && (cur != ' ')) {
                session.setAttribute("problems", ILLEGAL_FACULTY);
                request.getRequestDispatcher("/JSPs/PersonalHomePages/InvalidProfileUpdate.jsp").forward(request, response);
                return;
            }
        }

        if (!oldPassword.equals(curPassword)) { // old password field must be correct
            session.setAttribute("problems", "ეს არ არის თქვენი ძველი პაროლი, გთხოვთ ხელახლა შეიყვანოთ.");
            request.getRequestDispatcher("/JSPs/PersonalHomePages/InvalidProfileUpdate.jsp").forward(request, response);
            return;
        }

        if (oldPassword.equals(newPassword)) { // old and new mustn't match
            session.setAttribute("problems", "ძველი და ახალი პაროლები არ უნდა ემთხვეოდეს, კიდევ ერთხელ წაიკითხეთ პაროლის მოთხოვნები.");
            request.getRequestDispatcher("/JSPs/PersonalHomePages/InvalidProfileUpdate.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(repeatedPassword)) { // new and repeated passwords must match
            session.setAttribute("problems", "განმეორებული პაროლი არ ემთხვევა ახალს, გთხოვთ მეტი ყურადღებით შეავსოთ.");
            request.getRequestDispatcher("/JSPs/PersonalHomePages/InvalidProfileUpdate.jsp").forward(request, response);
            return;
        }


        //-----------------------------------------------------------------------------------------


        String message = "";
        try {
            message = um.isValidInput(info.get(0), info.get(1), newUsername, newPassword, info.get(4));
            if (message.equals(ALL_GOOD)) {
                um.updateUser(user_id, newUsername, sex, faculty, course, newPassword);
                if (saveleLocation != null && !saveleLocation.equals("")) {
                    try {
                        LocationID locationID = new LocationID(new BaseConnector());
                        int location_id = locationID.getIdByLocation(saveleLocation);
                        UserById userById = new UserById(new BaseConnector());
                        int id = userById.getIdByUsername(newUsername);
                        LocationAddition locationAddition = new LocationAddition(new BaseConnector());
                        if (locationAddition.alreadyRegistered(id)) {
                            locationAddition.updateLocationId(id, location_id);
                        } else {
                            locationAddition.addIdInLocation(id, location_id);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
            } else {
                if ((message.equals(USERNAME_EXISTS) && (newUsername.equals(username)))
                        // if username isn't changed, that's ok
                        || (message.equals(MAIL_EXISTS))) { // mail isn't problem here
                    // ok this isn't problem
                    um.updateUser(user_id, newUsername, sex, faculty, course, newPassword);
                    // set attributes
                    if (saveleLocation != null && !saveleLocation.equals("")) {
                        try {
                            LocationID locationID = new LocationID(new BaseConnector());
                            int location_id = locationID.getIdByLocation(saveleLocation);
                            UserById userById = new UserById(new BaseConnector());
                            int id = userById.getIdByUsername(newUsername);
                            LocationAddition locationAddition = new LocationAddition(new BaseConnector());
                            if (locationAddition.alreadyRegistered(id)) {
                                locationAddition.updateLocationId(id, location_id);
                            } else {
                                locationAddition.addIdInLocation(id, location_id);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    session.setAttribute("username", newUsername);
                    session.setAttribute("faculty", faculty);
                    switch (course) { // int -> string for course number
                        case 1:
                            session.setAttribute("course", "I");
                            break;
                        case 2:
                            session.setAttribute("course", "II");
                            break;
                        case 3:
                            session.setAttribute("course", "III");
                            break;
                        case 4:
                            session.setAttribute("course", "IV");
                            break;
                        case 5:
                            session.setAttribute("course", "IV+");
                            break;
                        default:
                            session.setAttribute("course", "არაა მითითებული");
                            break;
                    }


                    switch (sex) {
                        case "secret":
                            session.setAttribute("sex", "თავს შევიკავებ");
                            break;
                        case "male":
                            session.setAttribute("sex", "მამრობითი");
                            break;
                        case "female":
                            session.setAttribute("sex", "მდედრობითი");
                            break;
                    }
                    switch (saveleLocation) {
                        case "Qvabisxevi2":
                            session.setAttribute("saveleLocation", "ქვაბისხევი2");
                            break;
                        case "Qvabisxevi3":
                            session.setAttribute("saveleLocation", "ქვაბისხევი3");
                            break;
                        case "Baxmaro2":
                            session.setAttribute("saveleLocation", "ბახმარო2");
                            break;
                        case "Baxmaro3":
                            session.setAttribute("saveleLocation", "ბახმარო3");
                            break;
                        case "Fari2":
                            session.setAttribute("saveleLocation", "ფარი2");
                            break;
                        case "Fari3":
                            session.setAttribute("saveleLocation", "ფარი3");
                            break;
                    }
                    request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
                } else {
                    session.setAttribute("problems", message);
                    request.getRequestDispatcher("/JSPs/PersonalHomePages/InvalidProfileUpdate.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}