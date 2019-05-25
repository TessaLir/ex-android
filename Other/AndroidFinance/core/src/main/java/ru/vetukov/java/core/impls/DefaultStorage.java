package ru.vetukov.java.core.impls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.vetukov.java.core.exceptions.AmountException;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.interfaces.Storage;

public class DefaultStorage implements Storage {

    private String name;

    // Инициализируем пустые коллекции, потому что хоть одна валюта, но будет.
    private Map<Currency, BigDecimal> currencyAmount = new HashMap<>();
    private List<Currency> currencyList = new ArrayList<>();

    public DefaultStorage(){}
    public DefaultStorage(String name) {
        this.name = name;
    }
    public DefaultStorage(List<Currency> currencyList, Map<Currency, BigDecimal> currencyAmount, String name) {
        this.currencyList = currencyList;
        this.currencyAmount = currencyAmount;
        this.name = name;
    }
    public DefaultStorage(Map<Currency, BigDecimal> currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    @Override
    public Map<Currency, BigDecimal> getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Map<Currency, BigDecimal> currwncyAmount) {
        this.currencyAmount = currwncyAmount;
    }

    @Override
    public List<Currency> getAvailableCurrencies() {
        return currencyList;
    }

    public void setAcailableCurrencies(List<Currency> acailableCurrencies) {
        this.currencyList = acailableCurrencies;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public BigDecimal getAmount(Currency currency) throws CurrencyException {
        checkCurrencyExist(currency); // в Spring через АОР легче внедрять повторяющиеся участки кода
        return currencyAmount.get(currency);
    }

    @Override
    public BigDecimal getApproxAmount(Currency currency) {
        //TODO: реализовать расчет остатка с приведением в одну валюту реализуем позже
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        currencyAmount.put(currency, amount);
    }

    @Override
    public void addAmount(BigDecimal amount, Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);
        BigDecimal oldAmount = currencyAmount.get(currency);
        currencyAmount.put(currency, oldAmount.add(amount));
    }

    private void checkCurrencyExist(Currency currency) throws CurrencyException {
        if (!currencyAmount.containsKey(currency)) {
            throw new CurrencyException("Currency "+currency+" not exist");
        }
    }

    @Override
    public void expenseAmount(BigDecimal amount, Currency currency) throws CurrencyException, AmountException {
        checkCurrencyExist(currency);

        BigDecimal oldAmount = currencyAmount.get(currency);
        BigDecimal newValue = oldAmount.subtract(amount);
        checkAmount(newValue);
        currencyAmount.put(currency, newValue);
    }

    private void checkAmount(BigDecimal amount) throws AmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountException("Amount can't be < 0");
        }
    }

    @Override
    public void addCurrency(Currency currency) throws CurrencyException {
        if (currencyAmount.containsKey(currency)) {
            throw new CurrencyException("Currency already exist");// пока просто сообщение на англ, без локализации
        }
        currencyList.add(currency);
        currencyAmount.put(currency, BigDecimal.ZERO);

    }

    @Override
    public void deleteCurrency(Currency currency) throws CurrencyException {
        checkCurrencyExist(currency);

        // не даем удалять валюту, если в хранилище есть деньги по этой валюте
        if (!currencyAmount.get(currency).equals(BigDecimal.ZERO)) {
            throw new CurrencyException("Can't delete currency with amount");
        }

        currencyAmount.remove(currency);
        currencyList.remove(currency);

    }

    @Override
    public Currency getCurrency(String code) throws CurrencyException {
        // количество валют для каждого хранилища бует небольшим - поэтому можно проводить поиск через цикл
        // можно использовать библиотеку Apache Commons Collections

        for (Currency currency : currencyList) {
            if (currency.getCurrencyCode().equals(code)) {
                return currency;
            }
        }

        throw new CurrencyException("Currency (code=" + code + ") not exist in storage.");
    }
}
