/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;

/**
 *
 * @author kravtz
 */
public class City {
    double earth_eq_radius=6378.1370D;
    double convert_deg_to_radians=Math.PI/180D;
    double convert_km_to_miles=0.621371;
    private final String cityName; // represent name of the city 
    private final double coordinateX;
    private final double coordinateY;

    public City(String cityName, double coordinateX, double coordinateY) {
        this.cityName = cityName;
        this.coordinateX = coordinateX ;
        this.coordinateY = coordinateY;
    }

    public String getCityName() {
        return cityName;
    }


    public double getCoordinateX() {
        return this.coordinateX;
    }


    public double getCoordinateY() {
        return this.coordinateY;
    }

    
    //Eucledian distance , calculated distance between two cities
    public double distance(City city){
//        double deltaLongitude= city.getCoordinateY()- this.getCoordinateY();
//        double deltaLatidute= city.getCoordinateX()- this.getCoordinateX();
//        
//        double a= Math.pow(Math.sin(deltaLatidute/2D), 2D)+
//                  Math.cos(this.getCoordinateX())*Math.cos(city.getCoordinateX())*Math.pow(Math.sin(deltaLongitude / 2D),2D);
//        
//        return convert_km_to_miles * earth_eq_radius * 2D * Math.atan2(Math.sqrt(a),Math.sqrt(1D-a));
        double xDistance =Math.abs(this.coordinateX-city.getCoordinateX());
        double yDistance =Math.abs(this.coordinateY-city.getCoordinateY());
        double distance =Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));
        return distance;
    }
    
    @Override
    public String toString(){
        return this.cityName;
    }
}

