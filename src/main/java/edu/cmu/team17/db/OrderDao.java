package edu.cmu.team17.db;

import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Dish;
import edu.cmu.team17.model.Order;
import edu.cmu.team17.model.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by dev on 12/7/15.
 */
public class OrderDao {

    public static final String SELECT_ALL = "select * from orders as orders join order_items as order_items on orders.id = order_items.orderId";

    public static final String SELECT_BY_USER_ID = "select * from orders as orders join order_items as order_items on orders.id = order_items.orderId where orders.customerId = %d";

    public static final String SELECT_BY_ORDER_ID = "select * from orders as orders join order_items as order_items on orders.id = order_items.orderId where orders.id = %d";

    private static List<Order> process(ResultSet resultSet, List<Order> orders ) throws SQLException {
        Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
        while (resultSet.next()) {

            Order tempOrder = null;
            int tempId = resultSet.getInt("orders.id");
            if (orderMap.containsKey(tempId)) {
                tempOrder = orderMap.get(tempId);
            } else {

                tempOrder = new Order();
                tempOrder.setId(tempId);
                tempOrder.setItems(new ArrayList<OrderItem>());

                orders.add(tempOrder);
                orderMap.put(tempId, tempOrder);

            }

            tempOrder.setCreatedAt(resultSet.getTimestamp("orders.createdAt"));
            tempOrder.setUserId(resultSet.getInt("orders.customerId"));

            OrderItem orderItem = new OrderItem();
            orderItem.setId(resultSet.getInt("order_items.id"));
            orderItem.setDishId(resultSet.getInt("order_items.dishId"));
            orderItem.setOrderId(resultSet.getInt("order_items.orderId"));
            orderItem.setPrice(resultSet.getFloat("order_items.price"));
            orderItem.setQuantity(resultSet.getInt("order_items.quantity"));

            Dish dish = DishDao.find(orderItem.getDishId());
            orderItem.setDish(dish);

            tempOrder.getItems().add(orderItem);

        }
        return orders;
    }

    public static List<Order> listAll() {


        ArrayList<Order> orders = new ArrayList<Order>();
        ResultSet resultSet = null;
        try {
            resultSet = Util.getResultSet(SELECT_ALL);
            return process(resultSet, orders);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return orders;
    }

    public static  List<Order> find(int customerId) {

        ArrayList<Order> orders = new ArrayList<Order>();
        ResultSet resultSet = null;
        try {
            resultSet = Util.getResultSet(String.format(SELECT_BY_USER_ID, customerId));
            return process(resultSet, orders);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;

    }

    public static Order findById(int orderId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        ResultSet resultSet = null;
        try {
            resultSet = Util.getResultSet(String.format(SELECT_BY_ORDER_ID, orderId));
            process(resultSet, orders);

            if (orders.size() == 1) {
                return orders.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Order insertOrder(Order order) throws SQLException {

        String insertTableSQL = "INSERT INTO orders"
                + "(customerId) VALUES"
                + "(?)";
        PreparedStatement preparedStatement = Util.getPreparedStatement(insertTableSQL);
        preparedStatement.setInt(1, order.getUserId());

        preparedStatement.executeUpdate();
        ResultSet generatedKeys =  preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int orderId = generatedKeys.getInt(1);

        String insertTableSQL2 = "INSERT INTO order_items"
                + "(orderId, dishId, quantity, price) VALUES"
                + "(?,?,?,?)";

        PreparedStatement preparedStatement2 = Util.getPreparedStatement(insertTableSQL2);


        for (OrderItem orderItem:  order.getItems()) {
            preparedStatement2.setInt(1, orderId);
            preparedStatement2.setInt(2, orderItem.getDishId());
            preparedStatement2.setInt(3, orderItem.getQuantity());
            preparedStatement2.setFloat(4, orderItem.getPrice());
            preparedStatement2.addBatch();
        }

        preparedStatement2.executeBatch();

        return findById(orderId);
    }




}
