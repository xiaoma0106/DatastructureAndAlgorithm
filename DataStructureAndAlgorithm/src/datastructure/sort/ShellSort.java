package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-18 10:31
 * shell排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 999, 88, 55, 22, 33, 66, 11, 0};
//        shellSort(arr);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * shell排序方式1:先着眼每组数据进行排序,组数为就是间隔数(步长)
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int len = array.length;
        int step = len / 2; //间隔为数组个数除2
        //当间隔step不为0时,说明还需要排序
        while (step != 0) {
            //对每个组进行插入排序
            for (int i = 0; i < step; i++) {
                //
                int insertValue;
                int pos;
                //对该组进行插入排序,注意改组的起始下标为i,该组的下一个元素下标为i+step
                for (int j = i + step; j < len; j += step) {
                    insertValue = array[j];//要插入的目标数据
                    pos = j - step;//进行比较的位置,前后移动的间隔也是step
                    while (pos >= i && array[pos] > insertValue) {
                        array[pos + step] = array[pos];
                        pos -= step;
                    }
                    array[pos + step] = insertValue;
                }
            }
            step = step / 2;//缩小间隔,每次都是上一次的1/2
        }
    }

    /**
     * shell排序方式2:以每个数据着手,根据该数据的位置可以找到对应的该组数据(index-step),从而进行排序
     */
    public static void shellSort2(int[] array) {
        int len = array.length;
        for (int step = len / 2; step > 0; step /= 2) {
            //对每个数进行判断
            for (int i = step; i < len; i++) {
                int j = i;
                int insertValue = array[i];
                while ((j - step) >= 0 && array[j - step] > insertValue) {
                    array[j]=array[j-step];
                    j -= step;
                }
                array[j]=insertValue;
            }
        }
    }
}
