package Servlets.PersonalHomeServlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "Photo_Upload_Servlet", value = "/photo_upload")
public class PhotoUploadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Enumeration<String> enumeration = request.getParameterNames();
        System.out.println(enumeration.toString());

        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
        String url = request.getParameter("file");
        System.out.println(url);
        System.out.println("FDS");
        response.sendRedirect("/JSPs/PersonalHomePages/PersonalPage.jsp");


        // this must be fixed by Luka Samkharadze
    }
}