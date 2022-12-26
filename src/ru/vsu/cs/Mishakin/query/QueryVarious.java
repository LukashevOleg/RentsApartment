package ru.vsu.cs.Mishakin.query;

import ru.vsu.cs.Mishakin.FuncInterface.QueryFilter;
import ru.vsu.cs.Mishakin.entity.InfoEntityTable;

import java.util.*;

public class QueryVarious {

    public static String selectAll(String tableName){
        return QueryBuilder.createQuery()
                .add(QueryEnum.SELECT.EXTENSION_QUERY, Arrays.asList(tableName, tableName))
                .getQuery();
    }

    public static String selectAll(List<String> tableList){
        String firstTable = tableList.remove(0);
        Map<String, String> mapForeignKeyToTable = InfoEntityTable.getMapForeignKeys().get(firstTable);

        List<String> listForeignKey = new ArrayList<>();
        for(String str : tableList){
            listForeignKey.add(mapForeignKeyToTable.get(str));
        }

        QueryBuilder query = QueryBuilder.createQuery()
                .add(QueryEnum.SELECT.EXTENSION_QUERY, Arrays.asList(firstTable, firstTable));


        Iterator<String> listForeignKeyIterator = listForeignKey.iterator();
        Iterator<String> tableListIterator = tableList.iterator();



        while (tableListIterator.hasNext() && listForeignKeyIterator.hasNext()) {
            String curTable = tableListIterator.next();
            query.add(QueryEnum.INNER_JOIN.getEXTENSION_QUERY(), Arrays.asList(curTable,
                    curTable, firstTable, listForeignKeyIterator.next(), curTable)
            );
        }
        return query.getQuery();
    }

    public static String insertInto(String tableName, int fieldCount){
        QueryBuilder query = QueryBuilder.createQuery()
            .add(QueryEnum.INSERT.getEXTENSION_QUERY(), Collections.singletonList(tableName))
            .add(QueryEnum.INSERT_VALUES_START.getEXTENSION_QUERY(), new ArrayList<>());
        for(int i = 1; i < fieldCount; i++)
            query.add(QueryEnum.INSERT_VALUES_CONTINUE.getEXTENSION_QUERY(), new ArrayList<>());
        query.add(QueryEnum.INSERT_VALUES_END.getEXTENSION_QUERY(), new ArrayList<>());
        return query.getQuery();
    }

    public static String update(String tableName,  String field){
        return QueryBuilder.createQuery()
                .add(QueryEnum.UPDATE.getEXTENSION_QUERY(), Arrays.asList(tableName, tableName))
                .add(QueryEnum.SET.getEXTENSION_QUERY(), new ArrayList<>())
                .add(QueryEnum.ASSIGNING.getEXTENSION_QUERY(), Collections.singletonList(field))
                .getQuery();
    }

    public static String delete(String tableName){
        return QueryBuilder.createQuery()
                .add(QueryEnum.DELETE.getEXTENSION_QUERY(), Arrays.asList(tableName, tableName))
                .getQuery();
    }
}

