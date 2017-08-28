# VoogaSalad -- ItWorks

Game Authoring Engine Project

Mike Liu, Xingyu Chen, Bihan Zhuang, Yanbo Fang, Yuxiang He, Wei-Ting Yeh, Yuansong Feng, Gabriel Chen

Date Started: 03/24/2017

Date Finished: 04/30/2017

Hour spent in total: 1500

### Each person's role in developing the project

#### Mike Liu:
1. The leader in designing, establishing and heavily refactoring the general framework for the project, i.e. implementing the Component-Entity-System architecture. Implemented the gameobject root package and gameobject.component.type package.
2. Design and implemented the entire model package.
3. Wrote most of the util package.
4. Heavily refactored many classes on frontend and did a lot of frontend-backend integration. Some examples include GameView, ObjectView, many classes in the view.editor packages.

#### Bihan Zhuang:
1. Participated in designing and establishing the general framework -- Component-Entity-System architecture. Wrote part of the gameobject.component package and component.type package. 
2. Designed and implemented AI and AI system; heavily integrated and tested frontend-backend for making new templates and adding new components. Designed and implemented hero and hero control systems.
3. Designed, implemented and refactored the controller package.
4. Designed, implemented and refactored the network package for networked game that supports a host and multiple clients. Implemented chatroom over network.

#### Xingyu Chen
1. The major designer of the backend system structure. 
2. Programmed the backend system package. Implemented most systems excluding GoalSystem and AISystem. 
3. Implemented gameobject.Basics Package. Including All Templates, Bricks, Hero, Mushroom, etc...
4. Designed, Implemented part of gameobject.component package. Including Attack, Belongings, BonusScore, Collider, Generator, Health, Mass.
5. Designed, Implemented gameobject.basics.CollisionStrategy and gameobject.basics.GeneratorStrategy package.
6. Designed, Implemented view.editor.componentwindow package, which is in charge of editing each component and returning the GameObject template.

#### Yanbo Fang:
1. Designed and implemented GoalSystem and GoalStrategies package which utilized the Strategy pattern to maximize flexibility, as well as Level and its related stuff
2. Participated in designing of the Component Entity System, implemented part of the gameobject.component package such as Animation, Boss, Destination, etc.
3. Designed, implemented and refactored gameplay HUD, enabled the display of score, level, and goal status and utilized Observer pattern
4. Worked on the GameView, implemented vertical and horizontal scrolling
5. Developed the editor in the authroing environment for user to design their goals in their game

#### Yuxiang He
1. Contributed to the overall design and structure of the Component Entity System design of the program. Also proposed another alternative design to allow objects to interact with each other, i.e. the `Broadcast` system where objects broadcasts messages and gets messages so that they know what is happening in the game and make decisions about their next steps.
2. Designed and implemented everything in the `PhysicsEngine`, implemented the Strategy design pattern to allow flexible physics behavior of objects in the game.
3. Used physics laws to make sure the simulation is realistic. (Of course we can switch some effects ON/OFF to make the game more playable in some cases)
4. Added some utilities in the `util` package such as `Vector2D` to make calculations and overall handling of position and speed data easier.

#### Yuansong Feng
1. Designer for the game front end and led the game authoring environment.
Design and implement the game authoring layout and look-and-feel. For example, how to arrange the menu bar, canvas view and inventory. 
2. Developed a set of UI utilities including image processing package (cropping, scaling, decorating, etc.), drag and drop nodes, resizable node, window slide-in effect and other reactive UI elements. 
3. Created the Minimap view for the game authoring that is convenient for game developer to view the general design layout. 
4. Employed strategy pattern to build the grid system. 

#### Wei-Ting Yeh
1. Designer of the front end and worked on both game authoring environment and game player.
2. Design and implement AttributeEditorView, which shows the attributes of the object clicked in the canvas and allows the author to set values for the attributes.
3. Implemented CustomObjectView, which allows the author to create his own template, by loading image, choosing and setting values of attributes, choosing its type, and setting its name and description.
4. Implemented front end Championpool, which allows user to choose his or her preferred champion in the game player.

#### Gabriel Chen
1. Design and implement the front end. Worked on both game authoring environment and game player.
2. Design and implement Gameplay HUD, which displays the HUD designed by game maker during gameplay.
3. Design and implement HUD editor, which allows user to build a custom HUD for their game.
4. Design and implement HUDConfiguration which allows the HUD settings and custom HUD elements as selected by a game maker to save these settings.
Implemented parts of GameplayView, and Authoring Environment


### Resources used in developing the project
Official Java Documentation, StackOverflow, Online articles about game development. 

### Files used to start the project
Main.java

### Files used to test the project
See the folders provided in the folder including testItem, testAI and MarioGame

### Data or resource files required by the project
See the folder for more details

### Any known bugs, crashes, or problems with the project's functionality
We are not able to let a dropdown menu to disappear once we click it.


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

### Your impressions of the assignment to help improve it in the future

This assignment is a super interesting project which we enjoyed and SUFFERED a lot. Our team spend a lot of time together working and debugging. But we still LOVE 308.
