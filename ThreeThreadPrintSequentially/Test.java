import java.util.*;
import java.util.concurrent.atomic.*;

public class Test extends Thread {
    
    AtomicInteger num;
    int rem;
    Object lock;
    
    int max = 20;
    
    public Test( AtomicInteger num, int rem,Object lock, String name){
        super(name)  ;
        this.num = num;
        this.rem = rem;
        this.lock = lock;
    }
    
    public void run(){
        while(num.get() <= max){
            synchronized(lock){
                try{
                    if(num.get()  % 3 != rem){
                        lock.wait();
                    }else{
                        System.out.println(Thread.currentThread().getName() + " " + num);
                        //Thread.sleep(10);
                        num.incrementAndGet();
                        lock.notifyAll();
                    }
                }catch(Exception e){
                    
                }
            }
        }
    }
    
    
    
    
    public static void main(String args[]) {
     AtomicInteger num = new AtomicInteger(1);
      Object lock = new Object();
     
     Thread t1 = new Test(num, 0, lock, "THREE");
     Thread t2 = new Test(num, 1, lock, "ONE");
     Thread t3 = new Test(num, 2, lock, "TWO");
     
     t1.start();
       t2.start();
       t3.start();
     
    }
}
