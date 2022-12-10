package se.malmo.carlisting;

import java.util.ArrayList;

public class LoggedIn {
    private static boolean loggedIn;
    private static String username;
    private static int balance;
    private static LoggedIn instance = null;

    public static LoggedIn getInstance() {
        if(instance == null){
            instance = new LoggedIn();
        }
        return instance;
    }

    public static void setInstance(LoggedIn newInstance) {
        instance = newInstance;
    }

    public LoggedIn(){
        loggedIn = false;
    }

    private LoggedIn(Account acc){
        loggedIn = true;
        username = acc.getName();
        balance = acc.getBalance();
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void logIn(ArrayList<Account> accounts, String username, String password){
        for (int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getName().equals(username) && accounts.get(i).getPassword().equals(password)){
                setInstance(new LoggedIn(accounts.get(i)));
            }
        }
    }
}
