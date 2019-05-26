package ru.vetukov.java.core.impls;

import java.util.List;

import ru.vetukov.java.core.abstracts.AbstractTreeNode;
import ru.vetukov.java.core.interfaces.Source;
import ru.vetukov.java.core.interfaces.TreeNode;
import ru.vetukov.java.core.objects.OperationType;

public class DefaultSource
                            extends AbstractTreeNode
                            implements Source {

    private OperationType operationType;

    public DefaultSource() {
    }

    public DefaultSource(String name) {
        super(name);
    }

    public DefaultSource(List<TreeNode> childs) {
        super(childs);
    }

    public DefaultSource(long id, String name) {
        super(id, name);
    }

    public DefaultSource(long id, String name, TreeNode parent, List<TreeNode> childs) {
        super(id, name, parent, childs);
    }

    public DefaultSource(long id, String name, OperationType operationType) {
        super(id, name);
        this.operationType = operationType;
    }

    @Override
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public void addChild(TreeNode child) {

        //TODO: применить паттерн
        //      для дочернего эллемента устанавливаем
        //      тип операции родительского эллемента
        if (child instanceof DefaultStorage) {
            ((DefaultSource)child).setOperationType(operationType);
        }

        super.addChild(child);
    }
}
