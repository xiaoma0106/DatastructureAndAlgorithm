package datastructure.sparsearray;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mjh
 * @date 2021-10-02 20:35
 * 稀疏数组
 */
public class SparseArray {

    @Test
    public void testArrayToSparse() {
        arrayToSparse();
    }

    @Test
    public void testSparseToArray() {
        sparseToArray();
    }

    /**
     * 将原始数组以稀疏数组的形式存储在文件中
     */
    public static void arrayToSparse() {
        //创建原始棋盘数组 1:白子 2:黑子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;

        //遍历数组获取有效棋子个数
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0)
                    sum++;
            }
        }

        //创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (count == sum) {
                    break;
                }
                if (chessArray[i][j] != 0) {
                    sparseArray[count + 1][0] = i;
                    sparseArray[count + 1][1] = j;
                    sparseArray[count + 1][2] = chessArray[i][j];
                    count++;
                }
            }
        }

        //将稀疏数组的内容输出到sparse.txt文件中
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("sparse.txt"));

            for (int i = 0; i < sparseArray.length; i++) {
                for (int j = 0; j < sparseArray[i].length; j++) {
                    fw.write(sparseArray[i][j] + "\t");
                }
                fw.write("\r\n");
            }
            fw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sparseToArray() {
        //读取存储在文件中稀疏数组信息
        FileReader fr = null;

        try {
            fr = new FileReader(new File("sparse.txt"));
            int row = 0;
            int col = 0;
            int count = 0;
            int rs = -1;
            StringBuilder sb = new StringBuilder("");
            List<Integer> list = new ArrayList<>();

            //获取文件中的数据
            int i = 1;
            while ((rs = fr.read()) != -1) {
                if (rs > 47 && rs < 58) {
                    char ch = (char) rs;
                    sb.append(ch);
                } else if (rs == 9) {
                    list.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder("");
                }
            }

            //将稀疏数组转换成原来的数组
            row=list.get(0);
            col=list.get(1);
            count=list.get(2);

            int[][] chessArray=new int[row][col];
            for (int j = 1; j <= count; j++) {
               chessArray[list.get(j*3)][list.get(j*3+1)]=list.get(j*3+2);
            }

            for (int j = 0; j < chessArray.length; j++) {
                for (int k = 0; k < chessArray[j].length; k++) {
                    System.out.print(chessArray[j][k]+"\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
