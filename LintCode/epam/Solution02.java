package epam;

/*
Given a number, e.g., 2789, as an array [2,7,8,9], increment it: change it to [2,7,9,0].
 */
public class Solution02 {

    private static void printNumber(int[] number) {
        if(number == null) {
            return ;
        }

        for (int i=0; i<number.length; i++) {
            System.out.print(number[i] + " ");
        }
        System.out.println();
    }

    /*
    Need to consider the case that 9999
     */
    public static int[] nextNumber(int[] number) {
        if(number == null) {
            return null;
        }

        int carry = 1;
        int size = number.length;
        for(int i=size-1; i>=0; i--) {
            int sum   = number[i] + carry;
            number[i] = sum % 10;
            carry     = sum / 10;
        }

        if(carry > 0) {
            int [] nextNum = new int[size+1];
            nextNum[0] = carry;
            for(int i=0; i<size; i++) {
                nextNum[i+1] = number[i];
            }
            printNumber(nextNum);
            return nextNum;
        }

        printNumber(number);
        return number;
    }

    public static void main(String[] args) {
        int[] number = {2,7,8,9};
//        int[] number = {2,7,9,9};
//        int[] number = {9,9,9,9};

        int[] nextNumber = Solution02.nextNumber(number);

        System.out.println("done!");
    }
}
