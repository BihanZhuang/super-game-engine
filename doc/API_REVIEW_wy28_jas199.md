308 API Review
##What about your API/design is intendaed to be flexible?
###Wei-Ting: Can create new game objects, new layers
###Jimmy: Can only communicate with authoring environment internally with events, actions, and entities. Can create new layers, handle mouse clicks/hovers, pickSong, etc. Tried to think of every way you can communicate with authoring environment. The most general ways are with events, actions, and entities. Layers, mouse clicks, songs, etc. were special cases.

##How is your API/design encapsulating your implementation decisions?
###Wei-Ting: When creating InfoViews, these are abstract classes so that you don’t know how each instance of InfoView works
###Jimmy: Events/Entities/Actions are all special types of objects that are abstracted so that you don’t need to know how instances of it work. You don’t need to know how the events/entities/actions are added to the authoring environment. 

##How is your part linked to other parts of the project?
###
###Jimmy: The authoring environment is linked to the other parts of the project in that the authoring environment allows the user to create a level, and then this level is saved and can be loaded by the backend to actually make the game.

##What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
###Wei-Ting: When the user changes the attributes of the game objects, we give the range of each attribute so that the user will not input out of bound values.
###Jimmy: If the user tries to drag an entity outside of bounds, we need to bound it. If the user picks an invalid file or song, must throw an error. Must ensure that valid objects are sent to the backend to save.

##Why do you think your API/design is good (also define what your measure of good is)? 
###Wei-Ting: Only have necessary public methods that we need to access from other classes. 
###Jimmy: Doesn’t release much information to the outside world. Limited ways that you can communicate information. Only have methods that are absolutely necessary.

##How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
###Wei-Ting: Factories for creating objects.
###Jimmy: Factories for creating different entities/actions.

##How do you think the "advanced" Java features will help you implement your design?
###Wei-Ting: I can use composition to create different components of game objects.
###Jimmy: Reflections help for creating action/entity instances.

##What feature/design problem are you most excited to work on?
###Wei-Ting: I am excited to work on the author environment. 
###Jimmy: I’m excited to work on the canvas portion of the authoring environment. It is how the user really creates the game and thus it must be the most user-friendly.

##What feature/design problem are you most worried about working on?
###Wei-Ting: I am worried about the load and save feature. We have to make sure everything is serializable.
###Jimmy: I’m worried about how the authoring environment will communicate information with the backend. This is where errors could really begin to show, and I think we need to begin testing with them as soon as possible.

##What major feature do you plan to implement this weekend?
###Wei-Ting: Get the authoring environment done. 
###Jimmy: I plan on finishing the canvas and fully testing it to ensure that it is user-friendly and doesn’t have any errors. Specifically, I need to ensure that you can resize entities within the canvas, move the elements, and specify how big an area the game covers.

##Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
###Wei-Ting: The uses cases are descriptive, appropriate and reasonably sized.
###Jimmy: The use cases/issues are as descriptive as possible and appropraite. However, there are certainly some that are not reasonably sized and some parts encapsulate much more than others.

##Do you have use cases for errors that might occur?
###Wei-Ting: We have some use cases that prevent errors from happening. 
###Jimmy: We have a few use cases for errors that could occur, but we definitely did not focus on that. As error pop up, we will create issues in gitlab to address them.
