package edu.cmu.team17.servlet;

import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.model.Chef;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dev on 11/29/15.
 */
public class ChefServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Chef> chefs = ChefDao.listAll();
        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(chefs));
    }

}

