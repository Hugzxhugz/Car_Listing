package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    Repository sqlrepository;

    CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.add_btn);

        sqlrepository = SqliteCarRepository.getInstance(getApplicationContext());



        carAdapter = new CarAdapter(this, sqlrepository.findAllCars());
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        
    }


    public void onAddButtonClick(View view){

        Intent intent = new Intent(MainActivity.this,addCarActivty.class);
        startActivity(intent);

    }


//test
}