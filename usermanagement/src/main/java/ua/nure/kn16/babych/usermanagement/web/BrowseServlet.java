package ua.nure.kn16.babych.usermanagement.web;

import ua.nure.kn16.babych.usermanagement.User;
import ua.nure.kn16.babych.usermanagement.db.DBException;
import ua.nure.kn16.babych.usermanagement.db.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class BrowseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("addButton") != null) {
            add(req, resp);
        } else if(req.getParameter("editButton") != null) {
            edit(req, resp);
        } else if(req.getParameter("deleteButton") != null) {
            delete(req, resp);
        } else if(req.getParameter("detailsButton") != null) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null || id.trim().length() == 0) {
            req.setAttribute("error", "You must select a user");
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(id));
            req.getSession().setAttribute("user", user);

        }catch (Exception e){
            req.setAttribute("error", "ERROR: " + e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/details.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null || id.trim().length() == 0) {
            req.setAttribute("error", "You must select a user");
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }

        try {
            DaoFactory.getInstance().getUserDao().delete(new Long(id));
        } catch (Exception e) {
            req.setAttribute("error", "ERROR: " + e.toString());
        }
        browse(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null || id.trim().length() == 0) {
            req.setAttribute("error", "You must select a user");
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(id));
            req.getSession().setAttribute("user", user);

        }catch (Exception e){
            req.setAttribute("error", "ERROR: " + e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DBException e) {
            throw new ServletException(e);
        }
    }
}
