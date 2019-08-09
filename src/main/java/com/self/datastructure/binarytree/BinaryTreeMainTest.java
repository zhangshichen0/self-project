package com.self.datastructure.binarytree;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * 将一系列数据组装为二叉搜索树
 *
 * @author shichen
 * @create 2019-08-08
 * @desc
 */
public class BinaryTreeMainTest {

    public static void main(String[] args) {

        //构建一个数组
        List<Integer> list = Lists.newArrayListWithCapacity(10);
        for (int i = 0; i < 20; i ++) {
            Random r = new Random();
            int num = r.nextInt(50);
            if (!list.contains(num)) {
                list.add(num);
            }
        }

        list.add(47);

        System.out.println(list);

        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < list.size(); i ++) {
            binaryTree.insert(list.get(i));
        }

        System.out.println(binaryTree.search(47));

    }
}