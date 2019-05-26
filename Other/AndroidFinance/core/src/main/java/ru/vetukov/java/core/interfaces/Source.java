package ru.vetukov.java.core.interfaces;

import ru.vetukov.java.core.objects.OperationType;

public interface Source {

    String getName();

    long getId();

    OperationType getOperationType();
}
