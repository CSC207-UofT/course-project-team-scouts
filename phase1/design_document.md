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
  - Users will be able to create a new team to scout for, rather than choosing an existing team (still in progress).
  - The team will be added to the existing teams database and function just like any other team (same attributes and use cases).
- Serialization for data retrieval
  - User profiles must be stored in memory to enable login features, and players/teams should be able to save their state so that the databases aren't rebuilt every time the program runs.
  - The user, team, and player entities will also be serializable (still in progress).
- Updated presenter classes
  - Presenter classes now produce a summary of the most important player and team information, including ratings.
    - *e.g.* overall rating, offensive rating, defensive rating, and goalkeeping rating
  - These ratings are calculated by new calculator methods, which take averages of specific skill attributes.
- Updated search/input classes (more details found in walkthrough)
  - Searching by name (player or team) is more "fuzzy" &mdash; results will also include names that aren't exact matches.
  - Still in progress:
    - Users will no longer have to enter a value for every single attribute. Instead, they can specify which attributes are relevant to their search.
    - Users will also be able to specify a range of acceptable values.

## Major Design Decisions

- Created subclasses of player and implemented the **factory design pattern**
  - All players have the same kinds of attributes in our database, but depending on the type of player (forward, defense, goalkeeper, etc.), not all of those attributes are very relevant.
    - For example, when presenting a defender, we aren't interested in their goalkeeping abilities.
  - To avoid multiple switch statements in our program, we use polymorphism instead, thus avoiding a [code smell](https://refactoring.guru/smells/switch-statements).
    - We are automatically provided with the appropriate `Player` subclass by `PlayerFactory`, without needing to specify the exact class of the object that will be created.
    - The `PlayerPresenter` method is overloaded to allow different outputs depending on the subclass of `Player` that we pass in.
- Implemented the **builder design pattern** for the different types of searches
  - The builder design pattern is primarily used when we want to use the same object building process to build different kind of objects. 
  - Our code offers two ways to scout a player, one by searching for players by name and the other by searching for players by attributes. 
  - The `Builder` class works like the brain, and decides which object to instantiate (or build), based on the input from the user. 
- Replaced `Scout` with a `User` class
  - We had always thought of `Scout`s as the users of the program, so the class name we had didn't accurately represent what we wanted the class to do.
  - Our new `User` class allows for additional functionality that wouldn't make sense with the `Scout` class.
    - *e.g.* a `Scout` shouldn't have a username and password if it is just another entity alongside players and teams.
- Made our Business Data (entities) serializable
  - One of the most important features included in the new specification is the ability to save the state of the program.
    - Users should be able to keep track of scouting history and player shortlists.
    - Any teams added by users should be kept in memory, as well as any changes made to player attributes.
  - To enable saving state, we will be making all core entities serializable(`Player`s, `Team`s, `Users`, and the `Database` classes).
    - `User` serialization has already been implemented, the rest is in progress.
- Reworked our search/input classes (still in progress)
  - This decision has more to do with usability than software design principles.
  - We wanted to make sure that when searching by attribute, the user only needs to specify values for the attributes that they care about.
  - We also wanted users to be able to specify a range of values if they want, or just specify a single value.
  - Our previous design, which used `PlayerPropertiesIterator` and a text file with a list of attributes, didn't allow for this flexibility in input.

## Clean Architecture

We have largely preserved the structure of our program from Phase 0, but now with some additional classes. See [`phase0/crc_model.pdf`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase0/crc_model.pdf) for Phase 0's CRC cards.

Here's a summary of the major changes in Phase 1 compared to Phase 0:

- Enterprise Business Rules (entities)
  - Added subclasses of `Player`: `Defender`, `Forward`, `Midfielder`, `Goalkeeper`
  - Added `User` and removed `Scout`
  - Inverted `Player`'s and `Team`'s dependency on the corresponding `StatsCalculator` classes
- Application Business Rules (use cases)
  - Added `PlayerFactory` and `InputBuilder` classes (correspond to factor and builder design patterns)
  - Added `LoginController` and `LoginUseCase` classes to support logging in 
- Interface Adapters (controllers, gateways, and presenters)
  - Added `ReadWriter`, `UserReadWriter`, and `LoginInputBoundary` classes to support user serialization and input

## SOLID Design

- Single Responsibility Principle
  - For the most part, our code follows the Single Responsibility Principle fairly well. Almost all of our classes are broken down well, with each one of them handling a single concern. 
    - For example, we have different search classes `SearchForPlayer` and `SearchByPlayerAttributes` for different kinds of search operations, thus avoiding a single class to handle two different kinds of search tasks. 
  - One domain where our project might not follow the Single Responsibility Principle, is with our dataset. 
    - Our classes, and our code in general, are highly dependent on the specific dataset we are using (`players_20.csv`). 
    - If we changed this dataset to one which does not contain the same player attributes, then we would have to modify almost every class in our program.
- Open/Closed Principle
  - The entirety of our Phase 1 has been *extending* on our code from Phase 0 while minimizing any *modification* of our existing code. 
    - Overall, our project has been open to extension while closed for modification, with some exceptions. 
  - We have added features like a **new login system**, **team creation feature**, **adding the factory design pattern**. All of these added features have not required any existing classes to be modified very much.
  - Other changes in our project, like **updating the search methodology** and **upgrading presenter classes**, *have* required changes to existing classes.
    - This should happen less in Phase 2, when we have finalized the core functionality and are more focused on extending functionality.
- Liskov Substitution Principle
  - Our use of interfaces and parent classes allows many subtypes to be substituted for their parent classes.
  - For example, the `Defender`, `Goalkeeper`, and other subclasses of `Player`, as well as `Player` itself, can all be added to `PlayerDatabase` using the `addEntity` method.
      - The same is true for all the methods of `PlayersPresenter` and `PlayerStatsCalculator`.
- Interface Segregation Principle
  - The user is not forced to depend on methods it does not use in our code. 
    - For instance, the `InputPlayerName` and `InputPlayerAttributes` classes extend the `InputData` interface, therefore, when a client wants to search for a player by name, it does not need to worry about inputting attributes.
  - Additionally, all of our classes are specific enough that they only implement methods that they require, and we have avoided abstract classes or interfaces that enforce unnecessary methods.
    - *e.g.* Our `InputAdapter` interface only requires a `dataDump` method, which takes in a database file, to be implemented. It is up to the concrete adapter class to decide what other methods (like `makeHashMap`) are necessary for proper functionality.
- Dependency Inversion Principle
  - We tried as much as possible to introduce layers of abstraction between higher level and lower level classes, mostly by using interfaces.
  - For example, the `main` method of our program (in `CommandLine`) class could have depended directly on the concrete `CSVAdapter` class, but we applied Dependency Inversion by adding an interface (`InputAdapter`) between these classes.
    - The lower level `CommandLine` (a UI class) depends on the `InputAdapter` interface, and the higher level `CSVAdapter` (a gateway class) implements this interface.

## Packaging Strategy

There are many packaging strategies we could have chosen to apply to our project's file structure, however, we 
came to the conclusion that the Packaging by Component strategy would fit our project best. We came to this conclusion
by process of elimination; we considered how each of the strategies learned in class would organize all of the files
in the project before settling on packaging by component. 

To begin with, we looked at the Vertical Packaging strategy. This
was the one that certainly was the least applicable to our project; this would store all files in just one single package, which would
be very messy and difficult to deal with. For example, when writing and testing code, it would be harder to search and find files
since they are all bundled up together in no particular order, a problem that would also make it harder for someone else who is
unfamiliar with the project to understand its structure. 

Next, we considered the Inside/Outside packaging
strategy, which seems to divide code up into a main package that handles a service, and separate packages for the supplementary
components to the service. We concluded that this did not fit our project either, as individual services
might get too large, and it would not make sense to separate things like databases from the services that use them. 

After this, we
considered the Packaging by Layer strategy, which we felt was also not an optimal strategy. Abiding by this strategy would mean
we had to put all code corresponding to a specific layer of the Clean Architecture model into the same package. This would mean that 
packages would be large and have lots of files that are only related to each other just by layer, and not by what their functionality is,
which would once again be confusing to look at. 

Thus, we settled on Packaging by Component. This strategy allowed us to put files
that correspond to a single feature or function in our program, such as presentation or searching, into the same package. This would be the easiest
to look at and search through to find specific files, while also making it clear what all the different features of our 
program are and which files are responsible for providing them.

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
  - builder class = `InputBuilder`
  - products = `InputPlayerName`, `InputPlayerAttributes`

We also considered implementing the decorator design pattern in this phase.
The idea was to have a `BasePlayerStatsCalculator` class which can calculate the overall rating of a `Player` or `Team`, taking into account **all** skill attributes.
Then, we would have several decorator classes like `OffensiveRatingDecorator` and `GoalkeepingRatingDecorator` that would provide the additional functionality of generating a rating based on a **subset** of skill attributes.
However, we decided against using decorators for this purpose.
Decorators are most useful when we are working with a **singular object** whose behaviour or attributes we want to modify/expand. 
Keeping up with the theme of soccer players, a good example would be having a base `Player` entity that can be customized with many individual pieces of attire or accessories.
However, our `StatsCalculator` classes are simply being used by other classes to calculate singular statistics on the fly, 
so it is fine that they are just monolithic classes with methods for each kind of statistic.

We will continue to consider how other design patterns can be applied to our project in the future.

## Progress Report

See [`phase1/progress_report.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/progress_report.md).
