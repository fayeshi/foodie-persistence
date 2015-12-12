package edu.cmu.team17.db;

import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Customer;
import edu.cmu.team17.model.Dish;
import edu.cmu.team17.model.Review;
import org.apache.commons.lang3.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dev on 12/7/2015.
 */
public class ReviewDao {

    private static final String SELECT_BY_CHEF_ID = "select * from reviews as reviews1 join users as customer join images as customerImage on customer.avatarPic = customerImage.id join dishes as dishes1 join images as dishImage on dishes1.dishPic = dishImage.id join users as chef join images as chefImage on chef.avatarPic = chefImage.id where reviews1.customerId = customer.id and reviews1.dishId = dishes1.id and dishes1.chefId = chef.id and chef.id = %d";

    private static final String SELECT_BY_DISH_ID = "select * from reviews as reviews1 join users as customer join images as customerImage on customer.avatarPic = customerImage.id join dishes as dishes1 join images as dishImage on dishes1.dishPic = dishImage.id join users as chef join images as chefImage on chef.avatarPic = chefImage.id where reviews1.customerId = customer.id and reviews1.dishId = dishes1.id and dishes1.chefId = chef.id and dishes1.id = %d";

    public static List<Review> listAllByChefId(int chefId) {
        ArrayList<Review> reviews = new ArrayList<Review>();

        try {
            ResultSet resultSet = Util.getResultSet(String.format(SELECT_BY_CHEF_ID, chefId));
            return commonProcess(resultSet, reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public static List<Review> listAllByDishId(int dishId) {
        ArrayList<Review> reviews = new ArrayList<Review>();

        try {
            ResultSet resultSet = Util.getResultSet(String.format(SELECT_BY_DISH_ID, dishId));
            return commonProcess(resultSet, reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public static Review insert(Review review) throws SQLException {


        String insertTableSQL = "INSERT INTO reviews"
                + "(starRating, reviewText, dishId, customerId) VALUES"
                + "(?, ?, ?, ?)";
        PreparedStatement preparedStatement = Util.getPreparedStatement(insertTableSQL);
        preparedStatement.setFloat(1, review.getStarRating());
        preparedStatement.setString(2, review.getReviewText());
        preparedStatement.setInt(3, review.getDishId());
        preparedStatement.setInt(4, review.getCustomerId());


        preparedStatement.executeUpdate();
        ResultSet generatedKeys =  preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int reviewId = generatedKeys.getInt(1);

        review.setId(reviewId);
        return review;

    }

    private static List<Review> commonProcess(ResultSet resultSet, ArrayList<Review> reviews) throws SQLException {


        while (resultSet.next()) {

            try {
                Chef chef = new Chef();
                chef.setId(resultSet.getInt("chef.id"));
                chef.setAvatarPic(resultSet.getString("chefImage.url"));
                chef.setName(resultSet.getString("chef.name"));


                Dish dish = new Dish();
                dish.setId(resultSet.getInt("dishes1.id"));
                dish.setName(resultSet.getString("dishes1.name"));
                dish.setDishPic(resultSet.getString("dishImage.url"));
                dish.setPrice(resultSet.getFloat("dishes1.price"));
                dish.setStock(resultSet.getInt("dishes1.stock"));
                // dish.setChef(chef);

                Customer customer = new Customer();
                customer.setId(resultSet.getInt("customer.id"));
                customer.setAvatarPic(resultSet.getString("customerImage.url"));
                customer.setName(resultSet.getString("customer.name"));


                Review review = new Review();
                review.setId(resultSet.getInt("reviews1.id"));
                review.setReviewText(resultSet.getString("reviews1.reviewText"));
                review.setStarRating(resultSet.getInt("reviews1.starRating"));
                review.setUpdatedAt(resultSet.getTimestamp("reviews1.updatedAt"));
                review.setDishId(resultSet.getInt("reviews1.dishId"));
                review.setCustomerId(resultSet.getInt("reviews1.customerId"));

                review.setCustomer(customer);
                review.setDish(dish);
                review.setChef(chef);

                reviews.add(review);

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Object Relational Mapping

        }

        return reviews;

    }

}
