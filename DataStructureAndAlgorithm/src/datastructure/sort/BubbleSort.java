package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-14 22:15
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={5,0,9,10,1,50,-2};
        System.out.println("排序前:"+ Arrays.toString(arr));
        bubble(arr);
        System.out.println("排序后:"+Arrays.toString(arr));
    }

    public static void bubble(int[] arr){
        int temp;
        //要进行比较的次数,每次确定一个值
        for (int i = 0; i < arr.length-1; i++) {
            //确定该值需要比较的次数
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}


