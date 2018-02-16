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
