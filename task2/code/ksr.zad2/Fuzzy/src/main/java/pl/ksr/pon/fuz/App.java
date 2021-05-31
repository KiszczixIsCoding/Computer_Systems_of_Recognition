package pl.ksr.pon.fuz;

import io.vavr.Tuple2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        TriangularMembershipFunction function = new TriangularMembershipFunction(0.1, 0.9, 1);
        Tuple2<Double, Double> tuple = function.countConstraints(0.5);
//        System.out.println(function.countMembership(0.99));
        GaussianMembershipFunction func = new GaussianMembershipFunction(0.4, 0.1);
        System.out.println(func.countX(0.7));
    }
}
