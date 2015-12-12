package edu.cmu.team17.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.List;

/**
 * Created by dev on 11/29/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chef extends User {

    /**
     *
     *
     *
     *
     “id” : 1,
     “name” : “chef A”,
     “avatarPic” : “http://some.com/a.jpg”,
    // “numOfDishes” : 49,
    // “tags” : [“a”, “b”],
    // “score”: 2.5,
     *
     *
     */

    private int numOfDishes = 0;
    private Collection<String> tags;
    private Double score;
    private List<Dish> dishes;

    public int getNumOfDishes() {
        return numOfDishes;
    }

    public void setNumOfDishes(int numOfDishes) {
        this.numOfDishes = numOfDishes;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
