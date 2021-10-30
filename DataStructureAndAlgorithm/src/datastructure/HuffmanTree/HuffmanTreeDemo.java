package datastructure.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mjh
 * @date 2021-10-30 14:46
 * 将一个数组转化成哈夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = creatHuffmanTree(arr);
        root.preorder(root);
    }

    public static Node creatHuffmanTree(int[] arr) {
        //根据数组的值创建元素为Node的List
        List<Node> nodes = new ArrayList<>();
        for (int temp : arr) {
            nodes.add(new Node(temp));
        }
        Node leftNode = null;
        Node rightNode = null;
        Node parent = null;
        //当List中元素不是一个时,(一个表示已是哈夫曼树)
        while (nodes.size() > 1) {
            //将List进行排序
            Collections.sort(nodes);

            //从List中取出2个最小元素
            leftNode = nodes.get(0);
            rightNode = nodes.get(1);

            //根据这两个最小元素创建父节点
            parent = new Node(leftNode.value + rightNode.value);
            //不要忘记设置左右子节点
            parent.left=leftNode;
            parent.right=rightNode;

            //从List中删除已经处理好的最小两个元素,并将合成的父节点,添加到List中
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        //返回哈夫曼树的根节点
        return nodes.get(0);
    }
}


class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public void preorder(Node node) {
        System.out.println(node);
        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }

    Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //为了使用Collections.sort()方法,必须实现Comparable接口
    @Override
    public int compareTo(Node o) {
        //升序
        return this.value - o.value;

        //降序
//        return  o.value-this.value;
    }
}

