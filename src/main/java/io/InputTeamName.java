package io;

import data.Database;
import entities.Team;
import search.SearchByName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class InputTeamName implements InputData<Team> {
    // Storing a list of the results returned by a search.
    List<Team> searchResults;

    @Override
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    @Override
    public void run(Database<Team> database) {
        String teamName = "";
        System.out.print("Enter the name of the team: ");
        try {
            teamName = this.getInput();
        } catch (IOException e) {
            System.out.println("Invalid team name.");
        }

        try {
            if (teamName != null) {
                SearchByName<Team> searchByName = new SearchByName<>();
                this.searchResults = searchByName.search(database, teamName);
                TeamPresenter tPresenter = new TeamPresenter();
                tPresenter.outputResults(searchResults);
            }
        } catch (Exception e) {
            System.out.println("Empty name.");
        }
    }
}
