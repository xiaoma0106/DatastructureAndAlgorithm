package datastructure.search;

import java.util.ArrayList;

/**
 * @author mjh
 * @date 2021-10-21 15:03
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={2,8,12,33,50,50,50,50,66,76,99};
//        int result=binarySearch(arr,0,arr.length-1,0);
        int result=noRecursiveBinary(arr,0,arr.length-1,2);
        System.out.println(result);

        System.out.println("--------------");
//        List<Integer> list=listBinarySearch(arr,0,arr.length-1,50);
//        System.out.println(list);
    }

    /**
     * 二分查找
     * 1.不重复的有序序列
     */

    public static int binarySearch(int[] array,int left,int right,int findValue){
        if (left > right){
            return -1;
        }
        int mid=(left+right)/2;
        if (array[mid] > findValue){
            return binarySearch(array,left,mid-1,findValue);
        }else if (array[mid] < findValue){
            return binarySearch(array,mid+1,right,findValue);
        }else {
            return mid;
        }
    }

    /**
     * 二分查找
     * 2,可重复的有序序列,返回所有有效值的下标
     */

    public static ArrayList<Integer> listBinarySearch(int[] array,int left,int right,int findValue){
        if (left > right){
            return new ArrayList<>();
        }

        int mid=(left+right)/2;

        if (array[mid] > findValue){
            return listBinarySearch(array,left,mid-1,findValue);
        }else if (array[mid] < findValue){
            return listBinarySearch(array,mid+1,right,findValue);
        }else {
            ArrayList<Integer> list=new ArrayList<>();
            int temp=mid-1;
            while (temp>= left && array[temp] == array[mid]){
                temp--;
            }
            for (int i = temp+1; i < mid; i++) {
                list.add(i);
            }
            list.add(mid);

            temp=mid+1;
            while (mid <= right && array[temp] == array[mid]){
                temp++;
            }
            for (int i = mid+1; i < temp; i++) {
                list.add(i);
            }

            return list;
        }
    }


    /**
     * 不用递归的二分查找
     */
    public static int noRecursiveBinary(int[] array,int left,int right,int findValue){
        if (left > right)
            return -1;

        int mid =(left+right)/2;
        int myLeft=left;
        int myRight=right;

        while (array[mid] != findValue && myLeft <= myRight){
            if (array[mid] > findValue){
                myRight=mid-1;
                mid=(myLeft+myRight)/2;
            }else if (array[mid] < findValue){
                myLeft=mid+1;
                mid=(myLeft+ myRight)/2;
            }
        }
        if (myLeft > myRight){
            return -1;
        }else {
            return mid;
        }
    }

}
