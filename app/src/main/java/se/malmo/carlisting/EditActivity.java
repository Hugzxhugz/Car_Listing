package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
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
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));


        carRepository = SqliteCarRepository.getInstance(getApplicationContext());

        CarModelEditBox = findViewById(R.id.txtModel);
        CarBrandEditBox = findViewById(R.id.txtBrand);
        YearModelEditBox= findViewById(R.id.txtModelYear);
        MileageEditBox = findViewById(R.id.txtMileage);
        PriceEditBox = findViewById(R.id.txtPrice);
        DescriptionEditBox = findViewById(R.id.txtDescription);
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

    //  button function crashes app
    public void onUpdateBtnClick(View view){
        updateCar();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void updateCar(){
        Car car = new Car();
        car.setId(this.car.getId());
        car.setModel(CarModelEditBox.getText().toString());
        car.setBrand(CarBrandEditBox.getText().toString());
        car.setModelYear(YearModelEditBox.getText().toString());
        car.setMileage(Integer.parseInt(MileageEditBox.getText().toString()));
        car.setPrice(configPrice(PriceEditBox.getText().toString()));
        car.setDescription(DescriptionEditBox.getText().toString());
        carRepository.save(car);

    }

    public int configPrice(String price){
        price = price.replace(" ", "");
        price = price.replace("kr", "");



        return Integer.parseInt(price);
    }

}