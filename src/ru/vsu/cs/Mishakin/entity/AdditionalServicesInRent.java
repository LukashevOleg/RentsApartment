package ru.vsu.cs.Mishakin.entity;

import java.util.Iterator;
import java.util.List;

public class AdditionalServicesInRent implements MyEntity{
    private int idAdditionalServices;
    private int idRent;
    private int price;

    public AdditionalServicesInRent() {}



    @Override
    public int getCountArg() {
        return 3;
    }

    @Override
    public String getTableName() {
        return "Additional_services_in_rent";
    }
    @Override
    public void setAll(List<Object> valueField) {
        Iterator<Object> valueListIterator = valueField.iterator();
        this.price = (int) valueListIterator.next();
        this.idRent = (int) valueListIterator.next();
        this.idAdditionalServices = (int) valueListIterator.next();
    }
}
