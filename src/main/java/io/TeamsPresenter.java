package io;

import data.TeamStatsCalculator;
import entities.Team;

import java.util.List;

class TeamsPresenter implements PresentData<Team> {
    TeamStatsCalculator calculator = new TeamStatsCalculator();

    /**
     * Prints a well-formatted description of the key features of a list of
     * Team entities.
     *
     * @param teams the teams returned from a search.
     */
    @Override
    public void outputResults(List<Team> teams) {
        System.out.println("=".repeat(48));
        if (teams.isEmpty()) {
            System.out.println("No matching teams found.");
        } else {
            for (Team t : teams) {
                System.out.println("Team name: " + t.getName());
                System.out.println("Overall Rating: " + calculator.generateOverallRating(t));
                System.out.println("Net worth: " + calculator.generateNetWorth(t));
                System.out.println("Number of players: " + t.getPlayers().size());
                System.out.println("=".repeat(48));
            }
        }
    }
}