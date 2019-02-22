package tree;


import entity.Node;
import utils.TreeUtils;

import java.util.Arrays;

/**
 * Author:liuyu
 * Date:2019-02-22
 * 最小堆二叉树
 */
public class PriorityTree {

    private boolean print;

    private Integer[] queue = {};

    public PriorityTree() {
        this(true);
    }

    public PriorityTree(boolean printEveryTime) {
        this.print = printEveryTime;
    }

    public void offer(int v) {
        queue = Arrays.copyOf(queue, queue.length + 1);
        int index = queue.length - 1;
        queue[index] = v;
        int parent;
        for (parent = index; parent > 0 && queue[parent - 1 >> 1] > v; parent = parent - 1 >> 1) {
            queue[parent] = queue[parent - 1 >> 1];
        }
        queue[parent] = v;
        if (print) {
            TreeUtils.printTree(TreeUtils.transfer(queue));
        }
    }

    public Integer poll() {
        if (queue.length <= 0) {
            return null;
        }
        int v = queue[0];
        int del = queue[queue.length - 1];
        int size = queue.length;
        int k = 0;
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            int right = child + 1;
            if (right < size && queue[right] < queue[child]) {
                child = right;
            }
            if (queue[child] > del) {
                break;
            }
            queue[k] = queue[child];
            k = child;
        }
        queue[k] = del;
        queue = Arrays.copyOf(queue, queue.length - 1);
        if (print && queue.length >= 1) {
            TreeUtils.printTree(TreeUtils.transfer(queue));
        }
        return v;
    }

    public Node getRoot(){
        if(queue.length<=0){
            return null;
        }
        return TreeUtils.transfer(queue);
    }
}
