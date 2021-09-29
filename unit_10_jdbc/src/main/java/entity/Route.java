package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Route {

    private int id;
    private int fromId;
    private int toId;
    private int cost;
}
