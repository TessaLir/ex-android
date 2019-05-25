package ru.vetukov.java.core;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

public interface Storage {

    String getName();

    // Получение баланса остатка
    Map<Currency, BigDecimal> getCurrwncyAmount();              // Остаток по каждой доступной валюте в хранилище
    BigDecimal getAmount(Currency currency);                    // Остаток во определенной валюте
    BigDecimal getApproxAmount(Currency currency);              // Примерный остаток в переводе денег в одну валюту

    // Изменение баланса
    void changeAmount(BigDecimal amount, Currency currency);    // Изменение баланса по определенной валюте
    void addAmount(BigDecimal amount, Currency currency);       // Добавить сумму в валюте
    void expenseAmount(BigDecimal amount, Currency currency);   // Отнять сумму в валюте

    // Работа в валютой
    void addCurrency(Currency currency);                        // Добавить новую валюту в хранилище
    void  deleteCurrency(Currency currency);                    // Удалить валюту из хранилища
    Currency getCurrency(String code);                          // Получить валюту по коду
    List<Currency> getAcailableCurrencies();                    // Получить все доступные вылюты хранилища в отдельной коллекции


}
