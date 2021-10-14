package datastructure.recursion;

/**
 * @author mjh
 * @date 2021-10-14 9:02
 * 8皇后问题
 * 8*8棋盘中,放上8个棋子,且每个棋子的行,列,斜线都不能有重复,共有多少种放法.
 * 重点是如何将每一行中的每一列遍历,用递归的方式表现出来
 */
public class Queue8 {
    public static final int max = 8;
    public int[] array = new int[max];//一维数组存放一种摆放结果,该数组位置下标表示棋盘的第几行,该下标位置上的值,表示在该行的第几列
    public static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法", count);
    }

    // 1*** 递归处理,n表示第几行
    public void check(int n) {
        //结束条件,当n为8时,说明8行都以正确不冲突方式放上棋子,因此可以打印输出,并返回
        if (n == max) {
            print(array);
            count++;
            return;
        }

        //2*** 0到max的遍历为:在该行的每一个列位置都要进行遍历判断是否有冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                //3*** 当该行的该位置没有冲突时,则保留该行位置,并对下一行进行同样的遍历判断
                //(判断的行数0到7)并且每一行又都从位置0列到7列进行判断,从而对所有的可能行进行判断
                check(n + 1);
            }
            //若该行存在冲突,则回溯到上一行的
        }
    }

    /**
     * 判断该行的放置位置是否与之前的行有冲突(列和斜角)
     *
     * @param n
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] 判断列位置是否冲突
            //Math.abs(n-i) == Math.abs(array[n]- array[i]) 判断斜角位置是否冲突
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        //若不冲突,说明该n行的该位置不与之前行相冲突
        return true;
    }

    /**
     * 有效结果的输出
     *
     * @param arr
     */
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
