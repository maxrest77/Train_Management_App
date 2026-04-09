import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ==========================================================
 * TEST CLASS – UseCase8TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 8: Filter Passenger Bogies Using Streams
 *
 * Description:
 * This test class verifies that stream-based filtering
 * correctly selects bogies based on seating capacity.
 *
 * @author Developer
 * @version 8.0
 */

public class UseCase8TrainConsistMgmntTest {

    // ---- Helper: Inner Bogie class (same as UC8) ----
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // ---- Helper: creates a standard test bogie list ----
    private List<Bogie> createTestBogies() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));
        return bogies;
    }

    // ---- Helper: applies stream filter ----
    private List<Bogie> filterByCapacity(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    // Test 1: Bogies with capacity > threshold should be returned
    @Test
    public void testFilter_CapacityGreaterThanThreshold() {
        List<Bogie> bogies = createTestBogies();
        List<Bogie> result = filterByCapacity(bogies, 70);
        // Sleeper(72) and General(90) qualify
        assertEquals(2, result.size());
    }

    // Test 2: Bogies with capacity exactly equal to threshold should NOT be included
    @Test
    public void testFilter_CapacityEqualToThreshold() {
        List<Bogie> bogies = createTestBogies();
        List<Bogie> result = filterByCapacity(bogies, 72);
        // Only General(90) qualifies; Sleeper(72) is excluded (not strictly greater)
        for (Bogie b : result) {
            assertFalse(b.capacity == 72 && result.size() > 1);
        }
        assertEquals(1, result.size());
    }

    // Test 3: Bogies with capacity less than threshold should be excluded
    @Test
    public void testFilter_CapacityLessThanThreshold() {
        List<Bogie> bogies = createTestBogies();
        List<Bogie> result = filterByCapacity(bogies, 70);
        for (Bogie b : result) {
            assertTrue(b.capacity > 70);
        }
    }

    // Test 4: Multiple bogies matching the condition should all be returned
    @Test
    public void testFilter_MultipleBogiesMatching() {
        List<Bogie> bogies = createTestBogies();
        List<Bogie> result = filterByCapacity(bogies, 50);
        // Sleeper(72), AC Chair(56), General(90) qualify
        assertEquals(3, result.size());
    }

    // Test 5: No bogies should be returned when none match
    @Test
    public void testFilter_NoBogiesMatching() {
        List<Bogie> bogies = createTestBogies();
        List<Bogie> result = filterByCapacity(bogies, 100);
        assertTrue(result.isEmpty());
    }

    // Test 6: All bogies should be returned when all match
    @Test
    public void testFilter_AllBogiesMatching() {
        List<Bogie> bogies = createTestBogies();
        List<Bogie> result = filterByCapacity(bogies, 10);
        assertEquals(bogies.size(), result.size());
    }

    // Test 7: Filtering an empty list should return an empty list
    @Test
    public void testFilter_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();
        List<Bogie> result = filterByCapacity(bogies, 60);
        assertTrue(result.isEmpty());
    }

    // Test 8: Original list should remain unchanged after filtering
    @Test
    public void testFilter_OriginalListUnchanged() {
        List<Bogie> bogies = createTestBogies();
        int originalSize = bogies.size();
        filterByCapacity(bogies, 60);
        assertEquals(originalSize, bogies.size());
    }
}
