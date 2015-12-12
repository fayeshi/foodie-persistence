package edu.cmu.team17.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.db.ReviewDao;
import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Customer;
import edu.cmu.team17.model.Dish;
import edu.cmu.team17.model.Review;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 12/1/15.
 */
public class ReviewByChefServlet extends HttpServlet {
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int chefId = ServletUtils.getId(request.getRequestURI());
        List<Review> reviews = ReviewDao.listAllByChefId(chefId);
        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(reviews));


//        PrintWriter out = response.getWriter();
//
//        int chefId = ServletUtils.getId(request.getRequestURI());
//
//        String selectStatement = "select * \n" +
//                "from reviews as reviews1  \n" +
//                "join users as customer  \n" +
//                "join images as customerImage    \n" +
//                "join dishes as dishes1  \n" +
//                "join images as dishImage  \n" +
//                "join users as chef \n" +
//                "join images as chefImage   \n" +
//                "where reviews1.customerId = customer.id \n" +
//                "and customer.avatarPic = customerImage.id    \n" +
//                "and reviews1.dishId = dishes1.id  \n" +
//                "and dishes1.dishPic = dishImage.id  \n" +
//                "and dishes1.chefId = chef.id  \n" +
//                "and chef.avatarPic = chefImage.id " + "and chef.id = " + chefId;
//
//
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        Connection connection = null;
//
//        try {
//            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/foodiedb", "root", "123456");
//
//
//            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            ArrayList<Review> reviews = new ArrayList<Review>();
//
//
//
//            while (resultSet.next()){
//
//                // Object Relational Mapping
//
//                Chef chef = new Chef();
//                chef.setId(resultSet.getInt("chef.id"));
//                chef.setAvatarPic(resultSet.getString("chefImage.url"));
//                chef.setName(resultSet.getString("chef.name"));
//
//
//
//                Dish dish = new Dish();
//                dish.setId(resultSet.getInt("dishes1.id"));
//                dish.setName(resultSet.getString("dishes1.name"));
//                dish.setDishPic(resultSet.getString("dishImage.url"));
//                dish.setPrice(resultSet.getFloat("dishes1.price"));
//                dish.setStock(resultSet.getInt("dishes1.stock"));
//               // dish.setChef(chef);
//
//                Customer customer = new Customer();
//                customer.setId(resultSet.getInt("customer.id"));
//                customer.setAvatarPic("customer.url");
//                customer.setName(resultSet.getString("customer.name"));
//
//
//
//
//                Review review = new Review();
//                review.setId(resultSet.getInt("reviews1.id"));
//                review.setReviewText(resultSet.getString("reviews1.reviewText"));
//                review.setStarRating(resultSet.getInt("reviews1.starRating"));
//                review.setCustomer(customer);
//                review.setDish(dish);
//                review.setChef(chef);
//
//                reviews.add(review);
//            }
//
//
//            out.print(new ObjectMapper().writeValueAsString(reviews));
//            response.setContentType("application/json");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }

    }

}
