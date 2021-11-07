package datastructure.binarySortTree;

/**
 * @author mjh
 * @date 2021-11-07 9:53
 * 二叉排序树:节点的值比左子树都大,比右子树都小(中序遍历的结果为有序)
 * 难点:节点都删除
 *  删除的节点为根节点时
 *  当删除的节点有左右子树时
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        int[] arr = {7,3,9,5,4};

        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            tree.add(node);
        }

        tree.infixOrder();
        Node root = tree.getRoot();
        System.out.println("----删除前后----");
//        Node node1=root.searchParent(9);
//        System.out.println(node1);

        tree.deleteNode(7);
        tree.infixOrder();

    }


}

class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void deleteNode(int value) {
        if (root == null) {
            System.out.println("树为空~~");
            return;
        }
        Node target = root.search(value);
        if (target == null) {
            System.out.println("该值为[" + value + "]的节点不存在!");
            return;
        }
        Node parent = root.searchParent(value);//若parent为null时,要删除的节点就是根节点.之后的操作都是以此为判断
//        if (parent == null){
//           if (root.left == null && root.right == null){
//               root=null;
//               return;
//           }else {
//               parent=new Node(0);
//               parent.left=root;
//           }
//        }

        //1.要删除的节点没有子节点时
        if (target.left == null && target.right == null) {
            if (parent == null) {
                root = null;
                return;
            } else {
                if (parent.left == target) {
                    parent.left = null;
                } else if (parent.right == target) {
                    parent.right = null;
                }
            }
        }

        //2.要删除的节点存在左子树或右子树时
        if (target.left != null && target.right == null) {
            if (parent == null) {
                root = root.left;
            } else {
                if (parent.left == target) {
                    parent.left = target.left;
                } else if (parent.right == target) {
                    parent.right = target.left;
                }
            }
        } else if (target.right != null && target.left == null) {
            if (parent == null) {
                root=root.right;
            } else {
                if (parent.left == target) {
                    parent.left = target.right;
                } else if (parent.right == target) {
                    parent.right = target.right;
                }
            }
        }

        //3.当要删除的节点存在左右子树时
        //3.1 取左子树的最右边的节点值替换目标节点,并删除左子树的最右节点
        //3.2 取右子树的最左边的节点值替换目标节点,并删除右子树的最左节点
        if (target.left != null && target.right != null) {
            int val = delRightOfLeftTree(target.left);
            target.value = val;
        }
    }

    public int delRightOfLeftTree(Node leftParent) {
        Node target = leftParent;
        while (target.right != null) {
            target = target.right;
        }

        int result=target.value;
        deleteNode(target.value);
        return result;
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("树为空~~");
            return;
        }
        root.infixOrder();
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 返回要删除的节点
     *
     * @param val 该节点的值
     * @return
     */
    public Node search(int val) {
        if (this.value == val) {
            return this;
        }
        if (this.value > val) {
            if (this.left != null) {
                return this.left.search(val);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(val);
            } else {
                return null;
            }
        }
    }


    /**
     * 查找要删除节点的父节点,用于更新
     *
     * @param val
     * @return
     */
    public Node searchParent(int val) {
        if (this.value > val && this.left != null) {
            if (this.left.value == val) {
                return this;
            } else {
                return this.left.searchParent(val);
            }
        } else if (this.value < val && this.right != null) {
            if (this.right.value == val) {
                return this;
            } else {
                return this.right.searchParent(val);
            }
        }
        return null;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value > this.value) {
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        } else {
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}