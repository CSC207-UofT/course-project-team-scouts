import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

class SearchForPlayer {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the player you want to search for:");
        String inp = sc.nextLine();
        System.out.println("Searching for " + inp);
        BufferedReader b = null;
        String line = "";
        String cvsSplitBy = ",";
        //System.out.println(new File("players_20.csv").getAbsoluteFile());
        String path = "";
        b = new BufferedReader(new FileReader("players_20.csv"));
        while ((line = b.readLine()) != null) {
            // using comma as separator
            String[] lineArray = line.split(cvsSplitBy);
            //Check the value from array
            if(lineArray[2] == inp) {
                System.out.println(lineArray);
                return;
            }
            break;
        }
    }



}