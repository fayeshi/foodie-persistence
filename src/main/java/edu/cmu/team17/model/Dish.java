package edu.cmu.team17.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dev on 12/1/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Dish {
    private int id;
    private String name;
    private String dishPic;
    private float price;
    private Collection<String> tags;
    private int stock;
    private Chef chef;
    private Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDishPic() {
        return dishPic;
    }

    public void setDishPic(String dishPic) {
        this.dishPic = dishPic;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public String toString(){


        return "Dish Id is: " + id + " , Dish name is: " + name + " , Dish Picture is: " + dishPic +
            " ,Dish price is: " + price + " ,Dish stock is: " + stock ;//+ " , Chef is: " + chef.toString();


    }

}
