package ru.vetukov.java.core.database;

import ru.vetukov.java.core.dao.impls.OperationDAOImpl;
import ru.vetukov.java.core.dao.impls.SourceDAOImpl;
import ru.vetukov.java.core.dao.impls.StorageDAOImpl;
import ru.vetukov.java.core.decorator.OperationSync;
import ru.vetukov.java.core.decorator.SourceSync;
import ru.vetukov.java.core.decorator.StorageSync;

public class Initializer {

    private static OperationSync operationSync;
    private static StorageSync storageSync;
    private static SourceSync sourceSync;

    public static OperationSync getOperationSync() {
        return operationSync;
    }

    public static StorageSync getStorageSync() {
        return storageSync;
    }

    public static SourceSync getSourceSync() {
        return sourceSync;
    }

    public static void load(String driverName, String url) {

        SQLiteConnection.init(driverName, url);

        // последовательность создания объектов - важна (сначала справочные значения, потом операции)
        sourceSync = new SourceSync(new SourceDAOImpl());
        storageSync = new StorageSync(new StorageDAOImpl());
        operationSync = new OperationSync(new OperationDAOImpl(sourceSync.getIdentityMap(), storageSync.getIdentityMap()), sourceSync, storageSync);

    }
}
