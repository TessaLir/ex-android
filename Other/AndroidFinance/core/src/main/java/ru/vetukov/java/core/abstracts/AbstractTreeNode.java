package ru.vetukov.java.core.abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.vetukov.java.core.interfaces.TreeNode;
import sun.reflect.generics.tree.Tree;

public abstract class AbstractTreeNode implements TreeNode {

    private long id;
    private String name;
    private TreeNode parent;
    private List<TreeNode> childs = new ArrayList<>();
    private long parentId;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public AbstractTreeNode() {}

    public AbstractTreeNode(String name) {
        this.name = name;
    }

    public AbstractTreeNode(List<TreeNode> childs) {
        this.childs = childs;
    }

    public AbstractTreeNode(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AbstractTreeNode(long id, String name, TreeNode parent, List<TreeNode> childs) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.childs = childs;
    }

    @Override
    public void add(TreeNode child) {
        child.setParent(this);
        childs.add(child);
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public void remove(TreeNode child) {
        child.setParent(null);
        childs.remove(child);
    }

    @Override
    public List<TreeNode> getChilds() {
        return childs;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public TreeNode getChilds(long id) {

        for (TreeNode child : childs) {
            if (child.getId() == id) {
                return child;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasChilds() {
        return !childs.isEmpty();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }


}
