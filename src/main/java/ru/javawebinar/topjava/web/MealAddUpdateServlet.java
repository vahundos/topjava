package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemory;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealAddUpdateServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealAddUpdateServlet.class);

    public static final MealDao mealDao = MealDaoMemory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Meal meal = mealDao.getMealById(id);
            request.setAttribute("meal", meal);
        }
        log.debug("forward to /addUpdateMeal.jsp");
        getServletContext().getRequestDispatcher("/addUpdateMeal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dateTimeStr = request.getParameter("dateTime");
        String description = request.getParameter("description");
        String caloriesStr = request.getParameter("calories");
        String idStr = request.getParameter("id");
        if (idStr.isEmpty()) {
            mealDao.addMeal(LocalDateTime.parse(dateTimeStr), description, Integer.parseInt(caloriesStr));
        } else {
            Meal meal = new Meal(Integer.parseInt(idStr), LocalDateTime.parse(dateTimeStr), description,
                    Integer.parseInt(caloriesStr));
            mealDao.updateMeal(meal);
        }
        response.sendRedirect(getServletContext().getContextPath() + "/meals");
    }
}
