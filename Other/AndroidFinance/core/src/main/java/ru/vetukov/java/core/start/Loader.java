package ru.vetukov.java.core.start;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;

import ru.vetukov.java.core.dao.impls.OperationDAOImpl;
import ru.vetukov.java.core.dao.impls.SourceDAOImpl;
import ru.vetukov.java.core.dao.impls.StorageDAOImpl;
import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.decorator.OperationSync;
import ru.vetukov.java.core.decorator.SourceSync;
import ru.vetukov.java.core.decorator.StorageSync;
import ru.vetukov.java.core.enums.OperationType;
import ru.vetukov.java.core.exceptions.AmountException;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.impls.DefaultSource;
import ru.vetukov.java.core.impls.DefaultStorage;
import ru.vetukov.java.core.interfaces.Source;
import ru.vetukov.java.core.interfaces.Storage;
import ru.vetukov.java.core.interfaces.TreeNode;

public class Loader {

    public static void main(String[] args) {
        StorageSync storageSync = new StorageSync(new StorageDAOImpl());
        SourceSync sourceSync = new SourceSync(new SourceDAOImpl());
        OperationSync operationSync = new OperationSync(new OperationDAOImpl(sourceSync.getIdentityMap(), storageSync.getIdentityMap()), sourceSync, storageSync);

//        testSource(sourceSync);

        DefaultStorage storage = new DefaultStorage("def store");

        Storage parentStorage = storageSync.get(10);

        try {
            storage.addCurrency(Currency.getInstance("USD"), new BigDecimal(145));
            storage.addCurrency(Currency.getInstance("RUB"), new BigDecimal(199));

            storage.setParent(parentStorage);

            storageSync.add(storage);

            storageSync.deleteCurrency(storage, Currency.getInstance("USD"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void testSource(SourceSync sourceSync) {
        Source parentSource = sourceSync.get(4);

        DefaultSource s = new DefaultSource("test source");
//        s.setOperationType(OperationType.OUTCOME);
        s.setParent(parentSource);

        sourceSync.add(s);
        System.out.println("sourceSync = " + sourceSync.getAll());
        System.out.println("---");
    }


}
