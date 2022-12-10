package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCarActivity extends AppCompatActivity {

    Button AddBtn;
    EditText CarModelInputBox,
            CarBrandInputBox,
            YearModelInputBox,
            MileageInputBox,
            PriceInputBox,
            DescriptionInputBox;
    Repository sqlrepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_activty);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));


        sqlrepository = SqliteCarRepository.getInstance(getApplicationContext());

        CarModelInputBox = findViewById(R.id.CarModelDisplayBox);
        CarBrandInputBox = findViewById(R.id.CarBrandDisplayBox);
        YearModelInputBox= findViewById(R.id.YearModelDisplayBox);
        MileageInputBox = findViewById(R.id.MileageDisplayBox);
        PriceInputBox = findViewById(R.id.PriceDisplayBox);
        DescriptionInputBox = findViewById(R.id.DescriptionDisplayBox);
        AddBtn = findViewById(R.id.AddBtn);
    }

    public void onConfirmAddBtnClick(View view){
        addCar();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void addCar(){
        Car car = new Car();
        car.setModel(CarModelInputBox.getText().toString());
        car.setBrand(CarBrandInputBox.getText().toString());
        car.setModelYear(YearModelInputBox.getText().toString());
        car.setMileage(Integer.parseInt(MileageInputBox.getText().toString()));
        car.setPrice(Integer.parseInt(PriceInputBox.getText().toString()));
        car.setDescription(DescriptionInputBox.getText().toString());

        sqlrepository.save(car);
    }
}