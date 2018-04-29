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
public class Population {
    private ArrayList<Route> routes =new ArrayList<>(GeneticAlgorithm.POPULATION_SIZE);
    
    public Population(int populationSize, GeneticAlgorithm geneticAlgorithm){
        IntStream.range(0, populationSize).forEach(x-> routes.add(new Route(geneticAlgorithm.getInitialRoute())));
    }
    
    public Population(int populationSize, ArrayList<City> cities){
        IntStream.range(0, populationSize).forEach(x-> routes.add(new Route(cities)));//shuffling in Route class
    }

    Population() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }
    
    
    public void sortByFitness(){
        routes.sort((route1, route2) -> {
            int flag = 0;
            if (route1.getFitness()>  route2.getFitness()) flag= -1;
            else if (route1.getFitness()<route2.getFitness()) flag = 1;
            return flag;
        });
    }
}
