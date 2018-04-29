/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon
 */
public class SingleTravelManager {
//keeps track of the destination cities and number of cities 
// simply stores it in the array
    public static List<City> destinationCities = new ArrayList();
    public static int numOfCities;
    
    public static void addCity(City city){
        SingleTravelManager.destinationCities.add(city);
        SingleTravelManager.numOfCities++;
    }
    
    public static City getCity(int index){
        return SingleTravelManager.destinationCities.get(index);
    }
}
