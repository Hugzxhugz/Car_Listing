package se.malmo.carlisting;

public class Car {
    private int car_id;
    private String car_model;
    private String car_model_year;
    private int car_price;
    private String car_brand;
    private String car_description;
    private int car_mileage;
    private int car_owner_id;

    public Car(){
        car_id = car_mileage = car_price = car_owner_id = 0;
        car_brand = car_description = car_model_year = "";
    }

    public Car(int car_id, String car_model, String car_brand, String car_model_year, String car_description, int car_price, int car_mileage, int car_owner_id){
        this.car_id = car_id;
        this.car_model = car_model;
        this.car_brand = car_brand;
        this.car_price = car_price;
        this.car_description = car_description;
        this.car_mileage = car_mileage;
        this.car_model_year = car_model_year;
        this.car_owner_id = car_owner_id;
    }

    public int getOwnerId(){
        return car_owner_id;
    }

    public Car setOwnerId(int car_owner_id){
        this.car_owner_id = car_owner_id;
        return this;
    }

    public int getId(){ return car_id; }

    public Car setId(int car_id){
        this.car_id = car_id;
        return this;
    }

    public String getModel(){return car_model; }


    public Car setModel(String car_model){
        this.car_model = car_model;
        return this;
    }

    public Car setBrand(String car_brand){
        this.car_brand = car_brand;
        return this;
    }

    public String getBrand(){return car_brand; }

    public Car setModelYear(String car_model_year){
        this.car_model_year = car_model_year;
        return this;
    }

    public String getCarModelYear(){ return car_model_year; }

    public Car setPrice(int car_price){
        this.car_price = car_price;
        return this;
    }

    public int getPrice(){return car_price; }

    public Car setDescription(String car_description){
        this.car_description = car_description;
        return this;
    }

    public String getDescription(){
        return car_description;
    }

    public Car setMileage(int car_mileage){
        this.car_mileage = car_mileage;
        return this;
    }

    public int getMileage(){ return car_mileage; }
}

