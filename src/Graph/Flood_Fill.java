/*
You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

https://leetcode.com/problems/flood-fill/description/
*/

private final int[][] dirs={{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int oldColor=image[sr][sc];
    if(oldColor==color)
        return image;
    dfs(image, sr, sc, oldColor, color);
    return image;
}
private void dfs(int[][] img, int i, int j, int oldColor, int newColor) {
    if(i<0 || j<0 || i>=img.length || j>=img[0].length || img[i][j]!=oldColor)
        return;
    img[i][j]=newColor;
    for(int[] d:dirs)
        dfs(img, i+d[0], j+d[1], oldColor, newColor);
}

void main() {
    int[][] image={{1,1,1},{1,1,0},{1,0,1}};
    IO.println("Before filling:");
    for(int[] img:image)
        IO.println(Arrays.toString(img));
    floodFill(image, 1, 1, 2);
    IO.println("After filling:");
    for(int[] img:image)
        IO.println(Arrays.toString(img));
}