package rvt.shop;

import java.util.Scanner;

public class Store {

    private Warehouse warehouse;
    private Scanner scanner;

    public Store(Warehouse warehouse, Scanner scanner) {
        this.warehouse = warehouse;
        this.scanner = scanner;
    }

    // the method that handles the customers visit to the store.
    public void shop(String customer) {
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Welcome to the store " + customer);
        System.out.println("our selection:");

        for (String product : this.warehouse.products()) {
            System.out.println(product);
        }

        while (true) {
            System.out.print("What to put in the cart (press enter to go to the register): ");
            String product = scanner.nextLine();
            if (product.isEmpty()) {
                break;
            }
            if (this.warehouse.take(product)) {
                cart.add(product, this.warehouse.price(product));
            } else {
                System.out.println("Sorry, we don't have " + product);
            }
        }

        System.out.println("Your shopping cart contents:");
        cart.print();
        System.out.println("total: " + cart.price());
    }

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct("Coffee", 5, 10);
        warehouse.addProduct("Tea", 3, 20);
        warehouse.addProduct("Milk", 4, 15);

        Scanner scanner = new Scanner(System.in);
        Store store = new Store(warehouse, scanner);
        store.shop("Alice");
    }
}