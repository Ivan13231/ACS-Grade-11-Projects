 class Animal {
    private String name;
    private String species;
    private int age;
    private Habitat habitat;//Association with habitat


    public Animal(String name, String species, int age, Habitat habitat) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.habitat = habitat;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void displayInfo() {
        System.out.println(name + " (" + species + "), Age: " + age + ", Habitat: " + habitat.getType());
    }
}

 class Habitat {
    private String type;
    private int size;
    private double temperature;

    public Habitat(String type, int size, double temperature) {
        this.type = type;
        this.size = size;
        this.temperature = temperature;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getTemperature() {
        return temperature;
    }
}

 class Staff {
    private String name;
    private String role;
    private Habitat assignedHabitat;

    public Staff(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Habitat getAssignedHabitat() {
        return assignedHabitat;
    }

    public void assignStaff(Habitat habitat) {
        this.assignedHabitat = habitat;
        System.out.println(name + " is assigned to " + habitat.getType());
    }
}

class Zoo {
    private Animal a1, a2, a3, a4;  //Composition: Zoo owns the animals

    public void addAnimal(Animal animal, int slot) {
        switch (slot) {
            case 1: a1 = animal; break;
            case 2: a2 = animal; break;
            case 3: a3 = animal; break;
            case 4: a4 = animal; break;
            default: System.out.println("Invalid slot");
        }
    }

    public void displayAllAnimals() {
         a1.displayInfo();
         a2.displayInfo();
         a3.displayInfo();
         a4.displayInfo();

    }
}

public class ZooHabitatProject {
    public static void main(String[] args) {
        Habitat savannah = new Habitat("Savannah", 5000, 30.0);
        Habitat rainforest = new Habitat("Rainforest", 3000, 25.0);

        Animal lion = new Animal("Leo", "Lion", 5, savannah);
        Animal elephant = new Animal("Dumbo", "Elephant", 8, savannah);
        Animal monkey = new Animal("George", "Monkey", 3, rainforest);
        Animal kangaroo = new Animal("Max", "Kangaroo", 4, savannah);

        Staff zookeeper = new Staff("John", "Zookeeper");
        zookeeper.assignStaff(savannah);
        Staff specialist = new Staff("Emily", "Animal Specialist");
        specialist.assignStaff(rainforest);

        Zoo zoo = new Zoo();
        zoo.addAnimal(lion, 1);
        zoo.addAnimal(elephant, 2);
        zoo.addAnimal(monkey, 3);
        zoo.addAnimal(kangaroo, 4);

        zoo.displayAllAnimals();
    }
}
