package functionalinterface;

import java.util.function.Predicate;

public class Main
{
    public static void main(String[] args) {
        functional_interface fi = new functional_interface() {
            @Override
            public int add(int a, int b) {
                return 0;
            }
        };
            functional_interface obj =(a,b)->
            {
                return a+b;
            };
            System.out.println(obj.add(5,10));
        Predicate<Integer> pr=(t)->
        {
            return t%2==0;
        };
        System.out.println(pr.test(10));


    }


    }

