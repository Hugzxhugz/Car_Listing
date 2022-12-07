package se.malmo.carlisting;

import java.util.ArrayList;

public interface Repository {
        Car findCarById(int id);
        ArrayList<Car> findAllCars();
        void deleteCar(int id);
        void save(Car car);

}
