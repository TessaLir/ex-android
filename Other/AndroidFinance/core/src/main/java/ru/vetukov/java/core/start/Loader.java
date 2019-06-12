package ru.vetukov.java.core.start;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;

import ru.vetukov.java.core.dao.impls.SourceDAOImpl;
import ru.vetukov.java.core.dao.impls.StorageDAOImpl;
import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.decorator.SourceSync;
import ru.vetukov.java.core.decorator.StorageSync;
import ru.vetukov.java.core.exceptions.AmountException;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.impls.DefaultStorage;
import ru.vetukov.java.core.interfaces.Source;
import ru.vetukov.java.core.interfaces.Storage;
import ru.vetukov.java.core.interfaces.TreeNode;

public class Loader {

    public static void main(String[] args) {
        
    }

}
