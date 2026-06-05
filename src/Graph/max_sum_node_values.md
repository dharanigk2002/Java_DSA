# Find the Maximum Sum of Node Values

## Problem Statement

Given a tree with `n` nodes, an integer array `nums` (where `nums[i]` is the value of node `i`), and an integer `k`, you can perform the following operation **any number of times**:

> Choose any edge `(u, v)` in the tree and replace `nums[u]` with `nums[u] XOR k` and `nums[v]` with `nums[v] XOR k`.

Return the **maximum possible sum** of all node values after performing any number of operations.

---

## Key Insight: Tree Structure Doesn't Matter

This is the most surprising part of the problem. You never need to traverse or even look at the edges beyond knowing the tree exists.

Here's why: applying XOR-k on an edge `(u, v)` is equivalent to flipping the XOR status of `u`'s subtree XOR `v`'s subtree. By composing multiple edge operations, **any even-sized subset of nodes can be XOR-ed**. The tree edges only constrain parity — not which specific nodes you choose.

**Conclusion:** The problem reduces to — for each node, decide whether to XOR it or not, subject to the constraint that the total count of XOR-ed nodes must be **even**.

---

## Why the Count Must Be Even

### One Edge Operation

When you apply XOR-k on edge `(u → v)`, every node in **v's entire subtree** gets XOR-ed. This might flip 1 node or 50 nodes — it depends on the subtree size.

### Two Edge Operations

Apply XOR-k on edge `(u → v)` and then on edge `(v → w)`:

- The first operation flips all nodes in v's subtree (which includes w's subtree).
- The second operation flips all nodes in w's subtree again — undoing the first flip for that region.

Net result: only the nodes in v's subtree but **outside** w's subtree are XOR-ed. You've effectively XOR-ed a "ring" of nodes between the two edges.

### The General Rule

No matter how many edge operations you combine, the net count of nodes that end up XOR-ed is always **even**. This is because:

- Each subtree flip either adds or removes nodes symmetrically.
- You can freely pair any two nodes and XOR both of them (by finding a path between them and operating on each edge along the path — alternating flips cancel each other out everywhere except the two endpoints).
- You **cannot** XOR exactly one node in isolation.

---

## Greedy Strategy

Since we can XOR any even-sized subset, the approach is:

1. **Compute gain for each node:** `gain[i] = (nums[i] XOR k) - nums[i]`
   - Positive gain → XOR-ing this node helps.
   - Negative gain → XOR-ing this node hurts.

2. **Greedily take all positive gains** — add them to the base sum.

3. **Check parity:** Count how many nodes we chose to XOR.
   - If **even** → done. This is achievable.
   - If **odd** → this selection is unreachable. We must adjust by ±1 node.

### Parity Fix

When the count is odd, we have two options:

- **Drop** the node with the smallest positive gain (remove one from our "yes" set).
- **Add** the node with the smallest negative gain impact (include one from our "no" set).

Both options reduce the sum by `|min absolute gain|`. We pick whichever costs the least, which is simply: subtract `minAbsGain` from the total, where `minAbsGain = min(|gain[i]|)` across **all** nodes.

This elegantly handles both cases in one pass — we don't need to separately track the smallest positive gain and least harmful negative gain; `minAbsGain` covers both.

---

## Worked Example

Let `nums = [1, 2, 1]`, `k = 3`, tree edges = `[(0,1), (1,2)]`.

### Step 1: Compute gains

| Node | nums[i] | nums[i] XOR 3 | gain |
|------|---------|----------------|------|
| 0    | 1       | 2              | +1   |
| 1    | 2       | 1              | -1   |
| 2    | 1       | 2              | +1   |

### Step 2: Greedy selection

Base sum = 1 + 2 + 1 = **4**

Take all positive gains: nodes 0 and 2 → total gain = +1 + +1 = **+2**

Running sum = 4 + 2 = **6**

Positive gain count = **2** (even ✓)

### Step 3: Parity check

Count is even → no adjustment needed.

**Answer: 6**

---

## Another Example — Odd Count Parity Fix

Let `nums = [1, 2, 3]`, `k = 3`.

| Node | nums[i] | nums[i] XOR 3 | gain |
|------|---------|----------------|------|
| 0    | 1       | 2              | +1   |
| 1    | 2       | 1              | -1   |
| 2    | 3       | 0              | -3   |

Base sum = 1 + 2 + 3 = **6**

Positive gain count = **1** (only node 0) → count is **odd**.

`minAbsGain` = min(|+1|, |-1|, |-3|) = **1**

Adjusted sum = 6 + 1 - 1 = **6**

---

## Java Implementation

```java
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long totalSum = 0;
        int positiveGainCount = 0;
        int minAbsGain = Integer.MAX_VALUE;

        for (int num : nums) {
            int xorVal = num ^ k;
            int gain = xorVal - num;

            // Always accumulate the original value as the base
            totalSum += num;

            // If XOR-ing this node helps, greedily take the gain
            if (gain > 0) {
                totalSum += gain;
                positiveGainCount++;
            }

            // Track the minimum |gain| across ALL nodes (used for parity fix)
            minAbsGain = Math.min(minAbsGain, Math.abs(gain));
        }

        // If we chose an even number of nodes to XOR, we're done.
        // If odd, subtract the cheapest adjustment to make it even.
        if (positiveGainCount % 2 == 0) {
            return totalSum;
        } else {
            return totalSum - minAbsGain;
        }
    }
}
```

### Why `minAbsGain` Covers Both Fix Directions

When count is odd, you can either:

- **Remove** the weakest "yes" node → cost = its gain (a positive value).
- **Include** the least harmful "no" node → cost = |its gain| (absolute value of a negative gain).

Both are candidates for `minAbsGain` because we track `Math.abs(gain)` for every node — positive or negative. The minimum absolute gain across all nodes is automatically the cheapest adjustment regardless of direction.

---

## Complexity Analysis

| Metric | Value |
|--------|-------|
| Time   | O(n)  |
| Space  | O(1)  |

A single pass over `nums` is sufficient. The `edges` array is never used — only its existence (guaranteeing tree connectivity) matters for the parity argument.

---

## Summary

| Step | What we do |
|------|------------|
| 1. Gain calculation | For each node compute `gain = (num XOR k) - num` |
| 2. Greedy selection | Take all positive gains; count how many nodes were selected |
| 3. Parity check | If count is even, return the sum as-is |
| 4. Parity fix | If count is odd, subtract `minAbsGain` to make it achievable |

The tree structure constrains parity (even count of XOR-ed nodes only), but not which nodes you pick — so a simple greedy + parity fix on the flat array is both correct and optimal.
