package ru.vetukov.java.core.dao.interfaces;

import java.math.BigDecimal;
import java.util.Currency;

import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.interfaces.Storage;

public interface StorageDAO extends CommonDAO<Storage>{

    // boolean - что бы удостовериться, что операция  прошла успешно.
    boolean addCurrency(Storage storage, Currency currency, BigDecimal initAmount) throws CurrencyException;
    boolean updateAmount(Storage storage, Currency currency, BigDecimal amount); // Тут и прибавить, и отнять, и обновить.
    boolean deleteCurrency(Storage storage, Currency currency) throws CurrencyException;

}
