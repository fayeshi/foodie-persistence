package edu.cmu.team17.servlet;

import edu.cmu.team17.db.ChefDao;
import edu.cmu.team17.db.DishDao;
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
 * Created by dev on 12/1/15.
 */
public class DishServlet extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Dish> dishes = DishDao.listAll();
        response.setContentType("application/json");
        response.getWriter().print(ServletUtils.toJson(dishes));

//        String selectStatement = "select * from dishes as dishes1 join images as images1 join images as images2 join users as users1 \n" +
//                "where dishes1.dishPic = images1.id and dishes1.chefId = users1.id and users1.avatarPic = images2.id;";

//
//        String selectStatement = "select dishes1.*, images1.*, users1.*,  images2.*   , GROUP_CONCAT(tags.name SEPARATOR ',') as tags\n" +
//                "from dishes as dishes1 \n" +
//                "join images as images1 \n" +
//                "join users as users1 \n" +
//                "join images as images2 \n" +
//                "join tags as tags\n" +
//                "join tags_dishes as tags_dishes\n" +
//                "where dishes1.dishPic = images1.id \n" +
//                "and dishes1.chefId = users1.id \n" +
//                "and users1.avatarPic = images2.id\n" +
//                "and dishes1.id = tags_dishes.dishId\n" +
//                "and tags.id = tags_dishes.tagId\n" +
//                "group by dishes1.id";
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
//            ArrayList<Dish> dishes = new ArrayList<Dish>();
//            ArrayList<Chef> chefes = new ArrayList<Chef>();
//
//            while (resultSet.next()){
//
//                // Object Relational Mapping
//
//                Chef chef = new Chef();
//                chef.setId(resultSet.getInt("users1.id"));
//                chef.setAvatarPic(resultSet.getString("images2.url"));
//                chef.setName(resultSet.getString("users1.name"));
//
//                Dish dish = new Dish();
//                dish.setId(resultSet.getInt("dishes1.id"));
//                dish.setName(resultSet.getString("dishes1.name"));
//                dish.setDishPic(resultSet.getString("images1.url"));
//                dish.setPrice(resultSet.getFloat("dishes1.price"));
//                dish.setStock(resultSet.getInt("dishes1.stock"));
//                dish.setChef(chef);
//
//                String tags = resultSet.getString("tags");
//                if (tags != null || !tags.isEmpty())
//                {
//                    dish.setTags(Arrays.asList(tags.split(",")));
//                }
//
//                dishes.add(dish);
//            }
//            response.setContentType("application/json");
//            out.print(ServletUtils.toJson(dishes));
//
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }
//
    }

}
