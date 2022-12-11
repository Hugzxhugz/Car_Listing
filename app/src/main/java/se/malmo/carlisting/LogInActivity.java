package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        username = findViewById(R.id.tbxLogInUsername);
        password = findViewById(R.id.tbxLogInPassword);
        logIn = findViewById(R.id.btnLogIn);

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
            Intent intent = new Intent(this, BrowsCarActivity.class);
            startActivity(intent);
        }
    }
}