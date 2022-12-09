package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    Repository carRepository;
    TextView txtModel;
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_activity);

        this.setTitle("Car Display");

        txtModel = findViewById(R.id.txtCarModel);
        carRepository = SqliteCarRepository.getInstance(getApplicationContext());
        car = getCarFromIntent();
        txtModel.setText(car.getModel());
    }

    private Car getCarFromIntent(){
        Intent intent = getIntent();
        int car_id = intent.getIntExtra("id",0);

        Car car = carRepository.findCarById(car_id);

        return car;
    }
}