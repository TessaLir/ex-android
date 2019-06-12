package ru.vetukov.java.core.interfaces;

import java.util.Calendar;

import ru.vetukov.java.core.enums.OperationType;

public interface Operation {

    long getId();

    void setId(long id);

    OperationType getOperationType();

    Calendar getDateTime();

    String getDescription();

}
