package io;

import search.SearchByPlayerAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputPlayerAttributes implements InputData {

    @Override
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    /**
     * Will give prompts for each attribute that we will search players on.
     * Will also accept user input for each of these prompts and output each player that
     * falls under the range of the given categories.
     */
    @Override
    public void run() {
        PlayerPropertiesIterator prompts = new PlayerPropertiesIterator();
        List<String> temp = new ArrayList<>();

        System.out.print("Type 'exit' to quit or 'ok' to continue (for each prompt please only input integers): ");
        try {
            String input = getInput();
            while (!input.equals("exit") && prompts.hasNext()) {
                System.out.print(prompts.next());
                input = this.getInput();
                if (!input.equals("exit")) {
                    temp.add(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }

        try {
            if (temp.get(0) != null) {
                ArrayList<Integer> attributes = new ArrayList<>(); // NOTE: Will change to HashMap in future version
                for (String s : temp) {
                    attributes.add(Integer.parseInt(s));
                }
                PlayersPresenter pPresenter = new PlayersPresenter();
                pPresenter.outputResults(SearchByPlayerAttributes.searchPlayer(attributes));
            }
        } catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("Exiting.");
        }
    }
}