public class main3 implements sample4,sample5
{
    @Override
    public int add(int a, int b) {
        return 0;
    }

    @Override
    public int subtract(int a, int b)
    {
        return 0;
    }

    @Override
    public int divide(int a, int b) {
        return sample4.super.divide(a, b);
    }
}
