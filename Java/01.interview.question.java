1. singleton
------------
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonOne {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonOne.class);

    private volatile static SingletonOne instance;

    private SingletonOne() {
        LOGGER.warn("Warning: private SingletonOne constructor is being called...");
    }

    public static SingletonOne getInstance() {
        if (instance == null) {
            synchronized (SingletonOne.class) {
                if (instance == null) // double checked locking
                    instance = new SingletonOne(); // instance change to be volatile, ok now.
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonOne inst1 = SingletonOne.getInstance();
        SingletonOne inst2 = SingletonOne.getInstance();

        if(inst1 == inst2) {
            LOGGER.info("Two SingletonOne instances are the same");
        } else {
            LOGGER.info("Two SingletonOne instances are NOT the same");
        }
    }
}

2. factory
----------
/**
 * Define an interface for creating an object, but let subclasses decide which class to instantiate.
 * Factory Method lets a class defer instantiation to subclasses.
 *
 * It provides a way to delegate the instantiation logic to child classes.
 */

interface IDog {
    void speak();
}
class Poodle implements IDog {
    @Override
    public void speak() {
        System.out.println("Poodle spearks.");
    }
}
class Rottweiler implements IDog {
    @Override
    public void speak() {
        System.out.println("Rottweiler speaks.");
    }
}
public class FactoryOne {
    public static IDog getDog(String criteria)
    {
        if ( criteria.equals("small") )
            return new Poodle();
        else if ( criteria.equals("big") )
            return new Rottweiler();
        return null;
    }

    public static void main(String[] args) {
        IDog dog = FactoryOne.getDog("small");
        dog.speak();
        dog = FactoryOne.getDog("big");
        dog.speak();
        System.out.println("Done.");
    }
}

3. dependency injection
public interface Coder {
    public void writeCode();
}
public class Cpper implements Coder {
    private String name;
    public Cpper(String name){
        this.name=name;
    }
    @Override
    public void writeCode(){
        System.out.println(this.name + " is writing cpp code");
    }
}
public class Javaer implements Coder {
    private String name;
    public Javaer(String name) {
        this.name = name;
    }
    @Override
    public void writeCode() {
        System.out.println(this.name + " is writing java code");
    }
}
public class Task {
    private String name;
    //private Cpper owner;
    private Coder owner;
    public Task(String name){
        this.name  = name;
//      this.worker = new Cpper("zhang3");
    }
    //通过方法 setWorker 注入依赖对象
    public void setOwner(Coder worker) {
        this.owner = worker;
    }
    public void start(){
        System.out.println(this.name+ " started");
        this.owner.writeCode();
    }
}
public class Test {
    public static void main(String[] args) {
        Task t = new Task("Task #1");
        Cpper cpper = new Cpper("Cpper");
        t.setOwner(cpper);
        t.start();
        Javaer javaer = new Javaer("Javaer");
        t.setOwner(javaer);
        t.start();
    }
}

4. binary search
public int binarySearch(int[] array, int target) {
    if(array == null) {
        return -1;
    }
    int start=0;
    int end  = array.length;
    while(end > start) {
        int mid = start + (end-start)/2;
        if(array[mid] == target) {
            return mid;
        } else if(array[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    return -1;
}

5. Implementation of Array in jdk8

6. 
HashMap

2-Sum problem
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void solver01(int[] array, int sum) {
        if(array==null || array.length<2) {
            return;
        }

        Arrays.sort(array);
        int start=0, end=array.length-1;
        while(start < end) {
            if(array[start]+array[end] == sum) {
                System.out.println("["+start+"]+["+end+"]="+sum);
                start++;
                end--;
            } else if(array[start]+array[end] < sum) {
                start++;
            } else {
                end--;
            }
        }
    }
    public static void solver02(int[] array, int sum) {
        if(array==null || array.length<2) {
            return;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<array.length; i++) {
            if(map.containsKey(array[i])) {
                System.out.println("[" + i + "] + [" + map.get(array[i]) + "]");
            } else {
                map.put(sum - array[i], i);
            }
        }
    }

    public static void main(String[] args) {
        //              0 1 2 3 4 5 6 7 8
        int [] array = {1,2,3,4,5,6,7,8,9};
        int target = 10;

//        TwoSum.solver01(array, target);
        TwoSum.solver02(array, target);
    }
}
