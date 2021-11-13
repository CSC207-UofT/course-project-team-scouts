# Phase 1 Design Document

- [Specification and Walkthrough](#specification-and-walkthrough)
  - [Overview of Changes to Specification](#overview-of-changes-to-specification)
- [Major Design Decisions](#major-design-decisions)
- [Clean Architecture](#clean-architecture)
- [SOLID Design](#solid-design)
- [Packaging Strategy](#packaging-strategy)
- [Design Patterns](#design-patterns)
- [Progress Report](#progress-report)

## Specification and Walkthrough

See [`phase0/specification.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase0/specification.md) for full updated specification.

Also see [`phase0/walkthrough.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase0/walkthrough.md) for an updated walkthrough which reflects the new specification.

### Overview of Changes to Specification

- A new login process
  - Our program now supports multiple user profiles with usernames and passwords.
  - Users will be able to view scouting history and add new players to their scouting shortlist.
- Team creation
  - Users can create a new team to scout for, rather than choosing an existing team
  - The team will be added to the existing teams database and function just like any other team (same attributes and use cases).
- Serialization for data retrieval
  - User profiles must be stored in memory to enable login features, and players/teams should be able to save their state so that the databases aren't rebuilt every time the program runs.
  - The user, team, and player entities are all serializable now, as well as their associated databases.
- Updated presenter classes
  - Presenter classes now produce a summary of the most important player and team information, including ratings.
    - *e.g.* overall rating, offensive rating, defensive rating, and goalkeeping rating
  - These ratings are calculated by new calculator methods, which take averages of specific skill attributes.
- Updated search/input classes (more details found in walkthrough)
  - Users no longer have to enter a value for every single attribute. Instead, they can specify which attributes are relevant to their search.
  - Users are also able to specify a range of acceptable values.
  - Searching by name (player or team) is more "fuzzy" &mdash; results will also include names that aren't exact matches.

## Major Design Decisions

- Created subclasses of player and implemented the **factory design pattern**
  - All players have the same kinds of attributes in our database, but depending on the type of player (forward, defense, goalkeeper, etc.), not all of those attributes are very relevant.
    - For example, when presenting a defender, we aren't interested in their goalkeeping abilities.
  - To avoid multiple switch statements in our program, we use polymorphism instead, thus avoiding a [code smell](https://refactoring.guru/smells/switch-statements).
    - We are automatically provided with the appropriate `Player` subclass by `PlayerFactory`, without needing to specify the exact class of the object that will be created.
    - The `PlayerPresenter` method is overloaded to allow different outputs depending on the subclass of `Player` that we pass in.
- Implemented the **builder design pattern** for the different types of searches
  - *Details/justification...*
- Replaced `Scout` with a `User` class
  - We had always thought of `Scout`s as the users of the program, so the class name we had didn't accurately represent what we wanted the class to do.
  - Our new `User` class allows for additional functionality that wouldn't make sense with the `Scout` class.
    - *e.g.* a `Scout` shouldn't have a username and password if it is just another entity alongside players and teams.
- Made our Business Data (entities) serializable
  - One of the most important features included in the new specification is the ability to save the state of the program.
    - Users should be able to keep track of scouting history and player shortlists.
    - Any teams added by users should be kept in memory, as well as any changes made to player attributes.
  - To enable saving state, we have made all core entities serializable(`Player`s, `Team`s, `Users`, and the `Database` classes).
    - We have also introduced a new class for loading in the data files.
- Reworked our search/input classes
  - This decision has more to do with usability than software design principles.
  - We wanted to make sure that when searching by attribute, the user only needs to specify values for the attributes that they care about.
  - We also wanted users to be able to specify a range of values if they want, or just specify a single value.
  - Our previous design, which used `PlayerPropertiesIterator` and a text file with a list of attributes, didn't allow for this flexibility in input.

## Clean Architecture

*TODO: Draw a chart of our main classes in the layers of clean architecture w/ dependency arrows*

*TODO: Brief explanation of chart*

## SOLID Design

- Single Responsibility Principle
  - For the most part, our code follows the Single Responsiblity Principle fairly well. Almost all of our classes are broken down well, with each one of them handling a single concern. For example, we have different search classes `SearchForPlayer` and `SearchByPlayerAttributes` for different kinds of search operations, thus avoiding a single class to handle two different kinds of search tasks. 
  - One domain where our project might not follow the Single Responsibility Principle, is with our dataset. Our classes, and our code in general, is highly dependent on the specific dataset we are using (`players_20.csv`). If we changed this dataset to one which does not contain the same player attributes, then we would have to modify almost every class in our program.
- Open/Closed Principle
  - The entirety of our phase 1 has been *extending* on our code from phase 0 while minimising any *modification* in our source code from phase 0. We have extended our project and added features like a new login system, team creation feature, updating the search methadology, upgrading presenter class, adding the factory design pattern, etc. Overall,for the most part our project has been open to extension while closed for modification, with some exceptions. 
- Liskov Substitution Principle
  - *Explanation of how our project follows this...*
- Interface Segregation Principle
  - *Explanation of how our project follows this...*
- Dependency Inversion
  - *Explanation of how our project follows this...*

## Packaging Strategy

*TODO: Why did we go with package by component? (might help to explain by process of elimination)*

## Design Patterns

In the previous phase, we implemented the **adapter** design pattern. 
Our program requires that we interact with some sort of external database, and we chose to read in data from a CSV file. 
The `OpenCSV` Java library provides methods that will allow for this functionality. 
However, we don't want to make large parts of our program dependent on `OpenCSV`'s interface, since this could change in the future. 
Additionally, if our dataset changes format (*e.g.* we use a SQLite file instead), we could run into the same issue.

In essence, the responsibility of this adapter is to call methods from the `OpenCSV` library (which is the adaptee) on behalf of other classes (which are the clients). We have implemented the adapter design pattern like so:

1. `CommandLine` (the client) wants to import all players and teams from the CSV as Java entities
2. `CommandLine` creates a new instance of `CSVAdapter` (the adapter), which implements the `InputAdapter` interface (the adapter interface)
3. `CSVAdapter` creates a new instance of `CSVReaderBuilder` (the adaptee from the `OpenCSV` library)
4. `CommandLine` calls `CSVAdapter.dataDump()`, which calls `CSVReaderBuilder.readAll()`
	- `CSVAdapter` adds the players/teams corresponding to each row to our database classes (in the form of `Player` and `Team` entities)

In this phase, we also implemented the **factory** design pattern and **builder** design pattern (see [above](#major-design-decisions) for details/explanations). 
Below is an outline of the role that each class plays in satisfying the design pattern:

- **factory design pattern:**
  - factory class = `PlayerFactory`
  - common base class = `Player`
  - products = `Forward`, `Midfielder`, `Defender`, `Goalkeeper` (subclasses of `Player`)
- **builder design pattern:**
  - *...*

We also considered implementing the decorator design pattern in this phase.
The idea was to have a `BasePlayerStatsCalculator` class which can calculate the overall rating of a `Player` or `Team`, taking into account **all** skill attributes.
Then, we would have several decorator classes like `OffensiveRatingDecorator` and `GoalkeepingRatingDecorator` that would provide the additional functionality of generating a rating based on a **subset** of skill attributes.
However, we decided against using decorators for this purpose.
Decorators are most useful when we are working with a **singular object** whose behaviour or attributes we want to modify/expand. 
Keeping up with the theme of soccer players, a good example would be having a base `Player` entity that can be customized with many individual pieces of attire or accessories.
However, our `StatsCalculator` classes are simply being used by other classes to calculate singular statistics on the fly, 
so it is fine that they are just monolithic classes with methods for each kind of statistic. 

*In the future, we may consider implementing...*

## Progress Report

See [`phase1/progress_report.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/progress_report.md).
