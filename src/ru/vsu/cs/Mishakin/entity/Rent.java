package ru.vsu.cs.Mishakin.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Rent implements MyEntity {

    private int id;
    private int idFlat;
    private int idClient;
    private Date dateArrive;
    private Date dateLeave;

    public Rent() {}

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", idFlat=" + idFlat +
                ", idClient=" + idClient +
                ", dateArrive=" + dateArrive +
                ", dateLeave=" + dateLeave +
                '}';
    }

    @Override
    public String getTableName() {
        return "Rents";
    }

    

    @Override
    public int getCountArg() {
        return 5;
    }

    @Override
    public void setAll(List<Object> valueField) {
        Iterator<Object> valueListIterator = valueField.iterator();
        this.id = (int) valueListIterator.next();
        this.idFlat = (int) valueListIterator.next();
        this.idClient = (int) valueListIterator.next();
        this.dateArrive = (Date) valueListIterator.next();
        this.dateLeave = (Date) valueListIterator.next();
    }
}
