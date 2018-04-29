/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Simon
 */
public class Simulation {
    public void simulateTravillingProblem(){
        // as temp cooling down the distance dicreases
        //temp parameter specifies at what probablity we expect the solutions
        //cooling rate keep decreasing temperature at that rate
        double temperature= 100000;
        double coolingRate= 0.00003;
        MersenneTwister mt = new MersenneTwister(1);
        SingleTravel currentSolution=new SingleTravel();
        currentSolution.generateindividual();
 
        
        //outputs distances to 2 decimal pionts 
        DecimalFormat decim = new DecimalFormat("0.00");
        Double initialDistance= Double.parseDouble(decim.format(currentSolution.getDistance()));
        
        //System.out.println("Initial solution: "+ currentSolution.getDistance());
        //initializing the initial solution 
        System.out.println("Initial solution distance: "+ initialDistance);
        
        SingleTravel bestTravel =new SingleTravel(currentSolution.getTourList());
        ArrayList<String> bestPath = new ArrayList<>();
        //optimizing path
        while(temperature > 1){
            
            SingleTravel newSolution= new SingleTravel(currentSolution.getTourList());
            //chooses random position in the tour, chooses the tours and the travelles randomly 
            double travelPosition1 =(double) (newSolution.getTourList().size() * mt.nextDouble()); //Math.random()
            double travelPosition2 =(double) (newSolution.getTourList().size() * mt.nextDouble()); //Math.random()
            
            //keeps swapppin the cities 
            City firstCityToSwap = newSolution.getCity(travelPosition1);
            City secondCityToSwap = newSolution.getCity(travelPosition2);
            
            newSolution.setCity((int) travelPosition2, firstCityToSwap);
            newSolution.setCity((int) travelPosition1, secondCityToSwap);
            //keeps calculating the energies 
            //energy is the distance of the whole tour 
            double currentEnergy= currentSolution.getDistance();
            double neighbourEnergy=newSolution.getDistance();
            
            if(acceptanceProbability(currentEnergy, neighbourEnergy,temperature)> mt.nextDouble()){ //Math.random()
                currentSolution= new SingleTravel(newSolution.getTourList());
            }
            //keeps track of the best solution 
            if (currentSolution.getDistance() < bestTravel.getDistance() ){
                bestTravel= new SingleTravel(currentSolution.getTourList());
//                System.out.println(newSolution.getTourList());//correct solution 
                bestPath.clear();
                for(int i=0; i<newSolution.getTourList().size() ;i++){
                    bestPath.add(newSolution.getTourList().get(i).toString());
                }
                
                //bestPath.add(newSolution.getTourList().toString());
                //System.out.println("final "+bestPath);
            }
            
            //as temp keeps getting lower and lower the worse solutions are being rejected 
            temperature *= (1 - coolingRate);
        }
        
        
        Double finalDistance= Double.parseDouble(decim.format(bestTravel.getDistance()));
        
        
        if (bestPath.isEmpty()){
            System.out.println("Final solution distance: " + finalDistance);
            //rotating list to start from 1, still keeps the same order and distance 
            // rotates tour so it would display the tour from city 1 
            
           List<String> strings = bestTravel.getTourList().stream()
          .map(object -> Objects.toString(object, null))
          .collect(Collectors.toList());
           if(strings.indexOf("1")==0){
               System.out.println("Tour: "+ bestTravel.getTourList());
           }else{
               Collections.rotate(strings,strings.size()-strings.indexOf("1"));
               System.out.println("Tour: "+strings);
           }
           
        } else {
            System.out.println("Final solution distance: " + finalDistance);
           // bestPath.subList(0,bestPath.size()-2).clear();
            
            //System.out.println("Tour: "+bestTravel.getTourList()+ bestPath.get(bestPath.size()-1));
            if(bestPath.indexOf("1")==0){
               System.out.println("Tour: "+ bestTravel.getTourList());
           }else{
                
               Collections.rotate(bestPath, bestPath.size()-bestPath.indexOf("1"));
               System.out.println("Tour: "+ bestPath);
               
           }
            
            
        }
        
       
            
       
        //System.out.println("Tour: "+ bestTravel.getTourList()+"----"+bestTravel.getDistance());
    }
    
    public double acceptanceProbability(double energy, double newEnergy, double temperature){
        if (newEnergy < energy){
            return 1.0;
        }
        //if new energy is greatter then previous then there are higher probability that it is better solution 
        return Math.exp((energy-newEnergy)/temperature);
    }
}
