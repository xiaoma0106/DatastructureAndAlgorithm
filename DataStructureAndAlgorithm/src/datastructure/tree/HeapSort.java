package datastructure.tree;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-30 12:14
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = {4, 6, 8, 5, 9, 10, 53, 2, 66};
//        adjustHeap(array,1,array.length);
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 堆排序处理
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        //将无序数组处理成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
//        System.out.println(Arrays.toString(arr));

        int temp = 0;
        //将堆顶元素放到后面(堆的有效长度的最后一个位置)
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 除了目标位置target之外,所有节点都是大(小)顶堆
     * 以此为前提,
     * 将该顺序化二叉树处理成大(小)顶堆
     *
     * @param arr
     * @param target 目标位置
     * @param length 有效长度(不是下标)
     */
    public static void adjustHeap(int[] arr, int target, int length) {
        int temp = arr[target];

        for (int k = 2 * target + 1; k < length; k = k * 2 + 1) {
            //若右子树比左子树大,则将比较对象替换成右子树
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //若子树的值比父节点值大,则替换
            if (arr[k] > temp) {
                arr[target] = arr[k];
                target = k;
                //否则,父节点值比子节点值大时,说明此时为目标大顶堆,退出比较循环
            } else {
                break;
            }
        }
        //将待比较的值放到目标位置
        arr[target] = temp;
    }
}
