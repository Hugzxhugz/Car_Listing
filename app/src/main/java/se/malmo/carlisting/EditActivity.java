package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    Button update_button;
    EditText car_model_edit,
            car_brand_edit,
            year_model_edit,
            mileage_edit,
            price_edit,
            description_edit;
    Repository sqlrepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }




}