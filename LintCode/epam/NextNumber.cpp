
/*
Given a number, e.g., 2789, as an array [2,7,8,9], increment it: change it to [2,7,9,0].
*/
#include <iostream>
#include <vector>

using namespace std;

class NextNumber {
private:
	void printNumber(std::vector<int> number) {
		for (std::vector<int>::iterator iter = number.begin(); iter != number.end(); ++iter) {
			int value = *iter;
			std::cout << value << " ";
		}
		std::cout << std::endl;
	}
public:
	void nextNumber(int* number, int size, std::vector<int>& nextNumber) {
		if (number == NULL || size == 0) {
			return;
		}

		int carry = 1;
		for (int i = size - 1; i >= 0; i--) {
			int sum = number[i] + carry;
			number[i] = sum % 10;
			carry = sum / 10;
		}

		if (carry > 0) {
			nextNumber.reserve(size + 1);
			nextNumber.push_back(carry);
			for (int i = 0; i < size; i++) {
				nextNumber.push_back(number[i]);
			}
		} else {
			nextNumber.reserve(size);
			for (int i = 0; i < size; i++) {
				nextNumber.push_back(number[i]);
			}
		}
		printNumber(nextNumber);
	}
};

int main() {
	//int number[4] = { 2, 7, 8, 9 };
	//int number[4] = { 2, 7, 9, 9 };
	int number[4] = { 9, 9, 9, 9 };
	std::vector<int> nextNumber;
	NextNumber solution;
	solution.nextNumber(number, 4, nextNumber);

	return 0;
}