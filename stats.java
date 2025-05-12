import java.text.DecimalFormat;
import java.util.Scanner;
public class stats {
    
    public static void main(String[] args)
    {
        double n = 0;
        double sumx = 0;
        double sumy = 0;
        double sumxsquare = 0;
        double sumysquare = 0;
        double sumxy = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("What is n?");
        n = input.nextInt();
        System.out.println("What is sum of all x");
        sumx = input.nextDouble();
        System.out.println("What is sum of all x squared");
        sumxsquare = input.nextDouble();
        System.out.println("What is sum of all y");
        sumy = input.nextDouble();
        System.out.println("What is sum of all y squared");
        sumysquare = input.nextDouble();
        System.out.println("What is the sum of xy");
        sumxy = input.nextDouble();
        
        input.close();
        double ssxx = sumxsquare - ((sumx * sumx)/n);

        double ssyy = sumysquare - ((sumy * sumy)/n);

        double ssxy = sumxy - ((sumx*sumy)/n);

        double b = ssxy/ssxx;

        double sse = ssyy - (b * ssxy);

        double ssquare = sse/(n-2);
        double s = Math.sqrt(ssquare);

        double r = ssxy/(Math.sqrt(ssxx * ssyy));

        double bigRsquared = (ssyy - sse)/ssyy;

        DecimalFormat f = new DecimalFormat("##.000");

        System.out.println("\n");
        System.out.println("SSxx = " + f.format(ssxx));
        System.out.println("\n");
        System.out.println("SSyy = " + f.format(ssyy));
        System.out.println("\n");
        System.out.println("SSxy = " + f.format(ssxy));
        System.out.println("\n");
        System.out.println("B1 = " + f.format(b));
        System.out.println("\n");
        System.out.println("SSE = " + f.format(sse));
        System.out.println("\n");
        System.out.println("s = " + f.format(s));
        System.out.println("\n");
        System.out.println("r = " + f.format(r));
        System.out.println("\n");
        System.out.println("R^2 = " + f.format(bigRsquared));

    }


}
