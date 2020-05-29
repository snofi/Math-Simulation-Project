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

//      three shifts (6am – 2pm, 2pm – 10pm, 10pm – 6am)
        Shift firstShift = new Shift(6, 14, 5, 5);
        Shift secondShift = new Shift(14, 22, 5, 6);
        Shift thirdShift = new Shift(22, 6, 2, 2);
    }
}
