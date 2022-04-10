package datastructure.avl;

/**
 * @author mjh
 * @date 2022-03-21 20:12
 * 平衡二叉树
 *  难点: 单旋转(左旋,右旋)
 *       双旋转(进行右旋转时,若该目标节点的左子树的右子树的深度比左子树深,
 *              则先需要对目标节点的左子树进行左旋操作)
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {5,3,6,7};
        int[] arr={10,11,7,6,8,9,20,15};

        AVLTree avlTree = new AVLTree();
        for(int i = 0; i< arr.length; i++){
            avlTree.add(new Node(arr[i]));
        }

        avlTree.deleteNode(10);
        System.out.println("中序遍历:");
        avlTree.infixOrder();
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 往平衡树中添加节点
     * @param node
     */
    public void add(Node node){
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    /**
     * 删除值为val的节点
     * @param val
     */
    public void deleteNode(int val){
        // 该树为空时,输出空信息
        if (root == null){
            System.out.println("该树为空~~~");
            return;
        }

        //要删除的节点不存在时,返回信息
        Node target = root.search(val);
        if (target == null){
            System.out.println("值为 "+val+" 的节点不存在!");
            return;
        }

        // 要删除的节点存在,找出其父节点
        Node parent = root.searchParent(val);

        // 删除节点操作,分3种情况分别处理
        // 1.当要删除的节点为叶子节点时
        if (target.left == null && target.right == null){
            // 目标节点不存在父节点,即目标节点就是根节点
            if (parent == null){
                root = null;
                return;
            }
            // 目标节点存在父节点时
            if (parent.left == target)
                parent.left = null;
            else
                parent.right = null;
        }
        // 2.要删除的节点仅存在一个子树时
        if (target.left != null && target.right == null){
            if (parent == null){
                root = root.left;
                return;
            }

            if (parent.left == target)
                parent.left = target.left;
            else
                parent.right = target.left;

        }else if (target.right != null && target.left == null){
            if (parent == null ){
                root = root.right;
                return;
            }

            if (parent.left == target)
                parent.left = target.right;
            else
                parent.right = target.right;
        }

        // 3.要删除的节点同时存在左右子树时
        // 有两种解决方法
        //  1)找到目标节点的左子树的最右端节点,删除该节点,并把删除的值赋值给目标节点
        //  2)找到目标节点的右子树的最左端节点,删除该节点,并把删除的值赋值给目标节点
        //
        if (target.left != null && target.right != null){
           // 判读目标节点左右子树的高度,对高度高的的子树进行操作
            if (target.leftHeight() > target.rightHeight()){
                int newVal = delRightOfLeftTree(target.left);
                target.value= newVal;
            } else {
                int newVal = delLeftOfRightTree(target.right);
                target.value = newVal;
            }
        }
    }

    /**
     * 删除该子树的最右端节点,并返回该节点值
     * @param leftParent
     * @return
     */
    int delRightOfLeftTree(Node leftParent){
        Node target = leftParent;
        while (target.right != null)
            target = target.right;
        int val = target.value;
        deleteNode(val);
        return val;
    }

    /**
     * 删除该子树的最左端的节点,并返回该节点值
     * @param rightParent
     * @return
     */
    int delLeftOfRightTree(Node rightParent){
        Node target = rightParent;
        while (target.left != null)
            target = target.left;
        int val = target.value;
        deleteNode(val);
        return val;
    }


    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("二叉树为空~~,不能遍历!");
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

    /**
     * 返回该节点的左子树的高度
     * @return
     */
    public int leftHeight(){
        if (left == null)
            return 0;
        return left.height();
    }

    /**
     *返回该节点的右子树的高度
     * @return
     */
    public int rightHeight(){
        if (right == null)
            return 0;
        return right.height();
    }

    /**
     * 返回该节点的高度
     * @return
     */
    public int height(){
        return Math.max(left == null? 0:left.height(), right==null? 0: right.height())+1;
    }

    /**
     * 查找值为val的节点
     * @param val
     * @return
     */
    Node search(int val){
        // 该节点就是要找的节点
        if (this.value == val)
            return this;

        // 目标值大于该节点,并且右节点不为空时,递归查找
        if (this.value < val && this.right != null){
           return this.right.search(val);
        }

        // 目标值小于该节点值,并且左节点不为空时,递归查找
        if (this.value > val && this.left != null) {
            return this.left.search(val);
        }
        // 找不到时,返回null
        return null;
    }

    /**
     * 查找值为val的节点的父节点(删除时被使用)
     * @param val
     * @return
     */
    Node searchParent(int val){
        Node target = null;
        // 左节点不为空时
        if (this.left != null){
            // 左节点的值等于目标值时,该节点就是要找的父节点
            if(this.left.value == val)
                target= this;
            else
                // 否则左节点递归查找
                 target = this.left.searchParent(val);
        }

        // 右节点不为空时
        if (target == null && this.right != null ){
            // 右节点的值等于目标值时,该节点就是要找的父节点
            if (this.right.value == val)
                target = this;
            else
                // 否则右节点递归查找
                target = this.right.searchParent(val);
        }
        // 找不到时,返回null
        return target;
    }


    /**
     * 往该节点中添加子节点
     * 注意:添加完成后需要判断是否还是平衡树,
     *      若不是,则需要旋转到达平衡
     * @param node
     */
    protected void add(Node node) {
        if (node == null)
            return;

        // 比该节点值小时,往其左子树添加
        if (this.value > node.value){
            // 若左子树为空,则直接设置为左节点
            if (left == null){
                left = node;
            // 若左子树不为空,则左子节点递归添加节点
            } else {
                left.add(node);
            }

        // 不必该节点小时,往其右子树添加
        } else {
            // 若右子节点为空,则直接设置为右子节点
            if (right == null){
                right = node;
            // 若右子节点不为空,则右子节点递归添加节点
            }else {
                right.add(node);
            }
        }

        // 添加节点后,要判断是否还平衡(左右子树深度是否大于1),否则要旋转进行调整
        if (Math.abs(leftHeight()-rightHeight())>1){
            // 右子树深度较深时,要进行左旋转
            if (rightHeight() > leftHeight()){
                // 在左旋转之前要判读右子树的左子树是否比右子树深,若是则需要先对右子树进行右旋转
                if (right != null && right.leftHeight()>right.rightHeight()){
                    right.rightRotate();
                    leftRotate();
                // 若右子树的右子树深度大于其左左子树,则只要左旋转
                }else {
                    leftRotate();
                }
            // 左子树比右子树深时,需要右旋转
            } else {
                // 在右旋转之前,需要判读左子树的右子树是否比其左子树深,若是,则需要先对左子树进行右旋转,在左旋转
                if (left != null && left.rightHeight()>left.leftHeight()){
                    left.leftRotate();
                    rightRotate();
                }else {
                    rightRotate();
                }
            }
        }
    }


    /**
     * 左旋转
     *  步骤:
     *    1.以该节点的值创建一个新节点
     *    2.新节点的左节点指向该节点的左节点
     *    3.新节点的右节点指向该节点的右节点的左节点
     *    4.该节点的右节点的值赋值给该节点
     *    5.该节点右节点指向该节点的右节点的右节点
     *    6.该节点的左节点指向新节点
     *
     *
     * 为什么要新建一个节点,而不是直接
     * 将该节点的右节点指向右节点的左节点,
     * 右节点的左节点指向该节点?
     *  原因:该节点不一定是根节点,当该节点是非根节点是,父节点的子节点指向会出问题.
     */
    public void leftRotate(){

        //1.以该节点的值创建一个新节点
        Node newNode = new Node(value);
        //    2.新节点的左节点指向该节点的左节点
        newNode.left = left;
        //    3.新节点的右节点指向该节点的右节点的左节点
        newNode.right = right.left;
        // 6.该节点的左节点指向新节点
        this.left= newNode;
        //   4.该节点的右节点的值赋值给该节点
        this.value = right.value;
        //   5.该节点右节点指向该节点的右节点的右节点
        this.right = right.right;
    }

    /**
     * 右旋转与左旋转同理
     */
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.right = newNode;
        this.value = left.value;
        this.left = left.left;
    }

    /**
     * 中序遍历该节点
     */
    public void infixOrder(){
        if (left != null){
            left.infixOrder();
        }

        System.out.println(this);

        if(right != null){
            right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

