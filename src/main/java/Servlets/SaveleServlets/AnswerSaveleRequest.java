package Servlets.SaveleServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.LocationAddition;
import Manage.HelperClasses.SaveleList;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Savele_Answer", value = "/answerSaveleRequest")
public class AnswerSaveleRequest extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String receiverName = request.getParameter("username");
        String requesterName = request.getParameter("requesterName");
        //System.out.println(receiverName + " " + requesterName);
        String action = request.getParameter("action");
        if (action.equals("accept")) {
            try {
                UserById userById = new UserById(new BaseConnector());
                int requester_id = userById.getIdByUsername(requesterName);
                int receiver_id = userById.getIdByUsername(receiverName);
                LocationAddition locationAddition = new LocationAddition(new BaseConnector());
                //System.out.println(receiver_id + " vamowmeb " + requester_id);
                locationAddition.changeLocations(requester_id, receiver_id);
                //System.out.println(receiver_id + " vamowmeb " + requester_id);
                locationAddition.removeSimilars(requester_id);
                locationAddition.removeSimilars(receiver_id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                UserById userById = new UserById(new BaseConnector());
                int requester_id = userById.getIdByUsername(requesterName);
                int receiver_id = userById.getIdByUsername(receiverName);
                SaveleList saveleList = new SaveleList(new BaseConnector());
                saveleList.removeRequest(requester_id, receiver_id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("username", receiverName);
        request.getRequestDispatcher("/JSPs/PersonalHomePages/HomePage.jsp").forward(request, response);
    }
}
