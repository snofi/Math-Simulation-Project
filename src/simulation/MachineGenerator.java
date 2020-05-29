package simulation;

import java.util.ArrayList;

public class MachineGenerator {
    public void makeConsumerMachines(int numOfAgents, Queue conQ, Sink consumerSi, CEventList l) {
        ArrayList<Machine> consumers = new ArrayList<Machine>();
        for (int i = 1; i <= numOfAgents; i++) {
            String name = "Consumer " + i;
            Machine con = new Machine(conQ, consumerSi, l, name);
            consumers.add(con);
        }
    }

    public void makeCorporateMachines(int numOfAgents, Queue corpQ, Sink corporateSi, CEventList l) {
        ArrayList<Machine> corporates = new ArrayList<Machine>();
        for (int i = 0; i < numOfAgents; i++) {
            String name = "Corporate " + i;
            Machine cor = new Machine(corpQ, corporateSi, l, name);
            corporates.add(cor);
        }
    }

}
