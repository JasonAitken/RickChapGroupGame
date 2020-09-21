package nz.ac.vuw.ecs.swen225.a3.persistence;

//TODO Comment
//TODO Author
public class JsonUtils {
    /**
     * Takes a collection of strings, puts commas in-between them and
     * returns as a single string.
     *
     * @param strings   the strings to separate
     * @param separator what to separate them with
     * @return the very long string.
     */
    public static String separator(String separator, String... strings) {
        StringBuilder stringBuilder = new StringBuilder();

        int length = strings.length;

        for (int i = 0; i < length; i++) {
            stringBuilder.append(strings[i]);
            if (length > i + 1) {
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }
}
