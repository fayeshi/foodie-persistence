package edu.cmu.team17.driver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Order;
import edu.cmu.team17.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {

//    public static void main(String[] argv) {
//
//        String selectStatement = "select * from chefs";
//
//        System.out.println("-------- MySQL JDBC Connection Testing ------------");
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Where is your MySQL JDBC Driver?");
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("MySQL JDBC Driver Registered!");
//        Connection connection = null;
//
//        try {
//            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/foodiedb","root", "123456");
//
//
//            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            ArrayList<Chef> chefs = new ArrayList<Chef>();
//
//            while (resultSet.next()){
//
//                // Object Relational Mapping
//
//                Chef chef = new Chef();
//                chef.setId(resultSet.getInt("id"));
//                chef.setName(resultSet.getString("name"));
//                chefs.add(chef);
//
//            }
//
//            for(int i = 0; i<chefs.size(); i++){
//                System.out.println(chefs.get(i));
//            }
//
//
//
//
//
//        } catch (SQLException e) {
//            System.out.println("Connection Failed! Check output console");
//            e.printStackTrace();
//            return;
//        }
//
//        if (connection != null) {
//            System.out.println("You made it, take control your database now!");
//        } else {
//            System.out.println("Failed to make connection!");
//        }
//    }
//
//

    public static void main(String[] args) throws JsonProcessingException {

//        int dishId = 1;
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
//                "and chef.avatarPic = chefImage.id " + "and chef.id = " + 1;
//
//        System.out.print(selectStatement);

        Order order = new Order();
        order.setUserId(101);

        List<OrderItem> items = new ArrayList<OrderItem>();
        order.setItems(items);

        OrderItem item1 = new OrderItem();
        item1.setDishId(1);
        item1.setPrice(5.99F);
        item1.setQuantity(2);

        OrderItem item2 = new OrderItem();
        item2.setDishId(1);
        item2.setPrice(15.99F);
        item2.setQuantity(1);



        items.add(item1);
        items.add(item2);

        System.out.print(new ObjectMapper().writeValueAsString(order));

    }


}
