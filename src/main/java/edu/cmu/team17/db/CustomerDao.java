package edu.cmu.team17.db;

import edu.cmu.team17.db.Util;
import edu.cmu.team17.model.Customer;

import java.sql.ResultSet;

/**
 * Created by dev on 12/7/2015.
 */
public class CustomerDao {

    private static final String FIND = "SELECT * FROM users as users join images as images on images.id = users.avatarPic where users.username = '%s' and users.password = '%s';";

    public static Customer authenticate(String username, String password) {

        ResultSet resultSet = Util.getResultSet(String.format(FIND, username, password));
        try {

            if (resultSet.next()) {

                Customer customer = new Customer();
                customer.setId(resultSet.getInt("users.id"));
                customer.setAvatarPic(resultSet.getString("images.url"));
                customer.setName(resultSet.getString("users.name"));
                return customer;

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;

    }
}
