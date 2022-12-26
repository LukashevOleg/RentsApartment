package ru.vsu.cs.Mishakin.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JoinEntity implements MyEntity {

    List<MyEntity> listEntity = new ArrayList<>();

    public JoinEntity() {}

    @Override
    public String getTableName() {
        return "";
    }

    @Override
    public int getCountArg() {
        return 1;
    }
    @Override
    public void setAll(List<Object> valueField) {
        for (Object o : valueField)
            this.listEntity.add((MyEntity) o);
    }
}
