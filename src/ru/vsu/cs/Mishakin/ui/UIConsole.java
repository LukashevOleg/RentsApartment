package ru.vsu.cs.Mishakin.ui;

import ru.vsu.cs.Mishakin.FuncInterface.QueryFilter;
import ru.vsu.cs.Mishakin.entity.MyEntity;
import ru.vsu.cs.Mishakin.facotry.EntityFactory;
import ru.vsu.cs.Mishakin.query.QueryBuilder;
import ru.vsu.cs.Mishakin.query.QueryEnum;
import ru.vsu.cs.Mishakin.repository.EntityRepository;

import java.util.*;

public class UIConsole {

    private Scanner scanner = new Scanner(System.in);
    private EntityRepository entityRepository = new EntityRepository();
    private QueryFilter filter;

    public void start(){
        System.out.println("Что хотите сделать? " +
                "\n 1 - Вывести список " +
                "\n 2 - Добавить сущность " +
                "\n 3 - Обновить значение у сущности " +
                "\n 4 - Удалить сущность/сущности");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> list();
            case 2 -> create();
            case 3 -> update();
            case 4 -> delete();
        }
    }


    public void list(){
        System.out.println("Список чего вы хотите вывести? " +
                "\n 1 - Clients " +
                "\n 2 - Flats" +
                "\n 3 - Rents" +
                "\n 4 - Additional services");
        int choiceWhichEntityList = scanner.nextInt();
        String entityName = "";
        switch (choiceWhichEntityList) {
            case 1 -> entityName = "Client";
            case 2 -> entityName = "Flat";
            case 3 -> entityName = "Rent";
            case 4 -> entityName = "AdditionalServices";
        }
        System.out.println("Хотите добавить фильтр? " +
                "\n 1 - Да " +
                "\n 2 - Нет");
        int choiceIsFilter = scanner.nextInt();
        if (choiceIsFilter == 1) {
            addFilter();
        }
        List<MyEntity> entityList = entityRepository.getList(entityName, filter);
        System.out.println(entityList);
    }

    private void addFilter(){
        filter = (tableName) -> {
            QueryBuilder query = QueryBuilder.createQuery();
            System.out.println("Ведите параметр, по которому будет фильтрация ");
            String columnFilter = scanner.next();
            query.add(QueryEnum.WHERE.getEXTENSION_QUERY(), new ArrayList<>());
            System.out.println("Введите:" +
                    "\n 1 - > " +
                    "\n 2 - <" +
                    "\n 3 - =");
            int choiceWhichSign = scanner.nextInt();
            switch (choiceWhichSign) {
                case 1 -> query.add(QueryEnum.MORE.getEXTENSION_QUERY(), Arrays.asList(tableName, columnFilter));
                case 2 -> query.add(QueryEnum.LESS.getEXTENSION_QUERY(), Arrays.asList(tableName, columnFilter));
                case 3 -> query.add(QueryEnum.EQUALS.getEXTENSION_QUERY(), Arrays.asList(tableName, columnFilter));
            }
            System.out.println("Введите значение ");
            String value = scanner.next();
            query.add(value);
            return query.getQuery();
        };
    }

    public void create(){
        System.out.println("Какую сущность хотите добавить? " +
                "\n 1 - Client " +
                "\n 2 - Additional Services In Rent" +
                "\n 3 - Additional service");
        int choiceWhichEntityList = scanner.nextInt();
        String className = "";
        switch (choiceWhichEntityList) {
            case 1 -> className = "Client";
            case 2 -> className = "AdditionalServicesInRent";
            case 3 -> className = "AdditionalServices";
        }
        List<Object> value = new ArrayList<>();
        int argCount = new EntityFactory().createEntity(className).getCountArg();
        System.out.println(argCount);
        System.out.println("Вводите аргументы по порядку, не включая autoincrement. Всего " + (argCount-1));
        for (int i = 0; i < argCount-1; i++){
            Object o = null;
            String line = scanner.next();
            try {
                o = Integer.valueOf(line);
                System.out.println("int");
            } catch (NumberFormatException e) {
                System.out.println( );
            } finally {
                if (o == null)
                    o = line;
            }
            value.add(o);
        }
        entityRepository.createEntity(className, value);
    }


    public void update(){

        System.out.println("В какой сущности хотите изменить поле? " +
                "\n 1 - Client " +
                "\n 2 - Additional Services In Rent" +
                "\n 3 - Additional service" +
                "\n 3 - Flat");
        int choiceWhichEntityList = scanner.nextInt();
        String className = "";
        switch (choiceWhichEntityList) {
            case 1 -> className = "Client";
            case 2 -> className = "AdditionalServicesInRent";
            case 3 -> className = "AdditionalServices";
            case 4 -> className = "Flat";
        }
        System.out.println("Введите название колонки как в таблице");
        String columnName = scanner.next();
        System.out.println("Введите значение");
        String value = scanner.next();
        entityRepository.update(className, columnName, value, filter);
    }


    public void delete(){
        System.out.println("Что хотите удалить? " +
                "\n 1 - Client " +
                "\n 2 - Additional Services In Rent" +
                "\n 3 - Additional service" +
                "\n 3 - Flat");
        int choiceWhichEntityList = scanner.nextInt();
        String className = "";
        switch (choiceWhichEntityList) {
            case 1 -> className = "Client";
            case 2 -> className = "AdditionalServicesInRent";
            case 3 -> className = "AdditionalServices";
            case 4 -> className = "Flat";
        }
        addFilter();
        entityRepository.delete(className, filter);
    }

}
