package com.self.dictsort;

import java.util.Arrays;

/**
 * 字典排序
 *
 * 算法题目：
    给定一个正整数，实现一个方法来求出离该整数最近的大于自身的“换位数”。
    什么是换位数呢？就是把一个整数各个数位的数字进行全排列，从而得到新的整数。例如53241和23541。
    小灰也不知道这种经过换位的整数应该如何称呼，所以姑且称其为“换位数”。
    题目要求写一个方法来寻找最近的且大于自身的换位数。比如下面这样：
    输入12345，返回12354
    输入12354，返回12435
    输入12435，返回12453
 *
 * @author shichen
 * @create 2018/4/13
 * @desc
 */
public class DictSortTest {

    /**
    1.从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
    2.把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
    3.把原来的逆序区域转为顺序
     */

    /**
     * 找到离着给定数字最近的大于自身的“换位数”。
     * @param numbers
     * @return
     */
    public static int[] findNearestNumber(int[] numbers) {
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);

        int index = findTransferPoint(numbersCopy);
        if(index == 0) {
            return null;
        }

        //2.把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
        exchangeHead(numbersCopy, index);
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    /**
     * 找到逆序边界
     *
     * 12345 逆序边界为4
     * 12054 逆序边界是3，即返回3
     *
     * @param numbers
     * @return
     */
    public static int findTransferPoint(int[] numbers) {
        for(int i = numbers.length - 1; i > 0; i --) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
     *
     * 12345 没有逆序边界
     * 12054 逆序边界是3，即返回3
     *
     * @param numbers
     * @param index
     * @return
     */
    public static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > head) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }


    /**
     * 将逆序边界区域转换顺序
     *
     * 12054 转换为 12045
     *
     * @param numbers 数组
     * @param index 逆序边界
     * @return
     */
    public static int[] reverse(int[] numbers, int index){
        for(int i = index, j = numbers.length - 1; i < j; i ++, j --){
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,0,5,1,2,3,4};//-->12051243
        //int[] numbers = {1,2,3,4,5};//-->12354
        int[] result = findNearestNumber(numbers);
        for(int i = 0; i < result.length; i ++) {
            System.out.print(result[i]);
        }
        System.out.println();
    }
}
