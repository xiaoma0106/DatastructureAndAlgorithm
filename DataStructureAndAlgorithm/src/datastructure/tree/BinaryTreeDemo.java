package datastructure.tree;

/**
 * @author mjh
 * @date 2021-10-25 9:14
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.left = node2;
        root.right = node3;
        node3.right = node4;
        node3.left = node5;

        tree.setRoot(root);


        //删除某一个节点


//        System.out.println("前序遍历:");
//        tree.preOrder();//1,2,3,5,4
//
        System.out.println("中序遍历");
        tree.inOrder();//2,1,5,3,4

        tree.deleteNo(5);
        tree.deleteNo(3);
        System.out.println("删除后:");
        tree.inOrder();
//        System.out.println("后序遍历");
//        tree.postOrder();//2,5,4,3,1


//        HeroNode target = null;
//        int id = 15;
////        target=tree.preOrderSearch(id);
//        target = tree.postOrderSearch(id);
//        if (target != null) {
//            System.out.printf("找到了,id=%d name=%s", target.getId(), target.getName());
//        } else {
//            System.out.printf("不存在id为%d的节点", id);
//        }

    }
}

class BinaryTree {
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("树为空~~");
            return;
        }
        root.preorder();
    }

    //中序遍历
    public void inOrder() {
        if (root == null) {
            System.out.println("树为空~~");
            return;
        }
        root.inorder();
    }

    //后序遍历
    public void postOrder() {
        if (root == null) {
            System.out.println("树为空~~");
            return;
        }
        root.postOrder();
    }

    //前序查找
    public HeroNode preOrderSearch(int id) {
        System.out.println("前序查找~~~~");
        if (root == null) {
            System.out.println("树为空~~");
            return null;
        }
        return root.preorderSearch(id);
    }

    //中序查找
    public HeroNode inOrderSearch(int id) {
        System.out.println("中序查找~~~~");
        if (root == null) {
            System.out.println("树为空~~");
            return null;
        }

        return root.inorderSearch(id);
    }

    //后序查找
    public HeroNode postOrderSearch(int id) {
        System.out.println("后序查找~~~~");
        if (root == null) {
            System.out.println("树为空~~");
            return null;
        }
        return root.postorderSearch(id);
    }

    //删除某一个节点
    public void deleteNo(int id) {
        if (root == null) {
            System.out.println("树为空~~");
            return;
        }
        if (root.getId() == id) {
            //当删除根节点时,因为根节点不存在父节点,临时添加根节点的父节点,以便使用函数来处理
            HeroNode parent = new HeroNode(0, null);
            parent.left = root;
            root.changeNode(parent, root, 1);
            //处理完后,临时添加的节点的左子节点就是新的根节点
            root = parent.left;
            return;
        }
        root.deleteNode(id);
    }
}

class HeroNode {
    private Integer id;
    private String name;
    public HeroNode left;
    public HeroNode right;

    private final int LEFT = 1;
    private final int RIGHT = 2;

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
    public void postOrder() {
        if (left != null)
            left.postOrder();
        if (right != null)
            right.postOrder();
        System.out.println(this);
    }


    //前序查找
    public HeroNode preorderSearch(int id) {
        if (this.id == id) {
            return this;
        }
        HeroNode obj = null;
        if (left != null) {
            obj = left.preorderSearch(id);
        }
        if (obj != null) {
            return obj;
        }

        if (right != null) {
            obj = right.preorderSearch(id);
        }
        if (obj != null) {
            return obj;
        }
        return null;
    }

    //中序查找
    public HeroNode inorderSearch(int id) {
        HeroNode obj = null;
        if (left != null) {
            obj = left.inorderSearch(id);
        }
        if (obj != null) {
            return obj;
        }

        if (this.id == id)
            return this;

        if (right != null) {
            obj = right.inorderSearch(id);
        }
        if (obj != null)
            return obj;
        return null;
    }

    //后序查找
    public HeroNode postorderSearch(int id) {
        HeroNode obj = null;
        if (left != null)
            obj = left.postorderSearch(id);
        if (obj != null)
            return obj;

        if (right != null)
            obj = right.postorderSearch(id);
        if (obj != null)
            return obj;

        if (this.id == id)
            return this;
        return null;
    }

    /**
     * 删除某一个节点
     * 要删除的节点有左右子节点,则让左节点替换要删除的节点
     */
    public void deleteNode(int id) {
        if (left != null && left.id == id) {
//            left = null;
            changeNode(this, left, LEFT);
            return;
        }
        if (right != null && right.id == id) {
//            right = null;
            changeNode(this, right, RIGHT);
            return;
        }

        if (left != null)
            left.deleteNode(id);
        if (right != null)
            right.deleteNode(id);
    }

    /**
     * @param parent    要删除节点的父节点,更新需要
     * @param node      要删除的节点
     * @param direction 1:要删除节点在父节点左边 2:在右边
     */
    public void changeNode(HeroNode parent, HeroNode node, int direction) {
        //要删除的节点为叶子节点是,直接删除
        if (node.left == null && node.right == null) {
            if (direction == LEFT)
                parent.left = null;
            if (direction == RIGHT)
                parent.right = null;
        }

        //要删除的节点,有左子节点,没有右子节点时,删除该节点,并将左子节点替换上来
        if (node.left != null && node.right == null) {
            if (direction == LEFT)
                parent.left = node.left;
            if (direction == RIGHT)
                parent.right = node.left;
        }

        //要删除的节点,没有左子节点,有右子节点时,删除该节点,并将右子节点替换上来
        if (node.left == null && node.right != null) {
            if (direction == LEFT)
                parent.left = node.right;
            if (direction == RIGHT)
                parent.right = node.right;
        }

        //要删除的节点,存在左右子节点时
        if (node.left != null && node.right != null) {
            //找到左子树的最右端节点来替换删除的节点
            HeroNode target = node.left;
            int count = 0;//左子节点是否存在其右子节点
            HeroNode targetParent = target;
            while (target.right != null) {
                count++;
                targetParent = target;
                target = target.right;
            }

            //当count不为0时,说明要删除的节点的左子节点存在其右子节点,要找到要删除节点左子树的最右端节点
            if (count > 0) {
                //要替换的节点没有子节点时,删除
                if (target.left == null) {
                    targetParent.right = null;
                    //有子节点时,将左子节点换上来
                } else {
                    targetParent.right = target.left;
                }

                //替换处理
                target.left = node.left;
            }
            //若count为0,说明要删除的节点的左子节点不存在其右子节点,即是要替换的节点,不需要进行上述查找处理.
            target.right = node.right;
            if (direction == LEFT) {
                parent.left = target;
            }
            if (direction == RIGHT) {
                parent.right = target;
            }
        }
    }
}
