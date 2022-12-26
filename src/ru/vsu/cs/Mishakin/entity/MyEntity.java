package ru.vsu.cs.Mishakin.entity;

import java.util.List;

public interface MyEntity {

    String getTableName();

    int getCountArg();

    void setAll(List<Object> valueField);
}
