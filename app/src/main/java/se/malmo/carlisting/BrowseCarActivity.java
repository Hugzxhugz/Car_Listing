package se.malmo.carlisting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BrowseCarActivity extends AppCompatActivity {
    EditText editTextSearch;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    String username;
    int balance;
    LoggedIn loggedIn;
    Repository sqlRepository;
    CarAdapter carAdapter;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView userName, userBalance;
    private Button popUpcloseButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_cars);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));

        editTextSearch = findViewById(R.id.tbxSearch);


        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.addBtn);

        sqlRepository = SqliteCarRepository.getInstance(getApplicationContext());

        loggedIn = LoggedIn.getInstance();

        setCarAdaptor(sqlRepository.findCarsForSale());

        getAccountInformation();

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
    }

    public void onAddButtonClick(View view){
        Intent intent = new Intent(this,AddCarActivity.class);
        startActivity(intent);

}

    public void setCarAdaptor(ArrayList<Car> showCars){
        carAdapter = new CarAdapter(this, showCars);
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BrowseCarActivity.this));
    }

    public void getAccountInformation(){
        username = loggedIn.getUsername();
        balance = loggedIn.getBalance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.myAccount){
            createNewPopUpDialog();
        }
        if(item.getItemId() == R.id.myCars){
            Intent intent = new Intent(this, MyCarsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewPopUpDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View myAccountPopUp = getLayoutInflater().inflate(R.layout.popup,null);
        userName = myAccountPopUp.findViewById(R.id.userName);
        userBalance = myAccountPopUp.findViewById(R.id.userBalance);

        userName.setText(username);
        userBalance.setText(String.valueOf(balance)+" kr");

        popUpcloseButton = myAccountPopUp.findViewById(R.id.popUpcloseButton);

        dialogBuilder.setView(myAccountPopUp);
        dialog = dialogBuilder.create();
        dialog.show();

        popUpcloseButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        });



    }



}