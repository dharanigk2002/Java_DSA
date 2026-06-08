/**
 * ============================================================
 *  AVL TREE — Complete Java Implementation with Intuition
 * ============================================================
 *
 *  WHAT IS AN AVL TREE?
 *  ---------------------
 *  A BST where every node enforces: |height(left) - height(right)| <= 1.
 *  That single invariant guarantees O(log n) for insert, delete, search —
 *  unlike a plain BST that degenerates to O(n) on sorted input.
 *
 *  KEY CONCEPT: Balance Factor (BF)
 *  ----------------------------------
 *    BF(node) = height(left subtree) - height(right subtree)
 *    Valid BF values: -1, 0, +1
 *    If BF becomes +2 or -2 after an insert/delete → rotate to fix.
 *
 *  THE 4 ROTATION CASES
 *  ----------------------
 *    LL: inserted into left-left  → single right rotate
 *    RR: inserted into right-right → single left rotate
 *    LR: inserted into left-right  → left rotate child, then right rotate root
 *    RL: inserted into right-left  → right rotate child, then left rotate root
 *
 *  Memory trick:
 *    If BF > 1 (left-heavy) and BF(left) >= 0  → LL → rotateRight
 *    If BF > 1 (left-heavy) and BF(left) <  0  → LR → rotateLeft(left), rotateRight(root)
 *    If BF < -1 (right-heavy) and BF(right) <= 0 → RR → rotateLeft
 *    If BF < -1 (right-heavy) and BF(right) >  0 → RL → rotateRight(right), rotateLeft(root)
 */
public class AVLTree {

    // ─────────────────────────────────────────────
    //  NODE
    // ─────────────────────────────────────────────

    private static class Node {
        int val;
        Node left, right;
        int height;   // height of this subtree (leaf = 1)

        Node(int val) {
            this.val = val;
            this.height = 1;
        }
    }

    private Node root;

    // ─────────────────────────────────────────────
    //  HELPER UTILITIES
    // ─────────────────────────────────────────────

    /** Height of a node (null-safe). */
    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    /** Recalculate and store height from children. Call after structural changes. */
    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * Balance Factor = height(left) - height(right).
     * > 0 → left-heavy,  < 0 → right-heavy,  0 → balanced.
     */
    private int balanceFactor(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    // ─────────────────────────────────────────────
    //  ROTATIONS
    //  Intuition: a rotation is a local restructure that
    //  (a) preserves BST ordering, (b) reduces height by 1.
    // ─────────────────────────────────────────────

    /**
     * RIGHT ROTATION around y.
     *
     *       y              x
     *      / \    →      /   \
     *     x   C        A     y
     *    / \                / \
     *   A   B              B   C
     *
     *  BST order still holds: A < x < B < y < C
     *  y.height decreases by 1; x.height increases by 0 (net tree shrinks).
     */
    private Node rotateRight(Node y) {
        Node x  = y.left;
        Node T2 = x.right;  // B subtree — will be re-parented under y

        // Perform rotation
        x.right = y;
        y.left  = T2;

        // Update heights bottom-up (y is now a child, update first)
        updateHeight(y);
        updateHeight(x);

        return x;  // x is the new root of this subtree
    }

    /**
     * LEFT ROTATION around x.
     *
     *     x                y
     *    / \     →       /   \
     *   A   y           x     C
     *      / \         / \
     *     B   C       A   B
     *
     *  BST order still holds: A < x < B < y < C
     */
    private Node rotateLeft(Node x) {
        Node y  = x.right;
        Node T2 = y.left;   // B subtree

        y.left  = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;  // y is new root
    }

    // ─────────────────────────────────────────────
    //  BALANCE (the workhorse after every change)
    //  Called on the way back up the recursion stack.
    // ─────────────────────────────────────────────

    /**
     * Inspect balance factor of `n` and apply the right rotation(s).
     * Returns the new root of this subtree.
     */
    private Node balance(Node n) {
        updateHeight(n);
        int bf = balanceFactor(n);

        // ── LEFT-HEAVY (bf == +2) ──
        if (bf > 1) {
            if (balanceFactor(n.left) >= 0) {
                // LL case: straight left chain → single right rotate
                // Example: insert 3,2,1 → 3 has bf=2, left(2) has bf=1 (>=0)
                System.out.println("  [LL] Right-rotate at " + n.val);
                return rotateRight(n);
            } else {
                // LR case: zig-zag left-right → double rotate
                // Example: insert 3,1,2 → 3 has bf=2, left(1) has bf=-1 (<0)
                System.out.println("  [LR] Left-rotate at " + n.left.val + ", then right-rotate at " + n.val);
                n.left = rotateLeft(n.left);
                return rotateRight(n);
            }
        }

        // ── RIGHT-HEAVY (bf == -2) ──
        if (bf < -1) {
            if (balanceFactor(n.right) <= 0) {
                // RR case: straight right chain → single left rotate
                System.out.println("  [RR] Left-rotate at " + n.val);
                return rotateLeft(n);
            } else {
                // RL case: zig-zag right-left → double rotate
                System.out.println("  [RL] Right-rotate at " + n.right.val + ", then left-rotate at " + n.val);
                n.right = rotateRight(n.right);
                return rotateLeft(n);
            }
        }

        // bf is -1, 0, or +1 → already balanced, no change needed
        return n;
    }

    // ─────────────────────────────────────────────
    //  INSERT
    // ─────────────────────────────────────────────

    public void insert(int val) {
        System.out.println("Insert " + val);
        root = insertRec(root, val);
    }

    /**
     * Standard BST insert, then balance on the way back up.
     * Time: O(log n) — descend log n levels, rebalance at most O(1) rotations.
     */
    private Node insertRec(Node n, int val) {
        // 1. Normal BST insert
        if (n == null) return new Node(val);

        if (val < n.val)      n.left  = insertRec(n.left,  val);
        else if (val > n.val) n.right = insertRec(n.right, val);
        else return n;   // duplicate — ignored

        // 2. Fix balance on the way back up
        return balance(n);
    }

    // ─────────────────────────────────────────────
    //  DELETE
    // ─────────────────────────────────────────────

    public void delete(int val) {
        System.out.println("Delete " + val);
        root = deleteRec(root, val);
    }

    /**
     * BST delete (find the in-order successor for 2-child case),
     * then balance on every ancestor back to root.
     * Time: O(log n).
     */
    private Node deleteRec(Node n, int val) {
        if (n == null) return null;

        if (val < n.val) {
            n.left = deleteRec(n.left, val);
        } else if (val > n.val) {
            n.right = deleteRec(n.right, val);
        } else {
            // Found the node to delete — 3 sub-cases:

            if (n.left == null)  return n.right;  // no left child
            if (n.right == null) return n.left;   // no right child

            // 2-child case: replace value with in-order successor (min of right subtree)
            // then delete that successor from the right subtree
            Node successor = minNode(n.right);
            n.val   = successor.val;
            n.right = deleteRec(n.right, successor.val);
        }

        return balance(n);
    }

    /** Returns the leftmost (minimum) node in a subtree. */
    private Node minNode(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    // ─────────────────────────────────────────────
    //  SEARCH
    // ─────────────────────────────────────────────

    public boolean search(int val) {
        return searchRec(root, val);
    }

    /**
     * Standard BST search — guaranteed O(log n) because AVL
     * keeps height at most 1.44 * log2(n+2).
     */
    private boolean searchRec(Node n, int val) {
        if (n == null)        return false;
        if (val == n.val)     return true;
        if (val < n.val)      return searchRec(n.left,  val);
        return                       searchRec(n.right, val);
    }

    // ─────────────────────────────────────────────
    //  TRAVERSALS
    // ─────────────────────────────────────────────

    public void inOrder()   { System.out.print("InOrder: ");   inOrderRec(root);   System.out.println(); }
    public void preOrder()  { System.out.print("PreOrder: ");  preOrderRec(root);  System.out.println(); }
    public void postOrder() { System.out.print("PostOrder: "); postOrderRec(root); System.out.println(); }

    private void inOrderRec(Node n) {
        if (n == null) return;
        inOrderRec(n.left);
        System.out.print(n.val + " ");
        inOrderRec(n.right);
    }

    private void preOrderRec(Node n) {
        if (n == null) return;
        System.out.print(n.val + " ");
        preOrderRec(n.left);
        preOrderRec(n.right);
    }

    private void postOrderRec(Node n) {
        if (n == null) return;
        postOrderRec(n.left);
        postOrderRec(n.right);
        System.out.print(n.val + " ");
    }

    // ─────────────────────────────────────────────
    //  STATS
    // ─────────────────────────────────────────────

    public int height()     { return height(root); }
    public int size()       { return sizeRec(root); }

    private int sizeRec(Node n) {
        if (n == null) return 0;
        return 1 + sizeRec(n.left) + sizeRec(n.right);
    }

    /** Verify the AVL invariant is intact throughout the tree. */
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(Node n) {
        if (n == null) return 0;
        int lh = checkAVL(n.left);
        int rh = checkAVL(n.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    // ─────────────────────────────────────────────
    //  PRETTY PRINT (rotated 90° side tree)
    // ─────────────────────────────────────────────

    public void printTree() {
        System.out.println("AVL Tree (rotated 90°, right subtree on top):");
        printRec(root, "", true);
    }

    private void printRec(Node n, String prefix, boolean isRight) {
        if (n == null) return;
        printRec(n.right, prefix + (isRight ? "    " : "│   "), true);
        System.out.printf("%s%s[%d | h=%d bf=%d]%n",
            prefix, isRight ? "└── " : "┌── ", n.val, n.height, balanceFactor(n));
        printRec(n.left,  prefix + (isRight ? "│   " : "    "), false);
    }

    // ─────────────────────────────────────────────
    //  DEMO MAIN
    // ─────────────────────────────────────────────

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("══════════════════════════════════════════════");
        System.out.println("  Demo 1 — LL rotation (right-right insert)");
        System.out.println("══════════════════════════════════════════════");
        // Inserting 30, 20, 10 in order triggers an LL imbalance at 30.
        // bf(30) becomes +2, bf(20) is +1 (>= 0) → single right rotate.
        tree.insert(30); tree.insert(20); tree.insert(10);
        tree.printTree();
        tree.inOrder();

        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  Demo 2 — LR rotation");
        System.out.println("══════════════════════════════════════════════");
        AVLTree t2 = new AVLTree();
        // Inserting 30, 10, 20 → bf(30)=+2, bf(10)=-1 (<0) → LR double rotate.
        t2.insert(30); t2.insert(10); t2.insert(20);
        t2.printTree();

        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  Demo 3 — Full build + operations");
        System.out.println("══════════════════════════════════════════════");
        AVLTree t3 = new AVLTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25};
        for (int v : values) t3.insert(v);

        t3.printTree();
        t3.inOrder();
        t3.preOrder();

        System.out.println("Height: " + t3.height());
        System.out.println("Size: "   + t3.size());
        System.out.println("Search 40: " + t3.search(40));
        System.out.println("Search 99: " + t3.search(99));
        System.out.println("Valid AVL: " + t3.isValidAVL());

        System.out.println("\nDeleting 20:");
        t3.delete(20);
        t3.printTree();
        System.out.println("Valid AVL after delete: " + t3.isValidAVL());

        System.out.println("\nDeleting root (50):");
        t3.delete(50);
        t3.printTree();
        System.out.println("Valid AVL after delete: " + t3.isValidAVL());
    }
}
