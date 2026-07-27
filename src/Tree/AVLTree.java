package Tree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    public static class Node {
        int data;
        Node left, right;
        int height;
        Node(int d) {
            data=d;
            height=1;
            left=right=null;
        }
    }
    private Node root=null;
    private int height(Node n) {
        return (n==null) ? 0 : n.height;
    }
    private void updateHeight(Node n) {
        n.height = 1+Math.max(height(n.left), height(n.right));
    }
    private int balanceFactor(Node n) {
        return height(n.left)-height(n.right);
    }
    private Node rightRotate(Node y) {
        Node x=y.left;
        Node T2=x.right;
        x.right=y;
        y.left=T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    private Node leftRotate(Node x) {
        Node y=x.right;
        Node T2=y.left;
        y.left=x;
        x.right=T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }
    private Node balance(Node n) {
        updateHeight(n);
        int bf=balanceFactor(n);
        if(bf>1) {
            if (balanceFactor(n.left) >= 0)
                return rightRotate(n);
            else {
                n.left=leftRotate(n.left);
                return rightRotate(n);
            }
        } else if(bf<-1) {
            if (balanceFactor(n.right) <= 0)
                return leftRotate(n);
            else {
                n.right=rightRotate(n.right);
                return leftRotate(n);
            }
        }
        return n;
    }
    public void insert(int val) {
        root=insert(root, val);
    }
    private int findMinimum(Node n) {
        while(n.left!=null)
            n=n.left;
        return n.data;
    }
    private Node insert(Node root, int val) {
        if(root==null)
            return new Node(val);
        if(val<root.data)
            root.left=insert(root.left, val);
        else if(val>root.data)
            root.right=insert(root.right, val);
        else
            return root;
        return balance(root);
    }
    public void delete(int val) {
        root=delete(root, val);
    }
    private Node delete(Node root, int val) {
        if(root==null)
            return null;
        if(val<root.data)
            root.left=delete(root.left, val);
        else if(val>root.data)
            root.right=delete(root.right, val);
        else {
            if(root.left==null)
                return root.right;
            if(root.right==null)
                return root.left;
            root.data=findMinimum(root.right);
            root.right=delete(root.right, root.data);
        }
        return balance(root);
    }
    public void print() {
        List<Integer> res=new ArrayList<>();
        print(root, res);
        IO.println(res);
    }
    private void print(Node root, List<Integer> res) {
        if(root==null)
            return;
        print(root.left, res);
        res.add(root.data);
        print(root.right, res);
    }
    public static void main(String[] args) {
        AVLTree ds=new AVLTree();
        ds.insert(25);
        ds.insert(12);
        ds.insert(30);
        ds.insert(32);
        ds.insert(40);
        ds.insert(50);
        ds.insert(5);
        ds.insert(15);
        ds.print();
    }
}
