package ru.vetukov.java.core.start;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;

import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.exceptions.AmountException;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.impls.DefaultStorage;

public class Loader {
    public static void main(String[] args) {
//        first();

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
}
