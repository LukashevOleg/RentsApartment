package ru.vsu.cs.Mishakin.entity;

import java.util.Iterator;
import java.util.List;

public class Client implements MyEntity{
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    private int number;

    public Client() {}


    @Override
    public String getTableName() {
        return "Clients";
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
        this.number = (int) valueListIterator.next();
    }

}
