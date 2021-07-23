package utilities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.BooleanHelpers.isValidBooleanString;

@SpringBootTest(classes = BooleanHelpers.class)
public class BooleanHelpersTests {

    @ParameterizedTest
    @ValueSource(strings = {"True","False","true","false","TRUe","FALse","FalsE","TRue"})
    void isValidBooleanString_ShouldReturnTrue(String input){
        assertTrue(isValidBooleanString(input));
    }
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"Truedd","ssFalse","sstrue","sss","tr",""," "})
    void isValidBooleanString_ShouldReturnFalse(String input){
        assertFalse(isValidBooleanString(input));
    }
}
