package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));


        carRepository = SqliteCarRepository.getInstance(getApplicationContext());

        CarModelEditBox = findViewById(R.id.CarModelDisplayBox);
        CarBrandEditBox = findViewById(R.id.CarBrandDisplayBox);
        YearModelEditBox= findViewById(R.id.YearModelDisplayBox);
        MileageEditBox = findViewById(R.id.MileageDisplayBox);
        PriceEditBox = findViewById(R.id.PriceDisplayBox);
        DescriptionEditBox = findViewById(R.id.DescriptionDisplayBox);
        updateBtn = findViewById(R.id.AddBtn);

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


    public void onUpdateBtnClick(View view){
        updateCar();
    }

    private void updateCar(){
        if(MileageEditBox.getText().toString().isEmpty() || PriceEditBox.getText().toString().isEmpty() || CarBrandEditBox.getText().toString().isEmpty() || CarModelEditBox.getText().toString().isEmpty()
                || DescriptionEditBox.getText().toString().isEmpty() || YearModelEditBox.getText().toString().isEmpty()){
            Toast.makeText(this, this.getString(R.string.create_toast_fill_fields), Toast.LENGTH_SHORT).show();
        } else {
            Car car = new Car();
            car.setId(this.car.getId());
            car.setModel(CarModelEditBox.getText().toString());
            car.setBrand(CarBrandEditBox.getText().toString());
            car.setModelYear(YearModelEditBox.getText().toString());
            car.setMileage(Integer.parseInt(MileageEditBox.getText().toString()));
            car.setPrice(configPrice(PriceEditBox.getText().toString()));
            car.setDescription(DescriptionEditBox.getText().toString());
            car.setOwnerId(this.car.getOwnerId());
            carRepository.save(car);
            moveToBrowse();
        }
    }

    public int configPrice(String price){
        price = price.replace(" ", "");
        price = price.replace("kr", "");
        return Integer.parseInt(price);
    }
    public void moveToBrowse(){
        Intent intent = new Intent(this, BrowseCarActivity.class);
        startActivity(intent);
    }
}