# Phase 2 Design Document

*Note: This document covers a lot of the same information as the [Phase 1 Design Document](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/design_document.md#packaging-strategy). In some sections, we include links to what we wrote in the previous phase to keep things concise.*

- [Specification and Walkthrough](#specification-and-walkthrough)
  - [Overview of Changes to Specification](#overview-of-changes-to-specification)
- [Major Design Decisions](#major-design-decisions)
- [Clean Architecture](#clean-architecture)
- [SOLID Design](#solid-design)
- [Packaging Strategy](#packaging-strategy)
- [Design Patterns](#design-patterns)
- [Progress Report](#progress-report)

## Specification and Walkthrough

See [`specification.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase2/specification.md) for the final specification.

Also see [`walkthrough.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase2/walkthrough.md) for the walkthrough which reflects the final specification.

*Note: Both documents have not changed very much since Phase 1. Our focus in Phase 2 was to implement everything in the specification, rather than broaden our scope further.*

## Major Design Decisions

### Phase 1

*Note: All of these decisions were mentioned in the [previous phase](../phase1/design_document.md#major_design_decisions), but many were a work in progress. Now they have all been implemented, and some of the details have changed slightly.*

- Created subclasses of player and implemented the **factory design pattern**
  - All players have the same kinds of attributes in our database, but depending on the type of player (forward, defense, goalkeeper, etc.), not all of those attributes are very relevant.
    - For example, when presenting a single defender, we aren't interested in their goalkeeping abilities.
  - To avoid multiple switch statements in our program, we use polymorphism instead, thus avoiding a [code smell](https://refactoring.guru/smells/switch-statements).
    - We are automatically provided with the appropriate `Player` subclass by `PlayerFactory`, without needing to specify the exact class of the object that will be created.
    - Each type of player has a unique `getPositionSkills` method that returns the skills (and associated values) that are most relevant to that type of player.
- Implemented the **builder design pattern** for the different types of inputs/searches
  - Our program offers two ways to search for players (by name or attributes), and the `InputBuilder` class decides which type of input to build based on the user's search preference. 
- Replaced `Scout` with a `User` class
  - Our new `User` class allows for additional functionality that wouldn't make sense with the `Scout` class.
    - *e.g.* a `Scout` shouldn't have a username and password if it is just another entity alongside players and teams.
- Made our Business Data (entities) serializable
  - One of the most important features included in the new specification is the ability to save the state of the program.
    - User profiles should be preserved across multiple runs of the program.
    - Any teams added by users should be kept in memory, as well as any changes made to player attributes.
  - To enable saving state, we have made all core entities serializable(`Player`s, `Team`s, `Users`, as well as the `Database` classes).
- Reworked our search/input classes
  - We wanted to make sure that when searching by attribute, the user only needs to specify values for the attributes that they care about (*e.g.* only defensive attributes).
  - We also wanted users to be able to specify a range of values if they want, or just specify a single value.
  - Our previous design, which used `PlayerPropertiesIterator` and a text file with a list of attributes, didn't allow for this flexibility in input.

### Phase 2

- Created a `Database` superclass
  - We have created a `DataBase` superclass, which has the `addEntity`, `setEntities` and `getEntities`. The `TeamDatabase` and `PlayerDatabase` extend this class. Both of these classes have similar characteristcs, but in different contexts. Hence, making a parent class (`Database`), allows these child classes to access and utilise the methods of `Database` classes to their own unique functionality. 
- Made use of generics with multiple classes
- Made all database classes less static
- Removed some responsibility from `CSVAdapter`

## Clean Architecture

Our CRC diagram gives an overview of how the different classes in our program fit into the layers of Clean Architecture. It also shows some of the major dependencies in our program.

<!-- TODO: Add CRC diagram (image or link) -->

Here is a written summary of how our project adheres to Clean Architecture:
Ever since the inception of our project, we have worked to make our project and code adhere to the norms of Clean Architecture. 
To start with, our project doesn't heavily rely on any particular framework, this has allowed us to **independently expand** the domain of our project after each phase and not forced us to stay within the constraints of a framework. We have upgraded our UI without having to make significant changes in our core functionality, thus indicating that our code is somewhat **independent of UI**. Our code is very **testable** and can be tested without UI or a webserver. By avoiding **long method** code smell, we have a lot of small methods in our project, hence we can test each and every small functionality of our code.

<!-- TODO: Add summary of Clean Architecture stuff -->

## SOLID Design

<!-- TODO: Update this section to include any new info/changes -->

- Single Responsibility Principle
  - For the most part, our code follows the Single Responsibility Principle fairly well. Almost all of our classes are broken down well, with each one of them handling a single concern. 
    - For example, we have different search classes `SearchForPlayer` and `SearchByPlayerAttributes` for different kinds of search operations, thus avoiding a single class to handle two different kinds of search tasks. 
  - One domain where our project might not follow the Single Responsibility Principle, is with our dataset. 
    - Our classes, and our code in general, are highly dependent on the specific dataset we are using (`players_20.csv`). 
    - If we changed this dataset to one which does not contain the same player attributes, then we would have to modify almost every class in our program.
- Open/Closed Principle
  - In both Phase 1 and 2, we have focused on *extending* our code from Phase 0 while minimizing any *modification* of our existing code. 
    - Overall, our project has been open to extension while closed for modification, with some exceptions. 
  - We have added features like a **new login system**, **team creation feature**, and the ability to **save the state of the program**. All of these added features have not required existing classes to be modified very much.
  - Other changes in our project, like **updating the search methodology**, **upgrading presenter classes**, and especially **implementing the `Database` superclass**, *have* required changes to existing classes.
    - However, these changes will make future extensions easier. For example, if we were to add other entities to our program like coaches or managers, having the generic `Database` superclass would be really helpful.
- Liskov Substitution Principle
  - Our use of interfaces and parent classes allows many subtypes to be substituted for their parent classes.
  - For example, the `Defender`, `Goalkeeper`, and other subclasses of `Player`, as well as `Player` itself, can all be added to `PlayerDatabase` using the `addEntity` method.
      - The same is true for all the methods of `PlayersPresenter` and `PlayerStatsCalculator`.
- Interface Segregation Principle
  - The user is not forced to depend on methods it does not use in our code. 
    - For instance, the `InputPlayerName` and `InputPlayerAttributes` classes extend the `InputData` interface, therefore, when a client wants to search for a player by name, it does not need to worry about inputting attributes.
  - Additionally, all of our classes are specific enough that they only implement methods that they require, and we have avoided abstract classes or interfaces that enforce unnecessary methods.
    - *e.g.* Our `InputAdapter` interface only requires a `processFile` method, which takes in a database file, to be implemented. It is up to the concrete adapter class to decide what other methods (like `processRow` and `makeHashMap`) are necessary for proper functionality.
- Dependency Inversion Principle
  - We tried as much as possible to introduce layers of abstraction between higher level and lower level classes, mostly by using interfaces.
  - For example, the `main` method of our program (in `CommandLine`) class could have depended directly on the concrete `CSVAdapter` class, but we applied Dependency Inversion by adding an interface (`InputAdapter`) between these classes.
    - The lower level `CommandLine` (a UI class) depends on the `InputAdapter` interface, and the higher level `CSVAdapter` (a gateway class) implements this interface.

## Packaging Strategy

Our packaging strategy, "Packaging by Component," has not changed from Phase 1. Here is a snippet from the [Phase 1 Design Document](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/design_document.md#packaging-strategy):

> There are many packaging strategies we could have chosen to apply to our project's file structure, however, we came to the conclusion that the Packaging by Component strategy would fit our project best. We came to this conclusion by process of elimination; we considered how each of the strategies learned in class would organize all of the files in the project before settling on packaging by component.
>
> [...]
>
> Thus, we settled on Packaging by Component. This strategy allowed us to put files that correspond to a single feature or function in our program, such as presentation or searching, into the same package. This would be the easiest to look at and search through to find specific files, while also making it clear what all the different features of our program are, and which files are responsible for providing them.

## Design Patterns

In the [Phase 1 Design Document](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/design_document.md#design-patterns) (click for more details), we discussed the different design patterns we have implemented or considered. This includes:

- **Adapter design pattern**, implemented using the `InputAdapter` interface and `CSVAdapter` class.
- **Factory design pattern**, implemented using the `PlayerFactory` class.
- **Builder design pattern**, implemented using the `InputBuilder` class
  - Now uses an enum, `InputType`.
  - Now creates additional product classes: `InputLogin`, `InputTeamName`, `InputTeamAttributes`.
- **Decorator design pattern**, *NOT* implemented since it was unnecessary.

In Phase 2, we have not identified any additional design patterns that are applicable to our program.

## Progress Report

See [`phase2/progress_report.md`](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase2/progress_report.md).
