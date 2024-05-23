import java.util.TreeMap;

public class Test extends Thread
{
    @Override
    public void run() {
        for(int i=0;i<2;i++)
            System.out.println(i+" "+Thread.currentThread());
    }

    public static void main(String[] args) throws InterruptedException {
        /*
        When you launch a java application (run inside JVM),  new thread is created by the JVM and the entire program runs inside the core
        Can create different threads from that thread
        Debugging
        Frames: Function call stack (Stack trace) for recursion
        Thread: Current thread that is running
        Foreground thread: main, t1 (main might not wait for all threads to stop)
        background threads/ Daemon threads: Signal dispatcher, reference handler, finalizer (Will keep running)
        JVM will run only till any of the foreground threads are alive and will stop the background threads in the back
        Background threads will have minimum priority
    main()->t1.start()->t1.start0()->t1.run() doesnt have anything
    main()->t2.start()->t2.start0()->t2.run()
    if two threads run parallel no guarantee which will finish first

         */
        System.out.println("Hello");
        System.out.println(Thread.currentThread());
        Thread t1=new Thread();
        t1.start();
        Mythread t2=new Mythread();
        t2.start();
        Test t3=new Test();
        t3.start();
        for(int i=0;i<2;i++)
            System.out.println(i+" "+Thread.currentThread());

        // t2.join(); blocking call, our code inside main thread will not move ahead unit t2 completely executes can determine which line will execute
        System.out.println(Thread.currentThread());
    }
    private static class Mythread extends Thread
    {
        @Override
        public void run()
        { for(int i=0;i<2;i++)
            System.out.println(i+" "+Thread.currentThread());

        }
    }
}
