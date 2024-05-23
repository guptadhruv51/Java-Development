import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        List<Integer> ls=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            ls.add(i);
        }
        System.out.println(findEvenParallel(ls));

    }
    public static Integer findEvenParallel(List<Integer> numbers)
    {
        return numbers.parallelStream()
                .filter(x->{
                    System.out.println("Inside filter: num "+x+", thread= "+Thread.currentThread());
                    return x%2==0;
                }).findFirst().orElse(-1);

    }
    /*
    Computation can happen more than once in parallel streams
    Creating threads manually one by one, but it is better to use thread pool executor for abstraction.
    Nondeterministic number of calls and number of evaluations and result
    findFirst: will give a deterministic result  for number of calls/evaluations: In order to get the first element the number of evaluations will be non-deterministic
    Filter, reduce will most probably happen in the same thread to avoid latency

     */
}