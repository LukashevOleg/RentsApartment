package ru.vsu.cs.Mishakin.query;

public enum QueryEnum {

    SELECT("%s SELECT * FROM \"%s\" %s"),
    ORDER_BY_ID("%s ORDER BY %s.id "),
    ORDER_BY_DATE("%s ORDER BY %s.date"),
    ID_EQUALS("%s %s.id = %d"),
    INNER_JOIN("%s INNER JOIN \"%s\" %s ON %s.%s = %s.id"),
    WHERE("%s WHERE"),
    AND("%s AND"),
    SOME_MORE_THAN_COUNT("%s %s.%s > %d"),
    INSERT("%s INSERT INTO \"%s\" VALUES"),
    INSERT_VALUES_START("%s (default"),
    INSERT_VALUES_CONTINUE("%s ,?"),
    INSERT_VALUES_END("%s )"),
    UPDATE("%s UPDATE \"%s\" %s"),
    SET("%s SET"),
    ASSIGNING("%s %s = ?"),
    EQUALS("%s %s.%s = "),
    MORE("%s %s.%s > "),
    LESS("%s %s.%s < "),
    DELETE("%s DELETE FROM \"%s\" %s")
    ;


    String EXTENSION_QUERY;

    public String getEXTENSION_QUERY() {
        return EXTENSION_QUERY;
    }


    QueryEnum(String EXTENSION_QUERY) {
        this.EXTENSION_QUERY = EXTENSION_QUERY;

    }
}

