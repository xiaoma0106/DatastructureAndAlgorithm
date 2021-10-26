package datastructure.tree;

/**
 * @author mjh
 * @date 2021-10-26 11:36
 * 顺序二叉树
 * 物理结构为数组,逻辑结构为二叉树
 * 该树为完全二叉树
 * 若某一节点下标为n
 * 则其左子树下标为:2*n+1
 * 其右子树下标为:2*n+2
 * 其父节点下标为:(n-1)/2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        tree.preorder();
        System.out.println();

        System.out.println("中序遍历");
        tree.inorder();
        System.out.println();

        System.out.println("后序遍历");
        tree.postorder();

    }
}

class ArrBinaryTree {
    private int[] array;

    ArrBinaryTree(int[] array) {
        this.array = array;
    }

    public void preorder() {
        this.preorder(0);
    }

    public void inorder() {
        this.inorder(0);
    }

    public void postorder() {
        this.postorder(0);
    }

    //前序遍历
    private void preorder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("顺序二叉树为空~~");
            return;
        }

        System.out.println(array[index]);
        if ((index * 2 + 1) < array.length) {
            preorder(index * 2 + 1);
        }
        if ((index * 2 + 2) < array.length) {
            preorder(index * 2 + 2);
        }
    }

    //中序遍历
    private void inorder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("该顺序二叉树为空~~");
        }

        if ((2 * index + 1) < array.length) {
            inorder(2 * index + 1);
        }
        System.out.println(array[index]);
        if ((2 * index + 2) < array.length) {
            inorder(2 * index + 2);
        }
    }

    //后序遍历
    private void postorder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("该顺序二叉树为空~~");
        }
        if ((2 * index + 1) < array.length) {
            postorder(2 * index + 1);
        }
        if ((2 * index + 2) < array.length) {
            postorder(2 * index + 2);
        }
        System.out.println(array[index]);
    }
}