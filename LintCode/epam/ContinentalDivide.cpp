/*
1.
You are given a 2d rectangular array of positive integers representing the height map of a continent.
The "Pacific ocean" touches the left and top edges of the array and
the "Atlantic ocean" touches the right and bottom edges.

- Find the "continental divide".
That is, the list of grid points where water can flow either to the Pacific or the Atlantic.
Water can only flow from a cell to another one with height equal or lower.

Example:

               i/x-->
 Pacific  ~  ~  ~  ~  ~  |__
       ~  1  2  2  3 (5) ~
 j/y   ~  3  2  3 (4)(4) ~
  |    ~  2  4 (5) 3  1  ~
  v    ~ (6)(7) 1  4  5  ~
     __  (5) 1  1  2  4  ~
       |  ~  ~  ~  ~  ~  Atlantic

The answer would be the list containing the coordinates of all circled cells:
[(4,0), (3,1), (4,1), (2,2), (0,3), (1,3), (0,4)]

*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Point {
	int x;
	int y;
	Point(int x_, int y_) {
		x = x_;
		y = y_;
	}
};

class ContinentalDivide {
private:
	void printReachablePoints(bool* matrix, int height, int width);
	void printContinentalDividePoints(std::vector<Point*> points);
	void bfs(int* matrix, int height, int width, std::queue<Point*> queue, bool* visited);
	void intersect(bool* matrixA, bool* matrixB, int height, int width, std::vector<Point*>& result);
public:
	void solve(int* matrix, int height, int width, std::vector<Point*>& result);
};

/*
 * The general idea: 
 * BFS from both of the borders and find the intersection of points
 * that can flow to both of the borders.
 */
void ContinentalDivide::solve(int* matrix, int height, int width, std::vector<Point*>& result) {
	if (matrix == NULL || height == 0 || width == 0) {
		return;
	}

	std::queue<Point*> pacificBorder;
	//left border
	for (int j = 0; j < height; j++) {
		pacificBorder.push(new Point(0, j));
	}
	//upper border
	for (int i = 0; i < width; i++) {
		pacificBorder.push(new Point(i, 0));
	}
	bool* toPacific = new bool[height*width];
	bfs(matrix, height, width, pacificBorder, toPacific);
	printReachablePoints(toPacific, height, width);

	std::queue<Point*> atlanticBorder;
	//right border
	for (int j = 0; j < height; j++) {
		atlanticBorder.push(new Point(width - 1, j));
	}
	//bottom border
	for (int i = 0; i < width; i++) {
		atlanticBorder.push(new Point(i, height - 1));
	}
	bool* toAtlantic = new bool[height*width];
	bfs(matrix, height, width, atlanticBorder, toAtlantic);
	printReachablePoints(toAtlantic, height, width);

	intersect(toPacific, toAtlantic, height, width, result);
	printContinentalDividePoints(result);

	if (toPacific != NULL) {
		delete[] toPacific;
	}
	if (toAtlantic != NULL) {
		delete[] toAtlantic;
	}
};

void ContinentalDivide::bfs(int* matrix, int height, int width, std::queue<Point*> queue, bool* visited) {
	if (matrix == NULL || height == 0 || width == 0) {
		return;
	}

	for (int j = 0; j < height; j++) {
		for (int i = 0; i < width; i++) {
			visited[j*width + i] = false;
		}
	}

	while (!queue.empty()) {
		Point * point = queue.front();
		queue.pop();
		visited[point->y * width + point->x] = true;

		//up
		if (point->y - 1 >= 0 && !visited[(point->y - 1)*width + point->x]) {
			int curValue = matrix[point->y*width+point->x];
			int  upValue = matrix[(point->y-1)*width + point->x];
			if (curValue <= upValue) {
				queue.push(new Point(point->x, point->y-1));
			}
		}

		//down
		if (point->y + 1 < height && !visited[(point->y + 1)*width + point->x]) {
			int  curValue = matrix[point->y*width + point->x];
			int downValue = matrix[(point->y+1)*width + point->x];
			if (curValue <= downValue) {
				queue.push(new Point(point->x, point->y+1));
			}
		}

		//left
		if (point->x - 1 >= 0 && !visited[point->y*width + (point->x - 1)]) {
			int  curValue = matrix[point->y*width + point->x];
			int leftValue = matrix[point->y*width + point->x-1];
			if (curValue <= leftValue) {
				queue.push(new Point(point->x-1, point->y));
			}
		}

		//right
		if (point->x + 1 < width && !visited[point->y*width + (point->x + 1)]) {
			int   curValue = matrix[point->y*width + point->x];
			int rightValue = matrix[point->y*width + point->x+1];
			if (curValue <= rightValue) {
				queue.push(new Point(point->x+1, point->y));
			}
		}

		delete point;
	}
	return;
};

//for debugging purpose
void ContinentalDivide::printReachablePoints(bool* matrix, int height, int width) {
	if (matrix == NULL || height == 0 || width == 0) {
		return;
	}

	std::cout << std::endl << "---------" << std::endl;
	for (int j = 0; j < height; j++) {
		for (int i = 0; i < width; i++) {
			if (matrix[j * width + i]) {
				std::cout << "1 ";
			}
			else {
				std::cout << "0 ";
			}
		}
		std::cout << std::endl;
	}
}
void ContinentalDivide::intersect(bool* matrixA, bool* matrixB, int height, int width, std::vector<Point*>& result) {
	for (int j = 0; j < height; j++) {
		for (int i = 0; i < width; i++) {
			if (matrixA[j * width + i] && matrixB[j * width + i]) {
				result.push_back(new Point(i, j));
			}
		}
	}
}

//for debugging purpose
void ContinentalDivide::printContinentalDividePoints(std::vector<Point*> points) {
	for (std::vector<Point*>::iterator iter = points.begin(); iter != points.end(); ++iter) {
		Point* point = *iter;
		std::cout << "(" << point->x << ", " << point->y << "),";
	}
	std::cout << std::endl;
}

int main() {

	int matrix[5*5] = {
		1, 2, 2, 3, 5,
		3, 2, 3, 4, 4,
		2, 4, 5, 3, 1,
		6, 7, 1, 4, 5,
		5, 1, 1, 2, 4
	};
	std::vector<Point*> result;
	ContinentalDivide solution;
	solution.solve(matrix, 5, 5, result);
}

/*
running result:

---------
1 1 1 1 1
1 1 1 1 1
1 1 1 0 0
1 1 0 0 0
1 0 0 0 0

---------
0 0 0 0 1
0 0 0 1 1
0 0 1 1 1
1 1 1 1 1
1 1 1 1 1
(4, 0),(3, 1),(4, 1),(2, 2),(0, 3),(1, 3),(0, 4),
*/