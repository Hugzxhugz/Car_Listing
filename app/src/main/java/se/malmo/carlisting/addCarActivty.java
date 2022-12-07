package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addCarActivty extends AppCompatActivity {

    Button add_button;
    EditText car_model_input,
            car_brand_input,
            year_model_input,
            mileage_input,
            price_input,
            description_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_activty);

        car_model_input = findViewById(R.id.car_model_box);
        car_brand_input = findViewById(R.id.car_brand_box);
        year_model_input= findViewById(R.id.year_model_box);
        mileage_input = findViewById(R.id.mileage_box);
        price_input = findViewById(R.id.price_box);
        description_input = findViewById(R.id.description_box);
        add_button = findViewById(R.id.confirm_add_btn);
    }

    public void onConfirmAddBtnClick(View view){
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        //added this to test if it works. we can add the methods when Jens
        // finishes the database class.
    }
}