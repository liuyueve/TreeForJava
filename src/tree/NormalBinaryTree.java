package tree;

import entity.Node;
import utils.TreeUtils;

/**
 * Author:liuyu
 * Date:2019-02-22
 * 普通二叉树
 */
public class NormalBinaryTree {

    //是否每添加一个元素就打印一次树结构
    private boolean print;

    private Node root;

    public NormalBinaryTree() {
        this(true);
    }

    public NormalBinaryTree(boolean printEveryTime) {
        this.print = printEveryTime;
    }

    public Node getRoot() {
        return root;
    }

    public Node add(int... arr) {
        if (arr.length <= 0) {
            throw new RuntimeException("at least add one node!");
        }
        int cursor = 0;
        if (root == null) {
            root = new Node(arr[0]);
            cursor++;
        }
        for (int i = cursor; i < arr.length; i++) {
            Node parent = root;
            while (parent != null) {
                if (arr[i] > Integer.valueOf(parent.value)) {
                    if (parent.right != null) {
                        parent = parent.right;
                    } else {
                        parent.right = new Node(arr[i]);
                        parent.right.parent = parent;
                        break;
                    }
                } else if (arr[i] < Integer.valueOf(parent.value)) {
                    if (parent.left != null) {
                        parent = parent.left;
                    } else {
                        parent.left = new Node(arr[i]);
                        parent.left.parent = parent;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (print) {
            TreeUtils.printTree(this.root);
        }
        return root;
    }

    public boolean delete(int v) {
        if (root == null) {
            return false;
        }
        Node t = root;
        Node del = null;
        while (t != null) {
            if (v < Integer.valueOf(t.value)) {
                t = t.left;
            } else if (v > Integer.valueOf(t.value)) {
                t = t.right;
            } else {
                del = t;
                break;
            }
        }
        if (del == null) {
            return false;
        }
        if (del.left != null && del.right != null) {
            Node replace_del = del.right;
            while (replace_del.left != null) {
                replace_del = replace_del.left;
            }
            del.value = replace_del.value;
            del = replace_del;
        }
        Node replace = del.left != null ? del.left : del.right;
        if (replace != null) {
            replace.parent = del.parent;
            if (replace.parent == null) {
                root = replace;
            } else if (del == del.parent.left) {
                del.parent.left = replace;
            } else {
                del.parent.right = replace;
            }
            del.left = null;
            del.right = null;
            del.parent = null;
        } else if (del.parent == null) {
            root = null;
        } else {
            if (del == del.parent.left) {
                del.parent.left = null;
            } else {
                del.parent.right = null;
            }
            del.parent = null;
        }
        if (print) {
            TreeUtils.printTree(this.root);
        }
        return true;
    }

    public boolean isbalance(){
        return root==null || TreeUtils.isbalance(root);
    }


}
