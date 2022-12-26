package ru.vsu.cs.Mishakin.facotry;

import ru.vsu.cs.Mishakin.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityFactory {
//    private Map<String,? extends MyEntity> entityMap = new HashMap<>(){{
//        put("AdditionalServices", new AdditionalServices());
//        put("AdditionalServicesInLent", new AdditionalServicesInRent());
//        put("Client", new Client());
//        put("Flat", new Flat());
//        put("Rent", new Rent());
//        put("JoinEntity", new JoinEntity());
//    }};

    private MyEntity getInstanceEntity(String className){
        return switch (className) {
            case "AdditionalServices" -> new AdditionalServices();
            case "AdditionalServicesInRent" -> new AdditionalServicesInRent();
            case "Client" -> new Client();
            case "Flat" -> new Flat();
            case "Rent" -> new Rent();
            case "JoinEntity" -> new JoinEntity();
            default -> null;
        };
    }

    public MyEntity createEntity(String className){
        return getInstanceEntity(className);
    }

    public MyEntity createEntity(String className, ResultSet resultSet) throws SQLException {
        MyEntity entity = getInstanceEntity(className);
        List<Object> valueArg = new ArrayList<>();

        int countArg = entity.getCountArg();
        for(int i=0, k=1; i< countArg;k++, i++){
            valueArg.add(resultSet.getObject(k));
        }

        entity.setAll(valueArg);
        return entity;
    }
}
