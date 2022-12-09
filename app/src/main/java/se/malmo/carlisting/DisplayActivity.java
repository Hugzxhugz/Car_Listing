package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    Repository carRepository;
    TextView txtModel;
    Car car;
    Button DeleteBtn;
    Button EditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car_activity);

        this.setTitle("Car Display");

        DeleteBtn = findViewById(R.id.delete);
        EditBtn = findViewById(R.id.edit);

        txtModel = findViewById(R.id.txtCarModel);
        carRepository = SqliteCarRepository.getInstance(getApplicationContext());
        car = getCarFromIntent();
        txtModel.setText(car.getModel());

        DeleteBtn.setOnClickListener(View -> {
            onDeleteBtnClick();
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

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);



    }


}