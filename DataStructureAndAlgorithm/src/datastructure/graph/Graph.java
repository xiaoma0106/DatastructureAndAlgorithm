package datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author mjh
 * @date 2022-04-07 15:15
 */
public class Graph {
    // 顶点列表
    private ArrayList<String> vertexList;
    // 边关系:两个下标位置表示两个顶点,值表示两个顶点的权值.(假定0表示两顶点不存在边)
    private int[][] edges;
    // 这个图的边的个数
    private int numOfEdges;

    // 在图的遍历时使用,表示是否被访问过
    private boolean[] isVisited;

    // 测试
    public static void main(String[] args) {
        // 设置图的顶点个数
//        int n = 8;
        // 顶点值
//        String [] vs = {"A","B","C","D","E"};
        String[] vs = {"1","2","3","4","5","6","7","8"};

        // 新建一个图
        Graph graph = new Graph(vs.length);

        // 为图的顶点赋值
        for (String vertex : vs){
            graph.insertVertex(vertex);
        }

        // 为图的边赋值
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);

        // 为图的边赋值
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        // 显示邻接矩阵
        graph.showGraph();

        // 深度遍历测试
        System.out.println("深度遍历:");
        graph.dfs();

        System.out.println();
        // 广度遍历
        System.out.println("广度遍历:");
        graph.wdf();

    }

    // 1⃣️图的创建
    /**
     * 创建一个n个顶点的图结构
     * @param n
     */
    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        numOfEdges = 0;
    }

    /**
     * 插入顶点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 顶点v1和v2建立权值为weight的边
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1 ,int v2,int weight) {
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }

    /**
     * 返回下标为index的顶点值
     * @param index
     * @return
     */
    public String getVertex(int index) {
        return vertexList.get(index);
    }

    /**
     * 返回图的边的数量
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回图的顶点个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回顶点v1,v2的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示所有边的关系及权值
     */
    public void showGraph(){
        for (int[] line : edges)
            System.out.println(Arrays.toString(line));
    }

    // 2⃣️图的遍历
    // 2.1 深度遍历

    /**
     * 获取该顶点的第一个子顶点(相对来说是父子的关系)
     * @param vIndex
     * @return
     */
    private int getFirstNeighbor(int vIndex){
        if (vIndex >= getNumOfEdges())
            throw new RuntimeException("指定范围已超过图的顶点数!!");
        for (int i = 0; i < edges[vIndex].length; i++) {
            if (edges[vIndex][i] != 0) {
                return i;
            }
        }
        //指定顶点不存在与之连接的顶点!
        return -1;
    }

    /**
     * 已知顶点v1,v2存在边,求与v1连接的下一个顶点的索引
     * @param v1
     * @param v2
     * @return
     */
    private int getNextNeighbor(int v1, int v2) {
        if (v1 >= vertexList.size() || v2 >= vertexList.size())
            throw new RuntimeException("指定范围已超出图的顶点数!!");
        for (int i = v2+1; i <  vertexList.size(); i++){
            if (edges[v1][i] != 0)
                return i;
        }
        // 不存在下一个邻接顶点
        return -1;
    }

    /**
     * 从指定顶点开始深度遍历
     * @param isVisited
     * @param v
     * 深度遍历算法:
     */
    private void dfs(boolean[] isVisited,int v){
        // 输出该顶点值
        System.out.print(getVertex(v) + "->");
        // 设置成以访问过
        isVisited[v] = true;
        // 获取该顶点的第一个连接点
        int w = getFirstNeighbor(v);
        // 当存在第一个连接点或下一个邻接点时
        while (w != -1) {
            // 判读该顶点是否被访问过,
            if (!isVisited[w]){
                // 若没被访问过,则深度遍历该顶点
                dfs(isVisited,w);
            }

            // 若w顶点已被访问过,则寻找下一个邻接点
            w = getNextNeighbor(v,w);
        }
    }

    public void dfs(){
        // 每次遍历,被访问数组都要重新设置,否则,第二次之后都是已访问过状态
        isVisited = new boolean[vertexList.size()];
        // 整个图的顶点不一定都是连接在一起的,因此要分别判读各个顶点的访问状态,
        for (int i = 0; i < vertexList.size(); i++) {
            // 若该顶点还未被访问过,则深度遍历该顶点
            if (!isVisited[i])
                dfs(isVisited,i);
        }
    }


    // 2.2 广度遍历

    /**
     * 广度遍历
     * @param isVisited
     * @param v
     */
    private void wdf(boolean[] isVisited, int v){
        System.out.print(vertexList.get(v)+"->");
        isVisited[v]=true;
        int top;// 队列头节点对应的顶点下标
        int index; // 邻接节点下标
        // 存放已被遍历,但是还没遍历下一层的顶点
        LinkedList<Integer> queue= new LinkedList<>();
        // 初始将顶点v放入队列中
        queue.addLast(v);
        // 当队列不为空时,循环处理
        while (!queue.isEmpty()) {
            // 取出队列的第一个元素,要对该顶点的下一层顶点进行判读
            top = queue.removeFirst();
            // 获取该顶点的第一个顶点
            index = getFirstNeighbor(top);
            // 若存在则循环处理
            while (index != -1){
                // 判断获取的顶点是否被访问过
                if (!isVisited[index]){
                    // 若没被访问过,则输出该顶点,设置成已被访问状态
                    System.out.print(vertexList.get(index) + "->");
                    isVisited[index] = true;
                    // 将该顶点放入队列的最后一个位置,以便循环使用
                    queue.addLast(index);
                }
                // 获取下一个邻接顶点
                index = getNextNeighbor(top,index);
            }
        }
    }

    public void wdf(){
        // 遍历顶点时,将访问状态重置
        isVisited = new boolean[vertexList.size()];
        // 同深度遍历,整个图的顶点不一定都连接在一起,所以需要全部遍历判断
        for (int i = 0; i < vertexList.size(); i++){
            if (!isVisited[i])
                wdf(isVisited,i);
        }
    }
}
