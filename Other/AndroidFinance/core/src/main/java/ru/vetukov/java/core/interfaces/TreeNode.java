package ru.vetukov.java.core.interfaces;

import java.util.List;

// позволяет сохдать древовидную струтуру из любого набора объектов, которые реализуют интерфейс TreeNode
// паттерн "Компоновщик" - вольная реализация
public interface TreeNode {

    String getName();

    void setName(String name);

    long getId();                       // Каждый эллемент дерева должен иметь свой уникальный идентификатор

    void setId(long id);                // Установить id

    TreeNode getParent();               // Получить родителя

    long getParentId();

    //TODO: тут может не находиться данный Child
    TreeNode getChilds(long id);         // Получить дочерний эллемент

    List<TreeNode> getChilds();          // Получить лист всех дочерних эллементов

    void add(TreeNode child);      // Добмавить дочерний эллемент

    //TODO: Тут нужна проверка, не лежит ли потенциальный родитель ниже по дереву.
    void setParent(TreeNode parent);    // назначить родителя

    //TODO: тут может не находиться данный Child
    void remove(TreeNode child);   // Удалить дочерний эллемент

    boolean hasChilds();                // Проверяет, есть ли дочерние элементы

    boolean hasParent();                // Проверяет, есть ли родитель

}
