package com.self.datastructure.binarytree;

/**
 * @author shichen
 * @create 2019-08-08
 * @desc
 */
public class BinaryTreeNode {

    /**
     * 节点值
     */
    private int key;

    /**
     * 该节点的父节点
     */
    private BinaryTreeNode parentNode;

    /**
     * 左节点
     */
    private BinaryTreeNode leftNode;

    /**
     * 又节点
     */
    private BinaryTreeNode rightNode;

    public BinaryTreeNode(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public BinaryTreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(BinaryTreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }

}
