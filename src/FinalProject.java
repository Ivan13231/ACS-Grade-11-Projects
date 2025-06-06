public class FinalProject  {
    public static void main(String[] args) {
        
    }
}

class F1{

}

class Manufacturer extends F1{

}

class Tyre{
    char type;
    int life;
    int grip;

    public Tyre(char type, int life, int grip){
        this.type = type;
        this.life = 15;
        this.grip = 15;

    }

    public char getType(){
        return type;
    }

    public int getLife(){
        if(getType() == 'H')
            return life;

        else
        {if(getType() == 'M')
                    return life-5;
         else if (getType() == 'S')

            return life-10;

            }

    return 0;
    }

}

class RaceTrack{

}
