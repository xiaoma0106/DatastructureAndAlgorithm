package datastructure.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author mjh
 * @date 2021-10-07 7:46
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        HeroNode head = linkedList.getHead();

        SingleLinkedList linkedList2= new SingleLinkedList();
        HeroNode head2=linkedList2.getHead();

        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(5, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(7, "林冲", "豹子头");
//        HeroNode node02 = new HeroNode(5, "小卢", "玉麒麟~~");
        HeroNode node5 = new HeroNode(6, "宋江", "及时雨");
        HeroNode node6 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node7 = new HeroNode(8, "吴用", "智多星");
        HeroNode node8 = new HeroNode(4, "林冲", "豹子头");

        linkedList.addByOrder(node1);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node3);
        System.out.println("第一个单链表");
        linkedList.list();




        linkedList2.addByOrder(node5);
        linkedList2.addByOrder(node6);
        linkedList2.addByOrder(node7);
        linkedList2.addByOrder(node8);
        System.out.println("第二个单链表");
        linkedList2.list();
//        System.out.println("更新数据后~~");
//        linkedList.update(node02);
//        linkedList.del(3);
//        linkedList.list();
//        System.out.printf("共有%d个元素\n", getLength(head));

//        System.out.println("倒数第一个元素为:" + getLastIndexEle(head, 5));

//       reversePrint(head);

        System.out.println("排序后的单链表:");
        linkedList2.setHead(mergeList(head,head2));
        linkedList2.list();

//        HeroNode newHead=getReverseList(head);
//        linkedList.setHead(newHead);
//        linkedList.list();
    }

    //练习题1:获取单链表的有效节点个数
    public static int getLength(HeroNode head) {
        if (head == null || head.next == null)
            return 0;
        HeroNode cur = head.next;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //练习题2:获取单链表倒数第k个元素的值
    public static HeroNode getLastIndexEle(HeroNode head, int k) {
        //不存在任何元素时,返回null
        if (head == null || head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (k < 0 || k > size) {
            System.out.printf("输入的位置[%d]不在有效范围内.\n", k);
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //练习题3:返回给定单链表的逆序单链表
    public static HeroNode getReverseList(HeroNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;
        HeroNode newHead=new HeroNode(0,"","");
        HeroNode cur=head.next;
        HeroNode nextNode=null;
        while (cur != null){
            nextNode=cur.next;
            cur.next=newHead.next;
            newHead.next=cur;
            cur=nextNode;
        }
        return  newHead;
    }

    //练习题4:将单链表以倒序打印输出
    public static void reversePrint(HeroNode head){
        if (head == null || head.next==null){
            System.out.println("单链表为空!");
        }

        HeroNode cur=head.next;
        Deque<HeroNode> stack=new ArrayDeque<>();
        while (cur != null){
           stack.push(cur);
           cur=cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //练习题5:将2个有序单链表合并成一个有序单链表
    public static HeroNode mergeList(HeroNode head1,HeroNode head2){
        if (head1 == null || head1.next == null)
            return head2;
        if (head2 == null || head2.next == null)
            return head1;
        HeroNode newHead=new HeroNode(0,"","");
        HeroNode cur1=head1.next;
        HeroNode cur2=head2.next;
        HeroNode cur;
        HeroNode tail=newHead;
        while (cur1 != null && cur2 != null){
            if (cur1.no > cur2.no){
                cur=cur2;
                cur2=cur2.next;
            }else {
                cur=cur1;
                cur1=cur1.next;
            }
            tail.next=cur;
            tail=tail.next;
        }

        if (cur1 == null){
            tail.next=cur2;
        }else if (cur2 == null){
            tail.next=cur1;
        }
        return newHead;
    }

}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");
    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }


    //返回头节点单链表的头节点
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向 新的节点
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    //在单链表的最后位置添加节点
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null || temp.next.no > node.no)
                break;
            if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("要添加的数据%d已经存在\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }


    public void update(HeroNode node) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.printf("要更新的节点%d不存在!\n", node.no);
        }
    }

    public void del(Integer no) {
        HeroNode temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点%d不存\n", no);
        }
    }

    //显示链表[遍历]]
    public void list() {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空~~");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode {
    public Integer no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(Integer no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
