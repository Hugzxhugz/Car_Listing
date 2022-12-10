package se.malmo.carlisting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDatabaseHelper extends SQLiteOpenHelper implements UserRepository {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "users.db";
    private static final String COLUMN_ID = "id";
    private static final String DB_TABLE_NAME = "users";
    private static final String COLUMN_NAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_BALANCE = "money";
    private Context context;


    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createUserTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NAME);
        createUserTable(sqLiteDatabase);
    }

    public void createUserTable(SQLiteDatabase db){
        String query = "CREATE TABLE "+DB_TABLE_NAME+" ("+
            COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_NAME+" TEXT,"+
            COLUMN_PASSWORD+" TEXT,"+
            COLUMN_BALANCE+" INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void createAccount(Account acc) {
        saveNewAccount(acc);
    }

    private void saveNewAccount(Account acc){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = getAccContentValues(acc);
        long result = db.insert(DB_TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to create account", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "successfully created account", Toast.LENGTH_SHORT).show();
        }
        return ;
    }

    private ContentValues getAccContentValues(Account acc){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, acc.getName());
        cv.put(COLUMN_PASSWORD, acc.getPassword());
        cv.put(COLUMN_BALANCE, acc.getBalance());
        return cv;
    }

    @Override
    public ArrayList<Account> findAllAccounts() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Account> users = new ArrayList();

        String query = "SELECT * FROM "+DB_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Account acc = new Account();
                acc.setId(cursor.getInt(0));
                acc.setName(cursor.getString(1));
                acc.setPassword(cursor.getString(2));
                acc.setBalance(cursor.getInt(3));

                users.add(acc);
        }
        cursor.close();
        return users;
    }
}
