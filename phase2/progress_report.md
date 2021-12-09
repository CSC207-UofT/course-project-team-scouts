# Progress Report

- [The Good Parts](#the-good-parts)
- [Questions and Concerns](#questions-and-concerns)
- [Group Member Roles](#group-member-roles)
  - [Aditya](#aditya)
  - [Daniel](#daniel)
  - [Kaartik](#kaartik)
  - [Matthew](#matthew)
  - [Michael](#michael)
  - [Tobey](#tobey)

## The Good Parts

One of the major changes in this phase of the project was the **creation of a `Database` superclass**. Our TA, Ethan, asked us about having a superclass during our first presentation, but at the time, we didn't see much utility in it. However, after discussing it further, we decided that it would be a great idea for a few reasons:

1. We were in the process of designing our `User` and `UserDatabase` classes, and noticed that it would share a lot of functionality with our other database classes, `PlayerDatabase` and `TeamDatabase`.
  - Rather than duplicating code, it made more sense to inherit the identical functionality from a common superclass.
  - Also, if we were to ever extend our program and add other entities like coaches or managers, having the superclass would be really helpful. This helps our program adhere to the Open/Closed Principle.
2. We were also in the process of enabling serialization of our databases and entities. 
  - If we make our `Database` superclass serializable, then all subclasses will also automatically become serializable, and the methods that load and save data (in `ReadWriter`) will work with any instance of `Database`.

We also decided that using generics for this class would be a good idea, as it enables us to store any object and use the methods defined in the superclass. For example, `UserDatabase` extends `Database<User>`, so all of its methods work on `User` objects. This is one way in which the decision to make a `Database` superclass with generics **has already paid off**; It only took a few lines of code to have a fully working, serializable database for storing players.

While implementing the new `Database` superclass, we also decided to eliminate the "staticness" from all database classes. Previously, each database had a public static list of entities that could be accessed from anywhere in the program. This came with a few problems:

1. When you have "global data," such as a public static list, it is very easy to lose track of which methods are modifying that data. This makes bugs more likely and bug fixing more difficult. It also increases the chance that we will accidentally change data that we don't intend to change.
2. Static variables can make testing very tedious. To make sure that the operations in one test don't affect the next test, we have to make sure to reset the value of the static variable at the end of every single test.
3. Java simply does not allow static data to be written to a `.ser` file, which would make the serialization of our databases impossible.

In our current design, each database (`Player`/`Team`/`UserDatabase`) gets initialized when the program starts up. If there are existing `.ser` files for these databases, they will be loaded into the program. Otherwise, empty `Database` objects are created and added to by `CSVAdapter` (and `LoginUseCase`). Any classes or methods that used to access the static variable now require `Database` objects to be passed as arguments instead.

In addition to using generics in `Database`, we also applied them to one of our search classes, `SearchByName`. Our previous design would have required separate classes `SearchByPlayerName` and `SearchByTeamName` to enable searching of `Player`s and `Team`s, since the search methods would have different parameters. For example, `SearchByPlayerName.search()` would return a `List<Player>`, while `SearchByTeamName.search()` would return a `List<Team>`. (Alternatively, we could've used overloading, but this would still result in duplicated code.) With our new design, `SearchByName.search()` returns a generic `List<T>`, and the type of `T` will depend on how we initialize the `SearchByName` class. This design decision will also **make future expansion of our program easier**, and it **minimizes the amount of duplicated code**.

Our final major change that has made things easier is that we have simplified `CSVAdapter`. For one, we removed the `stringToDouble()` method after realizing that none of the values in our database are decimal values. We also removed the need for `updateTeamsDatabase()` by putting all of this logic in the `TeamDatabase` class. This means that our adapter class will no longer have to keep track of the teams it has added to the database, which eliminates a lot of complexity from the class. The main benefit of extracting logic out of `CSVAdapter` is that if we ever need to switch out our dataset, there is much less to reimplement in whatever concrete `InputAdapter` class we create. It also makes `CSVAdapter` **much easier to read, understand, and test**, simply because it has fewer responsibilities and variables to keep track of.

## Questions and Concerns

*Note: There are additional unresolved questions/issues that we have not had the time to address which you can view on the [project repository](https://github.com/CSC207-UofT/course-project-team-scouts/issues).*

- Limitations of our packaging strategy
  - In Phase 1, we decided to package by component and create six different packages: `data`, `entities`, `io`, `search`, `services`, and `ui`.
  - Now that we've added even more classes, each package is becoming quite bloated and it is becoming difficult to locate classes in our program.
  - Also, some of our classes also seem to be in the wrong place. For example, the `StatsCalculator` classes are together with the `Database` classes, even though they have no interaction with one another.
  - Over the next couple of days, we will reconsider the packages we have made and possibly create more granular packages that better describe the classes inside them.
- Databases are being down passed through multiple layers
  - Our `Database` classes are first initialized in the `main()` method of our program, which is stored in the outer layer (`CommandLine`). 
  - Now that we have made the databases non-static, we have to pass each one as an argument to whatever methods are reading from them/adding to them. This includes methods in the controller and use case layers.
    - This means that instances of `Database` are constantly travelling throughout our program, which can lead to issues (possibly a violation of Clean Architecture?).
- Too much responsibility given to `CommandLine`?
  - Now that our UI and inputs have become more complex and we have added safety mechanisms so no errors occur in the program, our `CommandLine` class has increased in length.
  - We have tried to break up the complexity by keeping as much logic as possible in the `Input` classes, and if that wasn't possible, we tried our best to break things up into separate methods.
  - In the future, it would probably be a good idea to create new UI classes that will handle the inputs that happen before we actually use the `Input` classes.
- In order to avoid dependencies in the wrong direction between the entities and `StatsCalculator` classes, we end up making our code calculate statistics every time we search for teams.
  - We will look into this and possibly come up with a solution that reduces the amount of calculations done.
  - One possibility: calculate new statistics every time a player gets added to the team (this can be handled by `TeamDatabase`, since it is in the same software layer as other use cases like `StatsCalculator`).
- A lot of duplicated code in input functions
  - We have a good number of input classes, that cover various input cases, *e.g.* inputting player name, team name, player attributes, or team ratings. These classes share a lot of common code. 
  - For instance the `InputPlayerAttributes` and `InputTeamRating`, share a lot of common input functionality, from the `run` methods to the `switch` cases (to get the correct input prompt) to the maps. Thus, in our input classes we see sequences of code that occur more than once. 
  - We tried to resolve this with the default `getInput` method in the `InputData` interface, but there is still some duplication (especially in `InputPlayerAttributes`).
- Fuzziness of search functions can lead to bad results with shorter names - Althought implementing the fuzzy search has its benefits, but not having a high accuracy means that we can potentially get a lot of unrelated and undesired players with short names(depending on the column of names we used) when we search.  

## Group Member Roles

<!-- 
TODO: What have you worked on since Phase 1? What are some additional features/improvements that you could implement in the future?
TODO: Make sure to add a link to a significant pull request that you had a part in! See https://q.utoronto.ca/courses/233945/pages/project-phase-2 for more details on this. 
-->

### Aditya
Added changes to Player, Goalkeeper, Defender, Midfielder and Forward classes in order for them to work with the PlayerPresenter class to output separate attributes for each player when viewed individually, corresponding to their position on the field. Furthermore, I added functionality that would help view a player's attributes individually after a list of players is returned by the search algorithm. (PR [#46](https://github.com/CSC207-UofT/course-project-team-scouts/pull/48) and [#58](https://github.com/CSC207-UofT/course-project-team-scouts/pull/58)). This contribution ,in my opinion, was very significant as it helps the application implement a key feature that every scouting application is expected to have. I  helped Kaartik with refactoring some of the code and and also helped him with some parts of the design document. I also helped writing tests for the project. In the future I would like to implement the functionality that would help generate an automated team of 11 players using the attributes input by the user.

### Daniel

Implemented new user functionality and `Database` superclass with Tobey, and implemented serialization of databases, players, teams, and users (PR [#38](https://github.com/CSC207-UofT/course-project-team-scouts/pull/38) and [#39](https://github.com/CSC207-UofT/course-project-team-scouts/pull/39)). These contributions were essential for **satisfying our specification** (*e.g.* logging in and saving state) and for **improving our testing** (no longer using static, global variables). Re-implemented `InputPlayerAttributes` according to updated specification from last phase. Refactored a lot of our code to support new features and simplify code. Contributed to user interface design decisions. Wrote and finalized much of the documentation (esp. the Accessibility Report). In the future, I would like to refactor the program and add more documentation, so it's easier for others to expand on our code.

### Kaartik

Refactored and added documentation to `Player` and its subclasses along with `Team`. Rewrote and added few tests, in accordance to the changes and additions in `processFile`. Helped Aditya enhance the functionality of the presenter classes. Introduced the Clean Arch Diagram. I Implemented and introduced the Factory Design Pattern in our project with `PlayerFactory`, `Player` and its subclasses. (PR [#6](https://github.com/CSC207-UofT/course-project-team-scouts/pull/6)) (PR [#40](https://github.com/CSC207-UofT/course-project-team-scouts/pull/40)). The following addition was a key feature of our project, as it helped instantiate appropriate objects (which are used in various parts of our project) without exposing the creation logic to client. In the future I would like to introduce a more enhanced GUI, and also possibly add a feature of calculating the potential chemistry of a scouted player in a team. 

### Matthew

Implemented the `PlayerPresenter` class to display more detailed output for a single player. Also helped with the implementation of the input and searching functionality of the program by extending it to work with `Team` entities as well (by implementing the classes `InputTeamName`, `InputTeamRating`, and `SearchByTeamRating` PR [#56](https://github.com/CSC207-UofT/course-project-team-scouts/pull/56])). These specific additions were crucial in fulfilling the basic functionality that was first defined in our specification, while also increasing the utility of the program to users. In the future, I would like to potentially create a GUI for the user interface, that allows users to enter in their search input more easily and see the results of their searches in a more visually pleasing fashion.

### Michael 

Made minor changes to Player, CsvAdapter classes to enable search by attributes functionality. Reimplemented the SearchForPlayer and SearchByAttributes classes to allow them to interact appropriately with new implementation of databases that can now be serialized. Implemented SearchByAttributes to enable Player searching using attributes as outlined by our specificication  (PR [#44](https://github.com/CSC207-UofT/course-project-team-scouts/pull/44)). Contributed to user interface and searching use case design decisions. In the future I would like to add database editing functionality like the ability to add, remove or edit players and teams so theprograma is still useful even after the current database becomes outdated.

### Tobey

Implemented the `Database` superclass along with it's subclasses `PlayerDatabase`, `TeamDatabase`, and `UserDatabase` with Daniel (PR [#38](https://github.com/CSC207-UofT/course-project-team-scouts/pull/38)). In light of this, we had revamped how we work with our data. Additionally, assisted with the creation and implementation of our `User` entities and how they would play a part in our program. Currently working to finish up implementing the ability for User's to create teams with our current layout of data and flow of our program.
