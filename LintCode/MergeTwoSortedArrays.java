/*
Merge two given sorted integer array A and B into a new sorted integer array.

Example

A=[1,2,3,4]
B=[2,4,5,6]
return [1,2,2,3,4,4,5,6]
 */
public class MergeTwoSortedArrays {
    /*
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int lenA = A.length;
        int lenB = B.length;
        int[] C = new int[lenA+lenB];
        int iA=0, iB=0, iC=0;
        while(iA<lenA && iB<lenB) {
            if(A[iA] <= B[iB]) {
                C[iC++] = A[iA++];
            } else {
                C[iC++] = B[iB++];
            }
        }
        while(iA<lenA) {
            C[iC++] = A[iA++];
        }
        while(iB<lenB) {
            C[iC++] = B[iB++];
        }
        return C;
    }
}
