package ru.vetukov.java.core.dao.interfaces;

import java.math.BigDecimal;
import java.util.Currency;

import ru.vetukov.java.core.interfaces.Storage;

public interface StorageDAO extends CommonDAO<Storage>{

    // boolean - что бы удостовериться, что операция  прошла успешно.
    boolean addCurrency(Storage storage, Currency currency);
    boolean updateCurrency(Storage storage, BigDecimal amount); // Тут и прибавить, и отнять, и обновить.
    boolean deleteCurrency(Storage storage, Currency currency);

}
