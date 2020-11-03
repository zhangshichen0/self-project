package com.self.datastructure;

/**
 * 二分查找
 *
 * 1 2 3 4 5 6
 *
 * @author shichen
 * @create 2020/11/3
 * @desc
 */
public class BinarySearchTest {

    public static void main(String[] args) {
        BinarySearchTest binarySearchTest = new BinarySearchTest();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        binarySearchTest.binarySearch(-1, array);

    }

    public void binarySearch(int searchValue, int[] array) {
        int low = 0;
        int high = array.length - 1;
        System.out.println(search(searchValue, array, low, high));
    }

    public int search(int searchValue, int[] array, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        if (array[middle] == searchValue) {
            return middle;
        } else if (array[middle] > searchValue){
            return search(searchValue, array, low, middle - 1);
        } else {
            return search(searchValue, array, middle + 1, high);
        }
    }

}
