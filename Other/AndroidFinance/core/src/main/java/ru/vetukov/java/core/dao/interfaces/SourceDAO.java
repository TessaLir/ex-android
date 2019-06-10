package ru.vetukov.java.core.dao.interfaces;

import java.util.List;

import ru.vetukov.java.core.enums.OperationType;
import ru.vetukov.java.core.interfaces.Source;

public interface SourceDAO extends CommonDAO<Source> {

    List<Source> getList(OperationType operationType); // Получить список коренных эллементов деревьев для определения типа

}
