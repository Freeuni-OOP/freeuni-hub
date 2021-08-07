package Servlets.PersonalHomeServlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Profile_Update", value = "/update")
public class ProfileUpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // get info
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        // to complete later

        response.sendRedirect("/JSPs/PersonalHomePages/PersonalPage.jsp");
    }
}