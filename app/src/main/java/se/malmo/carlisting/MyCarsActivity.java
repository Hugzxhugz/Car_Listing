package se.malmo.carlisting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class MyCarsActivity extends AppCompatActivity {
    EditText editTextSearch;
    RecyclerView recyclerView;
    String username;
    int balance;

    LoggedIn loggedIn;

    Repository sqlRepository;

    MyCarsAdaptor myCarsAdaptor;
    private AlertDialog.Builder dialogBuilder;
    private TextView userName,userBalance;
    private Button popUpcloseButton;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));

        editTextSearch = findViewById(R.id.tbxMyCarSearch);

        recyclerView = findViewById(R.id.mycarrecycler_view);

        sqlRepository = SqliteCarRepository.getInstance(getApplicationContext());


        loggedIn = LoggedIn.getInstance();
        getAccountInformation();


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
        balance = loggedIn.getBalance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.car_account,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.myAccount){
            createNewPopUpDialog();
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