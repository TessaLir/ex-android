package ru.vetukov.java.core.impls.operations;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import ru.vetukov.java.core.abstracts.AbstractOperation;
import ru.vetukov.java.core.enums.OperationType;
import ru.vetukov.java.core.interfaces.Storage;

// перевод из одного хранилища в другое в одной валюте
public class TransferOperation extends AbstractOperation{

    public TransferOperation() {
        super(OperationType.TRANSFER);
    }

    private Storage fromStorage;// откуда переводим
    private Storage toStorage; // куда переводим
    private BigDecimal fromAmount;// сумма перевода
    private Currency fromCurrency;// в какой валюте получили деньги

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

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
}
