package se.malmo.carlisting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText balance;
    Button createAccount;
    UserRepository userRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userRepo = new UserDatabaseHelper(getApplicationContext());

        username = findViewById(R.id.tbxCreateAccountUsername);
        password = findViewById(R.id.tbxCreateAccountPassword);
        balance = findViewById(R.id.tbxCreateAccountDeposit);

        createAccount = findViewById(R.id.btnCreateAccount);

        createAccount.setOnClickListener(view -> {
            createNewAccount();
            Intent intent = new Intent(this, StartActivity.class);

            startActivity(intent);
        });
    }

    private void createNewAccount(){
        Account acc = new Account();
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        int balance = Integer.parseInt(this.balance.getText().toString());
        acc.setBalance(balance);
        acc.setName(username);
        acc.setPassword(password);
        userRepo.createAccount(acc);
    }
}