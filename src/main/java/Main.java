import model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setBrand("Car" + random.nextInt(100));
            car.setModel("Model" + random.nextInt(100));
            car.setManufacturedYear(random.nextInt(30) + 1970);
            carList.add(car);
        }
        System.out.println("---------- Initial List of Car");
        for (Car object : carList) {
            System.out.println(object);
        }

        //Year sort
        carList.sort((car1, car2) -> car1.getManufacturedYear() - car2.getManufacturedYear());

        System.out.println("---------- Year sort");
        for (Car object : carList) {
            System.out.println(object);
        }

        //Brand sort
        carList.sort((car1, car2) -> car1.getBrand().compareTo(car2.getBrand()));

        System.out.println("---------- Brand name sort");
        for (Car object : carList) {
            System.out.println(object);
        }


    }

}
