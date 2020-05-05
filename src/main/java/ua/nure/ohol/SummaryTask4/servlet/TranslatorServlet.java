package ua.nure.ohol.SummaryTask4.servlet;

import ua.nure.ohol.SummaryTask4.db.utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Translator", urlPatterns = {"/translator"})
public class TranslatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentLanguage = MyUtils.getStoredLanguage(req);
        if (currentLanguage == null) {
            currentLanguage = "en";
        } else {
            if (currentLanguage.equals("en")) {
                currentLanguage = "ru";
            } else if (currentLanguage.equals("ru")) {
                currentLanguage = "en";
            }
        }
        MyUtils.storeLanguage(resp, currentLanguage);
        resp.sendRedirect(req.getHeader("referer"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
