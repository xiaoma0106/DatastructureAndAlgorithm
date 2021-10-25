package datastructure.tree;

/**
 * @author mjh
 * @date 2021-10-25 9:14
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree=new BinaryTree();

        HeroNode root=new HeroNode(1,"宋江");
        HeroNode node2=new HeroNode(2,"吴用");
        HeroNode node3=new HeroNode(3,"卢俊义");
        HeroNode node4=new HeroNode(4,"林冲");

        root.left=node2;
        root.right=node3;
        node3.right=node4;

        tree.setRoot(root);

        System.out.println("前序遍历:");
        tree.preOrder();//1,2,3,4

        System.out.println("中序遍历");
        tree.inOrder();//2,1,3,4

        System.out.println("后序遍历");
        tree.postOrder();//2,4,3,1

    }
}

class BinaryTree{
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (root == null){
            System.out.println("树为空~~");
            return;
        }
        root.preorder();
    }

    //中序遍历
    public void inOrder(){
        if (root == null){
            System.out.println("树为空~~");
            return;
        }
        root.inorder();
    }
    //后序遍历
    public void postOrder(){
        if (root == null){
            System.out.println("树为空~~");
            return;
        }
        root.postOrder();
    }


}

class HeroNode {
    private Integer id;
    private String name;
    public HeroNode left;
    public HeroNode right;

    public HeroNode(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preorder() {
        System.out.println(this);
        if (left != null) {
            left.preorder();
        }
        if (right != null) {
            right.preorder();
        }
    }

    //中序遍历
    public void inorder() {
        if (left != null)
            left.inorder();
        System.out.println(this);
        if (right != null)
            right.inorder();
    }

    //后序遍历
    public void postOrder(){
        if (left != null)
            left.postOrder();
        if (right != null)
            right.postOrder();
        System.out.println(this);
    }
}
