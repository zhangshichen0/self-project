package com.self.datastructure.binarytree;

import java.util.Objects;

/**
 * @author shichen
 * @create 2019-08-08
 * @desc
 */
public class BinaryTree {

    private BinaryTreeNode rootNode;


    public BinaryTree() {
    }

    public void insert(int num) {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(num);

        if (Objects.isNull(this.rootNode)) {
            this.rootNode = binaryTreeNode;

            System.out.println("root " + num);
            return;
        }

        BinaryTreeNode leftNode = this.rootNode.getLeftNode();
        BinaryTreeNode rightNode = this.rootNode.getRightNode();
        BinaryTreeNode parentNode = this.rootNode;
        for (; ; ) {
            //left或者right为叶子节点
            if (Objects.isNull(leftNode) || Objects.isNull(rightNode)) {

                if (parentNode.getKey() > num) {

                    if (Objects.isNull(leftNode)) {
                        binaryTreeNode.setParentNode(parentNode);
                        parentNode.setLeftNode(binaryTreeNode);
                        System.out.println("parent key:" + parentNode.getKey() + ", left key:" + num);
                        return;
                    } else {
                        parentNode = leftNode;
                        leftNode = parentNode.getLeftNode();
                        rightNode = parentNode.getRightNode();
                        continue;
                    }
                } else if (parentNode.getKey() < num){

                    if (Objects.isNull(rightNode)) {
                        binaryTreeNode.setParentNode(parentNode);
                        parentNode.setRightNode(binaryTreeNode);
                        System.out.println("parent key:" + parentNode.getKey() + ", right key:" + num);
                        return;
                    } else {
                        parentNode = rightNode;
                        leftNode = parentNode.getLeftNode();
                        rightNode = parentNode.getRightNode();
                        continue;
                    }
                } else {
                    break;
                }
            } else if (Objects.nonNull(leftNode) && Objects.nonNull(rightNode)) {
                if (parentNode.getKey() > num) {
                    parentNode = leftNode;
                } else if (parentNode.getKey() < num){
                    parentNode = rightNode;
                } else {
                    break;
                }
                leftNode = parentNode.getLeftNode();
                rightNode = parentNode.getRightNode();
            }
        }

    }

    /**
     * 查找
     * @param key
     * @return
     */
    public int search(int key) {
        if (Objects.isNull(this.rootNode)) {
            return -1;
        }
        BinaryTreeNode parentNode = this.rootNode;
        BinaryTreeNode leftNode = parentNode.getLeftNode();
        BinaryTreeNode rightNode = parentNode.getRightNode();
        for (;;) {

            if (Objects.isNull(parentNode)) {
                return -1;
            }

            if (parentNode.getKey() == key) {
                return 1;
            }

            if (parentNode.getKey() > key && Objects.nonNull(leftNode)) {
                parentNode = leftNode;
            } else if (parentNode.getKey() < key && Objects.nonNull(rightNode)) {
                parentNode = rightNode;
            } else {
                return -1;
            }
            leftNode = parentNode.getLeftNode();
            rightNode = parentNode.getRightNode();
        }
    }
}
