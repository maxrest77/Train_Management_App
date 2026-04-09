import java.util.LinkedList;
import java.util.List;

/**
 * ==========================================================
 * MAIN CLASS – UseCase4TrainConsistMgmnt
 * ==========================================================
 *
 * Use Case 4: Maintain Ordered Bogie Consist
 *
 * Description:
 * This class models the physical chaining of train bogies
 * using LinkedList for ordered operations.
 *
 * At this stage, the application:
 * - Adds bogies in sequence
 * - Inserts bogies at specific positions
 * - Removes bogies from front and rear
 * - Displays updated train structure
 *
 * This maps positional operations using LinkedList.
 *
 * @author Developer
 * @version 4.0
 */

public class UseCase4TrainConsistMgmnt {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" UC4 - Maintain Ordered Bogie Consist ");
        System.out.println("======================================\n");

        // Create a LinkedList
        // LinkedList maintains insertion order and allows fast inserts
        LinkedList<String> trainConsist = new LinkedList<>();

        // ---- Add bogies in sequence ----
        // addLast() appends a bogie to the end of the train
        // TODO: Add "Engine", "Sleeper", "AC", "Cargo", "Guard"

        System.out.println("Initial Train Consist:");
        System.out.println(trainConsist);

        // ---- Insert at specific position ----
        // add(index, element) inserts a bogie in the middle
        // TODO: Insert "Pantry Car" at position 2

        System.out.println("\nAfter Inserting 'Pantry Car' at position 2:");
        System.out.println(trainConsist);

        // ---- Remove from front and rear ----
        // removeFirst() detaches the locomotive
        // removeLast() detaches the guard coach
        // TODO: Remove first and last bogies

        System.out.println("\nAfter Removing First and Last Bogie:");
        System.out.println(trainConsist);

        System.out.println("\nUC4 ordered consist operations completed...");
    }
}
