import model.Car;
import model.Potato;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {

    private static final UnaryOperator<Integer> randomNumber = x -> new Random().nextInt(x);
    private static final BinaryOperator<Double> setDiscountPrice = (x, y) -> x - (x * y) / 100;
    private static final Consumer<Car> createUndercoverPoliceCar = x -> x.setBrand("Police");
    private static final Predicate<String> hasPoliceCar = x -> x.equals("Police");
    private static final Function<Double, String> intoUAH = x -> x + " UAH";

    private static String getRandomName() {

        StringBuilder s = new StringBuilder();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < randomNumber.apply(5) + 8; i++) {
            s.append(abc.charAt(randomNumber.apply(abc.length())));
        }
        return s.toString();
    }

    private static List<Potato> createRandomPotatoList() {
        List<Potato> list = new ArrayList<>();
        Supplier<Potato> createRandomPotato = () -> {
            Potato potato = new Potato();
            potato.setName(getRandomName() + randomNumber.apply(100));
            potato.setVariety(getRandomName() + randomNumber.apply(100));
            potato.setPrice((double) ((randomNumber.apply(300) + 300) / 3));
            return potato;
        };

        for (int i = 0; i < 3; i++) list.add(createRandomPotato.get());
        return list;
    }

    private static List<Car> createRandomCarList() {
        List<Car> list = new ArrayList<>();
        Supplier<Car> createRandomCar = () -> {
            Car car = new Car();
            car.setBrand(getRandomName() + randomNumber.apply(100));
            car.setModel(getRandomName() + randomNumber.apply(100));
            car.setManufacturedYear(randomNumber.apply(30) + 1970);
            return car;
        };

        for (int i = 0; i < 3; i++) list.add(createRandomCar.get());
        return list;
    }

    private static void showList(List list) {
        list.forEach(x -> System.out.println(x));
        System.out.println();
    }

    public static void main(String[] args) {

        List<Potato> potatoList = createRandomPotatoList();
        List<Car> carList = createRandomCarList();

        //Initial Potato list
        System.out.println("---------- Initial List of Potatoes");
        showList(potatoList);

        //Discount price set for holidays
        System.out.println("---------- Discounted prices list");
        potatoList.forEach(x -> x.setPrice(setDiscountPrice.apply(x.getPrice(), (double) 15)));
        showList(potatoList);
        potatoList.forEach(x -> System.out.print(intoUAH.apply(x.getPrice()) + " | "));
        System.out.println("\n");

        //Initial Car list
        System.out.println("---------- Initial List of Potatoes");
        showList(carList);

        //Brand sort
        System.out.println("---------- Year sort");
        carList.sort((car1, car2) -> car1.getBrand().compareTo(car2.getBrand()));
        showList(carList);

        //POLICE SEARCH
        System.out.println("---------- Searching for the POLICE");
        createUndercoverPoliceCar.accept(carList.get(randomNumber.apply(carList.size())));
        showList(carList);
        carList.forEach(car -> {
            if (hasPoliceCar.test(car.getBrand())) {
                System.out.println("POLICE HAS BEEN FOUNDED" + "    FUCK THE POLICE");
            }
        });
    }

}
