
/*
Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

This matrix has the following properties:

Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.

Example
Consider the following matrix:

[
  [1, 3, 5, 7],
  [2, 4, 7, 8],
  [3, 5, 9, 10]
]
Given target = 3, return 2.
 */
public class Search2DMatrixII {
    /*
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }

        if(matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        //from bottom-left to top-right
        int row   = matrix.length;
        int col   = matrix[0].length;
        int x     = row-1;
        int y     = 0;
        int count = 0;
        while(x>=0 && y<col) {
            if(matrix[x][y] < target) {
                y++;
            } else if(matrix[x][y] > target) {
                x--;
            } else {
                count++;
                x--;
                y++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1, 3, 5, 7},
                {2, 4, 7, 8},
                {3, 5, 9, 10}
        };
        Search2DMatrixII solution = new Search2DMatrixII();
        int count = solution.searchMatrix(nums, 3);
        System.out.println("count=" + count);
    }
}
