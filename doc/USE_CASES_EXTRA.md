1. A lowest level AI (example: a Evil Mushroom) moves horizontally in a defined range. The hero collides with the mushroom and his health is damaged by the AI’s attack.
2. A medium level AI checks if the hero is in its territory; if the hero is in its territory, it starts moving toward the hero and attacks the hero with a probability defined by the user. If the hero moves fast enough out of its territory, then the AI stops.
3. A highest level AI checks if the hero is in its territory; if the hero is in its territory, then it starts chasing after the hero using path finding algorithm to figure out the correct path to the hero. And then it starts a near-body fight with the hero. 
4. The hero just fulfilled goals to pass Level 2. The hero’s image and all other objects in the specific level scene are removed from the game play view, however, the hero’s information, including its game statistics, belongings such as weapons, and score it has, etc, will be saved. At the very beginning of Level 3, all the preserved information is loaded back into the game play view.
5. The hero killed an AI, which drops a treasure box behind. The hero touches the box and obtains the bonus object contained within, for example, a new weapon. The new weapon is then added to his belongings.
6. A hero slips down a steep hill covered with ice and cannot dodge an enemy so his health is damaged by enemy’s attack.
7. The hero’s sword powers up to have greater attack and defense after the hero eats a “sword-upgrade-powerup”.
8. A turtle gives birth to a new turtle after the hero attacks it. The turtle shell is able to give birth.
9. User drags character into editor canvas and its attributes propagates to the attribute’s view. The character snaps to the grid in the canvas.
10. The user zooms in to see details of the canvas more clearly. Zoom is centered on the mouse position.
11. The user drags slider to change attributes for the game objects.
12. The user changes the layer of the game objects in the “world”.
13. The user adds new characters by loading new images and set their components.
14. The user customizes heads-up display by dragging display types into the heads-up display campus.
15. The user adds or deletes a goal for the level.
16. The hero hits the power-up block from below. The ? power-up brick becomes a normal brick. And a Fire Flower is generated at the top of the brick.
17. The hero jumps to the Fire Flower and collides with the Fire Flower. The Fire Flower disappears and the hero gets a level up to Big Mario.
18. The hero collides with a Gold Coin. The Gold Coin disappears. 100-score is added to the game system.
19. The hero jumps to the top of a EvilMushroom. The EvilMushroom's health is reduced by the hero's attack. The EvilMushroom disappears and adds the score to the game system if its health drops below 0.
20. The hero collides with a EvilMushroom. The hero's health is reduced by the EvilMushroom's attack.
21.  If the hero's health is below 0, the hero's lives reduces 1. If the hero's lives is 0, Game Over is printed. If the hero's lives is not 0, a new hero is generated.
22. The hero moves to the edge of the brick. The hero will drop down from the edges of the brick and go down with respect to gravity.
23. The hero jumps into the valley and go outside of the screen. The hero dies no matter how much health he has.
24. The hero jumps to the top of the nails. The hero's health is reduced by the nail's attack and The hero becomes invulnerable for 2 seconds.
25. The hero goes to a ladder. The hero can climb up the ladder without the influence of the ladder.
26. The hero jumps into the magma. The hero dies directly.
27. The hero hits the brick from below. The brick breaks and a bonus score is added to the Game System.
28. The hero hits the winning flag, The hero wins and goes to the next level
29. The hero jumps into the water. The speed of moving decreases. And the hero won't be influenced by the gravity.
30. The hero is Level 3. And the player hits the Attack button. The weapon generates a bullet. The bullet won't be affected by the gravity. And reduce the health of anything it touches.
31. User sets a position goal to reach 1000m, once the user reaches that point, the game will move on to next level or, if there is no more level, tells the users they have won.
32. User sets a boss goal and adds a boss, once the user defeats the boss, the game will move on to next level or, if there is no more level, tells the users they have won
33. User sets a monster goal of a certain number, once the user kills that number of monsters, the game will move on to next level or, if there is no more level, tells the users they have won
34. User sets a score goal of a certain number, once the user reaches that score, the game will move on to next level or, if there is no more level, tells the users they have won
35. User sets a time goal of a certain number and another position goal 1000m. Once the time has ran out, if the user has reached the 1000m goal, the game will move on to next level or tells the users they have won; otherwise the user will lost.
36. User sets multiple goals: a time goal of a certain number and a position goal of 1000m and a monster goal of killing 30 monsters. Once the time has ran out, the user finished 1000m but didn't kill 30 monsters, the user will lost
37. User sets multiple goals: a position goal of 1000m and a boss goal of killing the boss. The user reaches the 1000m goal and successfully kills the boss, then the game will move on to next level or inform the user that they have won
38. User sets multiple goals: a position goal of 1000m and a score goal of 500, the hero died before he could reach either of the goal, the user loses. 
39. User sets multiple goals: the user sets a time goal and other goals such as position goal and monster goal. They finish all the goals before they reach the time goal. The user move on to next level or win the game.
40. User sets multiple goals: the user only sets a time goal. There is no "win" or "lose" technically, the score will be displayed.
