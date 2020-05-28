package simulation;

import java.util.ArrayList;

/**
 * Machine in a factory
 *
 * @author Joel Karel
 * @version %I%, %G%
 */
public class Machine implements CProcess, ProductAcceptor {
    /**
     * Eventlist that will manage events
     */
    private final CEventList eventlist;
    /**
     * Machine name
     */
    private final String name;
    /**
     * Product that is being handled
     */
    private Product product;
    /**
     * Queue from which the machine has to take products
     */
    private Queue queue;
    /**
     * Sink to dump products
     */
    private Sink sink;
    /**
     * Status of the machine (b=busy, i=idle)
     */
    private char status;
    /**
     * Mean processing time
     */
    private double meanProcTime;
    /**
     * Processing times (in case pre-specified)
     */
    private ArrayList<Double> processingTimes;
    /**
     * Processing time iterator
     */
    private int procCnt;

    /**
     * Constructor
     * Service times are exponentially distributed with mean 30
     *
     * @param q Queue from which the machine has to take products
     * @param s Where to send the completed products
     * @param e Eventlist that will manage events
     * @param n The name of the machine
     */
    public Machine(Queue q, Sink s, CEventList e, String n) {
        status = 'i';
        queue = q;
        sink = s;
        eventlist = e;
        name = n;
        meanProcTime = 30;
        queue.askProduct(this);
    }

    /**
     * Constructor
     * Service times are exponentially distributed with specified mean
     *
     * @param q Queue from which the machine has to take products
     * @param s Where to send the completed products
     * @param e Eventlist that will manage events
     * @param n The name of the machine
     * @param m Mean processing time
     */
    public Machine(Queue q, Sink s, CEventList e, String n, double m) {
        status = 'i';
        queue = q;
        sink = s;
        eventlist = e;
        name = n;
        meanProcTime = m;
        queue.askProduct(this);
    }

    /**
     * Constructor
     * Service times are pre-specified
     *
     * @param q  Queue from which the machine has to take products
     * @param s  Where to send the completed products
     * @param e  Eventlist that will manage events
     * @param n  The name of the machine
     * @param st service times
     */
    public Machine(Queue q, Sink s, CEventList e, String n, ArrayList<Double> st) {
        status = 'i';
        queue = q;
        sink = s;
        eventlist = e;
        name = n;
        meanProcTime = -1;
        processingTimes = st;
        procCnt = 0;
        queue.askProduct(this);
    }

    public static double drawRandomExponential(double mean) {
        // draw a [0,1] uniform distributed number
        double u = Math.random();
        // Convert it into a exponentially distributed random variate with mean 33
        double res = -mean * Math.log(u);
        return res;
    }

    /**
     * Method to have this object execute an event
     *
     * @param type The type of the event that has to be executed
     * @param tme  The current time
     */
    public void execute(int type, double tme) {
        // show arrival (commented out from original code)
        // System.out.println("Product finished at time = " + tme);

        // Remove product from system
        product.stamp(tme, "Production complete", name);

        sink.giveProduct(product);

        product = null;
        // set machine status to idle
        status = 'i';
        // Ask the queue for products
        queue.askProduct(this);


    }

    /**
     * Let the machine accept a product and let it start handling it
     *
     * @param p The product that is offered
     * @return true if the product is accepted and started, false in all other cases
     */
    @Override
    public boolean giveProduct(Product p) {
        // Only accept something if the machine is idle
        if (status == 'i') {
            // accept the product
            product = p;
            // mark starting time
            product.stamp(eventlist.getTime(), "Production started", name);
            // start production
            startProduction();
            // Flag that the product has arrived
            return true;
        }
        // Flag that the product has been rejected
        else return false;
    }

    /**
     * Starting routine for the production
     * Start the handling of the current product with an exponentionally distributed processingtime with average 30
     * This time is placed in the eventlist
     */
    public void startProduction() {
        // generate duration
        if (meanProcTime > 0) {
            double duration = name.contains("Corporate") ? Normal.getCorporate() : Normal.getConsumer();
            // Create a new event in the eventlist
            double tme = eventlist.getTime();
            eventlist.add(this, 0, tme + duration); //target,type,time
            // set status to busy
            status = 'b';
        } else {
            if (processingTimes.size()> procCnt) {
                eventlist.add(this, 0, eventlist.getTime() + processingTimes.get(procCnt)); //target,type,time
                // set status to busy
                status = 'b';
                procCnt++;
            } else {
                eventlist.stop();
            }
        }
    }
}