package com.self.datastructure.sort;

/**
 * 快速排序实现
 *
 * <a>https://blog.csdn.net/shujuelin/article/details/82423852</a>
 *
 * @author shichen
 * @create 2019-10-15
 * @desc
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {

        //定义临时变量
        int i, j, temp;

        //判断low和high用于退出函数
        if (low >= high) {
            return;
        }

        System.out.println("low :" + low + ", high :" + high);

        i = low;
        j = high;
        //基准值
        temp = arr[low];

        while (i < j) {

            //从右向左查找小于temp的元素
            while (arr[j] >= temp && i < j) {
                j --;
            }

            //从右向左查找大于temp的元素
            while (arr[i] <= temp && i < j) {
                i ++;
            }

            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        //将基准值和最后j位置确定的元素互换，因为j停止确定的位置一定小于基准值temp
        arr[low] = arr[j];
        arr[j] = temp;

        //迭代循环子集合，直至return
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    public static void main(String[] args){
        //int[] arr = {6, 1, 2, 7, 9, 11, 4, 5, 10, 8};
        //int[] arr = {1,2,3,4,5,6};
        int[] arr = {6,5,4,3,2,1};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
