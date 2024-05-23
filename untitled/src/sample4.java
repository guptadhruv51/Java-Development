public interface sample4
{
    int add(int a ,int b);
    int subtract(int a,int b);
    default int divide(int a,int b)
    {
        return a/b;
    }
}
