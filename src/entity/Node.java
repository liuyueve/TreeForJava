package entity;

/**
 * Author:liuyu
 * Date:2019-02-21
 */
public class Node {
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
    public Node(int value,int num){
        this.value = String.valueOf(value);
        this.num = num;
    }

    public Node(int len){
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = '*';
        }
        value = String.valueOf(chars);
    }
}

