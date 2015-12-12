package edu.cmu.team17.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.team17.db.OrderDao;
import edu.cmu.team17.db.ReviewDao;
import edu.cmu.team17.model.Order;
import edu.cmu.team17.model.Review;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dev on 12/10/15.
 */
public class ReviewServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Review review = objectMapper.readValue( request.getInputStream(), Review.class);

        if (review.getReviewText() == null) {
            response.setStatus(500);
            response.getWriter().print("Review text null");
            return;
        }


        if (review.getStarRating() < 0.5) {
            response.setStatus(500);
            response.getWriter().print("Star rating must be greater than 0.5");
            return;
        }


        if (review.getDishId() <= 0) {
            response.setStatus(500);
            response.getWriter().print("Bad dish id" );
            return;
        }


        if (review.getCustomerId() <= 0) {
            response.setStatus(500);
            response.getWriter().print("bad customer id");
            return;
        }


        Review review1 = null;
        try {
            review1 = ReviewDao.insert(review);

        } catch (SQLException e) {
            e.printStackTrace();

            response.setStatus(500);

        }

        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(review1));


    }
}
