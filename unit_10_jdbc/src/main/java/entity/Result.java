package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Result {

    private List<Integer> fromCity;
    private int distance;
    private boolean finish;
}
