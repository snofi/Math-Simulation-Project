package simulation;

public class Shift {
    public Shift(int startTime, int endTime, int numOfConsumerAgents, int numOfCorporateAgents) {
        System.out.println("Start of shift (" + startTime + ":00 - " + endTime + ":00)");

        // if shift goes into next day, add 24 to am time
        if (startTime > endTime){
            endTime += 24;
        }

        // Convert times to minutes
        startTime = startTime * 60;
        endTime = endTime * 60;

        // Create a MachineGenerator
        MachineGenerator agents = new MachineGenerator();

        // Create an eventlist
        CEventList l = new CEventList(startTime);

        // A queue for the machine
        Queue conQ = new Queue();
        Queue corpQ = new Queue();

        // A sink
        Sink consumerSi = new Sink("Sink Consumer");
        Sink corporateSi = new Sink("Sink Corporate");

        // A source
        Source consumerSource = new Source(conQ, corpQ, l, "Consumer Source");
        Source corporateSource = new Source(corpQ, l, "Corporate Source");

        // A machine
        agents.makeConsumerMachines(numOfConsumerAgents, conQ, consumerSi, l);
        agents.makeCorporateMachines(numOfCorporateAgents, corpQ, corporateSi, l);

        // start the eventlist
        l.start(endTime); // time in hours converted to minutes

        // Convert times to hours
        startTime = startTime / 60;
        endTime = endTime / 60;
        if (endTime > 24){
            endTime -= 24;
        }
        System.out.println("End of shift (" + startTime + ":00 - " + endTime + ":00)");
        System.out.println("Consumer average wait time: " + consumerSi.getAverageWaitTime());
        System.out.println("Corporate average wait time: " + corporateSi.getAverageWaitTime());
        System.out.println("");
    }
}