package simulation;

import java.util.ArrayList;
import java.util.Collections;

public class Poisson {
    public static void main(String[] args) {
//        Example time in minutes (180 is min, 900 is max)
        double tme = 900;
//        calculating lambda based of sinusoid
        double lambda = (1.8* Math.sin(((tme/60)+15)/3.82)+2);
//        rounding lambda to hundred thousandths place
        lambda = Math.round(lambda * 100000);
        lambda = lambda / 100000;
//        generating random from uniform [0,1]
        double u = Math.random();
//        printing out previous values
        System.out.println("u = " + u);
        System.out.println("lambda = " + lambda);
        System.out.println("inverse cdf = " + icdf(lambda, u));
    }

    public static double factorial(double n){
        if (n <= 1) {
            return 1;
        }

        double ans = n;
        while (n > 1){
            n = n - 1;
            ans = ans * n;
        }
        return ans;
    }

    public static ArrayList<Integer> poissonInv(double lambda, double u, int n, ArrayList<Integer> nList){
        double ans = Math.pow(Math.E, (lambda * -1));
        double sum = 0;
        for (int m=0; m<n; m++){
            double quotient = Math.pow(lambda, m) / factorial(m);
            sum = sum + quotient;
        }
        ans = ans * sum;

        if (u <= ans){
            nList.add(n);
            poissonInv(lambda, u, n-1, nList);
        }

        return nList;
    }

    public static int icdf(double lambda, double u){
        ArrayList<Integer> nList = new ArrayList<>();
        int n = (int) Math.ceil(100 * lambda);
        if (n > 171){n = 171;}
        nList = poissonInv(lambda, u, n, nList);
        try {
            n = Collections.min(nList) - 1;
        } catch(Exception e){
            System.out.println(e);
            System.out.println("nList.size() = " + nList.size());
        }
        return (n);
    }

}

