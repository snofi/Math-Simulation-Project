package simulation;

public class Test {
    public static void main(String[] args) {
        Poisson posse = new Poisson();
        Normal normie = new Normal();

        double currentTime = 900; // Time in minutes.

        System.out.println("Poisson number of consumer calls at " + (int) currentTime + " minutes: " + posse.getConsumer(currentTime));
        System.out.println("Poisson number of corporate calls at " + (int) currentTime + " minutes: " + posse.getCorporate(currentTime));

        System.out.println("Normal consumer service time: " + normie.getConsumer());
        System.out.println("Normal corporate service time: " + normie.getCorporate());


    }
}
