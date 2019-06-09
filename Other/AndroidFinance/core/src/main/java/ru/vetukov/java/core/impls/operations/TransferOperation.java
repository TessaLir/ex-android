package ru.vetukov.java.core.impls.operations;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import ru.vetukov.java.core.abstracts.AbstractOperation;
import ru.vetukov.java.core.interfaces.Storage;

public class TransferOperation extends AbstractOperation {

    private Storage fromStorage;
    private Storage toStorage;
    private BigDecimal amoount;
    private Currency currency;

    public TransferOperation(Storage fromStorage, Storage toStorage, BigDecimal amoount, Currency currency) {
        this.fromStorage = fromStorage;
        this.toStorage = toStorage;
        this.amoount = amoount;
        this.currency = currency;
    }

    public TransferOperation(long id, Calendar dateTime, String addInfo, Storage fromStorage, Storage toStorage, BigDecimal amoount, Currency currency) {
        super(id, dateTime, addInfo);
        this.fromStorage = fromStorage;
        this.toStorage = toStorage;
        this.amoount = amoount;
        this.currency = currency;
    }

    public TransferOperation(long id, Storage fromStorage, Storage toStorage, BigDecimal amoount, Currency currency) {
        super(id);
        this.fromStorage = fromStorage;
        this.toStorage = toStorage;
        this.amoount = amoount;
        this.currency = currency;
    }

    public TransferOperation(Calendar dateTime, String addInfo, Storage fromStorage, Storage toStorage, BigDecimal amoount, Currency currency) {
        super(dateTime, addInfo);
        this.fromStorage = fromStorage;
        this.toStorage = toStorage;
        this.amoount = amoount;
        this.currency = currency;
    }

    public Storage getFromStorage() {
        return fromStorage;
    }

    public void setFromStorage(Storage fromStorage) {
        this.fromStorage = fromStorage;
    }

    public Storage getToStorage() {
        return toStorage;
    }

    public void setToStorage(Storage toStorage) {
        this.toStorage = toStorage;
    }

    public BigDecimal getAmoount() {
        return amoount;
    }

    public void setAmoount(BigDecimal amoount) {
        this.amoount = amoount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
