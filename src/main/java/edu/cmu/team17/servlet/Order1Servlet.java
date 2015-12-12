package edu.cmu.team17.servlet;

import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.db.OrderDao;
import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dev on 12/7/15.
 */
public class Order1Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = ServletUtils.getId(request.getRequestURI());
        response.setContentType("application/json");

        if (request.getRequestURI().contains("user")) {
            List<Order> orders = OrderDao.find(id);
            response.getWriter().print(ServletUtils.toJson(orders));
        } else {
            Order order = OrderDao.findById(id);
            response.getWriter().print(ServletUtils.toJson(order));

        }

    }
}
