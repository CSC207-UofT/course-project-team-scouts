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
    - The `PlayerPresenter` method is overloaded to allow different outputs depending on the subclass of `Player` that we pass in.
- Implemented the **builder design pattern** for the different types of searches
  - *Details/justification...*
- Replaced `Scout` with a `User` class
  - We had always thought of `Scout`s as the users of the program, so this class name didn't accurately represent what we wanted the class to do.
    - *i.e.* It doesn't make much sense for a `Scout` class to have a username and password.
- Made our Business Data (entities) serializable
  - *Details/justification...*
- Reworked our search/input classes
  - *Details/justification...*

## Clean Architecture

*TODO: Draw a chart of our classes in the layers of clean architecture w/ dependency arrows*

*TODO: Brief explanation of chart*

## SOLID Design

- Single Responsibility Principle
  - *Explanation of how our project follows this...*
  - *Maybe mention how we can't follow this sometimes; if our dataset changes, we might have to change every single part of our program*
- Open/Closed Principle
  - *Explanation of how our project follows this...*
- Liskov Substitution Principle
  - *Explanation of how our project follows this...*
- Interface Segregation Principle
  - *Explanation of how our project follows this...*
- Dependency Inversion
  - *Explanation of how our project follows this...*

## Packaging Strategy

*TODO: Why did we go with package by component? (might help to explain by process of elimination)*

## Design Patterns

In the previous phase, we implemented the adapter design pattern. *Explanation...*

In this phase, we implemented the factory design pattern and builder design pattern (see [above](#major-design-decisions) for details/explanations). 
Below is an outline of the role that each class plays in satisfying the design pattern:

- factory design pattern:
  - factory class = `PlayerFactory`
  - common base class = `Player`
  - products = `Forward`, `Midfielder`, `Defender`, `Goalkeeper` (subclasses of `Player`)
- builder design pattern:
  - *...*

We also considered implementing the decorator design pattern in this phase.
The idea was to have a `BasePlayerStatsCalculator` class which can calculate the overall rating of a `Player` or `Team`, taking into account **all** skill attributes.
Then, we would have several decorator classes like `OffensiveRatingDecorator` and `GoalkeepingRatingDecorator` that would provide the additional functionality of generating a rating based on a **subset** of skill attributes.
*However, we decided against this, since...*

*In the future, we may consider implementing...*

## Progress Report

See [`phase1/progress_report.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/progress_report.md).