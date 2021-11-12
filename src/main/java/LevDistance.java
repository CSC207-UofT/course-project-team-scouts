/**
 * Class to calculate Levenshtein distance, based on algorithm and code described in
 * citation: https://www.baeldung.com/java-levenshtein-distance
 */
public class LevDistance {
    public LevDistance() {}
    static int distance(String a, String b){
        if (a.length() == 0) {
            return b.length();
        }

        if (b.length() == 0) {
            return a.length();
        }

        int substitution = distance(a.substring(1), b.substring(1))
                + substitutionWeight(a.charAt(0), b.charAt(0));
        int insertion = distance(a, b.substring(1)) + 1;
        int deletion = distance(a.substring(1), b) + 1;

        int[] results = {substitution, insertion, deletion};
        return min(results);
    }

    public static int substitutionWeight(char x, char y){
        if (x == y){
            return 0;
        }
        else{
            return 1;
        }
    }

    public static int min(int[] results){
        int smallest = results[0];
        for(int s: results){
            if (s < smallest){
                smallest = s;
            }
        }
        return smallest;
    }
}