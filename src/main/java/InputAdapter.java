import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InputAdapter {
    private Connection connect() {
        String url = "-TODO-";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the Player table within database
     */
    public void selectAll(){
        String sql = "SELECT * FROM Player";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("player_name") + "\t" +
                        rs.getDouble("height"));
            }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            InputAdapter app = new InputAdapter();
            app.selectAll();
        }
}

