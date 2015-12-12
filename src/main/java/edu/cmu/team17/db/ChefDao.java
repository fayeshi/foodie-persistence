package edu.cmu.team17.db;

import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Dish;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by dev on 12/7/2015.
 */
public class ChefDao {

    // public static final String SELECT_ALL = "SELECT chefs.*, images.*, COUNT(DISTINCT(dishes.id)) as numOfDishes, GROUP_CONCAT(DISTINCT(tags.name) SEPARATOR ',') as tags FROM users as chefs JOIN images as images on chefs.avatarPic = images.id JOIN dishes as dishes on dishes.chefId = chefs.id JOIN tags_dishes as tags_dishes on dishes.id = tags_dishes.dishId JOIN tags as tags on tags.id = tags_dishes.tagId WHERE chefs.type = 'CHEF' GROUP BY chefs.id";

    public static final String SELECT_ALL = "SELECT chefs.*, images.* FROM users as chefs LEFT JOIN images as images on chefs.avatarPic = images.id WHERE chefs.type = 'CHEF'";

    public static final String SELECT_ONE = "SELECT chefs.*, images.* FROM users as chefs LEFT JOIN images as images on chefs.avatarPic = images.id WHERE chefs.type = 'CHEF' and chefs.id = %d";

    public static List<Chef> listAll() {

        ArrayList<Chef> chefs = new ArrayList<Chef>();
        ResultSet resultSet = null;
        try {
            resultSet = Util.getResultSet(SELECT_ALL);

            while (resultSet.next()) {
                // Object Relational Mapping
                Chef chef = new Chef();
                chef.setId(resultSet.getInt("chefs.id"));
                chef.setName(resultSet.getString("chefs.name"));
                chef.setAvatarPic(resultSet.getString("images.url"));

                List<Dish> dishes = DishDao.listAll(chef.getId());
                chef.setNumOfDishes(dishes.size());

                Set<String> tags = new HashSet<String>();
                for (Dish dish : dishes) {
                    tags.addAll(dish.getTags());

                }
                chef.setTags(tags);


                double score = 0F;
                int i = 0;
                for (Dish dish : dishes) {
                    if (dish.getScore() > 0.4) {
                        score += dish.getScore();
                        i++;
                    }
                }
                chef.setScore(i == 0? 0 : score / i);

                chefs.add(chef);
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

        return chefs;

    }

    public static Chef find(int id) {

        Chef chef = new Chef();
        ResultSet resultSet = null;
        try {
            resultSet = Util.getResultSet(String.format(SELECT_ONE, id));

            while (resultSet.next()) {
                // Object Relational Mapping

                chef.setId(resultSet.getInt("chefs.id"));
                chef.setName(resultSet.getString("chefs.name"));
                chef.setAvatarPic(resultSet.getString("images.url"));

                List<Dish> dishes = DishDao.listAll(chef.getId());
                chef.setNumOfDishes(dishes.size());

                Set<String> tags = new HashSet<String>();
                for (Dish dish : dishes) {
                    tags.addAll(dish.getTags());
                }
                chef.setTags(tags);

                double score = 0F;
                int i = 0;
                for (Dish dish : dishes) {
                    if (dish.getScore() > 0.4) {
                        score += dish.getScore();
                        i++;
                    }
                }
                chef.setScore(i == 0? 0 : score / i);

                chef.setDishes(dishes);
                return chef;
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
}