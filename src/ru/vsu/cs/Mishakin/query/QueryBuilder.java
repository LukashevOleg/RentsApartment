package ru.vsu.cs.Mishakin.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class QueryBuilder {
    private String query = "";

    public static QueryBuilder createQuery(){
        return new QueryBuilder();
    }

    public QueryBuilder add(String str, List<Object> parameterValuesList){
        List<Object> parameterValues = new ArrayList<>(parameterValuesList);
        List<String> listQueryParts = Arrays.asList(str.split("(?=%)"));
        parameterValues.add(0, getQuery());
        Iterator<Object> parameterValuesIterator = parameterValues.iterator();
        Iterator<String> listValuesIterator = listQueryParts.iterator();
        StringBuilder curStr = new StringBuilder();
        while (parameterValuesIterator.hasNext() ){
            curStr.append(String.format(listValuesIterator.next(), parameterValuesIterator.next()));
        }
        setQuery(curStr.toString());
        return this;
    }

    public QueryBuilder add(Object o){
        setQuery(getQuery() + o);
        return this;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery(){
        return query;
    }


}
