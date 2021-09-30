import annotations.PropertyKey;

import java.util.Date;

public class AppProperties {

    @PropertyKey("default.id")
    public long id;
    public String userName;
    @PropertyKey("default.password")
    public String userPassword;
    @PropertyKey("start.time")
    public Date startUsing;
    public Date lastVisit;
    @PropertyKey("max.value")
    public int countTasks;
    @PropertyKey("default.role")
    public Role role;

    public enum Role {
        ANONYMOUS, USER, ADMIN
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                "," + "\n" + "startUsing=" + startUsing +
                ", lastVisit=" + lastVisit +
                ", countTasks=" + countTasks +
                ", role=" + role +
                '}';
    }
}
