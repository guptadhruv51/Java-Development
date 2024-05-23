package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


/*
output that we generate depends on various factors:
1. type of data we are using: ordered or unordered
2. type of function we are using: if findfirst and dataset if ordered whether i'm using parallel or sequential the output won't change

3. type of stream: sequential or parallel
parallel: operate on multiple threads,
sequential: single thread
 */
public class Main
{
    public static void main(String[] args)
    {
    List<Integer>  numbers= Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    printeven(numbers);

    }
    public static Integer ans(List<Integer> numbers)
    {
        /*
        Terminal function: Will check for all the values
        Short circuit terminal function: Will terminate as soon as the condition is true
         */
        Optional<Integer> ans=numbers.stream()
                .filter(x->x%2==0)
                .map(x->x*x)
                .filter(x->x>50)
                .findFirst();
        return ans.orElse(-1);

    }
    public static void printeven(List<Integer> numbers)
    {
        /*
        traverses through filer,map and reduce for every suitable element in one go
        Two types of functions in streams
        Terminal Function: Which do not return a stream as result
        Intermediate Functions: which return stream as an output, streams will not execute if no terminal function
        Cannot have two terminal functions
         */
        Stream<Integer> numberstream=numbers.stream();
        Stream<Integer> file=numberstream.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer)
            {
                System.out.println("Inside filter number= "+integer);
                return integer%2==0;
            }
        });
        // applies transformation to filter function
        Stream<Integer>map=file.map(new Function<Integer, Integer>()
        {
            @Override
            public Integer apply(Integer integer)
            {
                System.out.println("Inside apply number= "+integer);
                return integer*integer;
            }
        });
        /*
         *Identity: result remains same even after the operation, used during the first time on the operation
         * Binary Operator:
         */
        Integer ans=map.reduce(0, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2)
            {
                System.out.println("Inside filter numbers "+integer+" "+integer2);
                return integer+integer2;
            }
        });
        System.out.println(ans);

    }
}
