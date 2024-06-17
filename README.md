# Project Description
This Android application fetches a specified number of coordinate points (x, y) from a server and displays the received response as a table and a graph.

# Tech stack
Kotlin + Coroutines + Retrofit + Cicerone + Compose(partially)

# Features
### Main Screen

- Display of informational text block. [✔️] 
- Input field for the number of points. [✔️] 
- One button labeled "Go". [✔️]️ 

### Result Screen
- On button click, a request is made to the server API with the parameter of the requested number of points (count). [✔️]
- Handle incorrect number of requested points. [✔️]
- Handle server failures. [✔️]
- Display a table with the received coordinate points. [✔️]
- Display a graph with points connected by straight lines. [✔️]
- Points on the graph should be plotted in increasing order of the x-coordinate. [✔️] 

### Additional Features (Optional)
- Allow user to zoom in and out of the graph. [ ️]
- Connect points with a smoothed line instead of a straight line. [ ️]
- Support both portrait and landscape screen orientations. [✔️]️
- Save the graph image to a file. [ ️] 
