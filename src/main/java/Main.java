import model.Car;
import model.Potato;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            potato.setPrice(((randomNumber.apply(300) + 300) / 3d));
            return potato;
        };

        for (int i = 0; i < 10; i++) list.add(createRandomPotato.get());
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

        for (int i = 0; i < 10; i++) list.add(createRandomCar.get());
        return list;
    }

    private static void showList(List list) {
        list.forEach(System.out::println);
        System.out.println();
    }

    public static void main(String[] args) {

        List<Potato> potatoList = createRandomPotatoList();
        List<Car> carList = createRandomCarList();

        //map
        System.out.println("---------- Get prices from List of Potatoes");
        potatoList.stream().map(Potato::getPrice).forEach(System.out::println);

        //flatmap
        List<Potato> newPotatoList = createRandomPotatoList();
        List<Potato> combinedPotatoList = Stream.of(potatoList, newPotatoList).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("---------- Created new List of Potatoes from two lists");
        combinedPotatoList.forEach(System.out::println);

        //filter
        System.out.println("---------- Show only potatoes with price above 120");
        List<Potato> filteredPotatoList = combinedPotatoList.stream().filter(x -> x.getPrice() > 120d).collect(Collectors.toList());
        filteredPotatoList.forEach(System.out::println);

        //limit
        System.out.println("---------- Show first 3 potatoes with price above 120");
        filteredPotatoList.stream().limit(3).forEach(System.out::println);

        //skip
        System.out.println("---------- Show potatoes from 4th position with price above 120");
        filteredPotatoList.stream().skip(3).forEach(System.out::println);

        //sorted
        System.out.println("---------- Show sorted potatoes with price above 120");
        filteredPotatoList.stream().map(Potato::getPrice).sorted().forEach(System.out::println);

        //distinct
        System.out.println("---------- Show unique potatoes with price above 120");
        filteredPotatoList.stream().map(Potato::getPrice).distinct().forEach(System.out::println);

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
