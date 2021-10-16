public class CommandLine {
    public static void main(String[] args) {
        // Create new instance of InputAdapter
        CSVAdapter adapter = new CSVAdapter();
        adapter.dataDump("dataset(s)/players_20.csv");
        runPrompts();
    }

    private static void runPrompts() {
        System.out.println("Hello Word!");
        // Let's try to ask for a player name
        InputPlayerName inputClass = new InputPlayerName();
        // The following line raises a NullPointerException
        // TODO: Tobey, please fix!
        inputClass.run();
    }
}
