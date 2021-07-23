package utilities;

public class BooleanHelpers {
    public static boolean isValidBooleanString(String booleanString){
        return (booleanString!=null)&&
                (booleanString.equalsIgnoreCase("true")||booleanString.equalsIgnoreCase("false"));
    }
}
