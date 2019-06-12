package ru.vetukov.java.core.dao.impls;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.vetukov.java.core.dao.interfaces.StorageDAO;
import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.impls.DefaultStorage;
import ru.vetukov.java.core.interfaces.Storage;

public class StorageDAOImpl implements StorageDAO {
    private static final String CURRENCY_AMOUNT_TABLE = "currency_amount";
    private static final String STORAGE_TABLE = "storage";

    private List<Storage> storageList = new ArrayList<>();


    @Override
    public List<Storage> getAll() {
        storageList.clear();

        try (Statement stmt = SQLiteConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + STORAGE_TABLE + " order by parent_id");) {

            while (rs.next()) {
                DefaultStorage storage = new DefaultStorage();
                storage.setId(rs.getLong("id"));
                storage.setName(rs.getString("name"));
                storage.setParentId(rs.getLong("parent_id"));
                storageList.add(storage);
            }

            return storageList;

        } catch (SQLException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }


    @Override
    public boolean addCurrency(Storage storage, Currency currency) {

        // для автоматического закрытия ресурсов
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("insert into " + CURRENCY_AMOUNT_TABLE + "(currency_code, storage_id) values(?,?)");) {

            stmt.setString(1, currency.getCurrencyCode());
            stmt.setLong(2, storage.getId());


            if (stmt.executeUpdate() == 1) { // если была добавлена 1 запись
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
        // TODO реализовать: вместо true, false - выбрасывать исключение и перехватывать его выше, создать соотв. типы Exception
    }

    @Override
    public boolean deleteCurrency(Storage storage, Currency currency) {
        // TODO реализовать - если есть ли операции по данной валюте - запрещать удаление
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("delete from " + CURRENCY_AMOUNT_TABLE + " where storage_id=? and currency_code=?");) {

            stmt.setLong(1, storage.getId());
            stmt.setString(2, currency.getCurrencyCode());


            if (stmt.executeUpdate() == 1) {  // если была обновлена 1 запись
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public boolean updateAmount(Storage storage, Currency currency, BigDecimal amount) {
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("update " + CURRENCY_AMOUNT_TABLE + " set amount=? where storage_id=? and currency_code=?");) {

            stmt.setBigDecimal(1, amount);
            stmt.setLong(2, storage.getId());
            stmt.setString(3, currency.getCurrencyCode());

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public Storage get(long id) {
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("select * from " + STORAGE_TABLE + " where id=?");) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery();){
                DefaultStorage storage = null;

                if (rs.next()){
                    storage = new DefaultStorage();
                    storage.setId(rs.getLong("id"));
                    storage.setName(rs.getString("name"));
                    storage.setParentId(rs.getLong("parent_id"));
                }

                return storage;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }



    @Override
    public boolean update(Storage storage) {
        // для упрощения - у хранилища даем изменить только название, изменять parent_id нельзя (для этого можно удалить и заново создать)
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("update " + STORAGE_TABLE + " set name=? where id=?");) {

            stmt.setString(1, storage.getName());
            stmt.setLong(2, storage.getId());

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public boolean delete(Storage storage) {
        // TODO реализовать - если есть ли операции по данному хранилищу - запрещать удаление
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("delete from " + STORAGE_TABLE + " where id=?");) {

            stmt.setLong(1, storage.getId());

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(StorageDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }


}
