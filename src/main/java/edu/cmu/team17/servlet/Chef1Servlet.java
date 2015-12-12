package edu.cmu.team17.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Dish;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dev on 11/30/15.
 */
public class Chef1Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int chefId = ServletUtils.getId(request.getRequestURI());
        Chef chef = ChefDao.find(chefId);
        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(chef));
    }

}



