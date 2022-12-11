package se.malmo.carlisting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BrowsCarActivity extends AppCompatActivity {
    EditText editTextSearch;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    TextView txtUsername;
    TextView txtBalance;
    String username;
    int balance;
    Button btnShowMyCars;
    LoggedIn loggedIn;

    Repository sqlRepository;

    CarAdapter carAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_cars);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cartoonBlue)));

        editTextSearch = findViewById(R.id.tbxSearch);

        btnShowMyCars = findViewById(R.id.btnShowMyCars);
        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.addBtn);
        txtBalance = findViewById(R.id.txtMainBalance);
        txtUsername = findViewById(R.id.txtMainUsername);

        sqlRepository = SqliteCarRepository.getInstance(getApplicationContext());

        loggedIn = LoggedIn.getInstance();

        setCarAdaptor(sqlRepository.findCarsForSale());

        getAccountInformation();
        displayAccountInformation();

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searched = editTextSearch.getText().toString();
                if(!searched.isEmpty()) setCarAdaptor(sqlRepository.search(sqlRepository.findCarsForSale(),searched));
                else setCarAdaptor(sqlRepository.findCarsForSale());
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnShowMyCars.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyCarsActivity.class);
            startActivity(intent);
        });
    }


    public void onAddButtonClick(View view){
        Intent intent = new Intent(this,AddCarActivity.class);
        startActivity(intent);

}

    public void setCarAdaptor(ArrayList<Car> showCars){
        carAdapter = new CarAdapter(this, showCars);
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BrowsCarActivity.this));
    }

    public void getAccountInformation(){
        username = loggedIn.getUsername();
        balance = loggedIn.getBalance();
    }

    public void displayAccountInformation(){
        txtUsername.setText(username);
        txtBalance.setText(String.valueOf(balance)+" kr");
    }
}