package com.self.datastructure.binarytree;

import java.util.Objects;

/**
 * @author shichen
 * @create 2019-08-08
 * @desc
 */
public class BinaryTree {

    private BinaryTreeNode rootNode;

    private int high;

    public BinaryTree() {
        this.rootNode = null;
        this.high = 0;
    }

    public void insert(int num) {
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(num);

        if (Objects.isNull(this.rootNode)) {
            this.rootNode = binaryTreeNode;
            this.high = 1;

            System.out.println("root " + num);
            return;
        }

        if (this.high == 1) {
            if (this.rootNode.getKey() > num) {
                binaryTreeNode.setParentNode(this.rootNode);
                this.high = this.high + 1;
                this.rootNode.setLeftNode(binaryTreeNode);
                System.out.println("parent node :"+ this.rootNode.getKey() +" left node" + num);
                return;
            } else if (this.rootNode.getKey() < num) {
                binaryTreeNode.setParentNode(this.rootNode);
                this.high = this.high + 1;
                this.rootNode.setRightNode(binaryTreeNode);
                System.out.println("parent node :"+ this.rootNode.getKey() +" right node" + num);

                return;
            } else {
                return;
            }
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
                }

                if (Objects.isNull(leftNode) && Objects.isNull(rightNode)) {
                    this.high = this.high + 1;
                }
            } else if (Objects.nonNull(leftNode) && Objects.nonNull(rightNode)) {
                if (parentNode.getKey() > num) {
                    parentNode = leftNode;
                } else {
                    parentNode = rightNode;
                }
                leftNode = parentNode.getLeftNode();
                rightNode = parentNode.getRightNode();
            }
        }

    }
}
