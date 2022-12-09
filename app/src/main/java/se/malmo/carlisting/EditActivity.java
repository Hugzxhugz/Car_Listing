package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    Button updateBtn;
    EditText CarModelEditBox,
            CarBrandEditBox,
            YearModelEditBox,
            MileageEditBox,
            PriceEditBox,
            DescriptionEditBox;
    Repository sqlrepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        sqlrepository = SqliteCarRepository.getInstance(getApplicationContext());

        CarModelEditBox = findViewById(R.id.CarModelEditBox);
        CarBrandEditBox = findViewById(R.id.CarBrandEditBox);
        YearModelEditBox= findViewById(R.id.YearModelEditBox);
        MileageEditBox = findViewById(R.id.MileageEditBox);
        PriceEditBox = findViewById(R.id.PriceEditBox);
        DescriptionEditBox = findViewById(R.id.DescriptionEditBox);
        updateBtn = findViewById(R.id.UpdateBtn);
    }

}