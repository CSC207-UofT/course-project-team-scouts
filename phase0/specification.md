# Specification

This application will provide soccer scouts with a system for searching through **players** in a large external database. 
Scouts will be able to find players that are a good fit for a given team based on specific characteristics of their choosing. 
Each player in the database will have a set of attributes that describe their **age**, the **team** they currently play for,
their **value** (in Euros), their **position** (*e.g.* forward, defense, etc.), their **physique** (*e.g.* height, weight), 
and their **skills** (*e.g.* striking ability, speed, balance, etc.). 
Each skill will be **mapped to a number** (from 1 to 100), which represents the player's proficiency in that skill.
The application should also keep a database of **teams**, which are groupings of individual players. 
Each team also has its own attributes, including **name**, **number of players**, and total **net worth** (calculated from the constituent players). 
Finally, the application should have a database of **scouts**, the entities who will be using this application. 
Scouts will keep track of the **team they are scouting for**, their scouting **history**, 
and the **type** of scout that they are (player scout or tactical scout).

The applications first crucial task is to **download and organize the external database** into usable Java objects, 
so that players can be easily accessed when searched for. 
Some associated use cases include **adding players** to the database and **assigning**/grouping them into teams. 
Additionally, users will be able to make their own scout profiles, allowing them to **interact** with player entities (scout them). 
To accomplish this, the application will be able to **add new scout entities** to the system, or **retrieve an existing profile** from storage. 
Finally, the most crucial element of the application is the **searching** functionality. 
The application should allow users to search for players in the database either **by name**, 
or by querying the database based on a (sub)set of **player attributes** with desired values/ranges of values. 
Teams will also be easily searchable by name or attributes.
The returned player/team(s) will be **output neatly by presenter methods**.
