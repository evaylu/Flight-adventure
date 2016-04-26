Created by:  Yunjie Lu 
Email: luyunjie.eva@gmail.com    

                                                     

Files for this project: (14 .java files, 1 folder for icons)
  EnemyPlane
  Main.java
  MainPanel.java
  MapColorComBox.java
  Mountain.java
  MyMap.java
  MyPlane.java
  PauseButton.java
  Plane.java
  PlaneControls.java
  PlaneDirectionButtons.java
  ResetButton.java
  ShowCoordsList.java
  SpeedScrollBar.java
  image/  -- images used in this program


To compile:                                                                     
  javac Main.java                                                                 
                                                                                
To run:                                                                         
  java Main



To play:
The black plane labeled "MyPlane" is controlled with widgets in "MyPlane 
Controls" panel. The goal is fly "MyPlane" to the center airport tower while 
avoiding the enemies, the colorful planes and the mountains. The initial score 
is 5000, and it decreases with time. When the plane run into any obstacle, the 
score will drop significantly. Player loses the game if the score fall below 0,
and wins the game if the destination tower is reached. The user can simply 
click on the "New Game" button to start a new game.

Use the Main Panel to modify the game setting:
- Click on Pause/Unpause button to pause/unpause the game.
- Click on the list to display/undisplay the x, y coordinates for airplanes.
- Change the canvas by selecting a color from the drop-down menu
- Click on the "New Game" button to start a new game (reset game state)

Other features:
- The game state: current score and the number of enemies is displayed at the 
bottom right corner of the game map.
- If the plane collide with an enemy plane or it runs into a mountain, a 
explosion graphical effect will be displayed on top of the plane.
- The score does not decrease for the initial 3 seconds, in order for the player
to get familiar with the interface




User Interface Design:
  
  In order for the control panel to better communicate information to the user 
implicitly, I put widgets with related function into groups. Particularly, I 
divided the control panel into to categories: one to manipulate the player's
plane, the other one to control the overall game state. 

  To limit user's interaction with the game to a reasonable state, I set up a 
scroll bar with a limited range for the plane speed, and also a combo box for 
the map background color options.

  I made the "New Game" button handy for the user to start a new game after one
ends, so that they don't have to quit the program and restart every time.




DESIGN

1. Outline of Inheritance Hierarchy 
   (FORMAT: superclass: { subclasses })

    JFrame: Main
    JPanel: MainPanel, PlaneControls
    JComponent: MyMap, Plane, Mountain
    JButton: PauseButton, ResetButton, PlaneDirectionButton
    JList<String>: ShowCoordsList
    JComboBox<String>: MapColorComBox
    JTextField: MyTextField
    Plane: MyPlane, EnemyPlane

2. Outline of Aggregation Hierarchy
    (FORMAT: the hierarchy is reflect by indentation, each object is indented by
     one more unit than the above object that contains it)

    Main 
        Container
            MyMap 
                List<Plane>
                List<Mountain>
                Timer
            PlaneControls
                JLabel(s)
                SpeedScrollBar
                PlaneDirectionButtons
            MainPanel
                JLabel(s)
                PauseButton
                ShowCoordsList
                MapColorComBox
                ResetButton

  
