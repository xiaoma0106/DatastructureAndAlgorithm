package algorithm.dynamic;

/**
 * @author mjh
 * @date 2022-04-17 12:10
 * 背包问题:
 * 1.有几件物品,分别有不同的重量weight和价值value属性,w[] v[]
 * 2.背包的容量为定值,c[]
 * 3.求能放入背包中的物品的最大价值是多少,result[][]
 * <p>
 * 思路:
 * 动态规划:将复杂的问题分解成可以解决的小问题,但是某一个问题的解决要依赖上一个小问题的答案
 * 1.背包容量从0开始到最大,作为列
 * 2.物品的个数从0开始到最大,作为行
 * 3.从最小值开始,求各个包容量下,放允许的物品的个数的最大价值
 * <p>
 * <p>
 * 算法:
 * 1.在某一容量下,新增的物品i的重量>包的容量j时,包所能放下的最大物品的价值不变.if w[i] > c[j], result[i][j] = result[i-1][j]
 * 2.在某一容量下,新增的物品的重量<=包的容量时,if w[i] <= c[j],result[i][j] = max(v[i]+result[i-1][j-w[i]], result[i-1][j])
 * 3.循环1,2操作直到包的最大容量
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] v = {1500, 3000, 2000};
        int capacity = 4;

        int max = maxValue(w, v, capacity);
    }

    /**
     * 求包所能放的物品的最大价值
     *
     * @param w        各个物品的重量数组
     * @param v        各个物品的价值数组
     * @param capacity 背包的容量
     * @return
     */
    public static int maxValue(int[] w, int[] v, int capacity) {
        // 容量,价值表
        int[][] result = new int[v.length + 1][capacity + 1];
        // 0列,0行的初始值为0,并且所要设的值也是0,所以不需要做其他操作

        // 放入物品的路径
        int[][] path = new int[v.length + 1][capacity + 1];

        // 先设置各个列(包容量)的值,在此假定容量的单位是1,正是j值
        for (int j = 1; j <= capacity; j++) {
            // 在设置各个容量下,不同物品数量时的值
            for (int i = 1; i <= v.length; i++) {
                // 当新可放入的物品的重量大于当前包容量时
                if (w[i - 1] > j) {
                    result[i][j] = result[i - 1][j];
                    // 否则
                } else {
                    // 由于要存放放入物品的对象,要用if来区分
//                    result[i][j] = Math.max(v[i-1]+result[i-1][j-w[i-1]],result[i-1][j]);
                    if (v[i - 1] + result[i - 1][j - w[i - 1]] > result[i - 1][j]) {
                        result[i][j] = v[i - 1] + result[i - 1][j - w[i - 1]];
                        // 在此就是要放入的物品
                        path[i][j] = 1;
                    } else {
                        result[i][j] = result[i - 1][j];
                    }
                }
            }
        }
        // 返回二维表的最右下角的值
        int max = result[v.length][capacity];
        System.out.println("背包的最大价值: " + max);

        // 输出放入包中的物品
        int m = v.length;
        int n = capacity;
        // 从最大值开始找
        while (m > 0 && n > 0) {
            // 若是被放入包中的物品,输出,并去找剩余容量的最大价值
            if (path[m][n] == 1) {
                System.out.format("第%d个物品在包中\n", m);
                n = n - w[m - 1];
            }
            // 无论是否该物品放入包中,物品个数都要减一
            m--;
        }
        return max;
    }
}
