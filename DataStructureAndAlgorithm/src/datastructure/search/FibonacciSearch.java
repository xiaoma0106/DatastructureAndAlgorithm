package datastructure.search;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-23 10:29
 * 斐波那契查找,黄金分割点查找
 * 利用斐波那契数数组来确定要查找的数组的个数,和要找的那个位置
 * mid=low + f(k-1)-1;
 *
 * 推导过程:
 * f(k)=f(k-1)+f(k-2)
 * ->
 * f(k)-1=(f(k-1)-1)+(f(k-2)-1)+1
 * 最后那个1就是mid要占用的位置
 * (f(k-1)-1)就是mid的下标位置(黄金分割点)
 *
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr={5,6,12,19,33,64,99};
        System.out.println(fibSearch(arr,12));
    }

    /**
     * 斐波那契数数组
     *
     * @param n
     * @return
     */
    public static int[] fib(int n) {
        int[] fibInt = new int[n];
        fibInt[0] = 1;
        fibInt[1] = 1;
        for (int i = 2; i < n; i++) {
            fibInt[i] = fibInt[i - 1] + fibInt[i - 2];
        }
        return fibInt;
    }

    /**
     * 斐波那契数查找
     * 非递归方式
     *
     * @param array
     * @param findValue
     * @return
     */
    public static int fibSearch(int[] array, int findValue) {
        if (findValue < array[0] || findValue > array[array.length - 1])
            return -1;
        int low = 0;
        int high = array.length - 1;
        int k = 0;//斐波那契数数组的下标
        int[] fibArr = fib(20);//斐波那契数数组

        //找到斐波那契数数组中值比查找的数组的个数大的位置为止
        while (high > fibArr[k] - 1) {
            k++;
        }

        //将要查找的数组拷贝到新数组,使其个数刚好为斐波那契数-1
        int[] newArr = Arrays.copyOf(array, fibArr[k] - 1);
        //补位的地方,用最后一个位置的值进行填充
        if (fibArr[k] - 1 > high) {
            for (int i = high + 1; i < fibArr[k] - 1; i++) {
                newArr[i] = newArr[high];
            }
        }

        //非递归查找处理
        while (low <= high) {
            int mid = low + fibArr[k - 1] - 1;
            if (findValue < newArr[mid]) {
                high = mid - 1;
                k--;
            } else if (findValue > newArr[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
