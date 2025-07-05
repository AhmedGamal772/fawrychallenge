package fawrychallenge;


import java.util.List;

public class ShippingService {
    public void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0;

        for (Shippable item : items) {
            System.out.println(" - " + item.getName() + "  " + item.getWeight() * 1000 + "g");
            totalWeight += item.getWeight();
        }

        System.out.printf("Total package weight: %.2f kg\n\n", totalWeight);
    }
}