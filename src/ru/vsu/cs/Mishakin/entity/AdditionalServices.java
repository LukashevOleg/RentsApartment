package ru.vsu.cs.Mishakin.entity;

import java.util.Iterator;
import java.util.List;

public class AdditionalServices implements MyEntity{
    private int id;
    private String name;
    private int price;

    public AdditionalServices() {}

    @Override
    public String getTableName() {
        return "Additional_services";
    }

    @Override
    public String toString() {
        return "AdditionalServices{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int getCountArg() {
        return 3;
    }

    @Override
    public void setAll(List<Object> valueField) {
        Iterator<Object> valueListIterator = valueField.iterator();
        this.id = (int) valueListIterator.next();
        this.name = (String) valueListIterator.next();
        this.price = (int) valueListIterator.next();

    }
}
