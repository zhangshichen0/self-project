package com.self.datastructure.linkreverse;

import java.util.Objects;

/**
 * 定义三个节点
 * currentNode为当前节点
 * q节点为临时节点
 * nextNode为当前节点的下一个节点
 *
 * @author shichen
 * @create 2019-09-25
 * @desc
 */
public class LinkListReverse {

    public static void main(String[] args) {

        //组装一个链表
        Node header = new Node();
        Node tail = null;
        for (int i = 0; i < 5; i++) {
            Node node = new Node(i);
            if (Objects.isNull(header.next)) {
                header.next = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        //将上面的链表反转
        Node currentNode = header.next;
        header.next = null;
        Node q = null, nextNode;
        while (Objects.nonNull(currentNode)) {
            nextNode = currentNode.next;
            if (Objects.isNull(nextNode)) {
                header.next = currentNode;
            }
            currentNode.next = q;

            q = currentNode;
            currentNode = nextNode;

        }

        //打印链表
        Node printNode = header.next;
        while (Objects.nonNull(printNode)) {
            System.out.println(printNode.getValue());
            printNode = printNode.next;
        }


    }

    public static class Node {
        private int value;
        private Node next;

        public Node() {
            this.next = null;
        }

        public Node(int value) {
            this.value = value;
            this.next = null;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
