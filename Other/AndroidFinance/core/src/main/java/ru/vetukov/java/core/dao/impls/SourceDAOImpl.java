package ru.vetukov.java.core.dao.impls;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.vetukov.java.core.dao.interfaces.SourceDAO;
import ru.vetukov.java.core.database.SQLiteConnection;
import ru.vetukov.java.core.enums.OperationType;
import ru.vetukov.java.core.impls.DefaultSource;
import ru.vetukov.java.core.interfaces.Source;


//TODO можно реализовать общий абстрактный класс и вынести туда общие методы (getAll, delete и пр.
public class SourceDAOImpl implements SourceDAO {

    private static final String SOURCE_TABLE = "source";
    private List<Source> sourceList = new ArrayList<>();

//    // для каждого ключа (типа операции) - своя коллекция источников (корневых элементов дерева)
//    private Map<OperationType, List<Source>> sourceMap = new EnumMap<>(OperationType.class);// минус этого подхода в том, что придется всегда работать с 2 коллекциями
//    private TreeUtils<Source> treeConstructor = new TreeUtils();// для каждого объекта создаем свой экземпляр TreeUtils - т.к. передается тип Generics


    @Override
    public List<Source> getAll() {
        sourceList.clear();

        try (Statement stmt = SQLiteConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("select * from " + SOURCE_TABLE + " order by parent_id")) {

            while (rs.next()) {
                DefaultSource source = new DefaultSource();
                getSource(rs, source);
                sourceList.add(source);

//                Integer operationTypeId = rs.getInt("operation_type_id"); // можно использовать тип Integer
//                OperationType operationType = OperationType.getType(operationTypeId);
//                source.setOperationType(operationType);// operationType устанавливаем только для корневых элементов, т.к. для дочерних автоматически устанавливается тип от родителя

//                Long parentId = rs.getLong("parent_id");// тип Long, чтобы можно было проверять на null
//                sourceMap.put(operationType, sourceList);
//                treeConstructor.addToTree(parentId, source, sourceList);

            }

//            fillSourceMap();// разделяем коллекцию по типам (делается один раз при инициализации)
            return sourceList;// должен содержать только корневые элементы

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

//    private void fillSourceMap() {
//        // в sourceMap и sourceList находятся одни и те же объекты!!
//
//        for (OperationType type: OperationType.values()) {
//            // используем lambda выражение для фильтрации
//            sourceMap.put(type, sourceList.stream().filter(s -> s.getOperationType() == type).collect(Collectors.toList()));
//        }
//
//    }


    @Override
    public Source get(long id) {
        try ( PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("select * from " + SOURCE_TABLE + " where id=?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                DefaultSource source = null;

                if (rs.next()) {
                    source = new DefaultSource();
                    getSource(rs, source);
                }
                return source;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public boolean update(Source source) {
        // для упрощения - у хранилища даем изменить только название, изменять parent_id нельзя (для этого можно удалить и заново создать)
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("update " + SOURCE_TABLE + " set name=? where id=?");) {

            stmt.setString(1, source.getName());// у созданного элемента - разрешаем менять только название
            stmt.setLong(2, source.getId());

            // не даем обновлять operationType - тип устанавливается только один раз при создании корневеого элемента

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public boolean delete(Source source) {
        // TODO реализовать - если есть ли операции по данному хранилищу - запрещать удаление
        try (PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("delete from " + SOURCE_TABLE + " where id=?");) {

            stmt.setLong(1, source.getId());

            if (stmt.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public List<Source> getList(OperationType operationType) {
        sourceList.clear();

        try ( PreparedStatement stmt = SQLiteConnection.getConnection().prepareStatement("select * from " + SOURCE_TABLE + " where operation_type_id=?") ) {
            stmt.setLong(1, operationType.getId());

            try ( ResultSet rs = stmt.executeQuery() ) {
                DefaultSource source = null;

                while (rs.next()) {
                    source = new DefaultSource();

                    getSource(rs, source);

                    sourceList.add(source);
                }
                return sourceList;
            }
        } catch (SQLException e) {
            Logger.getLogger(SourceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    private void getSource(ResultSet rs, DefaultSource source) throws SQLException {
        source.setId(rs.getLong("id"));
        source.setName(rs.getString("name"));
        source.setParentId(rs.getLong("parent_id"));
        source.setOperationType(OperationType.getType(rs.getInt("operation_type_id")));
    }
}
