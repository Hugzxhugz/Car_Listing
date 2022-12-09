package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Repository carRepository;
    Car car;
    Button updateBtn;
    EditText CarModelEditBox,
            CarBrandEditBox,
            YearModelEditBox,
            MileageEditBox,
            PriceEditBox,
            DescriptionEditBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        carRepository = SqliteCarRepository.getInstance(getApplicationContext());

        CarModelEditBox = findViewById(R.id.CarModelEditBox);
        CarBrandEditBox = findViewById(R.id.CarBrandEditBox);
        YearModelEditBox= findViewById(R.id.YearModelEditBox);
        MileageEditBox = findViewById(R.id.MileageEditBox);
        PriceEditBox = findViewById(R.id.PriceEditBox);
        DescriptionEditBox = findViewById(R.id.DescriptionEditBox);
        updateBtn = findViewById(R.id.UpdateBtn);

        car = getCarFromIntent();
        displayEditCar();
    }


    private Car getCarFromIntent(){
        Intent intent = getIntent();
        int car_id = intent.getIntExtra("id",0);

        Car car = carRepository.findCarById(car_id);

        return car;
    }

    private void displayEditCar(){
        CarModelEditBox.setText(car.getModel());
        CarBrandEditBox.setText(car.getBrand());
        YearModelEditBox.setText(car.getCarModelYear());
        MileageEditBox.setText(String.valueOf(car.getMileage()));
        PriceEditBox.setText(String.valueOf(car.getPrice())+" kr");
        DescriptionEditBox.setText(car.getDescription());
    }
}