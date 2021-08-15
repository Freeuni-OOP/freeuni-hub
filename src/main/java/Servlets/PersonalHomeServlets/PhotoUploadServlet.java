package Servlets.PersonalHomeServlets;


import Manage.ManageUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static StarterManager.Attributes.USER_MANAGER_ATTRIBUTE;

@WebServlet(name = "Photo_Upload_Servlet", value = "/photo_upload")
public class PhotoUploadServlet extends HttpServlet {
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        ManageUser mu = (ManageUser) context.getAttribute(USER_MANAGER_ATTRIBUTE); // get manager

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String base64img = request.getParameter("img");

        try {
            mu.changeProfilePic(username, base64img);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("profilePic", base64img);

        request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
    }
}