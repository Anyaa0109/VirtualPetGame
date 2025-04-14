import java.util.Scanner;

class Pet {
    String name;
    int health;
    int mood;
    int energy;
    long lastFedTime;
    long lastPlayedTime;

    public Pet(String name) {
        this.name = name;
        this.health = 100;
        this.mood = 100;
        this.energy = 100;
        this.lastFedTime = System.currentTimeMillis();
        this.lastPlayedTime = System.currentTimeMillis();
    }

    public void feed() {
        lastFedTime = System.currentTimeMillis();
        health = Math.min(health + 10, 100);
        mood = Math.min(mood + 5, 100);
        energy = Math.min(energy + 10, 100);
        System.out.println(name + " has been fed. Health: " + health + ", Mood: " + mood + ", Energy: " + energy);
    }

    public void play() {
        if (energy > 0) {
            lastPlayedTime = System.currentTimeMillis();
            mood = Math.min(mood + 15, 100);
            health = Math.max(health - 5, 0);
            energy = Math.max(energy - 20, 0);
            System.out.println(name + " played! Health: " + health + ", Mood: " + mood + ", Energy: " + energy);
        } else {
            System.out.println(name + " is too tired to play!");
        }
    }

    public void rest() {
        energy = Math.min(energy + 20, 100);
        System.out.println(name + " took a rest and regained energy! Energy: " + energy);
    }

    public void checkStatus() {
        reduceMoodIfIgnored();
        reduceHealthIfNotFed();
        System.out.println(name + "'s Health: " + health + ", Mood: " + mood + ", Energy: " + energy);
    }

    public void makeSound() {
        System.out.println(name + " makes a generic sound!");
    }

    // 🟢 Reduce Mood If Not Played For Long
    public void reduceMoodIfIgnored() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastPlayedTime) > 30000) { // If ignored for 30 sec
            mood = Math.max(mood - 10, 0);
            System.out.println("\n[Mood Drop] " + name + " is feeling lonely! Mood: " + mood);
        }
    }

    // 🔴 Reduce Health If Not Fed For Long
    public void reduceHealthIfNotFed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastFedTime) > 45000) { // If not fed for 45 sec
            health = Math.max(health - 10, 0);
            System.out.println("\n[Hunger] " + name + " is starving! Health: " + health);
        }
    }
}

// Dog subclass
class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof Woof!");
    }
}

// Cat subclass
class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}

// Bird subclass
class Bird extends Pet {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Chirp Chirp!");
    }
}

// Main class for user interaction
public class VirtualPetGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter pet name: ");
        String petName = scanner.nextLine();
        System.out.print("Choose pet type (1: Dog, 2: Cat, 3: Bird): ");
        int petType = scanner.nextInt();
        scanner.nextLine();

        Pet pet;
        switch (petType) {
            case 1:
                pet = new Dog(petName);
                break;
            case 2:
                pet = new Cat(petName);
                break;
            case 3:
                pet = new Bird(petName);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Dog.");
                pet = new Dog(petName);
        }

        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Feed pet");
            System.out.println("2. Play with pet");
            System.out.println("3. Let pet rest");
            System.out.println("4. Check pet's status");
            System.out.println("5. Hear pet sound");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    pet.feed();
                    break;
                case 2:
                    pet.play();
                    break;
                case 3:
                    pet.rest();
                    break;
                case 4:
                    pet.checkStatus();
                    break;
                case 5:
                    pet.makeSound();
                    break;
                case 6:
                    gameRunning = false;
                    System.out.println("Thanks for playing! Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
