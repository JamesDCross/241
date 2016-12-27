package week03;

/**
 *  A recursive representation of a tower of blocks.
 *
 * @author Michael Albert
 */
public class Tower{
  

    /** The top block. */
    private char top;
    
    /** The rest of the tower. */
    private Tower rest;

    /**
     * Creates a new empty Tower.
     */
    public Tower() {
        this.top = ' ';
        this.rest = null;
    }
    
    /**
     *  External classes can only create empty towers and manipulate
     *  them using public methods, because this constructor is
     *  private.
     * @param top the top block in this tower
     * @param rest the rest of the tower
     */
    private Tower(char top, Tower rest) {
        this.top = top;
        this.rest = rest;
    }

    /**
     *  Returns true if this tower is empty, otherwise false.  Empty
     *  towers are represented with the top block being a space
     *  character.
     * @return whether the tower is empty or not.
     */
    
    public boolean isEmpty() {
        return top == ' ';
    }

    /**
     *  Counts the blocks in the tower recursively.
     *  Base case, if there are no towers (blocks) return 0.
     *  Return 1 if there is only one block in the tower.  
     * @return rest.height() + 1 recursively add 1, counting the blocks
     *  in the tower.
     */

    public int height(){
        if (this.isEmpty()){
            return 0;
        }
        
        if (rest == null){
            
            return 1 ; 
            
        }
        
        return rest.height()+ 1;
        
    }

    /**
     * Counts the occurrences of a given character in the tower.  
     * Return 0 if there are no towers (blocks) base case.
     * Return rest.count(c) + 1 recursively move through the tower add
     * 1 if the character is found.
     * @param  c the character we are searching for in the tower.
     * @return rest.count(c) recursively move through the tower return
     * nothing as the character is not found.
     *
     */
    
    public int count(char c){

        if(this.isEmpty()){
            return 0;
        }

        if(top == c ) {
            return rest.count(c)+ 1;
        } else {
            return rest.count(c);
            
        }

        
    }

    /**
     *  Creates a new tower by adding the given block to the top of
     *  this tower.
     * @param block a block to add to the top of this tower.
     * @return a new tower created by adding a block to the top of
     * this tower.
     */
    public Tower add(char block) {
        return new Tower(block, this);
    }

}
