package datastructure.tree;

/**
 * @author mjh
 * @date 2021-10-28 16:39
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        //测试一把中序线索二叉树的功能
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        //不同类型二叉树测试
//        Node node7 = new Node(20, "Jim");
//        node3.right = node7;

        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);//

//        tree.threadedNodeIn();//中序线索化二叉树
//        tree.inorderList();//中序线索化二叉树中序遍历:8,3,10,1,14,6

//        tree.threadedNodePre();//前序线索化二叉树
//        tree.preorderList();//前序线索化二叉树前序遍历:1,3,8,10,6,14

        tree.threadedNodePost();
        tree.postorderList();//后序线索化二叉树后序遍历:8,10,3,14,6,1
//        System.out.println(node5.left);
//        System.out.println(node5.right);

    }
}

class ThreadedBinaryTree {
    private Node root;
    private Node pre = null;

    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    public void threadedNodeIn() {
        this.threadedNodeIn(root);
    }

    //中序方式构建线索化二叉树
    private void threadedNodeIn(Node node) {
        if (node == null) {
            return;
        }

        threadedNodeIn(node.left);
        if (node.left == null) {
            node.leftType = 1;
            node.left = pre;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        threadedNodeIn(node.right);
    }

    public void inorderList() {
        inorderList(root);
    }

    //中序遍历二叉树
    private void inorderList(Node node) {
        while (node != null) {
            while (node.leftType != 1 && node.left != null) {
                node = node.left;
            }
            System.out.println(node);
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            node = node.right;
//            System.out.println(node);
        }
    }


    //前序构建线索化二叉树
    public void threadedNodePre() {
        this.threadedNodePre(root);
    }

    private void threadedNodePre(Node node) {
        if (node == null)
            return;

        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;

        //在对该节点的左子节点处理之前,已经该节点的左指针进行赋值,避免死循环,因此要判断子树类型
        if (node.leftType == 0) {
            threadedNodePre(node.left);
        }
        if (node.rightType == 0) {
            threadedNodePre(node.right);
        }
    }

    //前序遍历二叉树
    public void preorderList() {
        this.preorderList(root);
    }

    private void preorderList(Node node) {
        //最后一个节点的右指针必定是null,以此为判断依据,否则会死循环
        while (node != null && node.right != null) {
            System.out.println(node);
            //该节点存在左子节点(Type为0),就是要前序遍历的下一个节点
            while (node.left != null && node.leftType == 0) {
                node = node.left;
                System.out.println(node);
            }
            //该节点的右指针类型为1,就是前序遍历的下一个节点
            while (node.right != null && node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            //进行到这一步时,右指针必定为右子树或null.若存在左子树,则移动到该左子树
            if (node.leftType == 0) {
                node = node.left;
            }
        }
    }

    //后序线索化二叉树
    public void threadedNodePost() {
        this.threadedNodePost(root);
    }

    private void threadedNodePost(Node node) {
        if (node != null) {
            threadedNodePost(node.left);
            threadedNodePost(node.right);

            if (node.left == null) {
                node.left = pre;
                node.leftType = 1;
            }
            if (pre != null && pre.right == null) {
                pre.right = node;
                pre.rightType = 1;
            }
            pre = node;
        }
    }

    //后序化二叉树遍历
    public void postorderList(){
        this.postorderList(root);
    }
    private void postorderList(Node node){
        if (node == null)
            return;
        if (node.leftType == 0){
            postorderList(node.left);
        }
        if (node.rightType == 0){
            postorderList(node.right);
        }
        System.out.println(node);


        /*叶子节点的左右指针并没有用到
        boolean flag=true;
        Node myRoot=node;
        Node parent=node;
        while (node != myRoot && flag){
            flag= false;
            while (node.left != null){
                parent=node;
                node=node.left;
            }
            System.out.println(node);
            while (node.rightType == 1){
                node=node.right;
                System.out.println(node);
            }

        }

         */
    }

}

class Node {
    Integer id;
    String name;
    Node left;
    Node right;
    //左右指针的性质,
    //该指针表示子树:0
    //该指针表示前驱或后继节点:1
    Integer leftType = 0;
    Integer rightType = 0;

    public Node(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
