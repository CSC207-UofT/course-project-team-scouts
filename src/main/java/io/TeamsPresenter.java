package io;

import data.TeamStatsCalculator;
import entities.Player;
import entities.Team;
import ui.CommandLine;

import java.io.IOException;
import java.util.List;

public class TeamsPresenter implements PresentData<Team> {
    final TeamStatsCalculator calculator = new TeamStatsCalculator();

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
            int teamCount = 0;
            for (Team t : teams) {
                System.out.println("Team Id " + teamCount);
                System.out.println("Team name: " + t.getName());
                System.out.println("Overall Rating: " + calculator.generateOverallRating(t));
                System.out.println("Net worth: " + calculator.generateNetWorth(t));
                System.out.println("Number of players: " + t.getPlayers().size());
                System.out.println("=".repeat(48));

                teamCount++;
            }

            // Only let user view individual team if teams have been found.
            pauseOutput(teams);
        }
    }

    /**
     * Pauses the output and asks the user if they want to view a team individually
     *
     * @param teams the teams returned from a search.
     */
    private void pauseOutput(List<Team> teams) {
        try {
            String userChoice = CommandLine.userChoiceOutputTeams();

            if (userChoice.equals("team")) {

                String userChoiceTeam = CommandLine.individualTeamOutput();
                int teamId = Integer.parseInt(userChoiceTeam);

                outputPlayers(teamId, teams);


            } else {
                System.out.println();

            }

        } catch (IOException e) {
            System.out.println();

        }


    }

    /**
     * Pauses the output and asks the user if they want to view a team individually
     *
     * @param teams  list of teams returned by search
     * @param teamId the ID of the team to be viewed individually
     */
    private void outputPlayers(int teamId, List<Team> teams) {
        PlayersPresenter playersPresenter = new PlayersPresenter();
        Team team = teams.get(teamId);
        List<Player> players = team.getPlayers();
        playersPresenter.outputResults(players);
    }

}