package datastructure.sort;

import java.util.Arrays;

/**
 * @author mjh
 * @date 2021-10-14 22:40
 */
public class SelectedSort {
    public static void main(String[] args) {
        int[] arr={5,0,9,10,1,50,-2};
        System.out.println("排序前:"+ Arrays.toString(arr));
        select(arr);
        System.out.println("排序后:"+Arrays.toString(arr));
    }

    public static void select(int[] arr) {
        int address = 0;//最小值下标
        int temp;//值交换时的临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            address = i;
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[address] > arr[j + 1]) {
                    address = j + 1;
                }
            }
            if (address != i){
               temp=arr[i];
               arr[i]=arr[address];
               arr[address]=temp;
            }
        }
    }
}
