package edu.cmu.team17.servlet;

import edu.cmu.team17.db.DishDao;
import edu.cmu.team17.model.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dev on 12/7/2015.
 */
public class DishCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dish> dishes = DishDao.findByCategory(ServletUtils.getLastString(request.getRequestURI()));
        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(dishes));

    }
}
