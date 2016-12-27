package week07;

import java.util.*;
import java.io.*;



/**
 * Class to match letter frequencies to how they appear in the
 * english language.
 *
 * @author James Cross
 */
public class FrequencyGenerator implements WordGenerator {


    /**
     * Declares an  array to store the frequencies txt file.
     *           
     */
    
    
    private double [] frequencies;
    
    /**
     * Declares a random object for use in the constructor.
     *           
     */
 
        
    private Random random;

    /**
     * Constructor that makes the seed from the main class
     * the same as the fruency generator object for checks.
     * Also performs the scan for the frequencies array.
     *
     * @param  r takes in a random object          
     */
    
    public FrequencyGenerator(Random r){
        random = r;
        this.scan();
    }
    
 
    /**
     * Takes the chosen indexes from frequencies and uses them like
     * integers to add to the 'a' char then builds these into a string.
     *
     * @param  n the length of the string we are creating.
     *
     * @return the chars as a single String    
     *           
     */
    
    public String nextWord(int n) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char c = (char) ('a' + this.chooseIndex());
            result.append(c);
        
        }
        
       
        return result.toString();
       
    }


    
    /**
     * Chooses a random index according to the weight of
     * the index (between 0 and 1).
     *
     *
     * @return the chosen index    
     *           
     */
    
    public double chooseIndex(){
        int  i = 0;
        double r;
        r = random.nextDouble();
        while(r > frequencies[i]){
            r = r - frequencies[i];
            i++;

        }
        return i;

    }
   
    /**
     * Scans the txt file into an array of doubles.
     * Sets the  array to the correct size.
     *
     *
     * @return the frequencies populated array.   
     *           
     */
    
    public  double[] scan(){                           
        File file = new File("letter-frequencies.txt");
        final int arrayLength = 26;
        frequencies = new double[arrayLength];
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                
                for (int i = 0; i < frequencies.length ; i++){
                    frequencies[i] = sc.nextDouble();
                }
            }
            
        }catch (FileNotFoundException e){
            System.out.println(e);
 
        }
        
        return frequencies;
    }
    

}
