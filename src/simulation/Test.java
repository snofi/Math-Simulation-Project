package simulation;

public class Test {
    public static void main(String[] args) {
        Normal normie = new Normal();

        double currentTime = 900; // Time in minutes.

        System.out.println("Poisson number of consumer calls at " + (int) currentTime + " minutes: " + Poisson.getConsumer(currentTime));
        System.out.println("Poisson number of corporate calls at " + (int) currentTime + " minutes: " + Poisson.getCorporate(currentTime));

        System.out.println("Normal consumer service time: " + Normal.getConsumer());
        System.out.println("Normal corporate service time: " + Normal.getCorporate());


    }
}
