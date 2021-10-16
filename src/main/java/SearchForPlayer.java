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
    public static String searchPlayer(int ColumnIndex, String player) throws IOException {
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader("./dataset(s)/players_20.csv"));
        String l;
        while ((l = br.readLine()) != null)  // parses until we reach the end of line
        {
            String[] val = l.split(",");
            if(val[ColumnIndex].equals(player)) {
                resultRow = l;
                break;
            }
        }
        br.close();
        return resultRow;
    }
}