package model;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObser(Observer obs);
}
