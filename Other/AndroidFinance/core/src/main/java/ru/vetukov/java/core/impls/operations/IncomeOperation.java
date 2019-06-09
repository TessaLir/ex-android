package ru.vetukov.java.core.impls.operations;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import ru.vetukov.java.core.abstracts.AbstractOperation;
import ru.vetukov.java.core.interfaces.Source;
import ru.vetukov.java.core.interfaces.Storage;

//TODO для всех классов создать конструкторы, без поля id, так как оно autoincrement
public class IncomeOperation extends AbstractOperation {

    private Source fromSource;
    private Storage toStorage;
    private BigDecimal amount;
    private Currency currency;

    public IncomeOperation() {
    }

    public IncomeOperation(Calendar dateTime, String addInfo, Source fromSource, Storage toStorage, BigDecimal amount, Currency currency) {
        super(dateTime, addInfo);
        this.fromSource = fromSource;
        this.toStorage = toStorage;
        this.amount = amount;
        this.currency = currency;
    }

    public IncomeOperation(Source fromSource, Storage toStorage, BigDecimal amount, Currency currency) {
        this.fromSource = fromSource;
        this.toStorage = toStorage;
        this.amount = amount;
        this.currency = currency;
    }

    public IncomeOperation(long id, Calendar dateTime, String addInfo, Source fromSource, Storage toStorage, BigDecimal amount, Currency currency) {
        super(id, dateTime, addInfo);
        this.fromSource = fromSource;
        this.toStorage = toStorage;
        this.amount = amount;
        this.currency = currency;
    }

    public IncomeOperation(long id, Source fromSource, Storage toStorage, BigDecimal amount, Currency currency) {
        super(id);
        this.fromSource = fromSource;
        this.toStorage = toStorage;
        this.amount = amount;
        this.currency = currency;
    }

    public Source getFromSource() {
        return fromSource;
    }

    public void setFromSource(Source fromSource) {
        this.fromSource = fromSource;
    }

    public Storage getToStorage() {
        return toStorage;
    }

    public void setToStorage(Storage toStorage) {
        this.toStorage = toStorage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
