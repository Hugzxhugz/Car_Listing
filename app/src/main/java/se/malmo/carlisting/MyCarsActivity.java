package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCarsActivity extends AppCompatActivity {
    EditText editTextSearch;
    RecyclerView recyclerView;
    TextView txtUsername;
    String username;
    LoggedIn loggedIn;

    Repository sqlRepository;

    MyCarsAdaptor myCarsAdaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));

        editTextSearch = findViewById(R.id.tbxMyCarSearch);

        recyclerView = findViewById(R.id.mycarrecycler_view);
        txtUsername = findViewById(R.id.txtMyCarUsername);

        sqlRepository = SqliteCarRepository.getInstance(getApplicationContext());


        loggedIn = LoggedIn.getInstance();
        getAccountInformation();
        displayAccountInformation();

        setCarAdaptor(sqlRepository.findMyCars(loggedIn.getAccountId()));


        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searched = editTextSearch.getText().toString();
                if(!searched.isEmpty()) setCarAdaptor(sqlRepository.search(sqlRepository.findMyCars(loggedIn.getAccountId()),searched));
                else setCarAdaptor(sqlRepository.findMyCars(loggedIn.getAccountId()));
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void onAddButtonClick(View view){
        Intent intent = new Intent(this,AddCarActivity.class);
        startActivity(intent);

    }
    public void setCarAdaptor(ArrayList<Car> showCars){
        myCarsAdaptor = new MyCarsAdaptor(MyCarsActivity.this, showCars);
        recyclerView.setAdapter(myCarsAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyCarsActivity.this));
    }

    public void getAccountInformation(){
        username = loggedIn.getUsername();
    }

    public void displayAccountInformation(){
        txtUsername.setText(username);
    }

}