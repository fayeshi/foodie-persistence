package edu.cmu.team17.model;

/**
 * Created by dev on 12/2/15.
 */
public class User {

    private int id;
    private String name;

    private String avatarPic;

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

    public String getAvatarPic() {
        return avatarPic;
    }

    public void setAvatarPic(String avatarPic) {
        this.avatarPic = avatarPic;
    }


    public String toString(){
        return "User Id is: " + id + " User Name is: " + name + " User Image is: " + avatarPic;
    }

}
