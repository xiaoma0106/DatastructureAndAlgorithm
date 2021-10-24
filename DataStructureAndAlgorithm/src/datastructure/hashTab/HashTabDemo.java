package datastructure.hashTab;

/**
 * @author mjh
 * @date 2021-10-24 18:40
 * 使用哈希表对员工信息进行管理
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab=new HashTab(10);

        Emp emp1=new Emp(1,"zhang");
        Emp emp2=new Emp(2,"zhang");
        Emp emp11=new Emp(11,"zhang");
        Emp emp4=new Emp(4,"zhang");

        hashTab.add(emp1);
        hashTab.add(emp2);
        hashTab.add(emp11);
        hashTab.list();

    }
}

class HashTab{
    private EmpLinkedList[] empLinkedListsArray;
    private int size;

    public HashTab(int size){
        this.size=size;
        empLinkedListsArray=new EmpLinkedList[size];

        //给数组分配内存后,数组中的每一个链表对象也要分配内存
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i]=new EmpLinkedList();
        }
    }

    private int hashFunc(int id){
        return id % size;
    }

    public void add(Emp emp){
        int index=hashFunc(emp.id);
        empLinkedListsArray[index].add(emp);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list(i);
        }
    }

    public void findById(Integer id){
        int index=hashFunc(id);
        EmpLinkedList list=empLinkedListsArray[index];
        Emp emp=list.getEmpById(id);
        if (emp == null){
            System.out.println("不存在id为"+id+"的员工信息");
        }else{
            System.out.println("在第"+(index+1)+"条链表中找到该员工id为"+id+"信息");
        }
    }
}

class Emp {
    public Integer id;
    public String name;
    public Emp next;

    public Emp(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}

class EmpLinkedList{
    private Emp head=null;

    protected void add(Emp emp){
        if (head == null){
            head=emp;
            return;
        }

        Emp cur=head;
        while (cur.next != null){
            cur=cur.next;
        }
        cur.next=emp;
    }

    protected void list(Integer no){
        if (head == null){
            System.out.println("第 "+(no+1)+"条链表为空~~");
            return;
        }
        System.out.print("第 "+(no+1)+" 条链表的信息为:");
        Emp cur=head;
        while (cur != null){
            System.out.printf(" ==> id=%d name=%s",cur.id,cur.name);
            cur=cur.next;
        }
        System.out.println();
    }

    protected Emp getEmpById(Integer id){
        Emp cur=head;
        while (cur != null){
            if (id == cur.id)
                return cur;
           cur=cur.next;
        }
        return null;
    }

}
