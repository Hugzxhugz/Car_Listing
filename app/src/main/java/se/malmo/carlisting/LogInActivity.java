package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button logIn;
    LoggedIn log_In;
    UserRepository userRepo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));


        username = findViewById(R.id.userName);
        password = findViewById(R.id.userBalance);
        logIn = findViewById(R.id.popUpcloseButton);

        userRepo = new UserDatabaseHelper(getApplicationContext());
        log_In = LoggedIn.getInstance();

        logIn.setOnClickListener(view -> {
            logIn();
        });
    }

    private void logIn(){
        String name = username.getText().toString();
        String pass = password.getText().toString();
        LoggedIn.setInstance(new LoggedIn());
        log_In.logIn(userRepo.findAllAccounts(), name, pass);
        log_In = LoggedIn.getInstance();
        if(!LoggedIn.isLoggedIn()){
            Toast.makeText(this, "Log in failed", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(this, BrowseCarActivity.class);
            startActivity(intent);
        }
    }
}