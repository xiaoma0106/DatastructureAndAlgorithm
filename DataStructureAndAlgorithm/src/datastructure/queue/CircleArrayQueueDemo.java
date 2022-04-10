package datastructure.queue;

/**
 * @author mjh
 * @date 2021-10-10 20:33
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue=new CircleArrayQueue(4);
        queue.addQueue(5);
        queue.addQueue(7);
        queue.addQueue(9);
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        queue.addQueue(2);
        queue.showQueue();
    }
}

class CircleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1 + maxSize) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满,不能添加新数据!");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没有可获取的数据!");
        }
        int result = arr[front];
        front = (front + 1) % maxSize;
        return result;
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空~~");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d , ", (i % maxSize), arr[i % maxSize]);
        }
    }

    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("队列为空~~");
        }
        return arr[front];
    }
}
