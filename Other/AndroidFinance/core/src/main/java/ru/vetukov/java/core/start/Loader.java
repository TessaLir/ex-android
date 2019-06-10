package ru.vetukov.java.core.start;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;
import java.util.List;

import ru.vetukov.java.core.dao.impls.StorageDAOImpl;
import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.decorator.StorageSynchronizer;
import ru.vetukov.java.core.exceptions.AmountException;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.impls.DefaultStorage;
import ru.vetukov.java.core.interfaces.Storage;
import ru.vetukov.java.core.interfaces.TreeNode;

public class Loader {

    private static StringBuffer sb;
    private static int level = 0;

    public static void main(String[] args) {
//        first();
//        second();
//        third();

        StorageSynchronizer storageSync = new StorageSynchronizer(new StorageDAOImpl());
        DefaultStorage tmpStore = (DefaultStorage) storageSync.getAll().get(1).getChild().get(0);

        try {
            storageSync.addCurrency(tmpStore, Currency.getInstance("USD"));
            System.out.println("Storage.getAll() = " + storageSync.getAll());
        } catch (CurrencyException e) {
            e.printStackTrace();
        }


    }

    private static void runStorage(Storage s) {
        if (!s.hasChilds()) {
            println(s);
        }
        else {
            println(s);
            level++;
            for (TreeNode t : s.getChild()) {
                runStorage((Storage)t);
            }
            level--;
        }
    }

    private static void println(Storage s) {
        for (int i = 0; i < level; i++) sb.append("\t");
        sb.append(s.getName() + "\n");
    }

    private static void first() {
        try {
            DefaultStorage storage = new DefaultStorage();

            Currency currencyESD = Currency.getInstance("USD");
            Currency currencyRUB = Currency.getInstance("RUB");

            storage.addCurrency(currencyRUB);
            storage.addCurrency(currencyESD);

            storage.addAmount(new BigDecimal(100), currencyRUB);
            storage.addAmount(new BigDecimal(150), currencyRUB);

            storage.expenseAmount(new BigDecimal(150), currencyRUB);

            System.out.println(storage.getAmount(currencyRUB));
            System.out.println("storage = " + storage.getAvailableCurrencies());


        } catch (CurrencyException e) {
            e.printStackTrace();
        } catch (AmountException e) {
            e.printStackTrace();
        }
    }

    private static void second() {
        try (
                Statement stmt = SQLiteConnection.getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("select * from storage")) {
            while (rs.next()) {
                String line = rs.getInt("id") + " - " + rs.getString("name");
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void third() {
        List<Storage> storages = new StorageDAOImpl().getAll();

        sb = new StringBuffer();

        for (Storage s : storages) {
            runStorage(s);
        }

        System.out.println(sb.toString());
    }
}
