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
        Queue corpQ = new Queue();

        // A sink
        Sink consumerSi = new Sink("Sink Consumer");
        Sink corporateSi = new Sink("Sink Corporate");

        // A source

        Source consumerSource = new Source(conQ, corpQ, l,"Consumer Source");
        Source corporateSourc1 = new Source(corpQ, l,"Corporate Source");

        // A machine
        Machine con1 = new Machine(conQ, consumerSi, l, "Consumer 1");
        Machine con2 = new Machine(conQ, consumerSi, l, "Consumer 2");
        Machine con3 = new Machine(conQ, consumerSi, l, "Consumer 3");
        Machine con4 = new Machine(conQ, consumerSi, l, "Consumer 4");
        Machine con5 = new Machine(conQ, consumerSi, l, "Consumer 5");
        Machine corp1 = new Machine(corpQ, corporateSi, l,"Corporate 1");
        Machine corp2 = new Machine(corpQ, corporateSi, l,"Corporate 2");
        Machine corp3 = new Machine(corpQ, corporateSi, l,"Corporate 3");
        Machine corp4 = new Machine(corpQ, corporateSi, l,"Corporate 4");
        Machine corp5 = new Machine(corpQ, corporateSi, l,"Corporate 5");
        Machine corp6 = new Machine(corpQ, corporateSi, l,"Corporate 6");
        Machine corp7 = new Machine(corpQ, corporateSi, l,"Corporate 7");



        // start the eventlist
        l.start(1440); // 2000 is maximum time
        System.out.println(consumerSi.getAverageWaitTime());
        System.out.println(corporateSi.getAverageWaitTime());
    }
}
