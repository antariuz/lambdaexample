package model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {

    private String brand;
    private String model;
    private Integer manufacturedYear;

}
