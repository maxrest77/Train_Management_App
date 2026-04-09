import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ==========================================================
 * TEST CLASS – UseCase9TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 9: Group Bogies by Type (Collectors.groupingBy)
 *
 * Description:
 * This test class verifies that bogies are correctly
 * grouped by type using Collectors.groupingBy().
 *
 * @author Developer
 * @version 9.0
 */

public class UseCase9TrainConsistMgmntTest {

    // ---- Helper: Inner Bogie class ----
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    // ---- Helper: applies groupingBy ----
    private Map<String, List<Bogie>> groupBogies(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));
    }

    // Test 1: Bogies should be grouped correctly by type
    @Test
    public void testGrouping_BogiesGroupedByType() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("Sleeper", 70));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
    }

    // Test 2: Multiple bogies with same type should be in same group
    @Test
    public void testGrouping_MultipleBogiesInSameGroup() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 70));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        assertEquals(2, result.get("Sleeper").size());
    }

    // Test 3: Different bogie types should be in different groups
    @Test
    public void testGrouping_DifferentBogieTypes() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        assertEquals(3, result.size());
    }

    // Test 4: Grouping an empty list should return an empty Map
    @Test
    public void testGrouping_EmptyBogieList() {
        List<Bogie> bogies = new ArrayList<>();
        Map<String, List<Bogie>> result = groupBogies(bogies);
        assertTrue(result.isEmpty());
    }

    // Test 5: Only one bogie type should produce a Map with one key
    @Test
    public void testGrouping_SingleBogieCategory() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 68));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        assertEquals(1, result.size());
    }

    // Test 6: Map should contain expected bogie type keys
    @Test
    public void testGrouping_MapContainsCorrectKeys() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    // Test 7: Group size should match expected number of bogies
    @Test
    public void testGrouping_GroupSizeValidation() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 56));

        Map<String, List<Bogie>> result = groupBogies(bogies);

        assertEquals(2, result.get("Sleeper").size());
        assertEquals(1, result.get("AC Chair").size());
    }

    // Test 8: Original list should remain unchanged after grouping
    @Test
    public void testGrouping_OriginalListUnchanged() {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        int originalSize = bogies.size();

        groupBogies(bogies);

        assertEquals(originalSize, bogies.size());
    }
}
