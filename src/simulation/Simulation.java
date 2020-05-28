/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package simulation;

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
    	// Create an eventlist
        CEventList l = new CEventList();
        // A queue for the machine
        Queue conQ = new Queue();
        Queue corpQcon = new Queue();

        // A sink
        Sink consumerSi = new Sink("Sink Consumer");
        Sink corporateSi = new Sink("Sink Corporate");

        // A source
        Source consumerSource = new Source(conQ,l,"Consumer Source", 30);
        Source corporateSource = new Source(conQ,l,"Corporate Source", 60);
        corporateSource = new Source(conQ,l,"Corporate Source", 300);


        // A machine
        Machine con1 = new Machine(conQ, consumerSi,l, "Consumer 1");
        Machine con2 = new Machine(conQ, consumerSi,l, "Consumer 2");
        Machine corp1 = new Machine(corpQcon,corporateSi,l,"Corporate 1");
        // start the eventlist
        l.start(1440); // 2000 is maximum time
    }
}
