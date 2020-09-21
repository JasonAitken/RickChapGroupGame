#Rick Chap

Rick Chap is a game where you solve puzzles and avoid enemies to get to the exit. Collect the chips and use keys to unlock doors.

##Running the game
Start the game by running `nz.ac.vuw.ecs.swen225.a3.application.Main`. 

This should bring up the main menu. You can then play through the levels in order by pressing play. You can unlock further levels by completing the previous one.

##Playing the game
Use wasd or the arrow keys to move. Press space to pause, escape to resume, `ctrl + x` to exit, `ctrl + s` to save and exit, 
`ctrl + 1` in a game to play level 1, `ctrl + p` play the most recently unlocked level and `ctrl + r` resume a saved game. Reach the exit to win!

##Tiles
Tiles can be walls doors or even fire. They are things on or are the ground below your feet. These are only some examples. Watch out for other ones that might be lurking about.

###Door
There are four colors of door. Use a of the same color key to unlock them.

##Treasures
Treasures are items that can be found on the ground and are picked up by walking into them.

###Chips
Chips are the item required to open the socket. You can see how many you need in the tool bar

###Keys
Keys are used to unlock doors of the same color

###Boots
Pickup boots to wear them. They give you special bonuses!
 - Fire Boots allow you to survive on fire tiles.
 - Ice Boots allow you to ignore ice tiles.
 - Suction Boots allow you to ignore force tiles.
 - Flippers allow you to ignore water tiles.
 
 ##Replay
 `ctl + f` - choose where to save the replay. Then load from the main menu choose replay file and it will play
 
 ##Level Plugins
 Installing plug-ins (known as ”levels” from now) is as easy as placing the `*.zip` files in the `assets/zips/` directory. 
 
 You may also place `*.json` files in this directory but it is not recommended.