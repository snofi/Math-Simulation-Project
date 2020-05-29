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
//        System.out.println("End of shift (" + startTime + ":00 - " + endTime + ":00)");
//        System.out.println("Consumer average wait time: " + consumerSi.getAverageWaitTime());
//        System.out.println("Corporate average wait time: " + corporateSi.getAverageWaitTime());

//      90% of the consumers should be assisted within 5 minutes 95% within 10 minutes.
        double conWait5 = consumerSi.getWaitPercent(5);
        double conWait10 = consumerSi.getWaitPercent(10);
        System.out.println("Percentage of consumer wait times > 5 mins = " + conWait5);
        System.out.println("con5 Satisfied = " + (conWait5 < (1 - 0.90)));
        System.out.println("Percentage of consumer await times > 10 mins = " + conWait10);
        System.out.println("con10 Satisfied = " + (conWait10 < (1 - 0.95)));

//      For corporate clients, 95% should be assisted within 3 minutes; 99% within 7 minutes
        double corpWait3 = corporateSi.getWaitPercent(3);
        double corpWait7 = corporateSi.getWaitPercent(7);
        System.out.println("Percentage of corporate wait times > 3 mins = " + corpWait3);
        System.out.println("corp3 Satisfied = " + (corpWait3 < (1 - 0.95)));
        System.out.println("Percentage of corporate wait times > 7 mins = " + corpWait7);
        System.out.println("corp7 Satisfied = " + (corpWait7 < (1- 0.99)));

        System.out.println("");
    }
}