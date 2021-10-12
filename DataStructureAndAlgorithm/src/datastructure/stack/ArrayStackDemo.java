package datastructure.stack;

import java.util.Scanner;

/**
 * @author mjh
 * @date 2021-10-12 11:34
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(4);
        //接收输入值
        String key="";
        Scanner scanner=new Scanner(System.in);
        //判断是否跳出循环
        boolean flag=true;

        while (flag){
            System.out.println("--------------------------");
            System.out.println("show:显示所有数据");
            System.out.println("exit:退出");
            System.out.println("push:数据入栈");
            System.out.println("pop:数据出栈");
            System.out.println("--------------------------");
            System.out.println("请输入命令:");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入要入栈的数据:");
                    int value=scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    int rs=stack.pop();
                    System.out.printf("数据[%d]出栈\n",rs);
                    break;
                case "exit":
                    flag=false;
                    break;
                default:
                    System.out.println("输入命令不正确,请重新输入!");
            }
        }
        System.out.println("程序d退出~~");
    }
}

class ArrayStack{
    private int maxSize;
    private int top=-1;
    private int[] stack;

    //构造器,创建指定大小的栈
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[maxSize];
    }

    boolean isFull(){
        return top == maxSize-1;
    }

    boolean isEmpty(){
        return top == -1;
    }

    public void push(int n){
        if (isFull()){
            System.out.println("栈以满~~");
            return;
        }
        stack[++top]=n;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空~~");
        }
        int value=stack[top--];
        return value;
    }

    //显示所有有效数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空~~");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}