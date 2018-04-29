# Genetic Algorithm with Simulated Annealing for TSP 

##Solving Travels Man Sales Problems Using Genetic Algorithm (GA) and Simulated Annealing (SA) Algorithm Implemented in Java, Neatbeans Platform. 

Exlanation of Simulated Annealing on its own classes and code can be found at https://github.com/SimonKravtsov/simulatedAnnealing.git 

In the following algorithm it was decided to use the output of genetic algorithm of 150 generations, as an input for simulated annealing.  The results of that hasn’t shown to be much better that of simulate annealing on its own, however it has improved the stability of given results, the SA on its own would sometimes results with various ranges, GA helped to solve that problem, and by running algorithm several time it would give results around the same distance, showing the least distance the algorithm can obtain. The results can be improved if number of generation
are increased, the run time will be increased as well as the result. 

### Main.java

Reads dataset file and creates a new simulation

###Population class 

that is used to hold and sort a population of routes in the program 

###GeneticAlgorithm class 

is used for chromosome crossover selection and mutation, at total of 150 generation are made. 

The main principal of how the GA works is by these following process:

1. Initialization of route, the route is chosen randomly by using Collections.shuffle() in the route class.

2. Evaluating route and calculating a fitness for that route.

3. Crossover - selecting a subset from the first parent, and then adding that subset to the offspring. Any missing values are then adding to the offspring from the second parent in order that they are found.

4. mutation – adding a bit of randomness to the algorithm, it is swapping city locations, leaving the same values just in different order.

5. Selecting fitter routes for next generation

6. Going to the next generation, starting from step 2 until termination is reached. In this program a number 30 generations for obtaining result for tsp problem .

After the results are inputed to SA Algorithm, and final results are given

## How to Run a Project
After clonning the repository, project folder can be opened with Netbeans. 
```
git clone https://github.com/SimonKravtsov/annealingGenetic.git
```
Program is run from Main.java class where different csv files with different number of cities can to a solition from the 
algorithm. Other files can be downloaded from: http://www.math.uwaterloo.ca/tsp/data/ to try out and compare algorithm to 
other results



