# Detailed Scenario Walkthrough

## program startup

1. select an existing scout profile (corresponds to a Scout object), or create a new one
	1. if creating a new one, specify:
		1. name
		2. team
		3. type (player scout or tactical scout)
2. select a league to search from, or choose to search from all leagues
	- depending on user choise, SQLite database will query differently
	- choosing a league will result in less memory being used
3. present options:
	1. search for a player
	2. search for a team

## searching entities

4. if searching for a team:
	1. search by attributes?
		1. allow user to specify which attributes are relevant: present the user with a list (or table), and they can enter the attributes they care about (separated by commas)
		2. then, allow user to specify acceptable ranges of values for those attributes (maybe enter integer values one by one?)
			- *e.g.* `Minimum acceptable value for 'Defense Pressure': `
	2. search by name?
		1. allow user to enter a string, and search for any team whose name contains that string
			- *e.g.* `Manchester` will yield `Manchester United` and `Manchester City`
5. if searching for a player:
	1. search by attributes?
		- same as teams
		- should include BOTH physical attributes (height, weight) and skills
	2. search by name
		- same as teams

## presenting results

- searching by team or player, name or attributes will always yield a (possibly empty) list of results
	- we want to print out this list
- each entity's name should be printed, along with a summary of some important details
	- teams should be printed with their size (how many players in the roster) and possibly a list of current players
	- players shoud be printed with their age, weight, height, country
- each entity should be numbered
	- allow the user to input a team/player's corresponding number so they can see them in more detail (see ALL of the attributes)
	- pressing `Enter` will let them go back to the list, and pressing `Enter` again will let them make a new query

## future features (more complex)

- during the [searching](#searching-entities) phase, we can allow the user to choose from a list of previous queries
	- maybe each time they want to enter a new query, allow them to name it
	- the user can choose to discard a query if they don't want to use it again later
- when searching by attribute, we can allow the user to weight the importance of each attribute
	- *e.g.* the user can specify that they care about height the most; or stamina and volleys equally, but agility less
