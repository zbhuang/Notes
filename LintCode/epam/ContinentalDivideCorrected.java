package epam;

/**
 * reference:
 * https://github.com/monkeylyf/interviewjam/blob/master/graph/Continental_Divide.java
 *
 * Continental_Divide.
 *
 * Given a geo map, represented as a two-dimension matrix with each element as the height value,
 *
 * Define both left  and upper borders adjacent to pacific ocean,
 * and    both right and down  boaders adjacent to atlantic ocean.
 * Also define the continental divide as a location, that have flows
 * from itself to both pacific ocean and atlantic ocean.
 * At last, define a flow, think it as water flow, that can only flow from a to b,
 * where a and b are adjacent and and geo[a] is no less than geo[b].
 *
 * Question: Find all qualified continental divides.
 *
 * A good candidate will ask about edge cases for upper-right corner
 * and down-left corner. Define it as you want it be to.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Point {

    public final int x;
    public final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}

public class ContinentalDivideCorrected {

    /**
     * BFS from both of the borders and find the intersection of points
     * that can flow to both of the borders.
     */
    public List<Point> solve(int[][] matrix) {

        int height = matrix.length;
        int width  = matrix[0].length;

        //step 1 - find all points reaching to pacific ocean.
        Queue<Point> pacificBoader = new LinkedList<>();

        //left border
        for(int j=0; j<height; j++) {
            pacificBoader.add(new Point(0, j));
        }

        //upper border
        for(int i=0; i<width; i++) {
            pacificBoader.add(new Point(i, 0));
        }

        boolean[][] toPacific = bfs(matrix, pacificBoader);
        this.printReachablePoints(toPacific);

        //step 2 - find all points reaching to atlantic ocean.
        Queue<Point> atlanticBoader = new LinkedList<>();

        //right border
        for(int j=0; j<height; j++) {
            atlanticBoader.add(new Point(width-1, j));
        }

        //bottom border
        for(int i=0; i<width; i++) {
            atlanticBoader.add(new Point(i, height-1));
        }

        boolean[][] toAtlantic = bfs(matrix, atlanticBoader);
        this.printReachablePoints(toAtlantic);

        //step 3 - find the intersection
        return intersect(toPacific, toAtlantic);
    }

    /*
             (x,y-1)
     (x-1,y) (x,y  ) (x+1,y)
             (x,y+1)
     *
     * A lot of confusion here
     * The general rule is always follow the same pattern -
     *
     * point(x, y) - x first, then y second
     *
     * matrix[y][x] - y axis first, then x axis second.
     *
     */
    private boolean[][] bfs(int[][] matrix, Queue<Point> queue) {

        int height = matrix.length;
        int width  = matrix[0].length;

        boolean[][] visited = new boolean[height][width];

        while (!queue.isEmpty()) {

            Point point = queue.poll();
            visited[point.y][point.x] = true;

            //up
            if(point.y-1>=0 && !visited[point.y-1][point.x] && matrix[point.y][point.x]<=matrix[point.y-1][point.x]) {
                queue.add(new Point(point.x, point.y-1));
            }
            //down
            if(point.y+1<height && !visited[point.y+1][point.x] && matrix[point.y][point.x]<=matrix[point.y+1][point.x]) {
                queue.add(new Point(point.x, point.y+1));
            }
            //left
            if(point.x-1>=0 && !visited[point.y][point.x-1] && matrix[point.y][point.x]<=matrix[point.y][point.x-1]) {
                queue.add(new Point(point.x-1, point.y));
            }
            //right
            if(point.x+1<width && !visited[point.y][point.x+1] && matrix[point.y][point.x]<=matrix[point.y][point.x+1]) {
                queue.add(new Point(point.x+1, point.y));
            }
        }

        return visited;
    }

    private void printReachablePoints(boolean[][] matrix) {
        System.out.println("\n---------");
        int height = matrix.length;
        int width  = matrix[0].length;
        for(int j=0; j<height; j++) {
            for (int i = 0; i < width; i++) {
                if (matrix[j][i]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
    /**
     * Find the set of points that can flow to both pacific and atlantic.
     */
    private List<Point> intersect(boolean[][] matrixA, boolean[][] matrixB) {

        List<Point> result = new ArrayList<>();

        int height = matrixA.length;
        int width  = matrixA[0].length;
        for(int j=0; j<height; j++) {
            for(int i=0; i<width; i++) {
                if(matrixA[j][i] && matrixB[j][i]) {
                    result.add(new Point(i, j));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        ContinentalDivideCorrected solution = new ContinentalDivideCorrected();

        int[][] matrix = new int[][] {
//                {3, 2, 6, 8, 1},
//                {7, 2, 3, 2, 1},
//                {4, 5, 4, 6, 8},
//                {2, 7, 5, 3, 5},
//                {1, 1, 1, 1, 1}
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        System.out.println("\n"+solution.solve(matrix));
    }


}
