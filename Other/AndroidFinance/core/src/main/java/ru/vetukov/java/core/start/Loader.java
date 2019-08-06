package ru.vetukov.java.core.start;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import ru.vetukov.java.core.dao.impls.OperationDAOImpl;
import ru.vetukov.java.core.dao.impls.SourceDAOImpl;
import ru.vetukov.java.core.dao.impls.StorageDAOImpl;
import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.decorator.OperationSync;
import ru.vetukov.java.core.decorator.SourceSync;
import ru.vetukov.java.core.decorator.StorageSync;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.impls.DefaultSource;
import ru.vetukov.java.core.impls.DefaultStorage;
import ru.vetukov.java.core.impls.operations.IncomeOperation;
import ru.vetukov.java.core.interfaces.Operation;
import ru.vetukov.java.core.interfaces.Source;
import ru.vetukov.java.core.interfaces.Storage;

public class Loader {

    private static SourceSync source;
    private static StorageSync storage;
    private static OperationSync operation;

    public static void main(String[] args) {

        source = new SourceSync(new SourceDAOImpl());
        storage = new StorageSync(new StorageDAOImpl());
        operation = new OperationSync(new OperationDAOImpl(source.getIdentityMap()
                                                          ,storage.getIdentityMap())
                                     ,source
                                     ,storage);


//        testStorage();
//        testSource();
        testOperation();
        System.out.println("End");
    }

    private static void testStorage() {
        // Test Sotorage
        // Создание
//        Storage parent = storage.get(2);
//        DefaultStorage un = new DefaultStorage("Юникредит");
//        un.setParent(parent);
//        storage.add(un);

        // Обновление
//        Storage storUpdate = storage.get(33);
//        storUpdate.setName("Юникредит");
//        storage.update(storUpdate);

        // Удаление
//        Storage storUpdate = storage.get(33);
//        storage.delete(storUpdate);

//        System.out.println("Storage end.");
    }

    private static void testSource() {
        //TODO: Test Source
        // Создание
//        Source parentSource = source.get(8);
//        DefaultSource so = new DefaultSource("Что-то");
//        so.setParent(parentSource);
//        source.add(so);

        // Обновление
//        Source so = source.get(19);
//        System.out.println(so.getName());
//        so.setName("С чем-то");
//        System.out.println(so.getName());
//        source.update(so);

        // Удаление
//        Source so = source.get(19);
//        source.delete(so);

//        System.out.println("Source end");
    }

    private static void testOperation() {
        //TODO: Test Operation
        try {
            Storage to = storage.get(9);    // Эллектронные деньги
            Source from = source.get(11);   // Аванс

//            storage.addCurrency(to, Currency.getInstance("RUB"), new BigDecimal(0));

            IncomeOperation income = new IncomeOperation();
            income.setToStorage(to);
            income.setFromSource(from);
            income.setFromCurrency(to.getCurrency("RUB"));
            income.setFromAmount(new BigDecimal(250));
            income.setDateTime(Calendar.getInstance());

            operation.add(income);

        } catch (CurrencyException e) {
            e.printStackTrace();
        }
    }
}
