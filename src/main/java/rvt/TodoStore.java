package rvt;

public interface TodoStore {
    void add(String task);

    int size();

    String get(int index);

    void print();

    void remove(int index);
}