umer waleed 
d18124701
dt228 year 2 
OOP


the link to my youtube video is https://www.youtube.com/watch?v=GpcxmihmH6g&feature=youtu.be  

for this assignment the ide i have used is java eclipse and you have to import whole folder in order to run it

you can run the following files:
ColorsGame.java
SpinVisual.java
Main.java

Java
  |
  V
 src
  |
  V
example
  |
  V
AudioBandsVisual.java
-- This file would render the audio band rectangles at the bottom of the applet.

Color.java
-- File that stores rgb value of color function to use in other java files.

ColorsGame.java
-- Contains main function in this file. Used to play the applet. Also contains other processing functions like setup and draw to run the applet. The main Elements are made in this file i.e. The moving circles and its functionality like movability and clickability. 

MyVisual.java
-- This file makes objects of other java files like WaveForm, AudioBandsVisual, RedWhiteCircleVisual, ZoomSquareVisual and SpinVisual and can be run from ie.tudublin/Main.java.

RedWhiteCircleVisual.java
-- Functionality and motion control for red color and white color circles.

SpinVisual.java
-- A file that consist of main function (you can run it) and it will show some boxes spinning.

WaveForm.java
-- Place where the implementation of the sound wave exist. Shown in ColorsGame.java, the middle of the applet.

ZoomSquareVisual.java
-- Made for zooming in some rectangles. Some objects of this are placed in SpinVisual.java.

ie.tudublin
  |
  V
Visual.java
-- This file contains Calculations of frequencies, fft and amplitude. Also used for loading audio functions. All the sound engineering complexity sits in this file. Later they are used in other file.

VisualException.java
-- Throws exception if something is not working


Instructions 

When you are going to run this java file with the name of ColorsGame.java a processing windows is going to open once the window is open the a message will be written in the middle of the processing windows which says “press space bar to start the game” obviously to begin we have to press the space bar and then the game begin so once game started you will see some colours ball and out of all there will be some which has numbers written in the middle of those balls so you have to click in the ball which has numbers written in them on the top right corner round number is mentioned that in which round are you in the game also takes input from the mic like while game is running if there is some sound is coming balls moving speed will increase and some design will appear in the processing windows to speed of balls and design is directly proportional to the volume of the sound if it’s high sound  more speed and design and if it’s less then other way around and while playing the game you can also pause the game by pressing space bar and then continue again with the space bar