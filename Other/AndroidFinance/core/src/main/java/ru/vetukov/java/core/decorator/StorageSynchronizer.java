package ru.vetukov.java.core.decorator;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import ru.vetukov.java.core.dao.interfaces.StorageDAO;
import ru.vetukov.java.core.exceptions.CurrencyException;
import ru.vetukov.java.core.interfaces.Storage;

public class StorageSynchronizer implements StorageDAO {

    private StorageDAO storageDAO;
    private List<Storage> storageList;

    public StorageSynchronizer(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
        init();
    }

    private void init() {
        storageList = storageDAO.getAll();
    }

    @Override
    public boolean addCurrency(Storage storage, Currency currency) throws CurrencyException {
        if (storageDAO.addCurrency(storage, currency)) {
            storage.addCurrency(currency);
            return true;
        }
        return false;
    }

    // TODO: при обновлении происходит наоборот - сначала обновляется в коллекции, потом уже в БД
    //       Подумать , как сделать
    @Override
    public boolean updateCurrency(Storage storage, Currency currency, BigDecimal amount) {
        if (storageDAO.updateCurrency(storage, currency,  amount)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCurrency(Storage storage, Currency currency) throws CurrencyException {
        if (storageDAO.deleteCurrency(storage, currency)) {
            storage.deleteCurrency(currency);
            return true;
        }
        return false;
    }

    @Override
    public List<Storage> getAll() {
        if (storageList == null) {
            storageList = storageDAO.getAll();
        }
        return storageList;
    }

    @Override
    public Storage get(long id) {
        return storageDAO.get(id);
    }

    @Override
    public boolean update(Storage storage) {
        if (storageDAO.update(storage)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Storage storage) {
        // TODO: добавить нужные Exceptions
        if (storageDAO.delete(storage)) {
            storageList.remove(storage);
            return true;
        }
        return false;
    }
}
