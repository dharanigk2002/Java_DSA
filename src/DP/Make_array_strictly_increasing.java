/*
Make Array Strictly Increasing

Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.

https://leetcode.com/problems/make-array-strictly-increasing/description/
*/

private final Map<String, Integer> dp=new HashMap<>();
private int f(int[] arr1, int[] arr2, int i, int prev) {
    int n = arr1.length;
    String key=i+", "+prev;
    if(dp.containsKey(key))
        return dp.get(key);
    if (i == n) {
        dp.put(key, 0);
        return 0;
    }

    int result = Integer.MAX_VALUE / 2;

    if (arr1[i] > prev) {
        result = f(arr1, arr2, i + 1, arr1[i]);
    }

    // Replace current element
    int j = upper_bound(arr2, prev);

    if (j < arr2.length) {
        result = Math.min(
                result,
                1 + f(arr1, arr2, i + 1, arr2[j])
        );
    }
    dp.put(key, result);
    return result;
}

private int upper_bound(int[] arr, int target) {
    int l = 0, h = arr.length;

    while (l < h) {
        int mid = l + (h - l) / 2;

        if (arr[mid] > target)
            h = mid;
        else
            l = mid + 1;
    }

    return l;
}

public int makeArrayIncreasing(int[] arr1, int[] arr2) {
    Arrays.sort(arr2);

    int count = f(arr1, arr2, 0, Integer.MIN_VALUE);

    return count >= Integer.MAX_VALUE / 2 ? -1 : count;
}

void main() {
    int[] arr1 = {1,5,3,6,7}, arr2 = {4,3,1};
    IO.println(makeArrayIncreasing(arr1, arr2));
}