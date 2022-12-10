package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    Button logIn;
    Button createAccount;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logIn = findViewById(R.id.btnMoveToLogIn);
        createAccount = findViewById(R.id.btnMoveToCreateAccount);

        logIn.setOnClickListener(view -> {
            moveToActivity(LogInActivity.class);
        });
        createAccount.setOnClickListener(view -> {
            moveToActivity(CreateAccountActivity.class);
        });

    }
    private void moveToActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}