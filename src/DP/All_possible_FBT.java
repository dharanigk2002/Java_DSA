import com.sun.source.tree.Tree;

/*
All Possible Full Binary Trees

Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

https://leetcode.com/problems/all-possible-full-binary-trees/description/
*/
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int n) {
        this.val=n;
        left=right=null;
    }

    TreeNode(int n, TreeNode left, TreeNode right) {
        this.val=n;
        this.left=left;
        this.right=right;
    }
}

private final Map<Integer, List<TreeNode>> memo = new HashMap<>();

public List<TreeNode> allPossibleFBT(int n) {
    if (n % 2 == 0) return new ArrayList<>();

    if (memo.containsKey(n)) {
        return memo.get(n);
    }

    List<TreeNode> result = new ArrayList<>();

    if (n == 1) {
        result.add(new TreeNode(0));
        return result;
    }

    for (int left = 1; left < n; left += 2) {
        int right = n - 1 - left;

        List<TreeNode> leftTrees = allPossibleFBT(left);
        List<TreeNode> rightTrees = allPossibleFBT(right);

        for (TreeNode l : leftTrees) {
            for (TreeNode r : rightTrees) {
                result.add(new TreeNode(0, l, r));
            }
        }
    }

    memo.put(n, result);
    return result;
}

void main() {
    int n=7;
    IO.println(allPossibleFBT(n));
}