package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    Repository sqlrepository;

    CarAdaptor carAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.add_btn);

        sqlrepository = SqliteCarRepository.getInstance(getApplicationContext());



        carAdaptor = new CarAdaptor(this, sqlrepository.findAllCars());
        recyclerView.setAdapter(carAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        
    }


    public void onAddButtonClick(View view){

        Intent intent = new Intent(MainActivity.this,addCarActivty.class);
        startActivity(intent);

    }




}