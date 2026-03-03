package rvt;

public class Box {
    private double maxWeight;
    private java.util.List<Packable> contents = new java.util.ArrayList<>();

    public Box(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void add(Packable item) {
        if (item == null) return;
        if (weight() + item.weight() <= maxWeight) {
            contents.add(item);
        }
    }

    public double weight() {
        double total = 0;
        for (Packable p : contents) {
            total += p.weight();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Box: " + weight() + " kg (" + contents.size() + " items)";
    }
}
