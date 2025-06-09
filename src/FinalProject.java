import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinalProject  {
    public static void main(String[] args) {


        Manufacturer[] manufacturers = new Manufacturer[]{
                new Manufacturer("Mercedes"),
                new Manufacturer("Red Bull"),
                new Manufacturer("McLaren"),
                new Manufacturer("Ferrari"),
                new Manufacturer("Aston Martin"),
                new Manufacturer("Alpine"),
                new Manufacturer("Williams"),
                new Manufacturer("Haas"),
                new Manufacturer("Racing Bulls"),
                new Manufacturer("Stake Sauber"),
        };

        
    }
}

class F1 {
    int speed;
    int aero;
    int weight;
    Tyre tyre;

    public F1(int speed, int aero, int weight, Tyre tyre) {
        this.speed = speed;
        this.aero = aero;
        this.weight = weight;
        this.tyre = tyre;
    }

    public int getSpeed() {
        return speed;
    }
    public int getAero() {
        return aero;
    }
    public int getWeight() {
        return weight;
    }
    public Tyre getTyre() {
        return tyre;
    }
}

class Manufacturer extends F1 {
    String brand;
    public Manufacturer (String brand){
        super(15,15,700, new Tyre('S', 5, 15));
        this.brand = brand;

    }

    public String getBrand() {
        return brand;
    }
}

class Tyre {
    char type;
    int life;
    int grip;

    public Tyre(char type, int life, int grip) {
        this.type = type;
        this.life = 15;
        this.grip = 15;
    }

    public char getType() {
        return type;
    }

    public int getLife() {
        if(getType() == 'H') {
            return life;
        } else {
            if(getType() == 'M') {
                return life - 5;
            } else if (getType() == 'S') {
                return life-10;
            }

        }

        return 0;
    }

    public int getGrip() {
        if(getType() == 'H')
            return grip - 10;

        else {
            if(getType() == 'M') {
                return grip-5;
            } else if (getType() == 'S') {
                return grip;
            }
        }

        return 0;
    }

}

class RaceTrack{
    double lapTime;
    int position;


}
