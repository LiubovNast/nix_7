package entity;

import java.util.Date;

public class User {

    private int id;
    private String name;
    private String lastName;
    private Date birthdate;
    private double weight;
    private boolean isMale;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", weight=" + weight +
                ", isMale=" + isMale +
                '}';
    }
}
