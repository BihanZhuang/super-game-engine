# VoogaSalad Analysis
Bihan Zhuang

## Overall Design

Describe the overall design of the complete program(s):

What is the high level design of each sub-part and how do they work together (i.e., what behavior, data, or resources does each part depends on from the others)?

The high level design of the backend can be described by the following hierarchy: Model contains Game, SystemManager, and animation Timeline. 
Game contains World, key-input control, GameStats, and all the choices for the look of a champion, ChampionPool. The World contains all the 
GameObjects. The SystemManager has control over all the systems. There is also the network, physics engine, and goal strategies on the backend. 

The Systems and GameObjects utilizes the Component-Entity-System (as CES below) 
architecture to efficiently handle all the game objects that can be supported by the game engine. The components represent different 
properties that can be given to a game object. One game object is one entity. The systems each specializes in handling something and 
within them single step logics are defined. The systems only operate on a certain game object if the game object has all the components
required by a certain system. Using the CES allows us to make game objects with any 
combination of components that suit a game's need, to have clear, non-oeverlapping, modularized operations, and also to easily add systems 
that operates on a new component or combination of components. The SystemManager needs information from the World, the Game as well as the 
Physics Engine in order to calculate the next update for all the GameObjects.

Network is a somewhat separate component from the rest of backend in that only the host, which contains a server, needs to have access
to the all backend information (for instance, what are the gameobjects, how to update each of them). The client side only needs to be informed
about the change in value rather than knowing how they were calculated. The PhysicsEngine has access to all the gameobjects in the entire game 
and contains a set of different strategies for different interactions. And the goal strategies is completely not dependent on the backend model,
where it checks for a set of criteria to see if there should be a level up. 

The frontend can be broken into two main parts. The first one is the game editing view, where the view is comprised of 1) a menu to 
save/load game, record and inventory, an editing canvas, 2）an inventory where all the pre-made and customized templates would show up,
3) a canvas for building the game, 4) an area where the the component values of a certain gameobject can be changed, 5) a mini map 
that shows the entire canvas, and 6) a pop up for users to change default templates as well as defining one on their own. We used the 

The second part is the game play view, where pre-made game files could be loaded in and played by users. GameView is constantly observing
any changes to each of the game objects so that the correct position and so on could be reflected immediately in the game play. Since updating
the GameView only requires light-weight information, this basically allows our networked game to be as smooth as possible without too much 
latency on client side compared to the host. 

How is the design tied (or not) to the chosen game genre. What assumptions need to be changed (if anything) to make it handle different genres?
What is needed to represent a specific game (such as new code or resources or asset files)?

Our design is highly tied to scrolling platformer games because 1) we need to be able to define many different kinds of gameobjects. The CES and 
creating template feature on the frontend make this easy, 2) the way we set up the Observer pattern allows us to have fast and direct communication
so that the game could be as smooth as possible, 3) we have a big section dedicated to physics engine so that we could model the real world interactions
between objects, 4) scrolling platformer can usually be player in multiplayer moder, and our networked game supports that smoothly, 5) our editing
environment and save/load features also support any kind of scrolling platformer, as long as it can be made, it can be run， 6）we support both making
and playing multiple levels of game which is very normal for scrolling platformer games.  

For turn-based strategy games, our design would not be suitable because 1) no PhysicsEngine should be needed, 2) the updating logic of the information of
gameobjects should be fairly different because for instance, no gameobjects in the game should need a velocity anymore.

All the games will be serialized into XML format into a folder, where there must be at least three XML files: champions.xml, level1.xml, and metadata.xml.

Describe two packages that you did not implement:
Which is the most readable (i.e., whose classes and methods do what you expect and whose logic is clear and easy to follow) and give specific examples?

The gameobject package is the most readable.

Which is the most encapsulated (i.e., with minimal and clear dependencies that are easy to find rather than through "back channels") and give specific examples?

The model package is the most encapsulated because for each of World, Game and Level, there are two kinds of interfaces corresponding. The "I--" interface and the 
"--Info" interface. Both of the kinds contribute to encapsulating the backend actual World, Game and Level from the frontend very well. The "I--" interfaces allow a 
really general description of the classes, while the "--Info" interface constraints the amount of information that frontend can have access to. 

```java
@Override
	public List<IGoal> getGoals(){
    	return myGoals;
    }
    
	@Override
	public void addGoal(IGoal strategy) {
		myGoals.add(strategy);
	}
	
	@Override
    public double getTargetWeight(){
    	return myTargetWeight;
    }
    
	@Override
    public void setGoalWeight(double weight){
    	myTargetWeight = weight;
    }
	
	@Override
	public IWorld getWorld(){
		return myWorld;
	}

    @Override
    public void copyFrom(ILevel other) {
        myWorld.copyFrom(other.getWorld());
        myGoals = new ArrayList<>(other.getGoals());
    }

    @Override
    public void clear() {
        myWorld.clear();
        myGoals.clear();
        myTargetWeight = 1.0;
    }
```


What have you learned about design (either good or bad) by reading your team mates' code?



## Your Design
Reflect on the coding details of your part of the project.
Describe how your code is designed at a high level (focus on how the classes relate to each other through behavior (methods) rather than their state (instance variables)).
Describe two features that you implemented in detail — one that you feel is good and one that you feel could be improved:
Justify why the code is designed the way it is or what issues you wrestled with that made the design challenging.
Are there any assumptions or dependencies from this code that impact the overall design of the program? If not, how did you hide or remove them?

## Flexibility
Reflect on what makes a design flexible and extensible.
Describe how the project's final API, the one you were most involved with, balances
Power (flexibility and helping users to use good design practices) and
Simplicity (ease of understanding and preventing users from making mistakes)
Describe two features that you did not implement in detail — one that you feel is good and one that you feel could be improved:
What is interesting about this code (why did you choose it)?
What classes or resources are required to implement this feature?
Describe the design of this feature in detail (what parts are closed? what implementation details are encapsulated? what assumptions are made? do they limit its flexibility?).
How extensible is the design for this feature (is it clear how to extend the code as designed or what kind of change might be hard given this design)?

## Alternate Designs
Reflect on alternate designs for the project based on your analysis of the current design or project discussions.
Describe how the original APIs changed over the course of the project and how these changes were discussed and decisions ultimately made.
Describe a design decision discussed about two parts of the program in detail:
What alternate designs were proposed?
What are the trade-offs of the design choice (describe the pros and cons of the different designs)?
Which would you prefer and why (it does not have to be the one that is currently implemented)?

## Conclusions
Reflect on what you have learned about designing programs through this project, using any code in the project as examples:
Describe the best feature of the project's current design and what did you learn from reading or implementing it?
Describe the worst feature that remains in the project's current design and what did you learn from reading or implementing it?
Consider how your coding perspective and practice has changed during the semester:
What is your biggest strength as a coder/designer?
What is your favorite part of coding/designing?
What are two specific things you have done this semester to improve as a programmer this semester?