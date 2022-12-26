package ru.vsu.cs.Mishakin.entity;

import java.util.HashMap;
import java.util.Map;

public class InfoEntityTable {

    private static final Map<String, String> additionalServicesInRentForeignKey = new HashMap<>(){{
        put("Rents", "rent_id");
        put("AdditionalServices", "additional_services_id");
    }};
    private static final Map<String, String> rentForeignKey = new HashMap<>(){{
        put("Flats", "flat_id");
        put("Clients", "client_id");
    }};

    private static final Map<String, Map<String, String>> mapForeignKeys = new HashMap<>(){{
        put("Clients", null);
        put("Additional_services_in_rent", additionalServicesInRentForeignKey);
        put("Rents", rentForeignKey);
        put("Flats", null);
        put("AdditionalServices", null);
    }};

    public static Map<String, Map<String, String>> getMapForeignKeys(){
        return mapForeignKeys;
    }


}
