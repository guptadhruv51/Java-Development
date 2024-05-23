import java.util.Comparator;

public class sample implements Comparator <sample> {
    int frequency;
    private String student;
    public sample()
    {

    }

    public sample(int frequency, String name)
    {
        this.frequency = frequency;
        this.student = name;
    }
    public String getName()
    {
        return this.student;
    }

    @Override
    public int compare(sample o1, sample o2)
    {
        if(o1.frequency==o2.frequency)
            return o1.student.compareTo(o2.student);
        return o2.frequency-o1.frequency;

    }
}
