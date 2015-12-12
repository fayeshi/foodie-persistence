package edu.cmu.team17.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.db.CustomerDao;
import edu.cmu.team17.db.OrderDao;
import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Customer;
import edu.cmu.team17.model.Order;
import edu.cmu.team17.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dev on 12/7/15.
 */
public class OrderServlet extends HttpServlet {

        public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

            List<Order> orders = OrderDao.listAll();
            response.setContentType("application/json");
            response.getWriter().print(ServletUtils.toJson(orders));
        }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        Order order = objectMapper.readValue( request.getInputStream(), Order.class);

        if (order == null) {
            response.setStatus(500);
            response.getWriter().print("order null");
            return;
        }

        if (order.getUserId() <= 0) {
            response.setStatus(500);
            response.getWriter().print("invalid user id");
            return;

        }


        if (order.getItems() == null || order.getItems().isEmpty()) {
            response.setStatus(500);
            response.getWriter().print("order items null");
            return;
        }

        for (OrderItem item : order.getItems()) {

            if (item.getQuantity() <= 0) {
                response.setStatus(500);
                response.getWriter().print("item quantity is less than 0");
                return;
            }

            if (item.getDishId() <= 0) {
                response.setStatus(500);
                response.getWriter().print("wrong dish id");
                return;
            }

            if (item.getPrice() <= 0) {
                response.setStatus(500);
                response.getWriter().print("item price is less than 0");
                return;
            }



        }


        Order order1 = null;
        try {
            order1 = OrderDao.insertOrder(order);

        } catch (SQLException e) {
            e.printStackTrace();

            response.setStatus(500);

        }

        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(order1));


    }

}
