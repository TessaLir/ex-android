package ru.vetukov.java.core.dao.interfaces;

import java.util.List;

import ru.vetukov.java.core.enums.OperationType;
import ru.vetukov.java.core.interfaces.Operation;

public interface OperationDAO extends CommonDAO<Operation> {

    List<Operation> getList(OperationType operationType);// получить список операций определенного типа

}
