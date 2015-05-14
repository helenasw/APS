package homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeHeight {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();
        StringTokenizer st;
        ArrayList<Integer> inOrder = new ArrayList<Integer>();
        ArrayList<Integer> postOrder = new ArrayList<Integer>();
        String line = in.readLine();
        Tree tree;

        while (line != null && !line.isEmpty()) {

            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                inOrder.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(in.readLine());
            while (st.hasMoreTokens()) {
                postOrder.add(Integer.parseInt(st.nextToken()));
            }

            tree = new Tree(inOrder, postOrder);
            report.append(tree.root.minLeafVal).append('\n');

            //clean up
            inOrder.clear();
            postOrder.clear();
            line = in.readLine();
        }

        System.out.print(report);
    }
}

class Tree {
    Node root;
    ArrayList<Integer> inOrder;
    ArrayList<Integer> postOrder;

    public Tree(ArrayList<Integer> inOrder, ArrayList<Integer> postOrder) {
        this.inOrder = inOrder;
        this.postOrder = postOrder;
        root = constructTree(0, inOrder.size() - 1, 0, postOrder.size() - 1);
    }

    //let start and end be inclusive
    //return the root of the subtree you just constructed
    private Node constructTree(int inStart, int inEnd, int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        //the root is the last value in the range for the postorder
        Node subRoot = new Node(postOrder.get(postEnd));

        //find the root in the inorder traversal to get the start/end of the left and right sides
        int rootOffset = 0;
        while (inOrder.get(inStart + rootOffset) != subRoot.value) {
            rootOffset ++;
        }

        //construct the subtrees
        subRoot.setLeft(constructTree(inStart, inStart + rootOffset - 1, postStart, postStart + rootOffset - 1));
        subRoot.setRight(constructTree(inStart + rootOffset + 1, inEnd, postStart + rootOffset, postEnd - 1));

        //remember the value of the leaf with the smallest path
        if (subRoot.getLeft() == null && subRoot.getRight() == null) {
            subRoot.minPathSum = subRoot.value;
            subRoot.minLeafVal = subRoot.value;
        }

        return subRoot;
    }
}

class Node {
    int value;
    private Node left;
    private Node right;
    int minLeafVal;
    int minPathSum;

    public Node(int value) {
        this.value = value;
        minPathSum = Integer.MAX_VALUE;
        minLeafVal = Integer.MAX_VALUE;
    }

    public void setLeft(Node left) {
        if (left != null) {
            this.left = left;
            int possibleVal = left.minPathSum + value;
            if (minPathSum > possibleVal) {
                minPathSum = possibleVal;
                minLeafVal = left.minLeafVal;
            } else if (minPathSum == possibleVal) {
                minLeafVal = Math.min(left.minLeafVal, minLeafVal);
            }
        }
    }

    public void setRight(Node right) {
        if (right != null) {
            this.right = right;
            int possibleVal = right.minPathSum + value;
            if (minPathSum > possibleVal) {
                minPathSum = possibleVal;
                minLeafVal = right.minLeafVal;
            } else if (minPathSum == possibleVal) {
                minLeafVal = Math.min(right.minLeafVal, minLeafVal);
            }
        }
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
