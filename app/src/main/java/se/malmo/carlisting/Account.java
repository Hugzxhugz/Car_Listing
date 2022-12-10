package se.malmo.carlisting;

public class Account {
    private int id;
    private String name;
    private String password;
    private int balance;

    public Account(){
        id = balance = 0;
        name = password = "";
    }

    public Account(int id, String name, String password, int balance){
        this.id = id;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
