package fawrychallenge;


import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be at least 1.");
            return;
        }

        if (product.isExpired()) {
            System.out.println(product.getName() + " is expired.");
            return;
        }

        if (quantity > product.getQuantity()) {
            System.out.println("Not enough stock for " + product.getName());
            return;
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}