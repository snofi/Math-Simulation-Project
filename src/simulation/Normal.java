package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Normal {
    private Random r = new Random();

    // Returns consumer service time
    public double getConsumer(){
        double serviceTime = r.nextGaussian() * (35.0/60.0) + 1.2;  // multiplied by standard deviation & mean is addded
        if (serviceTime < (25.0/60.0)){
            serviceTime = (25.0/60.0);
        }
        return serviceTime;
    }

    // Returns corporate service time
    public double getCorporate(){
        double serviceTime = r.nextGaussian() * 1.2 + 3.6;
        if (serviceTime < (45.0/60.0)){
            serviceTime = (45.0/60.0);
        }
        return serviceTime;
    }
}


