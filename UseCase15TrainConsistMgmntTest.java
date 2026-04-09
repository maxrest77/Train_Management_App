import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ==========================================================
 * TEST CLASS – UseCase15TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 15: Safe Cargo Assignment Using try-catch-finally
 *
 * Description:
 * This test class verifies that CargoSafetyException is
 * thrown for unsafe cargo combinations and that the
 * finally block always executes.
 *
 * Rule: Rectangular bogies cannot carry Petroleum.
 *
 * @author Developer
 * @version 15.0
 */

public class UseCase15TrainConsistMgmntTest {

    // ---- Custom Runtime Exception ----
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    // ---- GoodsBogie with try-catch-finally in assignCargo ----
    static class GoodsBogie {
        String shape;
        String cargo;
        boolean finallyExecuted = false; // tracks finally execution

        GoodsBogie(String shape) {
            this.shape = shape;
        }

        void assignCargo(String cargo) {
            try {
                if (shape.equals("Rectangular") && cargo.equals("Petroleum")) {
                    throw new CargoSafetyException("Unsafe cargo assignment!");
                }
                this.cargo = cargo;
            } catch (CargoSafetyException e) {
                // cargo not assigned on exception
            } finally {
                finallyExecuted = true;
            }
        }
    }

    // Test 1: Safe cargo assignment should succeed
    @Test
    public void testCargo_SafeAssignment() {
        GoodsBogie bogie = new GoodsBogie("Cylindrical");
        bogie.assignCargo("Petroleum");
        assertEquals("Petroleum", bogie.cargo);
    }

    // Test 2: Unsafe combination should trigger CargoSafetyException internally
    @Test
    public void testCargo_UnsafeAssignmentHandled() {
        GoodsBogie bogie = new GoodsBogie("Rectangular");
        // Should NOT throw to the caller — exception is handled inside
        bogie.assignCargo("Petroleum");
        // cargo should not have been assigned
        assertNull(bogie.cargo);
    }

    // Test 3: Cargo should not be assigned after unsafe combination
    @Test
    public void testCargo_CargoNotAssignedAfterFailure() {
        GoodsBogie bogie = new GoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");
        assertNull(bogie.cargo);
    }

    // Test 4: Program should continue after exception — multiple assignments
    @Test
    public void testCargo_ProgramContinuesAfterException() {
        GoodsBogie b1 = new GoodsBogie("Rectangular");
        GoodsBogie b2 = new GoodsBogie("Cylindrical");
        GoodsBogie b3 = new GoodsBogie("Open");

        b1.assignCargo("Petroleum"); // unsafe — handled
        b2.assignCargo("Petroleum"); // safe
        b3.assignCargo("Coal");      // safe

        assertNull(b1.cargo);
        assertEquals("Petroleum", b2.cargo);
        assertEquals("Coal", b3.cargo);
    }

    // Test 5: finally block should always execute regardless of success or failure
    @Test
    public void testCargo_FinallyBlockExecution() {
        GoodsBogie safBogie = new GoodsBogie("Cylindrical");
        safBogie.assignCargo("Petroleum");
        assertTrue(safBogie.finallyExecuted);

        GoodsBogie unsafeBogie = new GoodsBogie("Rectangular");
        unsafeBogie.assignCargo("Petroleum");
        assertTrue(unsafeBogie.finallyExecuted);
    }
}
