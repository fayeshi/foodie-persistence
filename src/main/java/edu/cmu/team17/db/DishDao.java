package edu.cmu.team17.db;

import edu.cmu.team17.model.Chef;
import edu.cmu.team17.model.Dish;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dev on 12/7/2015.
 */
public class DishDao {

    // private static final String SELECT_ALL = "select dishes1.*, images1.*, users1.*,  images2.* , GROUP_CONCAT(distinct(tags.name) SEPARATOR ',') as tags from dishes as dishes1  join images as images1  join users as users1  join images as images2  join tags as tags join tags_dishes as tags_dishes where dishes1.dishPic = images1.id  and dishes1.chefId = users1.id  and users1.avatarPic = images2.id and dishes1.id = tags_dishes.dishId and tags.id = tags_dishes.tagId group by dishes1.id";

    private static final String SELECT_ALL = "select dishes1.*, images1.*, GROUP_CONCAT(distinct(tags.name) SEPARATOR ',') as tags, avg(reviews.starRating) as score from dishes as dishes1   join images as images1 on dishes1.dishPic = images1.id join tags_dishes as tags_dishes on dishes1.id = tags_dishes.dishId join tags as tags on tags.id = tags_dishes.tagId left join reviews on dishes1.id = reviews.dishId group by dishes1.id";

    private static final String SELECT_ONE = "select dishes1.*, images1.*, users1.*,  images2.* , GROUP_CONCAT(distinct(tags.name) SEPARATOR ',') as tags, avg(reviews.starRating) as score from dishes as dishes1  join images as images1  join users as users1  join images as images2  join tags as tags join tags_dishes as tags_dishes left join reviews on dishes1.id = reviews.dishId where dishes1.dishPic = images1.id  and dishes1.chefId = users1.id  and users1.avatarPic = images2.id and dishes1.id = tags_dishes.dishId and tags.id = tags_dishes.tagId and dishes1.id = %d group by dishes1.id";

    private static final String SELECT_ALL_BY_CHEF_ID = "select dishes1.*, images1.*, GROUP_CONCAT(distinct(tags.name) SEPARATOR ',') as tags, avg(reviews.starRating) as score from dishes as dishes1   join images as images1 on dishes1.dishPic = images1.id join tags_dishes as tags_dishes on dishes1.id = tags_dishes.dishId join tags as tags on tags.id = tags_dishes.tagId left join reviews on dishes1.id = reviews.dishId where dishes1.chefId = %d group by dishes1.id";

    public static List<Dish> listAll() {

        ArrayList<Dish> results = new ArrayList<Dish>();

        try {
            ResultSet resultSet = Util.getResultSet(SELECT_ALL);

            while (resultSet.next()) {

//                Chef chef = new Chef();
//                chef.setId(resultSet.getInt("users1.id"));
//                chef.setAvatarPic(resultSet.getString("images2.url"));
//                chef.setName(resultSet.getString("users1.name"));

                Dish dish = new Dish();
                dish.setId(resultSet.getInt("dishes1.id"));
                dish.setName(resultSet.getString("dishes1.name"));
                dish.setDishPic(resultSet.getString("images1.url"));
                dish.setPrice(resultSet.getFloat("dishes1.price"));
                dish.setStock(resultSet.getInt("dishes1.stock"));
                dish.setScore(resultSet.getDouble("score"));

                dish.setTags(Arrays.asList(StringUtils.split(resultSet.getString("tags"), ",")));
                results.add(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;

    }

    public static List<Dish> listAll(int chefId) {


        ArrayList<Dish> results = new ArrayList<Dish>();

        try {
            ResultSet resultSet = Util.getResultSet(String.format(SELECT_ALL_BY_CHEF_ID, chefId));

            while (resultSet.next()) {

                Dish dish = new Dish();
                dish.setId(resultSet.getInt("dishes1.id"));
                dish.setName(resultSet.getString("dishes1.name"));
                dish.setDishPic(resultSet.getString("images1.url"));
                dish.setPrice(resultSet.getFloat("dishes1.price"));
                dish.setStock(resultSet.getInt("dishes1.stock"));
                dish.setScore(resultSet.getDouble("score"));

                dish.setTags(Arrays.asList(StringUtils.split(resultSet.getString("tags"), ",")));

                results.add(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;

    }

    public static List<Dish> findByCategory(String category) {
        List<Dish> dishes = listAll();
        List<Dish> result = new ArrayList<Dish>();

        for (Dish dish : dishes) {
            if (dish.getTags() != null && dish.getTags().contains(category)) {
                result.add(dish);
            }
        }

        return result;

    }


    public static Dish find(int dishId) {

        try {
            ResultSet resultSet = Util.getResultSet(String.format(SELECT_ONE, dishId));

            while (resultSet.next()) {
                Chef chef = new Chef();
                chef.setId(resultSet.getInt("users1.id"));
                chef.setAvatarPic(resultSet.getString("images2.url"));
                chef.setName(resultSet.getString("users1.name"));

                Dish dish = new Dish();
                dish.setId(resultSet.getInt("dishes1.id"));
                dish.setName(resultSet.getString("dishes1.name"));
                dish.setDishPic(resultSet.getString("images1.url"));
                dish.setPrice(resultSet.getFloat("dishes1.price"));
                dish.setStock(resultSet.getInt("dishes1.stock"));
                dish.setScore(resultSet.getDouble("score"));

                dish.setTags(Arrays.asList(StringUtils.split(resultSet.getString("tags"), ",")));

                dish.setChef(chef);
                return dish;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

        //            while (resultSet.next()){
//
//                // Object Relational Mapping
//
//
//                Dish dish = new Dish();
//                dish.setId(resultSet.getInt("dishes1.id"));
//                dish.setName(resultSet.getString("dishes1.name"));
//                dish.setDishPic(resultSet.getString("images1.url"));
//                dish.setPrice(resultSet.getFloat("dishes1.price"));
//                dish.setStock(resultSet.getInt("dishes1.stock"));
//

    }


}
