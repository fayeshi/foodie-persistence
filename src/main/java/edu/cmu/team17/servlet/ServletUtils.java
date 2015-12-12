package edu.cmu.team17.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;

/**
 * Created by dev on 12/6/15.
 */
public class ServletUtils {


    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static String getLastString(String path ) {
        int index = path.lastIndexOf("/");
        String id = path.substring(index + 1);

        return id;

    }


    public static int getId(String path ) {
        int index = path.lastIndexOf("/");
        String id = path.substring(index + 1);

        return Integer.parseInt(id);

    }
}
