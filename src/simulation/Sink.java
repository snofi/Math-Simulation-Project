package simulation;

import java.util.ArrayList;

/**
 * A sink
 *
 * @author Joel Karel
 * @version %I%, %G%
 */
public class Sink implements ProductAcceptor {
    /**
     * All products are kept
     */
    private ArrayList<Product> products;
    /**
     * All properties of products are kept
     */
    private ArrayList<Integer> numbers;
    private ArrayList<Double> times;
    private ArrayList<String> events;
    private ArrayList<String> stations;
    /**
     * Counter to number products
     */
    private int number;
    /**
     * Name of the sink
     */
    private String name;

    /**
     * Constructor, creates objects
     */
    public Sink(String n) {
        name = n;
        products = new ArrayList<>();
        numbers = new ArrayList<>();
        times = new ArrayList<>();
        events = new ArrayList<>();
        stations = new ArrayList<>();
        number = 0;
    }

    @Override
    public boolean giveProduct(Product p) {
        number++;
        products.add(p);
        // store stamps
        ArrayList<Double> t = p.getTimes();
        ArrayList<String> e = p.getEvents();
        ArrayList<String> s = p.getStations();
        for (int i = 0; i < t.size(); i++) {
            numbers.add(number);
            times.add(t.get(i));
            events.add(e.get(i));
            stations.add(s.get(i));
        }
        return true;
    }

    public int[] getNumbers() {
        numbers.trimToSize();
        int[] tmp = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            tmp[i] = (numbers.get(i)).intValue();
        }
        return tmp;
    }

    public double[] getTimes() {
        times.trimToSize();
        double[] tmp = new double[times.size()];
        for (int i = 0; i < times.size(); i++) {
            tmp[i] = (times.get(i)).doubleValue();
        }
        return tmp;
    }

    public String[] getEvents() {
        String[] tmp = new String[events.size()];
        tmp = events.toArray(tmp);
        return tmp;
    }

    public String[] getStations() {
        String[] tmp = new String[stations.size()];
        tmp = stations.toArray(tmp);
        return tmp;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<ArrayList<Double>> getProductTimes() {
        ArrayList<ArrayList<Double>> times = new ArrayList<>();

        Product tmp;
        for (int i = 0; i < products.size(); i++) {
            tmp = products.get(i);
            times.add(tmp.getTimes());
        }

        return times;
    }

    public double getAverageWaitTime() {
        ArrayList<Double> waits = getWaitTimes();
        double mean = 0;
        for (Double wait : waits) {
            mean += wait;
        }
        mean = mean / (waits.size());

        return mean;
    }

    public double getWaitPercent(double maxWaitTime) {
//      90% of the consumers should be assisted within 5 minutes 95% within 10 minutes.
//      For corporate clients, 95% should be assisted within 3 minutes; 99% within 7 minutes
        ArrayList<Double> waits = getWaitTimes();

        double count = 0;
        for (Double wait : waits) {
            if (wait > (maxWaitTime)){
                count++;
            }
        }

        return (count/waits.size());
    }

    public ArrayList<Double> getWaitTimes(){
        ArrayList<ArrayList<Double>> times = getProductTimes();
        ArrayList<Double> waits = new ArrayList();

        for (ArrayList<Double> tmpProduct : times) {
            waits.add(tmpProduct.get(1) - tmpProduct.get(0));
        }
        return waits;
    }
}