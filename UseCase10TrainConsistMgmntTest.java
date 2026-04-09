import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * TEST CLASS – UseCase10TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 10: Count Total Seats in Train (reduce)
 *
 * Description:
 * This test class verifies that stream reduce()
 * correctly aggregates total seating capacity.
 *
 * @author Developer
 * @version 10.0
 */

public class UseCase10TrainConsistMgmntTest {

    // ---- Helper: Inner Bogie class ----
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // ---- Helper: calculates total seats using reduce ----
    private int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    // Test 1: Total seat calculation should equal the sum of all capacities
    @Test
    public void testReduce_TotalSeatCalculation() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));

        int total = calculateTotalSeats(bogies);

        assertEquals(222, total);
    }

    // Test 2: All bogie capacities should contribute to the total
    @Test
    public void testReduce_MultipleBogiesAggregation() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 100));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("General", 150));

        int total = calculateTotalSeats(bogies);

        assertEquals(300, total);
    }

    // Test 3: Single bogie total should equal its own capacity
    @Test
    public void testReduce_SingleBogieCapacity() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));

        int total = calculateTotalSeats(bogies);

        assertEquals(72, total);
    }

    // Test 4: Empty list should return 0 (identity value of reduce)
    @Test
    public void testReduce_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();

        int total = calculateTotalSeats(bogies);

        assertEquals(0, total);
    }

    // Test 5: map() should correctly extract capacity before reduction
    @Test
    public void testReduce_CorrectCapacityExtraction() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));

        int total = calculateTotalSeats(bogies);

        assertEquals(128, total);
    }

    // Test 6: All bogies must be included in aggregation
    @Test
    public void testReduce_AllBogiesIncluded() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("A", 10));
        bogies.add(new Bogie("B", 20));
        bogies.add(new Bogie("C", 30));
        bogies.add(new Bogie("D", 40));

        int total = calculateTotalSeats(bogies);

        assertEquals(100, total);
    }

    // Test 7: Original list should be unchanged after aggregation
    @Test
    public void testReduce_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        int originalSize = bogies.size();

        calculateTotalSeats(bogies);

        assertEquals(originalSize, bogies.size());
        assertEquals("Sleeper", bogies.get(0).name);
    }
}
