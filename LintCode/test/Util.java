package test;

/*
Coding Exercise:

Having an array of integers and a number X,
find all pairs of integers in the array which have difference equal to the number X.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Util {

    public static List<Integer> findPairs(int[] array, int x) {
        List<Integer> arrayList = new ArrayList<>();
        if(x < 0 || array == null || array.length == 0) {
            return arrayList;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<array.length; i++) {
            if(map.get(array[i]-x) != null) {
                arrayList.add(array[i]);
                arrayList.add(array[i]-x);
            } else {
                map.put(array[i], 1);
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8};
        int x = 4;
        List<Integer> resultList = Util.findPairs(array, x);

        System.out.println(resultList);
    }
}
