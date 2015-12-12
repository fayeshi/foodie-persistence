package edu.cmu.team17.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.db.CustomerDao;
import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dev on 12/7/2015.
 */
public class AuthenticationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ObjectMapper objectMapper = new ObjectMapper();

        Customer customer = objectMapper.readValue(request.getInputStream(), Customer.class);
//        boolean bool = CustomerDao.authenticate(customer.getUsername(), customer.getPassword());
        Customer customer1 = CustomerDao.authenticate(customer.getUsername(), customer.getPassword());

        if (customer1 == null) {
            response.setStatus(401);
            response.getWriter().print("User not found or password is wrong");
            return;
        }

        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(customer1));



    }

}
