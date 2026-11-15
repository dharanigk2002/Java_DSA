/*
Filling Bookcase Shelves

You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. You are also given an integer shelfWidth.

We want to place these books in order onto bookcase shelves that have a total width shelfWidth.

We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.

Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.

For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.

https://leetcode.com/problems/filling-bookcase-shelves/description/
*/

private Integer[][] dp;
private int f(int[][] books, int idx, int width, int max, int shelfWidth) {
    int n=books.length;
    if(dp[idx][width]!=null)
        return dp[idx][width];
    if(idx==n)
        return dp[idx][width]=max;
    int take=Integer.MAX_VALUE/2;
    if(books[idx][0]<=width)
        take=f(books, idx+1, width-books[idx][0], Math.max(max, books[idx][1]), shelfWidth);
    int skip=max+f(books, idx+1, shelfWidth-books[idx][0], books[idx][1], shelfWidth);
    return dp[idx][width]=Math.min(skip, take);
}

public int minHeightShelves(int[][] books, int shelfWidth) {
    int n = books.length;

    int[] dp = new int[n + 1];

    dp[n] = 0;

    for (int i = n - 1; i >= 0; i--) {

        int width = 0;
        int maxHeight = 0;

        dp[i] = Integer.MAX_VALUE;

        for (int j = i; j < n; j++) {

            width += books[j][0];

            if (width > shelfWidth)
                break;

            maxHeight = Math.max(maxHeight, books[j][1]);

            dp[i] = Math.min(dp[i], maxHeight + dp[j + 1]);
        }
    }

    return dp[0];
}

void main() {
    int[][] books = new int[][]{{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
    int shelfWidth = 4;
    IO.println(minHeightShelves(books, shelfWidth));
}