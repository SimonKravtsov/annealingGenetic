/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anealing_genetic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author kravtz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<City> initialRoute =new ArrayList<>();
    
    public static void main(String[] args) {
       try {
            Scanner read =new Scanner(new File("test_set3new.txt"));
            //data files 
            //test_set1.txt
            //test_set2.txt
            //test_set3.txt
            
            int i=0;
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] lineArray=line.split(" ");
                initialRoute.add(new City (lineArray[0],Double.parseDouble(lineArray[1]),Double.parseDouble(lineArray[2])));
                
            }
            
            
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        final long startTime1 = System.nanoTime();
        
        Main driver =new Main();
        Population population= new Population(GeneticAlgorithm.POPULATION_SIZE,driver.initialRoute); 
        population.sortByFitness();
        GeneticAlgorithm geneticAlgorithm=new GeneticAlgorithm(driver.initialRoute);
        
        final long endTime1 = System.nanoTime();
        
        int generationNumber =0;
        driver.printHeading(generationNumber++);
        driver.printPopulation(population);
        
        final long startTime2 = System.nanoTime();
        while (generationNumber < GeneticAlgorithm.NUM_OF_GENERATIONS)
        {
         driver.printHeading(generationNumber++);
         
         population=geneticAlgorithm.evolve(population);
         population.sortByFitness();
         
         driver.printPopulation(population);
        }
        final long endTime2 = System.nanoTime();
        System.out.println("Best Route Found so far GA" + population.getRoutes().get(0));
        System.out.println("Final distance of Genetic algorithm: "+String.format("%.2f", population.getRoutes().get(0).calculateTotalDistance()));
        
        
        //Starting Simulated Annealing using ouput from genetic algorithm 
        final long startTime3 = System.nanoTime();
        for (int i=0; i < population.getRoutes().get(0).getCities().size();i++){
            
            SingleTravelManager.addCity(new City(population.getRoutes().get(0).getCities().get(i).toString(),population.getRoutes().get(0).getCities().get(i).getCoordinateX(),population.getRoutes().get(0).getCities().get(i).getCoordinateY()));
            
        }
        Simulation simulation =new Simulation();
        simulation.simulateTravillingProblem();
        final long endTime3 = System.nanoTime();
        
        System.out.println("Total execution time: " + ((endTime1 - startTime1) +(endTime2 - startTime2)+(endTime3 - startTime3))/1000000000.0+" Seconds");
    }
     public void printPopulation(Population population){
        population.getRoutes().forEach(x -> {
            System.out.println(Arrays.toString(x.getCities().toArray())+" | "+ 
                    String.format("%.4f", x.getFitness())+ "  |  "+ String.format("%.2f", x.calculateTotalDistance()));
        });
        System.out.println("");
    }
    
    public void printHeading(int generationNumber){
        System.out.println(">Generation # "+ generationNumber);
        String headingColumn1="Route";
        String remainingHeadingColumns= "Fitness  |  Distance ";
        int cityNamesLength=0;
        for(int x=0; x< initialRoute.size(); x++) cityNamesLength +=initialRoute.get(x).getCityName().length();
        int arrayLength= cityNamesLength + initialRoute.size()*2;
        int partialLenght= (arrayLength - headingColumn1.length())/2;
        for (int x=0; x<partialLenght;x++)System.out.print(" ");
        System.out.print(headingColumn1);
        for(int x=0; x<partialLenght;x++)System.out.print(" ");
        if((arrayLength%2) == 0)System.out.print(" ");
        System.out.println(" | "+ remainingHeadingColumns);
        cityNamesLength +=remainingHeadingColumns.length()+3;
        for (int x=0; x< cityNamesLength+initialRoute.size()*2; x++)System.out.print("-");
        System.out.println("");
    }
    
    
}
