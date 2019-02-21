import entity.Node;
import utils.TreeUtils;

/**
 * Author:liuyu
 * Date:2019-02-21
 */
public class Test {
    public static void main(String[] args) {
        Integer[] integers = new Integer[]{25,23,35,78,74,95,89,34,56,67,34,56,67,78,23,65,23,56};
        Node root = TreeUtils.transfer(integers);
        TreeUtils.printTree(root);
    }
}
