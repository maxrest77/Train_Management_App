import java.util.HashMap;
import java.util.Map;

/**
 * ==========================================================
 * MAIN CLASS – UseCase6TrainConsistMgmnt
 * ==========================================================
 *
 * Use Case 6: Map Bogie to Capacity (HashMap)
 *
 * Description:
 * This class associates each bogie with its seating or
 * load capacity using a key-value mapping structure.
 *
 * At this stage, the application:
 * - Creates a HashMap for bogie-capacity mapping
 * - Inserts capacity values for each bogie
 * - Iterates through map entries
 * - Displays bogie and capacity information
 *
 * This maps lookup-based access using HashMap.
 *
 * @author Developer
 * @version 6.0
 */

public class UseCase6TrainConsistMgmnt {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" UC6 - Map Bogie to Capacity (HashMap) ");
        System.out.println("======================================\n");

        // HashMap stores data in key -> value format
        Map<String, Integer> capacityMap = new HashMap<>();

        // ---- Insert bogie capacities ----
        // put() maps bogie name (key) to its capacity (value)
        // TODO: Add "Sleeper" -> 72, "AC Chair" -> 56, "First Class" -> 24, "Cargo" -> 120

        System.out.println("Bogie Capacity Details:");

        // ---- Iterate and display ----
        // entrySet() provides access to all key-value pairs
        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            // TODO: Print each bogie and its capacity in format: "BogieType -> capacity"
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nUC6 bogie-capacity mapping completed...");
    }
}
