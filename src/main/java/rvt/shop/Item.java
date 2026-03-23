package rvt.shop;

public class Item {
    private String product;
    private int qty;
    private int unitPrice;

    public Item(String product, int qty, int unitPrice) {
        this.product = product;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public int price() {
        return this.qty * this.unitPrice;
    }

    public void increaseQty() {
        this.qty++;
    }

    public String toString() {
        return this.product + ": " + this.qty;
    }

    public static void main(String[] args) {
        Item item = new Item("Coffee", 2, 5);
        System.out.println(item);
        System.out.println("Price: " + item.price());
        item.increaseQty();
        System.out.println("After increasing quantity: " + item);
        System.out.println("New Price: " + item.price());
    }
}
