# Team Scouts

A CSC207 course project. We're making a football player scouting app.

## To-Do

### `Player` class

- [ ] add value attribute to players
- [ ] use `Skill` class instead of `HashMap` for storing player skills (?)
- [ ] use `Team` class instead of `String` for storing player's team

### `Search____` classes

- [ ] combine `SearchForPlayer` and `SearchByPlayerAttributes` (?)
  - instead of using separate classes, we can have one `Player` search class with an overloaded method
  - one version of the method takes in a single argument (a player's name)
  - the other version takes in a mapping of skills with whatever values the user specified
- [ ] also combine `SearchForTeam` and `SearchByTeamAttributes` (?)

## Questions

(Things to ask the TA)

- how do decide between private and public instance variables?
  - *i.e.* when to use getters and setters rather than accessing variables directly?
