import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        sample s1=new sample(5,"Dhruv");
        sample s2=new sample(5,"Yuvraj");
        sample s3=new sample(4,"Dhruv");
        sample s4=new sample(3,"ABC");
        sample []arr={s1,s2,s3,s4};
        sort(arr);
        print(arr);

    }
    public static void print(sample []arr)
    {
        for(sample s:arr)
        {
            System.out.println(s.getName());
        }
    }
    public static void sort(sample []arr)
    {
        Arrays.sort(arr, new Comparator<sample>() {
            @Override
            public int compare(sample o1, sample o2) {
                if(o1.frequency==o2.frequency)
                    return o1.getName().compareTo(o2.getName());
                return o2.frequency-o1.frequency;
            }
        });

    }
}