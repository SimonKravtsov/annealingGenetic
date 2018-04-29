/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author kravtz
 */
public class GeneticAlgorithm {
    //chromozone is a route and gene is city in a route 
    public static final double MUTATION_RATE= 0.25;
    //used for route cross over selection and chromozone crossover selection 
    public static final int TOURNAMENT_SELECTION_SIZE =3;
    public static final int POPULATION_SIZE = 8;
    public static final int NUM_OF_ELITE_ROUTES=1;
    public static final int NUM_OF_GENERATIONS = 30;
    private ArrayList<City> initialRoute=null;
    
    public GeneticAlgorithm(ArrayList<City> initialRoute) {
        this.initialRoute = initialRoute;
    }

    public ArrayList<City> getInitialRoute() {
        return initialRoute;
    }
    
    public Population evolve(Population population){
        return mutatePopulation(crossoverPopulation(population));
    }
    
    Population crossoverPopulation (Population population){
        Population crossoverPopulation = new Population(population.getRoutes().size(),this);//this size of algorithm in this genetic algorithm intance (this)
        IntStream.range(0, NUM_OF_ELITE_ROUTES).forEach(x -> crossoverPopulation.getRoutes().set(x, population.getRoutes().get(x)));
        IntStream.range(NUM_OF_ELITE_ROUTES, crossoverPopulation.getRoutes().size()).forEach(x -> {
            Route route1 = selectTournamentpopulation(population).getRoutes().get(0);
            Route route2= selectTournamentpopulation(population).getRoutes().get(0);
            crossoverPopulation.getRoutes().set(x, crossoverRoute(route1, route2));
        });
        return crossoverPopulation;
    }
    
    Population mutatePopulation(Population population){
        population.getRoutes().stream().filter(x -> population.getRoutes().indexOf(x) >= NUM_OF_ELITE_ROUTES).forEach(x -> mutateRoute(x));
        return population;
    }
    
    Route crossoverRoute(Route route1, Route route2){
        Route crossoverRoute=new Route(this);
        Route tempRoute1 = route1;
        Route tempRoute2 = route2;
        if(Math.random() < 0.5){
            tempRoute1 = route2;
            tempRoute2 = route1;
        }
        for (int x =0; x < crossoverRoute.getCities().size()/2; x++){
            crossoverRoute.getCities().set(x,tempRoute1.getCities().get(x));
        }
        return fillNUllsInCrossoverRoute(crossoverRoute, tempRoute2);
    }
    
    private Route fillNUllsInCrossoverRoute(Route crossoverRoute,Route route){
        route.getCities().stream().filter(x -> !crossoverRoute.getCities().contains(x)).forEach(cityX ->{
            for (int y=0; y<route.getCities().size();y++){
                if(crossoverRoute.getCities().get(y) == null){
                    crossoverRoute.getCities().set(y,cityX);
                    break;
                }
            }
        });
        return crossoverRoute;
    }
    
    //routemutation will output original and mutated path
    Route mutateRoute(Route route){
        route.getCities().stream().filter(x -> Math.random() < MUTATION_RATE).forEach(cityX ->{
            int y = (int)(route.getCities().size()*Math.random());
            City cityY= route.getCities().get(y);
            route.getCities().set(route.getCities().indexOf(cityX),cityY);
            route.getCities().set(y, cityX);
        });
        return route;
    }
    
    Population selectTournamentpopulation(Population population){
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE,this);
        IntStream.range(0, TOURNAMENT_SELECTION_SIZE).forEach(x -> tournamentPopulation.getRoutes().set( 
                x, population.getRoutes().get((int)(Math.random()*population.getRoutes().size()))));
        tournamentPopulation.sortByFitness();
        return tournamentPopulation;
    }
    
}
