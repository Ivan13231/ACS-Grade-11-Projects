public class Moodle {
    public static void main(String[] args) {

        Auto[] autos = {
                new Ferrari("488 Pista", 661, 0, true),
                new Porsche("GT3", 501, 0, false),
                new Porsche("Cayman S", 280, 0, true),
        };
        for (Auto auto : autos) {
            auto.makeLap();
            auto.Sport();
            System.out.println();

        }

    }
}

class Auto{
    String model;
    int power;
    int lap;
    boolean sport;

    public Auto(String model, int power, int lap, boolean sport) {
        this.model = model;
        this.power = power;
        this.lap = lap;
        this.sport = sport;
    }

    public String getModel() {
        return model;
    }
    public int getPower() {
        return power;
    }
    public void makeLap() {

    }
    public void Sport() {

    }
}

class Ferrari extends Auto{
    public Ferrari(String model, int power, int lap,boolean sport) {
        super(model, power, lap, sport);
    }
    @Override
    public void makeLap(){
        double time = (1000-power)*0.009;
        System.out.println("The car Ferrari " + model + " goes from 0-100 kmh in " + time + "seconds");
    }

    @Override
    public void Sport() {
        if(power>=500){
            System.out.println("The car Ferrari " + model + " is sporty");
        }
        else {System.out.println("The car Ferrari " + model + " is not sporty");
        }
    }
}

class Porsche extends Auto{
    public Porsche(String model, int power, int lap,boolean sport) {
        super(model, power, lap, sport);
    }
    @Override
    public void makeLap() {
        double time = (1000-power) * 0.0085;
        System.out.println("The car Porsche " + model + " goes from 0-100 kmh in " + time + " seconds");
    }

    @Override
    public void Sport() {
        if (power >= 300) {
            System.out.println("The car Porsche " + model + " is sporty");
        }
        else {System.out.println("The car Porsche " + model + " is not sporty");
        }
    }

}
