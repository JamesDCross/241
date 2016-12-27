
package week07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.*;



/**
 * Class to match randomly chosen letters from the firstletters txt file
 * with the relevant continuations String to create "words".
 * 
 *
 * @author James Cross
 */
public class DigramGenerator implements WordGenerator{

    /**
     * Declares a random object to use in the constructor.
     *           
     */

    private  Random random;
    /**
     * Declares a char array to hold firstLetters characters.
     *           
     */

    private char [] firstLetters;
    /**
     * Declares a String array to hold continutations strings.
     *           
     */
    private String [] continuations;
    /**
     * Declares an int to hold the randomly chosen index of
     * firstLetters.
     *           
     */
    private int riFirstLetters;
    /**
     * Declares an int to hold the randomly chosen index of
     * continutations.
     *           
     */
    private int  indexContinuations;
    /**
     * Declares a char array to hold the continuations string
     * convertered to a char array.
     *           
     */
    private char[] continuationsString;

    
   
    /**
     * Chooses a random index from the firstLetters
     * array.
     *
     *
     * @return the chosen index    
     *           
     */
    
    public double chooseIndex() {
        riFirstLetters = random.nextInt(firstLetters.length);

        return riFirstLetters;

    }


    
    /**
     * Constructor that makes the seed from the main class
     * the same as the frequency generator object for checks.
     * Also performs the scan for the txt files.
     *
     * @param  r takes in a random object          
     */
    
    public DigramGenerator(Random r) {
        random = r;
        
        this.scanFirstLetter();
       
        this.scanContinuations();
      
    }

    /**
     * Makes a word by combining a randomly chosen char from the
     * firstLetters array and then a randomly chosen char from the
     * matching continutaions string. Then the third char in the
     * word finds its index in continuations from the second char
     * in the word (which is now acting as the first letter in the
     * loop (for index reference purposes).
     * 
     *
     * @param  n the length of the string we are creating.
     *
     * @return s returns a string which is the concatenation of the
     *  first letter char randomly chosen and the relevant letters
     *  chosen from the continuations txt file.
     *           
     */
    
    public String nextWord(int n) {
        this.chooseIndex();
        String cString = "";
            
        char  firstLetter = firstLetters[riFirstLetters];

        String s = Character.toString(firstLetter);
        
        for(int i = 1; i < n; i++) {
            cString  = continuations[firstLetter - 'a'];
            continuationsString = cString.toCharArray();
            indexContinuations = random.nextInt(continuationsString.length);
            char secondLetter = continuationsString[indexContinuations];
            //didnt need a stringbuilder at all
            s += secondLetter;
            
            firstLetter = secondLetter;

        }
        
        return s;
    }

   
    /**
     * Scans the txt file into an array of chars.
     * 
     *
     *
     * @return the populated firstLetters array.  
     *           
     */
    public  char [] scanFirstLetter(){
    
        File file = new File("first-letters.txt");
       
        String c = "";
        try{
     
            Scanner scan = new Scanner(file);
            
            c = scan.nextLine();
            
            firstLetters = c.toCharArray();
       
            
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
          
        return firstLetters;
    }

    /**
     * Scans the txt file into an array of Strings.
     * 
     *
     *
     * @return the populated continuations array.  
     *           
     */

    public  String [] scanContinuations(){
    
        File file = new File("continuations.txt");
        final int arrayLength = 26;
        continuations = new String[arrayLength];
   
        try{
     
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
        
                for (int i = 0; i < continuations.length;i ++){
                    continuations[i] = scan.nextLine();
                }
        
            } 
            
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        
        return continuations;
    }

}




    

