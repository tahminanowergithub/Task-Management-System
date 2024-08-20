package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

/**Testing abstract class TMS. TMS should never be instantiated because the class is abstract
* It will yield false if the class is instantiated*/
public class TMSTest {
    /**
    * Method to test the class. Compares the exception thrown with the expected exceptions
    */
    @Test
    public void testTMSInstantiation() {
        try {
            Class.forName("hk.edu.polyu.comp.comp2021.tms.model.TMS").getDeclaredConstructor().newInstance();
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