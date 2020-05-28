package simulation;

import java.util.Random;

public class Normal {


    // Returns consumer service time
    public static double getConsumer() {
        Random r = new Random();
        double serviceTime = r.nextGaussian() * (35.0 / 60.0) + 1.2;  // multiplied by standard deviation & mean is addded
        if (serviceTime < (25.0 / 60.0)) {
            serviceTime = (25.0 / 60.0);
        }
        return serviceTime;
    }

    // Returns corporate service time
    public static double getCorporate() {
        Random r = new Random();
        double serviceTime = r.nextGaussian() * 1.2 + 3.6;
        if (serviceTime < (45.0 / 60.0)) {
            serviceTime = (45.0 / 60.0);
        }
        return serviceTime;
    }
}


