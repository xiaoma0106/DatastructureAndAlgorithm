package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-21 13:46
 * 基数排序
 * 典型的空间换时间,但是可能会溢出
 * */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {855, 65, 33, 5, 999, 631, 0,9999};
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void radixSort(int[] array) {
//        获取最大数(最长位)
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }

        int len = (max + "").length();

//        初始化基数排序所需要的辅助数组
        int[][] bucket = new int[10][array.length];
        //每个桶的标记位置数组
        int[] indexBucket = new int[10];

//        都多少位就进行几次排序
        for (int i = 0, n = 1; i < len; i++, n *= 10) {

//          对要排序的数组进行遍历
            for (int j = 0; j < array.length; j++) {
                int val = array[j] / n % 10;
                bucket[val][indexBucket[val]] = array[j];
                indexBucket[val]++;
            }

//          将放进桶中的数据依次重新放入数组
            int temp=0;
            for (int j = 0; j < indexBucket.length; j++) {
                for (int k = 0; k < indexBucket[j]; k++) {
                    array[temp]=bucket[j][k];
                    temp++;
                }
            }

            //在进行下一次排序前,要将标记位置数组置零
            indexBucket=new int[10];
        }

    }
}
