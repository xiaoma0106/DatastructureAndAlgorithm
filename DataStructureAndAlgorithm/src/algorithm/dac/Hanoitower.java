package algorithm.dac;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author mjh
 * @date 2022-04-16 18:31
 * Divide and conquer : 分治算法
 * 将大型复杂的问题,分解成一个个简单可操作的小问题,并解决它,最后将所有解决的结果汇聚成一个最终结果
 */
public class Hanoitower {
    private static int count = 0;
    public static void main(String[] args) {

        int num =10;
        Deque<Integer> a = new ArrayDeque<>();
        for (int i = 1; i <= num; i++) {
            a.push(i);
        }
        System.out.println("移动前:");
        System.out.println("a:"+Arrays.toString(a.toArray()));
        Deque<Integer> b = new ArrayDeque<>();
        Deque<Integer> c = new ArrayDeque<>();

        System.out.println("b:"+Arrays.toString(b.toArray()));
        System.out.println("c:"+Arrays.toString(c.toArray()));
        hanoi(num,a,b,c);

        System.out.println("移动后:");
        System.out.println("a:"+Arrays.toString(a.toArray()));
        System.out.println("b:"+Arrays.toString(b.toArray()));
        System.out.println("c:"+Arrays.toString(c.toArray()));
        System.out.println("盘子移动的次数:"+count);
    }

    /**
     * 分治算法的经典案例:汉诺塔
     * 将在a柱上的从小到大放的盘子,移动到c柱上,每次只能移动一个盘子,且每次移动后,大盘子必须在小盘子下方
     * @param a
     */
    public static void hanoi(int num,Deque a, Deque b, Deque c){
        if (num>0) {
            // num-1盘子看成是一个整体,递归将其移动到b柱
            hanoi(num - 1, a, c, b);
            // 将a柱的最后一个盘子移动到c柱
            count++;
            Object value = a.pop();
            c.push(value);

            // 将在b柱的num-1个盘子看成是一个整体,递归移动到c柱
            hanoi(num - 1, b, a, c);
        }
    }
}
