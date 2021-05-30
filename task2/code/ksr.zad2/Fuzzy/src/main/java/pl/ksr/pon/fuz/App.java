package pl.ksr.pon.fuz;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        TriangularMembershipFunction function = new TriangularMembershipFunction(0.4, 0.8, 1);
        System.out.println(function.countMembership(0.5));
    }
}
