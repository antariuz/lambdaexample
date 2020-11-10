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
            if (i != 4) {
                car.setBrand("Car" + random.nextInt(100));
                car.setModel("Model" + random.nextInt(100));
                car.setManufacturedYear(random.nextInt(30) + 1970);
            } else {
                car.setBrand("POLICE");
                car.setManufacturedYear(777);
            }
            carList.add(car);
        }
        //Initial list
        System.out.println("---------- Initial List of Car");
        carList.forEach(car -> System.out.println(car));

        //Year sort
        System.out.println("---------- Year sort");
        carList.sort((car1, car2) -> car1.getManufacturedYear() - car2.getManufacturedYear());
        carList.forEach(car -> System.out.println(car));

        //Brand sort
        System.out.println("---------- Year sort");
        carList.sort((car1, car2) -> car1.getBrand().compareTo(car2.getBrand()));
        carList.forEach(car -> System.out.println(car));

        //POLICE SEARCH
        System.out.println("---------- Searching for the POLICE");
        carList.forEach(car -> {
            if ("POLICE".equals(car.getBrand())) System.out.println("POLICE HAS BEEN FOUNDED" + "\nFUCK THE POLICE");
        });
    }

}
