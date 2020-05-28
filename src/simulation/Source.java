package simulation;

import java.util.ArrayList;

/**
 * A source of products
 * This class implements CProcess so that it can execute events.
 * By continuously creating new events, the source keeps busy.
 *
 * @author Joel Karel
 * @version %I%, %G%
 */
public class Source implements CProcess {
    /**
     * Eventlist that will be requested to construct events
     */
    private CEventList list;
    /**
     * Queue that buffers products for the machine
     */
    private Queue queue;
    private Queue queue2=null;
    /**
     * Name of the source
     */
    private String name;
    /**
     * Mean interarrival time
     */
    private double meanArrTime;
    /**
     * Interarrival times (in case pre-specified)
     */
    private ArrayList<Double> interarrivalTimes;
    /**
     * Interarrival time iterator
     */
    private int interArrCnt;

    /**
     * Constructor, creates objects
     * Interarrival times are exponentially distributed with mean 33
     *
     * @param q The receiver of the products
     * @param l The eventlist that is requested to construct events
     * @param n Name of object
     */
    public Source(Queue q, CEventList l, String n) {
        list = l;
        queue = q;
        name = n;
        // put first event in list for initialization
        meanArrTime = name.contains("Corporate") ?
                drawRandomExponential(Poisson.meanIATimeCorp(list.getTime())) :
                drawRandomExponential(Poisson.meanIATimeCust(list.getTime()));
        list.add(this, 0, meanArrTime); //target,type,time
    }
    public Source(Queue qCon, Queue qCorp, CEventList l, String n) {
        list = l;
        queue = qCon;
        queue2 = qCorp;
        name = n;
        // put first event in list for initialization
        meanArrTime = name.contains("Corporate") ?
                drawRandomExponential(Poisson.meanIATimeCorp(list.getTime())) :
                drawRandomExponential(Poisson.meanIATimeCust(list.getTime()));
        list.add(this, 0, meanArrTime); //target,type,time
    }

    /**
     * Constructor, creates objects
     * Interarrival times are exponentially distributed with specified mean
     *
     * @param q The receiver of the products
     * @param l The eventlist that is requested to construct events
     * @param n Name of object
     * @param m Mean arrival time
     */
    public Source(Queue q, CEventList l, String n, double m) {
        list = l;
        queue = q;
        name = n;
        meanArrTime = m;
        // put first event in list for initialization
        list.add(this, 0, drawRandomExponential(m)); //target,type,time
    }

    /**
     * Constructor, creates objects
     * Interarrival times are prespecified
     *
     * @param q  The receiver of the products
     * @param l  The eventlist that is requested to construct events
     * @param n  Name of object
     * @param ia interarrival times
     */
    public Source(Queue q, CEventList l, String n, ArrayList<Double> ia) {
        list = l;
        queue = q;
        name = n;
        meanArrTime = -1;
        interarrivalTimes = ia;
        interArrCnt = 0;
        // put first event in list for initialization
        list.add(this, 0, interarrivalTimes.get(0)); //target,type,time
    }

    public static double drawRandomExponential(double mean) {
        mean = 60/mean; // since we're using minute already we dont need this line
        // draw a [0,1] uniform distributed number
        double u = Math.random();
        // Convert it into a exponentially distributed random variate with mean 33
        return -mean * Math.log(u);
    }

    @Override
    public void execute(int type, double tme) {
        // show arrival

        // System.out.println("Arrival at time = " + tme);

        // give arrived product to queue
        Product p = new Product();
        p.stamp(tme, "Creation", name);
        if(queue2!=null && queue.getRequests().size()<1 && queue2.getRequests().size()>2){
            queue2.giveProduct(p);
        }
        else{
            queue.giveProduct(p);}

        // generate duration
        if (meanArrTime > 0) {
            double duration = name.contains("Corporate") ?
                    drawRandomExponential(Poisson.meanIATimeCorp(list.getTime())) :
                    drawRandomExponential(Poisson.meanIATimeCust(list.getTime()));
            // Create a new event in the eventlist
            list.add(this, 0, tme + duration); //target,type,time
        } else {
            interArrCnt++;
            if (interarrivalTimes.size() > interArrCnt) {
                list.add(this, 0, tme + interarrivalTimes.get(interArrCnt)); //target,type,time
            } else {
                list.stop();
            }
        }
    }
}