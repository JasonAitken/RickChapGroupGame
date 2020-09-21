package nz.ac.vuw.ecs.swen225.a3.maze.util;

import java.util.Arrays;

/**
 * Generalised utility methods for Arrays.
 * @author mike - Creator
 */
public class ArrayUtil {
    /**
     * Makes and undefined object array into an Integer(not int) array
     *
     * @param array the array to be mutated.
     * @return the Integer array.
     * @author Mike - Creator
     */
    public static Integer[] toIntegerArray(Object[] array) {
        //TODO Pre and post checks!
        //TODO Testing!
        return Arrays.stream(array).map(o -> Integer.parseInt(o.toString())).toArray(Integer[]::new);
    }

    /**
     * Makes an undefined object array into an int(not Integer) array
     *
     * @param array the array to be mutated.
     * @return the int array.
     * @author mike - creator
     */
    public static int[] toIntArray(Object[] array) {
        //TODO Pre and post checks!
        //TODO Testing!

        Integer[] intArray = toIntegerArray(array);
        return Arrays.stream(intArray).mapToInt(i -> i).toArray();
    }


}
