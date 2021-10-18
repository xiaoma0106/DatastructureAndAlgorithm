package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-18 20:08
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {88, 77, 32, 40, 6, 90, 0, 100, 2, 3, 1, 9};
//        int[] arr = {};
//        int[] arr = {5};
//        int[] arr = {40, 6};
//        int[] arr = {99, 5, 3, -5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] array, int left, int right) {
        //递归的结束条件,也是参数正确性判断
        if (right <= left)
            return;
        if (left < 0 || right > array.length - 1)
            return;
        int myLeft = left;
        int myRight = right;
        //选取中间位置为判断基准值
        int mid = (left + right) / 2;
        int pivot = array[mid];
        int temp;
        //单该次判断还没结束的条件,注意多次运行前后的影响效果
        while (myLeft < myRight) {
            //从左边一直找,直到出现比基准值大的情况(即需要替换的位置),且不可能超过基准值所在的位置
            while (array[myLeft] < pivot) {
                myLeft++;
            }
            //从右边一直找,直到出现比基准值小的情况(即需要替换的位置),且不可能超过基准值所在的位置
            while (array[myRight] > pivot) {
                myRight--;
            }
            //将找到的两位置值进行替换,即使是基准值所在位置,也进行替换,并不影响运行结果
            temp = array[myLeft];
            array[myLeft] = array[myRight];
            array[myRight] = temp;
            /*
            if (myLeft < mid && myRight > mid) {
                temp = array[myLeft];
                array[myLeft] = array[myRight];
                array[myRight] = temp;
            }

            if (myRight > mid && mid == myLeft) {
                temp = array[myRight];
                array[myRight] = array[mid];
                array[mid] = temp;
                mid = myRight;


//                temp = array[myRight];
//                array[myRight] = array[mid + 1];
//                array[mid + 1] = array[mid];
//                array[mid] = temp;
//                mid++;
//                myLeft++;
            } else if (myRight == mid && myLeft < mid) {
                temp = array[myLeft];
                array[myLeft] = array[mid];
                array[mid] = temp;
                mid = myLeft;

//                temp = array[myLeft];
//                array[myLeft] = array[mid - 1];
//                array[mid - 1] = array[mid];
//                array[mid] = temp;
//                mid--;
//                myRight--;
            }
            */
        }
        //走到这一步,一定是左右两边同时到达基准值这个位置,然后为递归所需要的值,进行准备
        //并不需要判断值是否越界或无效,因为该递归方法的开头会进行参数值的有效性判断
        myLeft++;
        myRight--;
        quickSort(array, left, myRight);
        quickSort(array, myLeft, right);
    }
}
