public class Main
{
    /**
     * variable: constant whose value cannot be changed
     *              instance: for an object
     *              static: class level
     * Can be assigned only once: only during definition or inside the constructor
     * static final should be defined either at the time of creation or inside a static block
     * finals cannot be left undefined
     * use cases: want to define something that you don't want to change
     * final functions: cannot be overriden in the subclasses
     * use case: while creating libraries or code bases for SaaS based companies
     * Classes: which cannot be inherited and by default all the functions are final, wrapper classes are final
     * final static int a;
     * static
     * {
     *     a=10;
     * }
     * functions
     * classes
     * @param args
     */
    final static int a;
    static int b;
    static
    {
        b=40;
        a=10;
    }
    static void test()
    {
        b=40;
        // a=10; not allowed
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}