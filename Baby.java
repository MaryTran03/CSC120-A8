import java.util.ArrayList;
import java.util.List;

/**
 * The `Baby` class implements the `Contract` interface and represents a playful,
 * curious baby character. The baby can interact with various items, explore the environment,
 * and has a unique way of handling shrinking, flying, and speed adjustments.
 */
public class Baby implements Contract {

    // List to store items the Baby has grabbed
    private List<String> toys = new ArrayList<>();
    // Baby's level of cuteness
    private int cutenessLevel = 5;
    // Baby's position in the world
    private int posX = 0;
    private int posY = 0;
    // Baby's speed, affecting movement distance
    private int speed = 1;

    /**
     * Grabs a toy and adds it to the toys list.
     *
     * @param item the name of the toy to grab
     */
    @Override
    public void grab(String item) {
        toys.add(item);
        System.out.println("Baby grabbed the " + item + "!");
    }

    /**
     * Drops a toy from the toys list if it exists.
     *
     * @param item the name of the toy to drop
     * @return a message indicating the toy has been dropped, or an error if the toy is not in the toys list
     */
    @Override
    public String drop(String item) {
        if (toys.remove(item)) {
            return "Baby dropped the " + item + ".";
        } else {
            return "Baby doesn't have that toy!";
        }
    }

    /**
     * Examines a toy with curiosity.
     *
     * @param item the name of the toy to examine
     */
    @Override
    public void examine(String item) {
        System.out.println("Baby is looking curiously at the " + item + ".");
    }

    /**
     * Uses a toy from the toys list.
     *
     * @param item the name of the toy to use
     */
    @Override
    public void use(String item) {
        if (toys.contains(item)) {
            System.out.println("Baby is playing happily with the " + item + ".");
        } else {
            System.out.println("Baby doesn't have the " + item + "!");
        }
    }

    /**
     * Crawls in a specified direction based on the baby's speed.
     *
     * @param direction the direction to crawl in (e.g., "north", "south", "east", "west")
     * @return true if the direction is valid and the baby can crawl, false otherwise
     */
    @Override
    public boolean walk(String direction) {
        int distance = speed; // Movement distance is equal to speed

        switch (direction.toLowerCase()) {
            case "north": posY += distance; break;
            case "south": posY -= distance; break;
            case "east": posX += distance; break;
            case "west": posX -= distance; break;
            default:
                System.out.println("Baby doesn't understand that direction.");
                return false;
        }
        System.out.println("Baby crawls " + direction + " for a distance of " + distance + ".");
        return true;
    }

    /**
     * Baby "flies" by being lifted and moved to new coordinates, affected by speed.
     * (In this case, "fly" means the baby is lifted by an adult!)
     *
     * @param x the x-coordinate to be moved to
     * @param y the y-coordinate to be moved to
     * @return true if the baby successfully "flies" to the new location
     */
    @Override
    public boolean fly(int x, int y) {
        posX = x * speed;
        posY = y * speed;
        System.out.println("Baby is lifted and flown to coordinates (" + posX + ", " + posY + ") by a friendly grown-up!");
        return true;
    }

    /**
     * Shrinks the baby’s cuteness level by "scrunching" up into a tiny ball,
     * making it even cuter but only to a minimum level of 3.
     *
     * @return the new cuteness level of the baby
     */
    @Override
    public Number shrink() {
        if (cutenessLevel > 3) {
            cutenessLevel -= 1;
            System.out.println("Baby scrunches up! New cuteness level: " + cutenessLevel);
        } else {
            System.out.println("Baby is already as cute as can be!");
        }
        return cutenessLevel;
    }

    /**
     * Grows the baby’s cuteness level by stretching out and smiling.
     *
     * @return the new cuteness level of the baby
     */
    @Override
    public Number grow() {
        cutenessLevel += 1;
        System.out.println("Baby stretches and smiles! New cuteness level: " + cutenessLevel);
        return cutenessLevel;
    }

    /**
     * Rests, indicating that the baby is taking a nap.
     */
    @Override
    public void rest() {
        System.out.println("Baby is taking a nap... shh!");
    }

    /**
     * Undoes the last action by saying "uh-oh" and dropping the last grabbed item if any.
     */
    @Override
    public void undo() {
        if (!toys.isEmpty()) {
            String lastToy = toys.remove(toys.size() - 1);
            System.out.println("Baby says 'uh-oh' and drops the " + lastToy + "!");
        } else {
            System.out.println("Baby doesn't have anything to undo!");
        }
    }

    /**
     * Gets the current list of toys the baby has grabbed.
     *
     * @return the list of toys in the baby's possession
     */
    public List<String> getToys() {
        return toys;
    }

    /**
     * Gets the current cuteness level of the baby.
     *
     * @return the cuteness level of the baby
     */
    public int getCutenessLevel() {
        return cutenessLevel;
    }

    /**
     * Gets the current position of the baby.
     *
     * @return the coordinates as a string representation
     */
    public String getPosition() {
        return "(" + posX + ", " + posY + ")";
    }

    /**
     * Gets the current speed of the baby.
     *
     * @return the current speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the baby's speed.
     *
     * @param speed the new speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println("Baby's speed is now set to " + speed + ".");
    }

    public static void main(String[] args) {
        // Create a new Baby instance
        Baby baby = new Baby();

        // Display initial attributes
        System.out.println("Initial Position: " + baby.getPosition());
        System.out.println("Initial Cuteness Level: " + baby.getCutenessLevel());
        System.out.println("Initial Speed: " + baby.getSpeed());

        // Baby grabs some toys
        baby.grab("Teddy Bear");
        baby.grab("Toy Car");
        System.out.println("Toys after grabbing: " + baby.getToys());

        // Baby examines and uses a toy
        baby.examine("Teddy Bear");
        baby.use("Toy Car");

        // Baby drops a toy
        System.out.println(baby.drop("Toy Car"));
        System.out.println("Toys after dropping: " + baby.getToys());

        // Set a new speed and test walk method
        baby.setSpeed(2);  // Baby can now move 2 units per step
        baby.walk("north");  // Moves north by 2 units
        baby.walk("east");   // Moves east by 2 units
        System.out.println("Position after walking: " + baby.getPosition());

        // Test flying to a new location
        baby.fly(3, 4);  // Flies to coordinates affected by speed
        System.out.println("Position after flying: " + baby.getPosition());

        // Testing shrinking and growing
        baby.shrink();
        baby.grow();
        System.out.println("Cuteness Level after shrinking and growing: " + baby.getCutenessLevel());

        // Baby takes a rest
        baby.rest();

        // Undo the last action
        baby.undo();

        // Display final state
        System.out.println("Final Position: " + baby.getPosition());
        System.out.println("Final Cuteness Level: " + baby.getCutenessLevel());
        System.out.println("Final Toys: " + baby.getToys());
    }
}
