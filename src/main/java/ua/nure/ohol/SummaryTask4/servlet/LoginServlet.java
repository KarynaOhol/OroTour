package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.beans.Users;
import ua.nure.ohol.SummaryTask4.db.utils.DBUtils;
import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // Показать страницу Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String language = MyUtils.getStoredLanguage(request);
        if (language == null) {
            language = "en";
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings", new Locale(language));
        request.setAttribute("errorString", resourceBundle.getString("w.login"));

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("remember");
        boolean remember = "Y".equals(rememberMeStr);

        String language = MyUtils.getStoredLanguage(request);
        if (language == null) {
            language = "en";
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("warnings", new Locale(language));
        request.setAttribute("errorString", resourceBundle.getString("w.login"));

        Users user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = resourceBundle.getString("w.login_password");
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Найти user в DB.
                user = DBUtils.findUserByLoginPassword(conn, userName, password);

                if (user == null) {
                    hasError = true;
                    errorString = resourceBundle.getString("w.invalid");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (!hasError && !user.isValidUser()) {
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/blockedUserView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        // В случае, если есть ошибка,
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {
            user = new Users();
            user.setLogin(userName);
            user.setPassword(password);

            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward (перенаправить) к странице /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице userInfo.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            // Если пользователь выбирает функцию "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Наоборот, удалить Cookie
            else {
                MyUtils.deleteUserCookie(response);
            }

            int redirectId;
            String loginUrl = null;
            try{
                redirectId = (int) session.getAttribute("redirectId");
                loginUrl = MyUtils.getRedirectAfterLoginUrl(session, redirectId);
            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }

            if(loginUrl != null){
                response.sendRedirect(loginUrl);
            } else {

                // Redirect (Перенаправить) на страницу /userInfo.
                response.sendRedirect(request.getContextPath() + "/userInfo");
            }
        }
    }
}
