package rvt;

public class Book implements Packable {
    private String author;
    private String title;
    private double weight;

    public Book(String author, String title, double weight) {
        this.author = author;
        this.title = title;
        this.weight = weight;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return author + ": " + title;
    }
}
