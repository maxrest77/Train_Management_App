import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ==========================================================
 * TEST CLASS – UseCase14TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 14: Handle Invalid Bogie Capacity (Custom Exception)
 *
 * Description:
 * This test class verifies that InvalidCapacityException
 * is thrown correctly when invalid capacity values are used.
 *
 * @author Developer
 * @version 14.0
 */

public class UseCase14TrainConsistMgmntTest {

    // ---- Custom Exception ----
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // ---- PassengerBogie with validation ----
    static class PassengerBogie {
        String type;
        int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type = type;
            this.capacity = capacity;
        }
    }

    // Test 1: Valid capacity should create bogie without exception
    @Test
    public void testException_ValidCapacityCreation() throws InvalidCapacityException {
        PassengerBogie bogie = new PassengerBogie("Sleeper", 72);
        assertNotNull(bogie);
        assertEquals(72, bogie.capacity);
    }

    // Test 2: Negative capacity should throw InvalidCapacityException
    @Test(expected = InvalidCapacityException.class)
    public void testException_NegativeCapacityThrowsException() throws InvalidCapacityException {
        new PassengerBogie("AC Chair", -10);
    }

    // Test 3: Zero capacity should throw InvalidCapacityException
    @Test(expected = InvalidCapacityException.class)
    public void testException_ZeroCapacityThrowsException() throws InvalidCapacityException {
        new PassengerBogie("First Class", 0);
    }

    // Test 4: Exception message should match expected text
    @Test
    public void testException_ExceptionMessageValidation() {
        try {
            new PassengerBogie("Sleeper", -5);
            fail("Expected InvalidCapacityException was not thrown");
        } catch (InvalidCapacityException e) {
            assertEquals("Capacity must be greater than zero", e.getMessage());
        }
    }

    // Test 5: Valid bogie should retain correct type and capacity
    @Test
    public void testException_ObjectIntegrityAfterCreation() throws InvalidCapacityException {
        PassengerBogie bogie = new PassengerBogie("First Class", 24);
        assertEquals("First Class", bogie.type);
        assertEquals(24, bogie.capacity);
    }

    // Test 6: Multiple valid bogies should be created without exceptions
    @Test
    public void testException_MultipleValidBogiesCreation() throws InvalidCapacityException {
        PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
        PassengerBogie b2 = new PassengerBogie("AC Chair", 56);
        PassengerBogie b3 = new PassengerBogie("First Class", 24);

        assertNotNull(b1);
        assertNotNull(b2);
        assertNotNull(b3);
        assertEquals(72, b1.capacity);
        assertEquals(56, b2.capacity);
        assertEquals(24, b3.capacity);
    }
}
