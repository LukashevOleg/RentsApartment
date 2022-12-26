package ru.vsu.cs.Mishakin.entity;

import java.util.Iterator;
import java.util.List;

public class Flat implements MyEntity {
    private int id;
    private String address;
    private int roomCount;
    private int priceOneDay;

    public Flat() {}

    @Override
    public String getTableName() {
        return "Flats";
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", roomCount=" + roomCount +
                ", priceOneDay=" + priceOneDay +
                '}';
    }

    @Override
    public int getCountArg() {
        return 4;
    }

    @Override
    public void setAll(List<Object> valueField) {
        Iterator<Object> valueListIterator = valueField.iterator();
        this.id = (int) valueListIterator.next();
        this.roomCount = (int) valueListIterator.next();
        this.priceOneDay = (int) valueListIterator.next();
        this.address = (String) valueListIterator.next();
    }
}
