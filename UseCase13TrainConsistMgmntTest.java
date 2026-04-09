import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ==========================================================
 * TEST CLASS – UseCase13TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 13: Performance Comparison (Loops vs Streams)
 *
 * Description:
 * This test class verifies that both loop-based and
 * stream-based filtering produce correct and consistent
 * results, and that timing measurement works correctly.
 *
 * @author Developer
 * @version 13.0
 */

public class UseCase13TrainConsistMgmntTest {

    // ---- Helper: Inner Bogie class ----
    static class Bogie {
        String type;
        int capacity;

        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    // ---- Helper: loop-based filter ----
    private List<Bogie> filterByLoop(List<Bogie> bogies, int threshold) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > threshold) {
                result.add(b);
            }
        }
        return result;
    }

    // ---- Helper: stream-based filter ----
    private List<Bogie> filterByStream(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .collect(Collectors.toList());
    }

    // ---- Helper: creates standard test data ----
    private List<Bogie> createTestData() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));
        return bogies;
    }

    // Test 1: Loop filtering should return only bogies with capacity > 60
    @Test
    public void testLoopFilteringLogic() {
        List<Bogie> bogies = createTestData();
        List<Bogie> result = filterByLoop(bogies, 60);

        assertEquals(2, result.size()); // Sleeper(72) and General(90)
        for (Bogie b : result) {
            assertTrue(b.capacity > 60);
        }
    }

    // Test 2: Stream filtering should return only bogies with capacity > 60
    @Test
    public void testStreamFilteringLogic() {
        List<Bogie> bogies = createTestData();
        List<Bogie> result = filterByStream(bogies, 60);

        assertEquals(2, result.size()); // Sleeper(72) and General(90)
        for (Bogie b : result) {
            assertTrue(b.capacity > 60);
        }
    }

    // Test 3: Loop and stream results should match
    @Test
    public void testLoopAndStreamResultsMatch() {
        List<Bogie> bogies = createTestData();
        List<Bogie> loopResult = filterByLoop(bogies, 60);
        List<Bogie> streamResult = filterByStream(bogies, 60);

        assertEquals(loopResult.size(), streamResult.size());
    }

    // Test 4: Execution time measured via nanoTime should be positive
    @Test
    public void testExecutionTimeMeasurement() {
        List<Bogie> bogies = createTestData();

        long start = System.nanoTime();
        filterByLoop(bogies, 60);
        long end = System.nanoTime();

        long elapsed = end - start;
        assertTrue(elapsed > 0);
    }

    // Test 5: Large dataset filtering should complete and return expected results
    @Test
    public void testLargeDatasetProcessing() {
        List<Bogie> bogies = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            bogies.add(new Bogie("Sleeper", i % 100));
        }

        List<Bogie> loopResult = filterByLoop(bogies, 60);
        List<Bogie> streamResult = filterByStream(bogies, 60);

        // Both should return same count
        assertEquals(loopResult.size(), streamResult.size());
        // All results should satisfy the condition
        for (Bogie b : loopResult) {
            assertTrue(b.capacity > 60);
        }
    }
}
