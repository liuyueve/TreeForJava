package entity;

/**
 * Author:liuyu
 * Date:2019-02-21
 */
public class Node implements Comparable<Node>{
    //父节点
    public Node parent;
    //左-子节点
    public Node left;
    //右-子节点
    public Node right;
    //数组节点(方便打印)
    public int num;


    //值
    public String value;
    public Node(int value){
        this.value = String.valueOf(value);
    }

    public Node(int len ,char fill){
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = fill;
        }
        value = String.valueOf(chars);
    }




    @Override
    public int compareTo(Node o) {
        return Integer.valueOf(value)-Integer.valueOf(o.value);
    }
}

