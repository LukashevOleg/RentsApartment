package ru.vsu.cs.Mishakin.repository;

import ru.vsu.cs.Mishakin.FuncInterface.QueryFilter;
import ru.vsu.cs.Mishakin.db.ConnectionOnly;
import ru.vsu.cs.Mishakin.entity.*;
import ru.vsu.cs.Mishakin.facotry.EntityFactory;
import ru.vsu.cs.Mishakin.query.QueryBuilder;
import ru.vsu.cs.Mishakin.query.QueryVarious;

import java.sql.*;
import java.util.*;

public class EntityRepository {
    private Connection con;

    private final EntityFactory entityFactory;

    public EntityRepository() {
        try {
            before();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.entityFactory = new EntityFactory();
        }
    }


    private void before()throws SQLException {
        con = ConnectionOnly.getConnection();
    }


    public List<MyEntity> getList(String className, QueryFilter queryFilter){
        List<MyEntity> entityList = new ArrayList<>();
        String tableName = entityFactory.createEntity(className).getTableName();
        String query = QueryVarious.selectAll(tableName);
        if (queryFilter != null)
            query += queryFilter.generateQueryFilter(tableName);
        try(PreparedStatement statement = con.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                entityList.add(entityFactory.createEntity(className,resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }


    public List<MyEntity> getList(List<String> listEntityName, QueryFilter queryFilter){
        List<MyEntity> entityList = new ArrayList<>();
        for(String str : listEntityName)
            entityList.add(entityFactory.createEntity(str));
        List<String> listTableName = new ArrayList<>();
        for(MyEntity en : entityList)
            listTableName.add(en.getTableName());
        try (Statement statement = con.createStatement()) {
            String query = QueryVarious.selectAll(listTableName);
            System.out.println(query);
            if (queryFilter != null)
                query += queryFilter.generateQueryFilter(listTableName.get(0));
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                entityList.add(entityFactory.createEntity(listEntityName.get(0),resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }


    public void createEntity(String className, List<Object> valueList){
        MyEntity en = entityFactory.createEntity(className);
        String tableName = en.getTableName();
        int argCount = en.getCountArg();
        String query = QueryVarious.insertInto(tableName, argCount);
        Iterator<Object> valueListIterator = valueList.iterator();
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
            for(int i = 1; i < argCount; i++)
                preparedStatement.setObject(i, valueListIterator.next());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(String className, String field, Object value, QueryFilter queryFilter){
        MyEntity en = entityFactory.createEntity(className);
        String tableName = en.getTableName();
        String query = QueryVarious.update(tableName, field);
        if (queryFilter != null)
            query += queryFilter.generateQueryFilter(tableName);
        System.out.println(query);
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(String className, QueryFilter queryFilter){
        MyEntity en = entityFactory.createEntity(className);
        String tableName = en.getTableName();
        String query = QueryVarious.delete(tableName);
        if (queryFilter != null)
            query += queryFilter.generateQueryFilter(tableName);
        System.out.println(query);

        try(PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
