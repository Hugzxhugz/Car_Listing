package se.malmo.carlisting;

import java.util.ArrayList;

public interface UserRepository {
    void createAccount(Account acc);
    ArrayList<Account> findAllAccounts();
    void updateAccount(Account acc);
    Account findAccountById(int accId);
}
