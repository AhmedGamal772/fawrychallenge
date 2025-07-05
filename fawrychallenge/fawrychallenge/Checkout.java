package fawrychallenge;

import java.util.*;

public class Checkout {
    private static final double SHIPPING_FEE_PER_KG = 30;

    public static void process(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Cannot proceed to checkout.");
            return;
        }

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.isExpired()) {
                System.out.println(product.getName() + " is expired.");
                return;
            }

            if (qty > product.getQuantity()) {
                System.out.println("Not enough stock for " + product.getName());
                return;
            }

            subtotal += product.getPrice() * qty;

            if (product.requiresShipping() && product instanceof Shippable) {
                for (int i = 0; i < qty; i++) {
                    toShip.add((Shippable) product);
                }
            }
        }

        double shippingFee = toShip.stream().mapToDouble(Shippable::getWeight).sum() * SHIPPING_FEE_PER_KG;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            System.out.println("Insufficient balance.");
            return;
        }

        if (!toShip.isEmpty()) {
            new ShippingService().ship(toShip);
        }

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            System.out.println(qty + "x " + product.getName() + "    " + product.getPrice() * qty);
            product.reduceQuantity(qty);
        }

        System.out.println("----------------------");
        System.out.println("Subtotal         " + subtotal);
        System.out.println("Shipping         " + shippingFee);
        System.out.println("Amount           " + total);

        customer.pay(total);
        System.out.println("Customer balance " + customer.getBalance());

        cart.clear();
    }
}