package se.malmo.carlisting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "car.db";
    public static final String DB_TABLE_NAME = "cars";
    public static final String DB_COLUMN_ID = "id";
    public static final String DB_COLUMN_CAR_MODEL = "car_model";
    public static final String DB_COLUMN_CAR_BRAND = "car_brand";
    public static final String DB_COLUMN_CAR_MODEL_YEAR = "car_model_year";
    public static final String DB_COLUMN_CAR_DESCRIPTION = "car_description";
    public static final String DB_COLUMN_CAR_PRICE = "car_price";
    public static final String DB_COLUMN_CAR_MILEAGE = "car_milage";
    private static SqliteHelper instance = null;

    public SqliteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static SqliteHelper getInstance(Context context) {
        if(instance == null)
            instance = new SqliteHelper(context.getApplicationContext());

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createCarTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_NAME);
        onCreate(db);
    }

    public void createCarTable(SQLiteDatabase db){
        String query =
                "CREATE TABLE "+DB_TABLE_NAME+" (" +
                        DB_COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        DB_COLUMN_CAR_MODEL+" TEXT," +
                        DB_COLUMN_CAR_BRAND+" TEXT," +
                        DB_COLUMN_CAR_MODEL_YEAR+" TEXT," +
                        DB_COLUMN_CAR_DESCRIPTION+" TEXT," +
                        DB_COLUMN_CAR_PRICE+" INTEGER," +
                        DB_COLUMN_CAR_MILEAGE+" INTEGER" +
                        ")";
        db.execSQL(query);
    }
}

