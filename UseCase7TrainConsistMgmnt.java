import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ==========================================================
 * MAIN CLASS – UseCase7TrainConsistMgmnt
 * ==========================================================
 *
 * Use Case 7: Sort Bogies by Capacity (Comparator)
 *
 * Description:
 * This class sorts passenger bogies based on seating
 * capacity using a custom Comparator.
 *
 * At this stage, the application:
 * - Creates bogie objects
 * - Stores them in a list
 * - Displays unsorted data
 * - Sorts using Comparator logic
 * - Displays sorted result
 *
 * This maps custom ordering using Comparator.
 *
 * @author Developer
 * @version 7.0
 */

public class UseCase7TrainConsistMgmnt {

    // Inner Bogie class to model passenger bogies
    static class Bogie {
        String name;
        int capacity;

        // Constructor
        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println(" UC7 - Sort Bogies by Capacity (Comparator) ");
        System.out.println("=============================================\n");

        // Create list of passenger bogies
        List<Bogie> bogies = new ArrayList<>();

        // ---- Add bogie objects ----
        // TODO: Add Bogie("Sleeper", 72), Bogie("AC Chair", 56),
        //       Bogie("First Class", 24), Bogie("General", 90)

        // ---- Display before sorting ----
        System.out.println("Before Sorting:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        // ---- Sort using Comparator ----
        // Comparator.comparingInt() sorts by capacity in ascending order
        // TODO: bogies.sort(Comparator.comparingInt(...));

        // ---- Display after sorting ----
        System.out.println("\nAfter Sorting by Capacity:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        System.out.println("\nUC7 sorting completed...");
    }
}
