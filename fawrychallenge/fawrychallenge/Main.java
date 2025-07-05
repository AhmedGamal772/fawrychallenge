package fawrychallenge;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Ahmed", 1000);

        Cheese cheese = new Cheese("Cheese", 100, 5, 0.2, LocalDate.of(2025, 8, 1));
        TV tv = new TV("TV", 300, 3, 7.5);
        ScratchCard card = new ScratchCard("ScratchCard", 50, 10);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(tv, 1);
        cart.add(card, 1);

        Checkout.process(customer, cart);
    }
}