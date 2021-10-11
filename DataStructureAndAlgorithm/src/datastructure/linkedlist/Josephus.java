package datastructure.linkedlist;

/**
 * @author mjh
 * @date 2021-10-11 8:42
 * Josephus问题:
 * 对环形结构的特定人数(sum)的人,以某个人(k)为开端,
 * 向下传递m后,删除该位置上的人,在重复此操作,直到删除所有人
 *
 */
public class Josephus {
    public static void main(String[] args) {
        CircleSingledLinkedList linkedList=new CircleSingledLinkedList(5);
        linkedList.showBoy();
        linkedList.josephus(0,1);
    }
}

class CircleSingledLinkedList{
    private Boy first=null;

    /**
     * 创建节点数为sum的环形单向链表
     * @param sum
     */
    public CircleSingledLinkedList(int sum){
        Boy cur=null;
        for (int i = 1; i <= sum; i++) {
            if (i ==1){
                first=new Boy(i);
                cur=first;
                first.setNext(first);
                continue;
            }
            Boy newBoy=new Boy(i);
            cur.setNext(newBoy);
            newBoy.setNext(first);
            cur=newBoy;
        }
    }

    /**
     * 显示环形链表的数据
     */
    public void showBoy(){
        if (first==null){
            System.out.println("队列为空~~");
             return;
        }
        Boy cur=first;
        while (cur.getNext() != first){
            System.out.printf("元素%d\n",cur.getNo());
            cur=cur.getNext();
        }
        System.out.printf("元素%d\n",cur.getNo());

    }

    /**
     *Josephus处理
     * @param startNo 指定开始位置
     * @param k 指定间隔数
     */
    public void josephus(int startNo,int k){
        if (first == null){
            System.out.println("队列为空~~");
            return;
        }
        if (startNo <0 || k <0){
            System.out.println("参数有误,请重新输入~~");
            return;
        }

        for (int i = 0; i < startNo; i++) {
            first=first.getNext();
        }

        Boy helper=first.getNext();
        while (helper.getNext() != first){
            helper=helper.getNext();
        }

        while (first.getNext()!= first){
            for (int i = 0; i < k; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.printf("删除元素%d\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.println("删除的最后一个元素为"+first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
