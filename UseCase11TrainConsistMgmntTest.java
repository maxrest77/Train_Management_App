import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ==========================================================
 * TEST CLASS – UseCase11TrainConsistMgmntTest
 * ==========================================================
 *
 * Use Case 11: Validate Train ID and Cargo Codes (Regex)
 *
 * Description:
 * This test class verifies that regex patterns correctly
 * validate Train ID and Cargo Code formats.
 *
 * @author Developer
 * @version 11.0
 */

public class UseCase11TrainConsistMgmntTest {

    // ---- Helper: validates Train ID format TRN-\d{4} ----
    private boolean isValidTrainId(String trainId) {
        Pattern pattern = Pattern.compile("TRN-\\d{4}");
        Matcher matcher = pattern.matcher(trainId);
        return matcher.matches();
    }

    // ---- Helper: validates Cargo Code format PET-[A-Z]{2} ----
    private boolean isValidCargoCode(String cargoCode) {
        Pattern pattern = Pattern.compile("PET-[A-Z]{2}");
        Matcher matcher = pattern.matcher(cargoCode);
        return matcher.matches();
    }

    // Test 1: Valid Train ID should return true
    @Test
    public void testRegex_ValidTrainID() {
        assertTrue(isValidTrainId("TRN-1234"));
    }

    // Test 2: Incorrectly formatted Train IDs should be rejected
    @Test
    public void testRegex_InvalidTrainIDFormat() {
        assertFalse(isValidTrainId("TRAIN12"));
        assertFalse(isValidTrainId("TRN12A"));
        assertFalse(isValidTrainId("1234-TRN"));
    }

    // Test 3: Valid Cargo Code should return true
    @Test
    public void testRegex_ValidCargoCode() {
        assertTrue(isValidCargoCode("PET-AB"));
    }

    // Test 4: Incorrectly formatted Cargo Codes should be rejected
    @Test
    public void testRegex_InvalidCargoCodeFormat() {
        assertFalse(isValidCargoCode("PET-ab"));
        assertFalse(isValidCargoCode("PET123"));
        assertFalse(isValidCargoCode("AB-PET"));
    }

    // Test 5: Train ID must have exactly 4 digits after prefix
    @Test
    public void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(isValidTrainId("TRN-123"));    // only 3 digits
        assertFalse(isValidTrainId("TRN-12345"));  // 5 digits
        assertTrue(isValidTrainId("TRN-0000"));    // exactly 4 digits
    }

    // Test 6: Cargo Code must have only uppercase letters
    @Test
    public void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(isValidCargoCode("PET-ab")); // lowercase
        assertFalse(isValidCargoCode("PET-Ab")); // mixed case
        assertTrue(isValidCargoCode("PET-FH"));  // uppercase only
    }

    // Test 7: Empty strings should return invalid
    @Test
    public void testRegex_EmptyInputHandling() {
        assertFalse(isValidTrainId(""));
        assertFalse(isValidCargoCode(""));
    }

    // Test 8: Partial matches should be rejected (full string must match)
    @Test
    public void testRegex_ExactPatternMatch() {
        assertFalse(isValidTrainId("TRN-1234XYZ")); // extra characters
        assertFalse(isValidCargoCode("PET-ABC"));    // 3 letters instead of 2
    }
}
