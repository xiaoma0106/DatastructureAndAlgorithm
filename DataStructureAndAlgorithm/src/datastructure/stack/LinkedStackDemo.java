package datastructure.stack;

import java.util.Scanner;

/**
 * @author mjh
 * @date 2021-10-12 12:02
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        //接收输入值
        String key = "";
        Scanner scanner = new Scanner(System.in);
        //判断是否跳出循环
        boolean flag = true;

        while (flag) {
            System.out.println("--------------------------");
            System.out.println("show:显示所有数据");
            System.out.println("exit:退出");
            System.out.println("push:数据入栈");
            System.out.println("pop:数据出栈");
            System.out.println("--------------------------");
            System.out.println("请输入命令:");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入要入栈的数据:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    int rs = stack.pop();
                    System.out.printf("数据[%d]出栈\n", rs);
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("输入命令不正确,请重新输入!");
            }
        }
        System.out.println("程序退出~~");
    }
}

/**
 * 无头节点单向链表实现栈
 */
class LinkedStack {
    private Node top;

    boolean isEmpty() {
        return null == top;
    }

    public void push(int n) {
        Node node = new Node(n);
        if (top == null) {
            top = node;
            return;
        }
        node.next = top;
        top = node;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空~~");
        }
        int value = top.getValue();
        top = top.next;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空~~");
        }
        Node cur = top;
        while (cur != null) {
            System.out.printf("元素%d\n", cur.getValue());
            cur=cur.next;
        }
    }

}

class Node {
    private int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}