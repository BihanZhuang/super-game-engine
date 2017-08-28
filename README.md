# Super Game Engine -- Game Authoring Engine Project

Worked on a team of eight students. 

### My Responsibilities:
1. Participated in designing and establishing the general framework -- Component-Entity-System architecture. Wrote part of the gameobject.component package and component.type package. 
2. Designed and implemented AI and AI system; heavily integrated and tested frontend-backend for making new templates and adding new components. Designed and implemented hero and hero control systems.
3. Designed, implemented and refactored the controller package.
4. Designed, implemented and refactored the network package for networked game that supports a host and multiple clients. Implemented chatroom over network. 

### Files used to start the project
Main.java

### Information about running the program:

##### Authoring Environment Menu Bar
1. If the user wants to author a game, the step is as the following:
Click ‘Make my Own Game’, then the Game Authoring Environment Window will jump out
2. Our Game Authoring Environment Window contains a Menu Bar on the top. Inventory Menu will contain ‘Load Inventory’, ‘Save Inventory’ and ‘Create Template’
3. Clicking ‘Load Inventory’ will allow User to load their templates into Inventory.
4. Clicking ‘Save Inventory’ will allow User to save their current templates in the Inventory into a file. So they can save their progress.
5. Clicking ‘Create Template’ will allow User to build their own customized templates. See * for further instruction in this part.
6. Game Menu will contain ‘Load Game’, ‘Save Game’, ‘Add Level’, ‘To Level’. ‘Load Game’ will let User to load a previously constructed authoring file into the environment. ‘Save Game’ will save the current environment as a game. ‘Add Level’ will let user add a new level. ‘To level’ will let user switch between different levels.
7. Edit Menu will contain ‘Modify Constants’. ‘Modify Constans’ will let the user modify the constants existing in the game world.
8. View Menu can change the style of the window
9. Help Menu will provide an instruction to the User.

##### Authoring Environment Window
1. The body of the authoring environment is composed of four sections. The main one on the upper left is the canvas where user could drag templates into and instantiate new game objects. The upper right window will show all the attributes related to the current game object. The bottom left window is our inventory where user could retrieve templates and add to the game. The bottom right window is our minimap that shows the general layout of the current game design. 
2. When we click on any object in the canvas, all related attributes will show up on the right hand side of the canvas and allow user to adjust any attribute. 
3. The minimap will automatically adjust as we resize the whole canvas and will also adjust the  children nodes including added objects. 

* To create a template, you can start from ‘Customize’, which provides you with infinite freedom but needs to start from very beginning. Or from existing templates, like ‘Enemy’, ‘Hero’, these templates can save you time because it provides you with some components. For each component, you can click on it and change the setting in the popup window. Then click submit to save your changes to the component.

##### GamePlay
1. If the user wants to play the Game, the step is as the following:
2. Click ‘Play Game’, then an empty Gaming Screen will come out
3. Click ‘File’-’Load Game’, then select the folder containing the game information
4. Choose the preferred champion template 
5. You can save the status of the game by Clicking ‘Save Record’
6. You can load and resume your game by Clicking ‘Load Record’ and choosing the record.

##### To realize networked game:
1. Run the program and load a host game as described in the above section
2. Run the program one more time. Click ‘Connect To Game’, then enter the IP address of the host serving the game in the dialog box
3. Choose the preferred champion template
4. Add multiple players in the same way described.

## Extra features:
1. Networked game with host-client architecture. After a host game is loaded and running, a client can select Connect To Game on the main menu and connect to host game on the IP address entered. This architecture supports one host and multiple clients at the same time.
Networked chatroom for all the players.
2. AI with intelligent movement.
3. Very thorough physics engine.
	1. Collision are handled by realistic conservation of momentum and modelling inelasticity of collisions. The effect is almost on par with a scientific simulation program
	2. Considered friction and gravitational forces. So motions are really realistic.
	3. Collision checking are thorough, made sure that we do not miss cases or misinterpret collision events (e.g. giving the correct collision surface on almost all known cases we can come up with)
4. Strategy pattern allows the user to define the behavior 
5. Robust System Structure, through the system, we can achieve several features including Shooting Bullet, Turtle dies and generates a shell, Transforming into Super Mario and Kicking the shell to kill other enemies.
6. In the game authoring environment, when the user drags and drops the image to the canvas, the image snaps to grid.
7. The user can resize the canvas and the images in the authoring environment.
8. There is a mini map in the game authoring environment that shows the current overview of of the map.
The background color of the splash screen changes in vary of time.
9. There is different background music for every stage, and has sound effects when player wins or loses.
10. The user creates a champion pool in the game authoring environment to allow the user to choose his or her preferred champion in the game player.
