/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package simulation;

import java.util.ArrayList;

public class Simulation {

    public CEventList list;
    public Queue queue;
    public Source source;
    public Sink sink;
    public Machine mach;
	

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Poisson posse = new Poisson();
        Normal normie = new Normal();

    	// Create an eventlist
        CEventList l = new CEventList();
        // A queue for the machine
        Queue conQ = new Queue();
        Queue corpQcon = new Queue();

        // A sink
        Sink consumerSi = new Sink("Sink Consumer");
        Sink corporateSi = new Sink("Sink Corporate");

        // A source
        ArrayList<Double> corpArrival = Poisson.getCorpArrivalList(1,120);




        Source consumerSource = new Source(conQ,l,"Consumer Source", 9);
        Source corporateSource = new Source(conQ,l,"Corporate Source", 78);



        // A machine
        Machine con1 = new Machine(conQ, consumerSi,l, "Consumer 1");
        Machine con2 = new Machine(conQ, consumerSi,l, "Consumer 2");
        Machine corp1 = new Machine(corpQcon,corporateSi,l,"Corporate 1");
        // start the eventlist
        l.start(1440); // 2000 is maximum time
    }
}
