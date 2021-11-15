# Scenario Walk-Through

The user is presented with a welcome/startup message, and then is prompted to enter a username and password.
If the user enters the username of an existing user, the input password will be checked against the existing password.
Otherwise, a new account will be created with the specified credentials.

Upon successful login, the user is prompted to choose what they're searching for (this is handled by `CommandLine`). 
They may choose to search for a player or a team. For example, let's say that the user chooses to search for a player.
Then, the user will be asked if they would like to search for players by name or by specifying attributes.
Let's say that the user chooses to search based on attributes.
They will then be asked to select which attributes will be used in their search.
For example, the user can enter `1` for offensive attributes.
They will be given a series of prompts asking them to specify a single value or a range of values (min. and max.) for each attribute (handled by `InputPlayerAttributes`).
For example, the console will display `Striking ability:`, and the user could enter `22` (single value) or `18 25` (range).
This process will repeat until the user has entered a desired value for all relevant attributes.
Once all attribute values have been entered, the application will search for all players (in `PlayerDatabase`) which match the user's specification (handled by `SearchByPlayerAttributes`).
The results of the search will be passed to a presenter class (`PlayersPresenter`) which displays the names, attributes, and ratings of the matching players.

If instead the user chooses to search by name, they will be given a prompt asking them to specify a name (handled by `InputPlayerName`).
For example, the user may input `Danny`. 
The application will search for all players (in `PlayerDatabase`) which contain `Danny`, or something similar, in their name (handled by `SearchForPlayer`).
The results of the search will be passed to the same presenter class (`PlayersPresenter`), and the user will see a similar output. 
Similar processes would occur if a user chose to search for teams at the beginning,
although different classes are involved in those processes (*e.g.* `InputTeamAttributes`, `TeamsPresenter`, etc.).
