package hk.edu.polyu.comp.comp2021.tms.utilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class to test the criterion. Compares the exception thrown with the expected exceptions
 */
public class CriterionTest {
    /**
     * Method to test the class. Compares the exception thrown with the expected exceptions
     */
    @Test
    public void testCriterionInstantiation() {
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.utilities.Criterion").getDeclaredConstructor().newInstance();
            fail("Should have thrown an exception because AbstractClassName is abstract!");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            fail("Unexpected exception: " + e.getMessage());
        } catch (InstantiationException e) {
            // This exception is expected, so the test passes when it's thrown
        } catch (InvocationTargetException e){
            fail("Unexpected target exception: " + e.getTargetException().getMessage());
        }
    }
}
