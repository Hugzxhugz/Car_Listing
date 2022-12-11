/*package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    EditText editTextSearch;
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    Repository sqlRepository;

    CarAdapter carAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.cartoonBlue)));

        editTextSearch = findViewById(R.id.tbxSearch);

        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.addBtn);

        sqlRepository = SqliteCarRepository.getInstance(getApplicationContext());

        setCarAdaptor(sqlRepository.findAllCars());

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searched = editTextSearch.getText().toString();
                if(!searched.isEmpty()) setCarAdaptor(sqlRepository.search(searched));
                else setCarAdaptor(sqlRepository.findAllCars());
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void onAddButtonClick(View view){

        Intent intent = new Intent(HomeActivity.this, AddCarActivity.class);
        startActivity(intent);

    }
    public void setCarAdaptor(ArrayList<Car> showCars){
        carAdapter = new CarAdapter(this, showCars);
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }
}*/