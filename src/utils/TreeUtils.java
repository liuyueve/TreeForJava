package utils;

import entity.Node;

/**
 * Author:liuyu
 * Date:2019-02-21
 */
public class TreeUtils {

    //打印树的时候依据每行来打印，每行最多256个字符
    private static char[] line = new char[256];

    static {
        for (int i = 0; i < 256; i++) {
            line[i] = ' ';
        }
    }

    //将结构化的数组，转变为二叉树
    public static Node transfer(Integer[] arr) {
        if (arr[0] == null) {
            throw new RuntimeException("first element can't be null!");
        }
        Node[] nodes = new Node[arr.length];
        for (Integer i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            nodes[i] = new Node(arr[i], i);
            if (i != 0) {
                int parent = i - 1 >>> 1;
                if (nodes[parent] == null) {
                    throw new RuntimeException("can't transfer!");
                }
                nodes[i].parent = nodes[parent];
                if (i % 2 == 0) {
                    nodes[parent].right = nodes[i];
                } else {
                    nodes[parent].left = nodes[i];
                }
            }
        }
        return nodes[0];
    }

    //打印二叉树
    public static void printTree(Node root) {
        if (root == null) {
            throw new RuntimeException("root element can't be null!");
        }
        System.out.println("----------------------------------------------------------------------------------");
        //探测树转变为数组后需要的长度
        int length = length(root, 0);
        //根据长度计算树的高度
        int height = 0;
        while ((length >>> (height + 1)) > 0) {
            height++;
        }
        Node[] nodes = new Node[(1 << height + 1) - 1];
        //遍历，将树放入数组,并返回树中最长的节点，方便计算格式
        int len = insert_tree(root, nodes);

        //为了观看方便，将空节点塞满 '*'
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                nodes[i] = new Node(len);
            }
        }
        //根据数组逐层打印树结构
        int point = 0;
        for (int i = 0; i <= height; i++) {
            char[] value = line.clone();
            //计算每行第一个元素的缩进距离
            int cursor = len * ((1 << height - i) - 1);
            char[] node = nodes[point].value.toCharArray();
            for (char aNode : node) {
                value[cursor++] = aNode;
            }
            point++;
            //遍历元素，将每层剩下的元素加入行中
            while (point < (1 << i + 1) - 1 && point < nodes.length) {
                char[] no = nodes[point].value.toCharArray();
                cursor = cursor + ((1 << height - i + 1) - 1) * len;
                for (char aNo : no) {
                    value[cursor++] = aNo;
                }
                point++;
            }
            System.out.println(String.valueOf(value));
            if (point >= nodes.length) {
                break;
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    //查找树结构需要多大的数组
    private static int length(Node root, int length) {
        if (root == null) {
            return length;
        }
        int left = length(root.left, root.num);
        int right = length(root.right, root.num);
        return left > right ? left : right;
    }

    //将树结构塞入数组
    private static int insert_tree(Node root, Node[] arr) {
        if (root == null) {
            return 0;
        }
        arr[root.num] = root;
        int left = insert_tree(root.left, arr);
        int right = insert_tree(root.right, arr);
        return Math.max(root.value.length(), Math.max(left, right));
    }
}
