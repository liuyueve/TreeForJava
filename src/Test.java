import tree.NormalBinaryTree;
import tree.PriorityTree;
import tree.Red_Black_Tree;
import utils.TreeUtils;

import java.util.Random;
import java.util.TreeSet;

/**
 * Author:liuyu
 * Date:2019-02-21
 */

/**
 * template:66,19,90,16,23,73,92,11,17,21,34,69,78,91,96
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        
        NormalBinaryTree normalBinaryTree = new NormalBinaryTree(false);
        for (int i = 0; i < 8; i++) {
            normalBinaryTree.add(random.nextInt(99));
        }
        TreeUtils.printTree(normalBinaryTree.getRoot());

        PriorityTree priorityTree = new PriorityTree(false);
        for (int i = 0; i < 7; i++) {
            priorityTree.offet(random.nextInt(99));
        }
        TreeUtils.printTree(priorityTree.getRoot());

        Red_Black_Tree red_black_tree = new Red_Black_Tree(false);
        for (int i = 0; i < 8; i++) {
            red_black_tree.add(random.nextInt(99));
        }
        TreeUtils.printTree(red_black_tree.getRoot());
    }

}
