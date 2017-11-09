package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealRemoveServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(MealRemoveServlet.class);

    public static final MealDao mealDao = MealDaoMemory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            mealDao.removeMeal(Integer.parseInt(id));
        }
        log.debug("send redirect to {}/meals", getServletContext().getContextPath());
        response.sendRedirect(getServletContext().getContextPath() + "/meals");
    }
}
