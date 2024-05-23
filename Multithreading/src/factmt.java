import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class factmt extends Thread
{
    private BigInteger result;
    private final int number;
    factmt(int number)
    {
        this.number=number;
        this.result=BigInteger.ONE;
    }

    @Override
    public void run() {
        for(int i=2;i<=number;i++)
        {
            result=result.multiply(BigInteger.valueOf(i));
        }
    }

    public static void main(String[] args) throws InterruptedException {
    List<Integer> numbers= Arrays.asList(1,2,300,4,5,6,7);
    // computation to be done in different threads

    List<factmt> threads=new ArrayList<>();
    for(int i=0;i<numbers.size();i++)
    {
        factmt thread=new factmt(numbers.get(i));
        threads.add(thread);
        thread.start();
        //thread.join(); // will disable the multithreading concept
        //System.out.println(threads.get(i).result);
    }
   for(int i=0;i<numbers.size();i++)
    {
        threads.get(i).join(); // wait for that result to finish first  multi threading programming
        System.out.println(threads.get(i).result);
    }

    }
}
