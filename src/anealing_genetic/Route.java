/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author kravtz
 */
public class Route {
    private boolean isFtinessChange =true;
    private double fitness = 0;
    private ArrayList<City> cities =new ArrayList<>();
    
    public Route(GeneticAlgorithm geneticAlgorithm){
        geneticAlgorithm.getInitialRoute().forEach(x-> cities.add(null));
    }
    public Route(ArrayList<City> cities){
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
    }
    public ArrayList<City> getCities() {
        isFtinessChange =true;
        return cities;
    }
    
    public double getFitness(){
        if (isFtinessChange ==  true){
            fitness = (1/calculateTotalDistance())*10;
            isFtinessChange = false;
        }
        return fitness;
    }
    
    public double calculateTotalDistance(){
        int citiesSize = this.cities.size();
        return (int) (this.cities.stream().mapToDouble(x -> {
            int cityIndex = this.cities.indexOf(x);
            double returnValue=0;
            //calculated distance between cities 
            if(cityIndex < citiesSize -1) returnValue = x.distance(this.cities.get(cityIndex + 1));
            return returnValue;
        }).sum()+ this.cities.get(0).distance(this.cities.get(citiesSize-1)));//summing all all distance between cities up and adding distance between the last citie and the original city
    }
    
    @Override
    public String toString(){
        return Arrays.toString(cities.toArray());
    }
    
    
}
