package se.malmo.carlisting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.security.acl.Owner;
import java.util.ArrayList;

public class SqliteCarRepository implements Repository{
    private SQLiteOpenHelper sqlite;
    private static SqliteCarRepository instance = null;
    private Context context;


    private SqliteCarRepository(Context context){
        this.context = context;
        sqlite = SqliteHelper.getInstance(context);
    }

    public static SqliteCarRepository getInstance(Context context){
        if(instance == null)
            instance = new SqliteCarRepository(context);

        return instance;
    }

    @Override
    public Car findCarById(int id) {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        String query = "SELECT * FROM cars WHERE id = " + id;
        Cursor cursor = db.rawQuery(query, null);

        Car car = cursor.moveToFirst() ?
                new Car()
                        .setId(cursor.getInt(0))
                        .setModel(cursor.getString(1))
                        .setBrand(cursor.getString(2))
                        .setModelYear(cursor.getString(3))
                        .setDescription(cursor.getString(4))
                        .setPrice(cursor.getInt(5))
                        .setMileage(cursor.getInt(6))
                        .setOwnerId(cursor.getInt(7))
                : null;


        cursor.close();
        return car;
    }

    @Override
    public ArrayList<Car> findCarsForSale() {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();

        String query = "SELECT * FROM "+SqliteHelper.DB_TABLE_NAME+" WHERE "+ SqliteHelper.DB_COLUMN_OWNER_ID+" = 0";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            Car car = new Car()
                    .setId(cursor.getInt(0))
                    .setModel(cursor.getString(1))
                    .setBrand(cursor.getString(2))
                    .setModelYear(cursor.getString(3))
                    .setDescription(cursor.getString(4))
                    .setPrice(cursor.getInt(5))
                    .setMileage(cursor.getInt(6))
                    .setOwnerId(cursor.getInt(7));
            cars.add(car);
        }
        cursor.close();
        return cars;
    }

    @Override
    public void deleteCar(int id) {
        SQLiteDatabase db = sqlite.getWritableDatabase();
        String[] args = getWhereArgs(id);
        long result = db.delete(SqliteHelper.DB_TABLE_NAME, "id = ?", args);
        if (result == -1){
            String message = context.getString(R.string.message_fail);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            String message = context.getString(R.string.message_success);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void save(Car car) {
        if(car.getId() == 0)
            insertCar(car);
        else
            updateCar(car);
    }

    private void insertCar(Car car){
        SQLiteDatabase db = sqlite.getWritableDatabase();
        ContentValues c = getContentValues(car);
        long result = db.insert(SqliteHelper.DB_TABLE_NAME, null, c);
        if (result == -1){
            String message = context.getString(R.string.message_fail);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            String message = context.getString(R.string.message_success);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    private String[] getWhereArgs(int id) {
        String[] whereArgs = {String.valueOf(id)};
        return whereArgs;
    }

    @NonNull
    private ContentValues getContentValues(Car car) {
        ContentValues c = new ContentValues();
        c.put(SqliteHelper.DB_COLUMN_CAR_MODEL, car.getModel());
        c.put(SqliteHelper.DB_COLUMN_CAR_BRAND, car.getBrand());
        c.put(SqliteHelper.DB_COLUMN_CAR_MODEL_YEAR, car.getCarModelYear());
        c.put(SqliteHelper.DB_COLUMN_CAR_DESCRIPTION, car.getDescription());
        c.put(SqliteHelper.DB_COLUMN_CAR_PRICE, car.getPrice());
        c.put(SqliteHelper.DB_COLUMN_CAR_MILEAGE, car.getMileage());
        c.put(SqliteHelper.DB_COLUMN_OWNER_ID, car.getOwnerId());
        return c;
    }

    private void updateCar(Car car){
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues c = getContentValues(car);
        String[] whereArgs = getWhereArgs(car.getId());

        long result = db.update(SqliteHelper.DB_TABLE_NAME, c, "id = ?", whereArgs);
        if (result == -1){
            String message = context.getString(R.string.message_fail);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            String message = context.getString(R.string.message_success);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Car> search(ArrayList<Car> carList, String searchingFor){
        ArrayList<Car> newList = new ArrayList<>();
        searchingFor = searchingFor.replace(" ", "").toLowerCase();
        for (int i = 0; i < carList.size(); i++){
            if ((carList.get(i).getBrand()+carList.get(i).getModel()).toLowerCase().contains(searchingFor)){
                newList.add(carList.get(i));
            }
        }
        return newList;
    }

    private void buyCar(Car car, int buyerId){
        car.setOwnerId(buyerId);
        updateCar(car);
    }

    @Override
    public ArrayList<Car> findMyCars(int ownerId) {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();

        String query = "SELECT * FROM "+SqliteHelper.DB_TABLE_NAME+" WHERE "+ SqliteHelper.DB_COLUMN_OWNER_ID+" = "+ownerId;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            Car car = new Car()
                    .setId(cursor.getInt(0))
                    .setModel(cursor.getString(1))
                    .setBrand(cursor.getString(2))
                    .setModelYear(cursor.getString(3))
                    .setDescription(cursor.getString(4))
                    .setPrice(cursor.getInt(5))
                    .setMileage(cursor.getInt(6))
                    .setOwnerId(cursor.getInt(7));
            cars.add(car);
        }
        cursor.close();
        return cars;
    }
    public boolean attemptBuyCar(int carId){
        UserRepository userRepo = UserDatabaseHelper.getInstance(context);
        Repository carRepository = getInstance(context);
        int accId = LoggedIn.getInstance().getAccountId();
        Car car =  carRepository.findCarById(carId);
        Account acc = userRepo.findAccountById(accId);

        if (acc.getBalance()-car.getPrice() > 0){
            int newBalance = acc.getBalance()-car.getPrice();
            acc.setBalance(newBalance);
            LoggedIn.getInstance().setBalance(newBalance);
            buyCar(car, accId);
            userRepo.updateAccount(acc);
            return true;
        } else{
            String message = context.getString(R.string.not_enough_funds);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

