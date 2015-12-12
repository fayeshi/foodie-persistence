package edu.cmu.team17.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by dev on 12/2/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer extends User {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
