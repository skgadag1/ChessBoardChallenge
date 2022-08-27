# ChessBoardChallenge
This repository contains Java program to simulate the game of chess, which can be played using console/command line by 2 players.

Environment Setup to run java
-------------------------------
1. Download JDK 1.8 (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Install the JDK on your machine (installing in 'C:\Program Files\' folder is recommended)
3. Add below 2 environment variables 
   Variable Name : JAVA_HOME
   Variable Value : C:\Program Files\Java\jdk1.8.0_31

   Variable Name : PATH 
   Variable Value : %JAVA_HOME%\bin

Environment Setup to run Unit test using JUnit4
-------------------------------------------------
1. Download JUnit4 (https://search.maven.org/remotecontent?filepath=junit/junit/4.13-beta-3/junit-4.13-beta-3.jar)
2. Copy/Move the downloaded jar to 'C:\Program Files\JUnit'
3. Add below 2 environment variables 
   Variable Name : JUNIT_HOME
   Variable Value : C:\Program Files\JUnit
   
   Variable Name : CLASSPATH
   Variable Value : %CLASSPATH%;%JUNIT_HOME%\junit-4.13-beta-3.jar;.;

Steps to run the program
--------------------------
1. Open the project 'ChessBoardChallenge' in an IDE such Eclipse, IntelliJ, etc.
2. Right click on the /ChessBoradChallenge/src/StartTheGame.java -> Run As -> Java Application
3. Once the application is run, a set of instructions will be given.
4. Follow the instructions for better experience.

Steps to run the Unit tests
--------------------------
1. Open the project 'ChessBoardChallenge' in an IDE such Eclipse, IntelliJ, etc.
2. Right click on the /ChessBoradChallenge/src/test/TestRunner.java -> Run As -> Java Application
3. The above file will start executing unit test cases one by one with the dealy of 1.5 sec.

***Note: When application/test is run, maximize the console so that chess board representation can be seen better.

Following are the instructions and guide lines to play chess using command line
---------------------------------------------------------------------------------
1. This is a standard 8x8 Chess Board representation with following notations used:
	-> '*' -> a black square/cell
	-> 'O' -> a white square/cell
	-> 'R' -> white Rook    &  'r' -> black Rook
	-> 'N' -> white Knight  &  'n' -> black Knight
	-> 'B' -> white Bishop  &  'b' -> black Bishop
	-> 'Q' -> white Queen   &  'q' -> black Queen
	-> 'K' -> white King    &  'k' -> black King
	-> 'x' -> white Pawn    &  'y' -> black Pawn

2. To move the pawn enter the 'pawn name' as shown in the representation and 
   enter the square no. where you wnat the pawn to be moved

	In the below example given, 'move' is combination of 'Row no.'(3) and 'Col no.'(6)
	ex: pawn: N2
	    move:36

3. Apart from the above notations, rest of the notations used are self-explanatory

***Note: This is game is designed according to rules and standards of real world Chess, so always try to make valid moves,
	you will end up getting exceptions otherwise!

![alt text](https://raw.githubusercontent.com/skgadag1/ChessBoardChallenge/master/c001.PNG)
![alt text](https://raw.githubusercontent.com/skgadag1/ChessBoardChallenge/master/c002.PNG)
![alt text](https://raw.githubusercontent.com/skgadag1/ChessBoardChallenge/master/c003.PNG)
![alt text](https://raw.githubusercontent.com/skgadag1/ChessBoardChallenge/master/c004.PNG)
![alt text](https://raw.githubusercontent.com/skgadag1/ChessBoardChallenge/master/c005.PNG)


