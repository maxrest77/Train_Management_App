import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * TEST CLASS – UseCase12TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 12: Safety Compliance Check for Goods Bogies
 *
 * Description:
 * This test class verifies that safety rules are enforced
 * on goods bogies using Stream allMatch().
 *
 * Rule: Cylindrical bogies must carry only Petroleum.
 *
 * @author Developer
 * @version 12.0
 */

public class UseCase12TrainConsistMgmntTest {

    // ---- Helper: Inner GoodsBogie class ----
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    // ---- Helper: applies safety validation using allMatch ----
    private boolean isSafeFormation(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !b.type.equals("Cylindrical")
                        || b.cargo.equals("Petroleum"));
    }

    // Test 1: All cylindrical bogies with Petroleum should return true
    @Test
    public void testSafety_AllBogiesValid() {
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Open", "Coal"));

        assertTrue(isSafeFormation(bogies));
    }

    // Test 2: Cylindrical bogie with non-Petroleum cargo should fail
    @Test
    public void testSafety_CylindricalWithInvalidCargo() {
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Coal"));

        assertFalse(isSafeFormation(bogies));
    }

    // Test 3: Non-cylindrical bogies with any cargo should be allowed
    @Test
    public void testSafety_NonCylindricalBogiesAllowed() {
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Open", "Coal"));
        bogies.add(new GoodsBogie("Box", "Grain"));

        assertTrue(isSafeFormation(bogies));
    }

    // Test 4: Mixed bogies with one violation should return false
    @Test
    public void testSafety_MixedBogiesWithViolation() {
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum")); // safe
        bogies.add(new GoodsBogie("Open", "Coal"));             // safe
        bogies.add(new GoodsBogie("Cylindrical", "Coal"));      // UNSAFE

        assertFalse(isSafeFormation(bogies));
    }

    // Test 5: Empty bogie list should return true (no violations possible)
    @Test
    public void testSafety_EmptyBogieList() {
        List<GoodsBogie> bogies = new ArrayList<>();

        assertTrue(isSafeFormation(bogies));
    }
}
