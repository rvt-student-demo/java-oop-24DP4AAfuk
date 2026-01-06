package rvt;

import java.util.Scanner;

public class OrderHistory {
    public static void main(String[] args) {
        String filePath = "data/order.csv";
        Scanner scanner = new Scanner(filePath);
        double totalSum = 0.0;

        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            String[] parts = row.split(",");

            // Skip header line
            if (parts[0].equals("Order ID")) {
                continue;
            }

            String customer = parts[1];
            String product = parts[2];
            int quantity = Integer.parseInt(parts[3]);
            double price = Double.parseDouble(parts[4]);
            double orderTotal = quantity * price;
            totalSum += orderTotal;

            System.out.println("Customer: " + customer + ", Product: " + product + ", Quantity: " + quantity +
                    ", Price: " + price + ", Order Total: " + orderTotal);
        }
    }
}
