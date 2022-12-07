package se.malmo.carlisting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
                : null;


        cursor.close();
        return car;
    }

    @Override
    public ArrayList<Car> findAllCars() {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();

        String query = "SELECT * FROM "+SqliteHelper.DB_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            Car car = new Car()
                    .setId(cursor.getInt(0))
                    .setModel(cursor.getString(1))
                    .setBrand(cursor.getString(2))
                    .setModelYear(cursor.getString(3))
                    .setDescription(cursor.getString(4))
                    .setPrice(cursor.getInt(5))
                    .setMileage(cursor.getInt(6));
            cars.add(car);
        }
        cursor.close();
        return cars;
    }

    @Override
    public void deleteCar(int id) {
        SQLiteDatabase db = sqlite.getWritableDatabase();
        String[] args = getWhereArgs(id);
        db.delete(SqliteHelper.DB_TABLE_NAME, "id = ?", args);
    }

    @Override
    public void save(Car car) {
        if(car.getId() == 0)
            insertCar(car);
        else
            updateBook(car);
    }

    private void insertCar(Car car){
        SQLiteDatabase db = sqlite.getWritableDatabase();
        ContentValues c = getContentValues(car);
        long result = db.insert(SqliteHelper.DB_TABLE_NAME, null, c);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
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
        return c;
    }

    private void updateBook(Car car){
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues c = getContentValues(car);
        String[] whereArgs = getWhereArgs(car.getId());

        db.update(SqliteHelper.DB_TABLE_NAME, c, "id = ?", whereArgs);
    }
}

