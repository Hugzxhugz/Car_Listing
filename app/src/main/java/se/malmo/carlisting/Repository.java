package se.malmo.carlisting;

import java.util.ArrayList;

public interface Repository {
        Car findCarById(int id);
        ArrayList<Car> findCarsForSale();
        void deleteCar(int id);
        void save(Car car);
        ArrayList<Car> search(ArrayList<Car> carList,String searchFor);
        ArrayList<Car> findMyCars(int ownerId);
        boolean attemptBuyCar(int carId);
}
