/*
Unique Binary Search Trees II

Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order

https://leetcode.com/problems/unique-binary-search-trees-ii/description/
*/


public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}

private List<TreeNode> f(int l, int r) {
    List<TreeNode> list=new ArrayList();
    if(l>r) {
        list.add(null);
        return list;
    }
    if(l==r) {
        list.add(new TreeNode(l));
        return list;
    }
    for(int i=l;i<=r;i++) {
        List<TreeNode> left=f(l, i-1);
        List<TreeNode> right=f(i+1, r);
        for(TreeNode lt:left)
            for(TreeNode rt:right) {
                TreeNode node=new TreeNode(i);
                node.left=lt;
                node.right=rt;
                list.add(node);
            }
    }
    return list;
}

public List<TreeNode> generateTrees(int n) {
    return f(1, n);
}

void main() {
    int n=3;
    IO.println(generateTrees(n));
}