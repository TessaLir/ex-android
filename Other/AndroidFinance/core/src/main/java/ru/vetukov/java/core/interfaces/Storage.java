package ru.vetukov.java.core.interfaces;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import ru.vetukov.java.core.exceptions.AmountException;
import ru.vetukov.java.core.exceptions.CurrencyException;

//TODO: изменть тип BigDecimal на готовый класс по работе с деньгами.
public interface Storage extends TreeNode {

    // Получение баланса остатка
    Map<Currency, BigDecimal> getCurrencyAmount();                                                          // Остаток по каждой доступной валюте в хранилище
    BigDecimal getAmount(Currency currency) throws CurrencyException;                                       // Остаток во определенной валюте
    BigDecimal getApproxAmount(Currency currency) throws CurrencyException;                                 // Примерный остаток в переводе денег в одну валюту

    // Изменение баланса
    void changeAmount(BigDecimal amount, Currency currency) throws CurrencyException;                       // Изменение баланса по определенной валюте
    void addAmount(BigDecimal amount, Currency currency) throws CurrencyException;                          // Добавить сумму в валюте
    void expenseAmount(BigDecimal amount, Currency currency) throws CurrencyException, AmountException;     // Отнять сумму в валюте

    // Работа в валютой
    void addCurrency(Currency currency) throws CurrencyException;                                           // Добавить новую валюту в хранилище
    void  deleteCurrency(Currency currency) throws CurrencyException;                                       // Удалить валюту из хранилища
    Currency getCurrency(String code) throws CurrencyException;                                             // Получить валюту по коду
    List<Currency> getAvailableCurrencies();                                                                // Получить все доступные вылюты хранилища в отдельной коллекции

}
