package tree;

import entity.Colour;
import entity.Node;
import utils.TreeUtils;

import static entity.Colour.BLACK;
import static entity.Colour.RED;

/**
 * Create by Intellij IDEA
 *
 * @project: TreeForJava
 * @package: tree
 * @author: liuyu
 * @date: 2019/2/24
 */
public class Red_Black_Tree {

    private boolean print;

    public Node root;

    public Red_Black_Tree() {
        this(true);
    }

    public Red_Black_Tree(boolean printEveryTime) {
        this.print = printEveryTime;
    }

    public Node getRoot() {
        return root;
    }

    public Node add(int... arr){
        if (arr.length <= 0) {
            throw new RuntimeException("at least add one node!");
        }
        for(int i : arr){
            this.add(i);
        }
        if (print) {
            TreeUtils.printTree(root);
        }
        return root;
    }

    private void add(int v) {
        if (root == null) {
            root = new Node(v);
            return;
        }
        Node t = root;
        Node parent;
        int com;
        do {
            parent = t;
            com = v - Integer.valueOf(parent.value);
            if (com < 0) {
                t = t.left;
            } else if (com > 0) {
                t = t.right;
            } else {
                return ;
            }
        } while (t != null);

        Node e = new Node(v);
        if (com < 0) {
            parent.left = e;
            e.parent = parent;
        } else {
            parent.right = e;
            e.parent = parent;
        }
        fixAfterInsert(e);
        if(print){
            TreeUtils.printTree(root);
        }
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
            if(del.colour == BLACK){
                fixAfterDelete(replace);
            }
        } else if (del.parent == null) {
            root = null;
        } else {
            if(del.colour == BLACK){
                fixAfterDelete(del);
            }
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

    private void fixAfterDelete(Node x) {
        while (x != root && colourOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Node sib = rightOf(parentOf(x));

                if (colourOf(sib) == RED) {
                    setColour(sib, BLACK);
                    setColour(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colourOf(leftOf(sib))  == BLACK &&
                        colourOf(rightOf(sib)) == BLACK) {
                    setColour(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colourOf(rightOf(sib)) == BLACK) {
                        setColour(leftOf(sib), BLACK);
                        setColour(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColour(sib, colourOf(parentOf(x)));
                    setColour(parentOf(x), BLACK);
                    setColour(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Node sib = leftOf(parentOf(x));

                if (colourOf(sib) == RED) {
                    setColour(sib, BLACK);
                    setColour(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colourOf(rightOf(sib)) == BLACK &&
                        colourOf(leftOf(sib)) == BLACK) {
                    setColour(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colourOf(leftOf(sib)) == BLACK) {
                        setColour(rightOf(sib), BLACK);
                        setColour(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColour(sib, colourOf(parentOf(x)));
                    setColour(parentOf(x), BLACK);
                    setColour(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColour(x, BLACK);
    }

    private void fixAfterInsert(Node e) {
        e.colour = RED;
        while (e != null && e != root && e.parent.colour == RED) {
            if(parentOf(e)==leftOf(parentOf(parentOf(e)))){
                Node uncle = rightOf(parentOf(parentOf(e)));
                if(colourOf(uncle) == RED){
                    setColour(uncle,BLACK);
                    setColour(parentOf(e),BLACK);
                    setColour(parentOf(parentOf(e)),RED);
                    e = parentOf(parentOf(e));
                }else {
                    if(e == rightOf(parentOf(e))){
                        e = parentOf(e);
                        rotateLeft(e);
                    }
                    setColour(parentOf(e),BLACK);
                    setColour(parentOf(parentOf(e)),RED);
                    rotateRight(parentOf(parentOf(e)));
                }
            }else {
                Node uncle = leftOf(parentOf(parentOf(e)));
                if(colourOf(uncle) == RED){
                    setColour(uncle,BLACK);
                    setColour(parentOf(e),BLACK);
                    setColour(parentOf(parentOf(e)),RED);
                    e = parentOf(parentOf(e));
                }else {
                    if(e == leftOf(parentOf(e))){
                        e = parentOf(e);
                        rotateRight(e);
                    }
                    setColour(parentOf(e),BLACK);
                    setColour(parentOf(parentOf(e)),RED);
                    rotateLeft(parentOf(parentOf(e)));
                }
            }
        }
        root.colour = BLACK;
    }

    private void rotateLeft(Node p) {
        if (p != null) {
            Node r = p.right;
            p.right = r.left;
            if (r.left != null){
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null){
                root = r;
            }
            else if (p.parent.left == p){
                p.parent.left = r;
            }
            else{
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }
    private void rotateRight(Node p){
        if (p != null) {
            Node l = p.left;
            p.left = l.right;
            if (l.right != null){
                l.right.parent = p;
            }
            l.parent = p.parent;
            if (p.parent == null){
                root = l;
            }
            else if (p.parent.right == p){
                p.parent.right = l;
            }
            else{
                p.parent.left = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private Node parentOf(Node node){
        return node.parent;
    }
    private Node rightOf(Node node){
        return node.right;
    }
    private Node leftOf(Node node){
        return node.left;
    }
    private Colour colourOf(Node e){
        if(e == null){
            return BLACK;
        }
        return e.colour;
    }
    private void setColour(Node e,Colour colour){
        if(e !=null){
            e.colour = colour;
        }
    }
}
