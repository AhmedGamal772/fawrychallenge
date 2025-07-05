package fawrychallenge;


public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void pay(double amount) {
        balance -= amount;
    }

    public String getName() {
        return name;
    }
}