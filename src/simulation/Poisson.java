package simulation;

import java.util.ArrayList;
import java.util.Collections;

public class Poisson {
    private static double factorial(double n) {
        if (n <= 1) {
            return 1;
        }

        double ans = n;
        while (n > 1) {
            n = n - 1;
            ans = ans * n;
        }
        return ans;
    }

    private static ArrayList<Integer> poissonInv(double lambda, double u, int n, ArrayList<Integer> nList) {
        double ans = Math.pow(Math.E, (lambda * -1));
        double sum = 0;
        for (int m = 0; m < n; m++) {
            double quotient = Math.pow(lambda, m) / factorial(m);
            sum = sum + quotient;
        }
        ans = ans * sum;

        if (u <= ans) {
            nList.add(n);
            poissonInv(lambda, u, n - 1, nList);
        }

        return nList;
    }

    private static int icdf(double lambda, double u) {
        ArrayList<Integer> nList = new ArrayList<>();
        int n = (int) Math.ceil(100 * lambda);
        if (n > 171) {
            n = 171;
        }
        nList = poissonInv(lambda, u, n, nList);
        try {
            n = Collections.min(nList) - 1;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("nList.size() = " + nList.size());
        }
        return (n);
    }

    // Time is in minutes (180 is min, 900 is max)
    public static int getConsumer(double currentTime) {
        // calculating lambda based of sinusoid
        double lambda = (1.8 * Math.sin(((currentTime / 60.0) + 15.0) / 3.82) + 2.0);
        // rounding lambda to hundred thousandths place
        lambda = Math.round(lambda * 100000);
        lambda = lambda / 100000;
        // generating random from uniform [0,1]
        double u = Math.random();
        return icdf(lambda, u);
    }

    public static int getCorporate(double currentTime) {
        // Between 8 am and 6 pm, rate is 1 per minute
        // Between 6 pm and 8 am, rate is 0.2 per minute.
        double lambda;
        currentTime = currentTime / 60;
        if ((currentTime > 8) && (currentTime < 18)) {
            lambda = 1;
        } else {
            lambda = 0.2;
        }
        double u = Math.random();
        return icdf(lambda, u);
    }

    public static double meanIATimeCorp(double currentTime) {
        double lambda;
        currentTime = currentTime / 60;
        if ((currentTime > 8) && (currentTime < 18)) {
            lambda = 1;
        } else {
            lambda = 0.2;
        }

        return 60/lambda;
    }
    public static ArrayList<Double> getCorpArrivalList(double currentTime, double endTime) {
        ArrayList<Double> list = new ArrayList<>();
        while(currentTime< endTime ){
            int corpPerMin = getCorporate(currentTime);
//            System.out.println(corpPerMin);
            for(int i=0; i<corpPerMin; i++) {

                double newDuration = Source.drawRandomExponential(corpPerMin);
                list.add(newDuration);
                System.out.println(newDuration);


            }
            if(corpPerMin==0){
                currentTime+=1;
            }
        }
        return list;
    }
}

