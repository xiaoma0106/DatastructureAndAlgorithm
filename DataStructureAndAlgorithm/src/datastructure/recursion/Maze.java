package datastructure.recursion;

/**
 * @author mjh
 * @date 2021-10-13 11:40
 * 迷宫状态:
 * 0:未经过
 * 1:墙
 * 2:以经过
 * 3:死路
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map=createMap(8, 8);
        setWay(map,1,1);

        System.out.println("------------走出迷宫路线-----------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 创建大小m*n的迷宫
     *
     * @param m
     * @param n
     * @return
     */
    public static int[][] createMap(int m, int n) {
        int[][] map = new int[m][n];

        //迷宫外围设置墙
        //左右两边
        for (int i = 0; i < m; i++) {
            map[i][0] = 1;
            map[i][n - 1] = 1;
        }
        //上下两边
        for (int i = 0; i < n; i++) {
            map[0][i] = 1;
            map[m - 1][i] = 1;

        }

        //迷宫内特殊墙体设置
        for (int i = 1; i < n-2; i++) {
            map[3][i] = 1;
            map[5][i+1] = 1;
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }
        return map;
    }


    /**
     * @param map 迷宫地图
     * @param i   ,j 在迷宫中所处位置
     * @return 1, 能走到最右下角则为成功
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[map.length - 2][map[0].length - 2] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;  //设置该位置为经过状态
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    map[i][j] = 3; //该位置的上下左右位置都返回false(不可行状态),因此设置为死路状态,可供查看路线状态
                    return false;
                }
            } else { //该位置状态为1,2,3,返回
                return false;
            }
        }
    }
}
