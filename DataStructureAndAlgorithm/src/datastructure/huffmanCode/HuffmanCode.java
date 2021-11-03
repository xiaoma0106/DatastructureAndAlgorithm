package datastructure.huffmanCode;

import java.io.*;
import java.util.*;

/**
 * @author mjh
 * @date 2021-10-31 16:37
 * A)字符串进行以哈夫曼形式进行压缩
 * 1)根据输入的字符数组得到某个字符对应出现的个数的countMap,创建对应的Node对象(此时已定义好树节点Node)
 * 2)根据Node对象得到哈夫曼树huffmanTree
 * 3)根据huffmanTree得到哈夫曼编码表huffmanMap
 * 4)根据huffmanMap求出对应的压缩字符数组outStr(字符与二进制的转换处理)
 * <p>
 * B)对压缩的字符数组进行解压 (难点:1.字节转二进制字符串 2.最后一个字节的有效位是否为8,进行不同的处理)
 *
 *
 */
public class HuffmanCode {
    public static void main(String[] args) {

//        System.out.println(Integer.toBinaryString(-8|256));
//        String m="abc".substring(0,0);
//        System.out.println(m.equals(""));
//
//        A)压缩字符串
//        String inStr = "i like like like java do you like a java i";
//        byte[] inByte = inStr.getBytes();
//        System.out.println(inByte.length);
//        byte[] zipByte = huffmanZip(inByte);
//
////        StringBuilder test=new StringBuilder("12345678");
////        String my=test.substring(0,8);
//        System.out.println(Arrays.toString(zipByte) + " 长度: " + zipByte.length);

//         B)解压字符串
//        System.out.println("解压后的数据:");
//        byte[] bytes=unZip(zipByte,huffmanCodes,lastLen);
//        System.out.println(new String(bytes));


        //C)压缩某一个文件到另一个文件
//        zipFile("/Users/majiahui/Coding/myTest.txt","/Users/majiahui/Coding/myTest.zip");
        zipFile("/Users/majiahui/Coding/myPic.png","/Users/majiahui/Coding/myPic.zip");
//        System.out.println("压缩成功~~");

        //D)解压文件
//        unZipFile("/Users/majiahui/Coding/myTest.zip","/Users/majiahui/Coding/myTest2.txt");
        unZipFile("/Users/majiahui/Coding/myPic.zip","/Users/majiahui/Coding/myPic2.png");
        System.out.println("解压成功~~");
    }


    //D)解压被压缩的文件
    public static void unZipFile(String srcFile, String dstFile){
        InputStream is=null;
        ObjectInputStream ois=null;
        OutputStream os=null;
        try {
            is=new FileInputStream(srcFile);
            ois=new ObjectInputStream(is);
            byte[] zipByte=(byte[]) ois.readObject();
            Map<Byte,String> hashMap=(Map<Byte,String>)ois.readObject();
            Integer LLen=(Integer) ois.readObject();
            byte[] unZipByte=unZip(zipByte,hashMap,LLen);

            os=new FileOutputStream(dstFile);
            os.write(unZipByte);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //C)压缩某一个目录下的文件
    public static void zipFile(String srcFile,String dstFile){

        InputStream is=null;
        OutputStream os=null;
        ObjectOutputStream oos=null;
        try {
            is=new FileInputStream(srcFile);
            byte[] bytes=new byte[is.available()];
            is.read(bytes);
            byte[] zipByte=huffmanZip(bytes);
            os=new FileOutputStream(dstFile);
            oos=new ObjectOutputStream(os);
            oos.writeObject(zipByte);
            oos.writeObject(huffmanCodes);
            oos.writeObject(lastLen);
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }finally {
            try {
                oos.close();
                os.close();
                is.close();
            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
            }
        }
    }

    //B)解压回字符串
    public static byte[] unZip(byte[] zipByte, Map<Byte, String> hashMap,int LLen) {
        //1)将hashMap的键值对应关系反过来,便于根据编码值来查找字符
        Map<String, Byte> newHashMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : hashMap.entrySet()) {
            newHashMap.put(entry.getValue(), entry.getKey());
        }
        //2)对压缩字节数组进行遍历
        int point = 0;
        int cur = 0;
        String target=null;
        Byte value=null;
        //获取压缩字节数组对应的二进制字符串
        StringBuilder sb=new StringBuilder();
        String str=null;

        for (int i = 0; i < zipByte.length-1; i++) {
             str=byteToBitString(zipByte[i],true);
            sb.append(str);
        }
        //字节数组的最后一个的位数为1到7时,则表示要根据有效长度截取,否则都是截取8位长度
        if (LLen >0){
             str=byteToBitString(zipByte[zipByte.length-1],false);
        }else {
             str=byteToBitString(zipByte[zipByte.length-1],true);
        }
        sb.append(str);
        //得到二进制字符串
        String zipString=sb.toString();
        int len=zipString.length();
        //此时并不知道压缩后字符个数,因此用List来存储数据而非数组
        List<Byte> result = new ArrayList<>();
        while (point < len) {
            while (cur <= len) {
                target=zipString.substring(point,cur);
                value=newHashMap.get(target);
                if (value != null){
                    result.add(value);
                    point =cur;
                    break;
                }
                cur++;
            }
            if (cur >= len)
                break;
        }
        //此时已获取到所有压缩后的字符,并将数据转移到数组中返回
        byte[] rs=new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            rs[i]=result.get(i);
        }
        return rs;

    }

    /**
     * 将一个字节转化成二进制字符串
     * @param b 传入的字节数据
     * @param flag 表示是否需要补高位.true:需要补高位,false:不需要补,传入的最后一个字节时,要根据有效长度进行截取
     * @return
     */
    public static String byteToBitString(byte b,boolean flag){
        int temp=b;
        String str=null;
        if (flag){
           str=Integer.toBinaryString(temp|256);
            return str.substring(str.length()-8);
        }else {
            str=Integer.toBinaryString(temp);
            return str.substring(str.length()-lastLen);
        }
    }


    //A)压缩字符串
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> list = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(list);
        Map<Byte, String> huffmanMap = getCodes(huffmanTreeRoot);
        byte[] zipByte = zip(bytes, huffmanMap);
        return zipByte;
    }

    //1)根据输入的字符数组得到某个字符对应出现的个数的countMap,创建对应的Node对象(此时已定义好树节点Node)

    private static List<Node> getNodes(byte[] bytes) {
        List<Node> list = new ArrayList<>();

        Map<Byte, Integer> map = new HashMap<>();
        Integer count = null;
        for (byte target : bytes) {
            count = map.get(target);
            if (count == null) {
                map.put(target, 1);
            } else {
                map.put(target, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            list.add(node);
        }

//        System.out.println(list.toString());
        return list;
    }

    //2)根据Node对象得到哈夫曼树huffmanTree
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    //生成哈夫曼表时要用到的静态变量
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    static  Integer lastLen=0; // 最后一个字节的有效长度

    //3)根据huffmanTree得到哈夫曼编码表huffmanMap
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            System.out.println("树为空,无法创建哈夫曼编码表!");
        }
//            root.preorder();
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * @param node      当前节点
     * @param leftRight 当前节点往左子树走:0 往右子树走:1
     * @param path      根节点到当前节点路径字符串
     */
    private static void getCodes(Node node, String leftRight, StringBuilder path) {
        StringBuilder sb = new StringBuilder(path);
        if (node != null) {
            sb.append(leftRight);
            if (node.data == null) {
                getCodes(node.left, "0", sb);
                getCodes(node.right, "1", sb);
            } else {
                huffmanCodes.put(node.data, sb.toString());
            }
        }
    }

    //4)根据huffmanMap求出对应的压缩字符数组outStr(字符与二进制的转换处理)
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanMap) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String str = huffmanMap.get(b);
            sb.append(str);
        }
//        System.out.println("压缩后的二进制编码:" + sb + " 长度: " + sb.length());

        int len = (sb.length() + 7) / 8;

        byte[] result = new byte[len];
        int count = 0;
        int data;
        for (int i = 0; i < sb.length(); i += 8) {
            if (i + 8 <= sb.length()) {
                data = Integer.parseInt(sb.substring(i, i + 8), 2);
            } else {
                data = Integer.parseInt(sb.substring(i), 2);
                lastLen=sb.length()-i;
            }
            result[count] = (byte) data;
            count++;
        }

        return result;
    }

}

//哈夫曼树节点定义
class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preorder() {
        System.out.println(data + "->" + weight);
        if (left != null)
            left.preorder();
        if (right != null)
            right.preorder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", count=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
