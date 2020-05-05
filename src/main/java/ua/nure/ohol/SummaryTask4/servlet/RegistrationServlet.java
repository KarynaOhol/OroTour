package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "Registration", urlPatterns ={"/register"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
         String errorMassage;

        String language = MyUtils.getStoredLanguage(request);
        if(language==null){
            language="en";
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings",new Locale(language)) ;

        Connection conn = MyUtils.getStoredConnection(request);


        if(!email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)" +
                "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
            errorMassage="Invalid email";

            request.setAttribute("errorMassage",errorMassage);
            request.setAttribute("errorId","email");
             RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(request, response);
            return;

        }
        if(phone.length()<11 ||phone.length()>15){
            errorMassage=resourceBundle.getString("w.invalid_phone");

            request.setAttribute("errorMassage",errorMassage);
            request.setAttribute("errorId","phone");
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(request, response);
            return;

        }
        if(password.length()<8||password.length()>64 ){
            errorMassage=resourceBundle.getString("w.invalid_password");

            request.setAttribute("errorMassage",errorMassage);
            request.setAttribute("errorId","password");
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(request, response);
            return;

        }
        try {
            if(DBUtils.findUserByLogin(conn,login)!=null){
                errorMassage=resourceBundle.getString("w.invalid_login");
                request.setAttribute("errorMassage",errorMassage);
                request.setAttribute("errorId","login");
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

                dispatcher.forward(request, response);
                return;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(DBUtils.findUserByEmail(conn,email)!=null){
                errorMassage="";resourceBundle.getString("w.invalid_email");
                request.setAttribute("errorMassage",errorMassage);
                request.setAttribute("errorId","email");
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

                dispatcher.forward(request, response);
                return;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if( DBUtils.createNewCustomer(conn,login,password,firstName,lastName,email,phone)){
                response.sendRedirect(request.getContextPath()+"/home");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
