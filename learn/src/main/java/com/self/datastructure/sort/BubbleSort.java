package com.self.datastructure.sort;

/**
 * 冒泡排序
 *
 * <a>https://blog.csdn.net/NathanniuBee/article/details/83413879</a>
 *
 * @author shichen
 * @create 2019-10-15
 * @desc
 */
public class BubbleSort {

    /**
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i ++) {
            //是否立即退出循环标识，如果在第一次比较时，没有任何元素的交换，则说明数据本来就有序
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j ++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 11, 4, 5, 10, 8};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
