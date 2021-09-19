package thirdTask.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class City {

    private int id;
    private String name;
    private Map<Integer, Integer> neighbourDistance;
}
