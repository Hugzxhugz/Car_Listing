package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ResourceBundle;

public class CreateAccountActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText balance;
    Button createAccount;
    UserRepository userRepo;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.marineBlue)));


        userRepo = new UserDatabaseHelper(getApplicationContext());

        username = findViewById(R.id.tbxCreateAccountUsername);
        password = findViewById(R.id.tbxCreateAccountPassword);
        balance = findViewById(R.id.tbxCreateAccountDeposit);

        createAccount = findViewById(R.id.btnCreateAccount);

        createAccount.setOnClickListener(view -> {
            createNewAccount();
        });
    }

    private void createNewAccount(){
        if(balance.getText().toString().isEmpty() || password.getText().toString().isEmpty() || username.getText().toString().isEmpty()){
            String message = context.getString(R.string.create_toast_fill_fields);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        Account acc = new Account();
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        int balance = Integer.parseInt(this.balance.getText().toString());
        acc.setBalance(balance);
        acc.setName(username);
        acc.setPassword(password);
        userRepo.createAccount(acc);
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}