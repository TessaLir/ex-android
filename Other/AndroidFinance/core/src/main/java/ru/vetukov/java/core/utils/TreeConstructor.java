package ru.vetukov.java.core.utils;

import java.util.List;

import ru.vetukov.java.core.interfaces.TreeNode;

// Построитель дерева
public class TreeConstructor<T extends TreeNode> {

    // встраивает новый элемент в нужное место дерева: суть в том, что нужно найти родительский элемент для объекта newNode
    public void addToTree(long parentId, T newNode, List<T> storageList) {
        if (parentId!=0){
            for (T currentNode: storageList) {// искать сначала во всех корневых объектах
                if (currentNode.getId()==parentId){
                    currentNode.addChild(newNode);
                    return;
                }else {// если среди корневых элементов не найдены родители
                    TreeNode node = recursiveSearch(parentId, currentNode);// проходим по всем уровням дочерних элементов
                    if (node!=null){// если нашли среди дочерних элементов
                        node.addChild(newNode);
                        return;
                    }
                }
            }
        }

        storageList.add(newNode);// если не найден родительский элемент - добавляем как корневой
    }


    // рекурсивно проходит по всем дочерним элементам
    private TreeNode recursiveSearch(long parentId, TreeNode child){
        TreeNode nodeResult = null;
        for (TreeNode node: child.getChilds()) {
            if (node.getId() == parentId){
                nodeResult = node;
            }else if (node.hasChilds()){// если у текущего узло есть свои дочерние элемента - проходим и по ним
                nodeResult = recursiveSearch(parentId, node);
            }
        }
        return nodeResult;
    }
}
