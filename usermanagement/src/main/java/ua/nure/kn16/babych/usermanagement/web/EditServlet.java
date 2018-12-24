package ua.nure.kn16.babych.usermanagement.web;

import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.db.DBException;
import ua.nure.kn16.babych.usermanagement.db.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("okButton") != null){
            doOk(req, resp);
        } else if(req.getParameter("cancelButton") != null){
            doCancel(req, resp);
        } else{
            showPage(req, resp);
        }
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;

        try {
            user = getUser(req);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            showPage(req, resp);
            return;
        }

        try {
            processUser(user);
        } catch (DBException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    protected void processUser(User user) throws DBException {
        DaoFactory.getInstance().getUserDao().update(user);
    }

    private User getUser(HttpServletRequest req) throws ValidationException {
        User user = new User();
        String id = req.getParameter("id");
        String name = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("date");

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        if(id != null) {
            user.setId(new Long(id));
        }


        if (name == null || name.length() == 0) {
            throw new ValidationException("Name is missing");
        }

        if (lastName == null || lastName.length() == 0) {
            throw new ValidationException("Last name is missing");
        }

        if (dateStr == null || dateStr.length() == 0) {
            throw new ValidationException("Date is missing");
        }

        user.setFirstName(name);
        user.setLastName(lastName);

        try {
            user.setDateOfBirth(df.parse(dateStr));
        } catch (ParseException e) {
            throw new ValidationException("Date is in a wrong format");
        }


        return user;
    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse.jsp").forward(req, resp);
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }
}
