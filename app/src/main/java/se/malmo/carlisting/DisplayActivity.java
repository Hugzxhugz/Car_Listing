package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    Repository carRepository;
    TextView txtModel;
    TextView txtBrand;
    TextView txtModelYear;
    TextView txtMileage;
    TextView txtPrice;
    TextView txtDescription;
    Car car;
    Button DeleteBtn;
    Button EditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_activity);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));


        DeleteBtn = findViewById(R.id.DeleteBtn);
        EditBtn = findViewById(R.id.EditBtn);

        txtModel = findViewById(R.id.CarModelDisplayBox);
        txtBrand = findViewById(R.id.CarBrandDisplayBox);
        txtModelYear = findViewById(R.id.YearModelDisplayBox);
        txtMileage = findViewById(R.id.MileageDisplayBox);
        txtPrice = findViewById(R.id.PriceDisplayBox);
        txtDescription = findViewById(R.id.DescriptionDisplayBox);
        carRepository = SqliteCarRepository.getInstance(getApplicationContext());
        car = getCarFromIntent();

        DisplayCar();

        DeleteBtn.setOnClickListener(View -> {
            onDeleteBtnClick();
        });


        EditBtn.setOnClickListener(View -> {
            onEditBtnClick();
        });
    }

    private Car getCarFromIntent(){
        Intent intent = getIntent();
        int car_id = intent.getIntExtra("id",0);

        Car car = carRepository.findCarById(car_id);

        return car;
    }

    public void onDeleteBtnClick(){

        Car car = getCarFromIntent();
        carRepository.deleteCar(car.getId());

        Intent intent = new Intent(this, BrowsCarActivity.class);
        startActivity(intent);



    }

    public void onEditBtnClick(){
        Car car = getCarFromIntent();

        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra("id",car.getId());
        startActivity(intent);
    }

    public void DisplayCar(){

        txtModel.setText(car.getModel());
        txtBrand.setText(car.getBrand());
        txtModelYear.setText(car.getCarModelYear());
        txtMileage.setText(String.valueOf(car.getMileage()));
        txtPrice.setText(String.valueOf(car.getPrice())+" kr");
        txtDescription.setText(car.getDescription());

    }

}