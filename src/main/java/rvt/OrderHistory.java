package rvt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class OrderHistory {
    public static void main(String[] args) {
        String filePath = "data/order.csv";
        double totalSum = 0.0;
        int orderCount = 0;

        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().trim();
                if (row.isEmpty()) continue;

                String[] parts = row.split(",");

                if (parts.length < 5) continue;

                String firstCol = parts[0].trim();
                String fourthCol = parts[3].trim();
                if (firstCol.equalsIgnoreCase("OrderID") || fourthCol.equalsIgnoreCase("Quantity")) {
                    continue;
                }

                String customer = parts[1].trim();
                String product = parts[2].trim();

                int quantity;
                double price;
                try {
                    quantity = Integer.parseInt(fourthCol);
                    price = Double.parseDouble(parts[4].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid numeric row: " + row);
                    continue;
                }

                orderCount++;
                double orderTotal = quantity * price;
                totalSum += orderTotal;

                System.out.println(String.format(Locale.US,
                        "Pasutijums #%d: %s pasutija %d x %s (%.2f EUR) -> Kopa: %.2f EUR",
                        orderCount, customer, quantity, product, price, orderTotal));
            }

            System.out.println();
            System.out.println(String.format(Locale.US, "Kopeja pasutijumu summa: %.2f EUR", totalSum));

        } catch (FileNotFoundException e) {
            System.err.println("CSV fails nav atrasts: " + filePath);
        }
    }
}
