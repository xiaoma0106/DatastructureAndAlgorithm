package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-20 18:43
 * 分治排序
 * 需要额外临时空间
 * 速度快
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        sortMerge(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }


    public static void sortMerge(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            //以中间点为间隔分成左右2个部分,一直分直到单个数为止,然后合并排序处理并返回
            int mid = (left + right) / 2;
            //左边部分
            sortMerge(array, left, mid, temp);
            //右边部分
            sortMerge(array, mid + 1, right, temp);
            //合并处理
            merge(array, left, mid, right, temp);
        }
    }

    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left; //要被合并的左边部分开始处
        int j = mid + 1;//要被合并的右边部分开始处
        int pos = 0;//临时空间的开始处

        //左右两边部分都有数据时,将数值较小都那一个值,拷贝到临时数据区,并且地址加1,直到某一边没有数据为止
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[pos] = array[i];
                pos++;
                i++;
            } else {
                temp[pos] = array[j];
                pos++;
                j++;
            }
        }

        //当左边仅存在数据时
        while (i <= mid) {
            temp[pos] = array[i];
            pos++;
            i++;
        }

        //当右边仅存在数据时
        while (j <= right) {
            temp[pos] = array[j];
            pos++;
            j++;
        }

        //将排序好的临时空间的数据重新拷贝回原来的那个数组中,且位置范围不能改变
        //至此,该范围内的数据已排序完成.
        int tempLeft = left;
        int t = 0;
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
