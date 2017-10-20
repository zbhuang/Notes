
/*
Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example

Consider the following matrix:

[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]
Given target = 3, return true.
 */
public class Search2DMatrix {
    /*
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        if(matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int start=0, end=row*col-1;
        while(start <= end) {
            int mid = start+(end-start)/2;
            int r = mid / col;
            int c = mid % col;
            int value = matrix[r][c];
            if(value == target) {
                return true;
            } else if(value < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] nums = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };
        Search2DMatrix solution = new Search2DMatrix();
        boolean result = solution.searchMatrix(nums, 4);
        if(result) {
            System.out.println("Found it.");
        } else {
            System.out.println("Not find it.");
        }
    }
}
