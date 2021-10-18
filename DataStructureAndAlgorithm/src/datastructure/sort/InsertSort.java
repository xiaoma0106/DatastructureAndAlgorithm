package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-18 8:54
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {50, 22, 88, 1, 66, 3};
        System.out.println(Arrays.toString(arr));
        System.out.println("-------排序前后--------");
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] array) {
        int insertValue;
        int len = array.length;
        int j = 0;
        for (int i = 1; i < len; i++) {
            insertValue = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > insertValue) {
                array[j + 1] = array[j];
                j--;
            }
            //注意要替换的位置是比较的位置的前一个
            array[j + 1] = insertValue;
        }
    }
}
