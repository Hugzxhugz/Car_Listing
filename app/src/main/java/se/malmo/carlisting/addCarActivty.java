/*package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addCarActivty extends AppCompatActivity {

    Button add_button;
    EditText car_model_input,
            car_brand_input,
            year_model_input,
            mileage_input,
            price_input,
            description_input;
    Repository sqlrepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_activty);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));


        sqlrepository = SqliteCarRepository.getInstance(getApplicationContext());

        car_model_input = findViewById(R.id.car_model_box);
        car_brand_input = findViewById(R.id.car_brand_box);
        year_model_input= findViewById(R.id.year_model_box);
        mileage_input = findViewById(R.id.mileage_box);
        price_input = findViewById(R.id.price_box);
        description_input = findViewById(R.id.description_box);
        add_button = findViewById(R.id.confirm_add_btn);
    }

    public void onConfirmAddBtnClick(View view){
        addCar();
        Intent intent = new Intent(this, BrowsCarActivity.class);
        startActivity(intent);
    }

    private void addCar(){
        Car car = new Car();
        car.setModel(car_model_input.getText().toString());
        car.setBrand(car_brand_input.getText().toString());
        car.setModelYear(year_model_input.getText().toString());
        car.setMileage(Integer.parseInt(mileage_input.getText().toString()));
        car.setPrice(Integer.parseInt(price_input.getText().toString()));
        car.setDescription(description_input.getText().toString());

        sqlrepository.save(car);
    }
} */