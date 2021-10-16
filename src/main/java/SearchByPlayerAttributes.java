import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchByPlayerAttributes{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println(searchPlayer(90, 80, 90)); // just a sample
    }
    public static String searchPlayer(int val1, int val2, int val3) throws IOException {
        //we can add more vals eventually
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader("./dataset(s)/players_20.csv"));
        String l;
        int iteration = 0;
        while ((l = br.readLine()) != null)  // parses until we reach the end of line
        {
            if(iteration == 0) {
                iteration++;
                continue;
            }
            String[] val = l.split(",");
            if(Integer.parseInt(val[column1]) > val1 && Integer.parseInt(val[column2]) > val1 &&
                    Integer.parseInt(val[column3]) > val1 ) {
                resultRow = l;
                break;
            }
        }
        br.close();
        return resultRow;
    }
}