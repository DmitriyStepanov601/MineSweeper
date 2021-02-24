# MineSweeper

This application is an interpretation of the famous Minesweeper game.
A flat or three-dimensional playing field is divided into adjacent cells (squares, hexagons, cubes, etc.), some of which are "mined"; the number of "mined" cells is known. The goal of the game is to open all the cells that do not contain mines.

The player opens the cells, trying not to open the cell with a mine. After opening the cell with a mine, he loses. If there is no mine under the open cell, then a number appears in it, showing how many cells adjacent to the newly opened one are "mined"; using these numbers, the player tries to calculate the location of the mines, but sometimes even in the middle and at the end of the game, some cells still have to be opened at random. If there are also no mines under the neighboring cells, then some "non-mined" area opens up to the cells that contain numbers. The player can mark the "mined" cells so as not to accidentally open them. By opening all the "non-mined" cells, the player wins.

![minesweeper](https://user-images.githubusercontent.com/61186198/109040764-558caf80-76df-11eb-97e2-02b0e50ebaf5.gif)
