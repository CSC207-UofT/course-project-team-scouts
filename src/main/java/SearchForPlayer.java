import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchForPlayer{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the player you want to search for:"); //gets the query from client
        String inp = sc.nextLine();
        System.out.println(searchPlayer(2, inp));
    }
    public static String searchPlayer(int ColumnIndex, String p) throws IOException {
        String target = null;
        BufferedReader b = new BufferedReader(new FileReader("./dataset(s)/players_20.csv"));
        String l;
        while ((l = b.readLine()) != null)  // loops until we reach the end of line
        {
            String[] val = l.split(",");
            if(val[ColumnIndex].equals(p)) {
                target = l;
                break;
            }
        }
        b.close();
        return target;
    }
}