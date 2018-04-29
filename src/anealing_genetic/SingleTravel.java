/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Simon
 */
public class SingleTravel {
    
    //N cities -> N! permutations 
    
    private List<City> tourList;
    private double distance;//represent distance if the whole tour
    //keeps going from city a to b to c .... and it keeps adding the distance between each pair of cities
    // after it will be summed and it will be final distance 
    public SingleTravel(){
        this.tourList = new ArrayList();
        
        for (int i=0; i<SingleTravelManager.numOfCities; i++){
            tourList.add(null);//keep instanciating the tourlist 
        }

    }
    
    public SingleTravel(List<City> tourList){
        this.tourList=tourList;
    }
   
    public List<City> getTourList(){
        return tourList;
    }
    
    public void setTourList(List<City> tourList){
        this.tourList=tourList;
    }
    
    //at the begining of program, this is the solution and in the algorith we keep enchacing this initial solution 
    //keeos iterating throu the number of cities and set the city to the given city 
    public void generateindividual(){
        for(int i=0; i<SingleTravelManager.numOfCities;i++){
            setCity(i,SingleTravelManager.getCity(i));
        }//randome tour 
        
        // generates random tour!!!)
        //Collections.shuffle(this.tourList); No need since we using output from genetic algorithm 
        
        
        
        
        System.out.println("Initial Solution from GA"+this.tourList);
        
    }
    

    public City getCity(double index){
        return this.tourList.get((int) index);
    }
    
    public void setCity(int cityIndex, City city) {
        this.tourList.set(cityIndex,city);
        this.distance=0;
    }
    
    // gets distance of the single given tour 
    public double getDistance(){
        if (this.distance==0){
            double tourDistance =0.0;
            double size=this.tourList.size();
            //keep iterating throw the tourlist size 
        
            for(int i=0; i<size;i++){
                
                City fromCity=getCity(i);
                City destinationCity;
                //needs to start at city and end up in the same city at the end  
                if(i+1 <this.tourList.size()){
                    destinationCity=getCity(i+1);
                }else{
                    destinationCity=getCity(0);
                }
                tourDistance += fromCity.distance(destinationCity);
            }
            
            
            this.distance = tourDistance;
        }
        
        return this.distance;
    }
    
    @Override
    public String toString(){
        String s="";
        double size=this.tourList.size();
        for (int i=0; i<size;i++){
            s+=getCity(i)+" - ";
        }
        
        return s;
    }
}
