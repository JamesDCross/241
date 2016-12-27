package week11;

import java.util.Scanner;

import static week11.LinkedBST.Direction.*;

/**
 *  A binary tree implementation using links. We assume that the tree
 *  is not to store 'null' elements. In particular if the root node
 *  *is* null then the tree is empty. This can only occur if a tree
 *  is initially constructed with no arguments, or if we remove the
 *  only element from a tree.
 *
 *  @author James Cross 1350026
 */
public class LinkedBST<T extends Comparable<T>> {

    /** The element held at the root of this tree. */
    //root
    private T root;

    /** The left subtree of this tree. */
    //left child
    private LinkedBST<T> left;

    /** The right subtree of this tree. */
    //right child
    private LinkedBST<T> right;

    /**
     *  Creates a BST with the given value.
     *
     * @param value to store at the root of this LinkedBST.
     */
    public LinkedBST(T value) {
        root = value;
        left = null;
        right = null;
    }
   
    /** 
     *  Creates a new empty BST.
     */
    public LinkedBST() {
        this(null);
    }

    /**
     *  Adds an element to this BST if it isn't already there.
     *
     * @param element an element to be added.
     */
    public void add(T element) {
        if (root == null) {
            root = element;
        }
        int comparison = root.compareTo(element);
        if (comparison > 0) {
            if (left == null) {
                left = new LinkedBST<T>(element);
            } else {
                left.add(element);
            }
        } else if (comparison < 0) {
            if (right == null) {
                right = new LinkedBST<T>(element);
            } else {
                right.add(element);
            }
        }
    }

    /**
     *  Returns the height of this tree.
     *
     * @return the height of this tree.
     */
    public int height() {
        //can't except -1 as empty height
        if (root==null){
            
            return 0;
        }
        int resultLeft = 0;
        //note: has to be plus one on each sub-tree so we
        //get the root node added from each sub-tree
        if (left != null) {
            
            resultLeft += left.height()+1;
        }
        int resultRight = 0;
        if (right != null) {
            
            resultRight += right.height()+1;
        }
        
        return Math.max(resultLeft,resultRight);
    }

    /**
     *  Searches for the given target within this tree.
     *
     * @param target 
     * @return true if target is found, otherwise false.
     */
   
    public boolean search(T target) {
        if(root==null){
            
            return false;
        }
     
        if(root==target){
            
            return true;
        }
        if (left != null && left.search(target)) {
            
            return true;
        }
        if (right != null && right.search(target)) {
            
            return true;
        }
        return false;

    }
  
    /**
     *  Returns the size of this BST.
     *
     * @return the size of this BST.
     */
    public int size() {
        if (root==null) {
            return 0;
        }
        
        int result = 1;
        if (left != null) {
            
            result += left.size();
        }
        if (right != null) {
            
            result += right.size();
        }
        return result;

    }

    /**
     *  Returns how many elements are greater than or equal to the
     *  parameter <code>low</code>.
     *
     * @param low the lower bound to use when counting elements.
     * @return how many elements are greater than or equal to the
     *         parameter <code>low</code>.
     */
   
    
    public int sizeAbove(T low) {
        int count = 0;
        
        if (root== null){
            return 0;
            //return root.sizeAbove(0)
        }else if (low.compareTo(root) == 0) {
            
            count++;
        }else if(low.compareTo(root) < 0){
            
            count++;
        } 
        //if the root is less than, then only look on the right side
        if (left != null) {
            //use return because we want to return the count
            count +=  left.sizeAbove(low);
        }
        if (right != null) {
            //return creates the end of the loop
            count +=  right.sizeAbove(low);
        }
        
        return count;
    }
    
       

    /**
     *  Returns how many elements are less than the parameter
     *  <code>high</code>.
     *
     * @param high the element to compare when counting elements.
     * @return how many elements are less than the parameter
     *         <code>high</code>.
     */
    public int sizeBelow(T high) {
        int count = 0;
      
        if (root== null){
            return 0;
        }else if(high.compareTo(root) > 0){
            count++;
        } 
        if (left != null) {
           
            count +=  left.sizeBelow(high);
        }
        if (right != null) {
            count +=  right.sizeBelow(high);
        }
       
        return count;
    }
    
    /**
     *  Returns how many elements are greater than or equal to the
     *  parameter <code>low</code> and less than the parameter
     *  <code>high</code>.
     *
     * @param low the lower bound to use when counting elements.
     * @param high the upper bound to use when counting elements.
     * @return how many elements are between low (inclusive) and
     *         high (exclusive).
     */
    public int sizeBetween(T low, T high) {
        int lowT =  sizeBelow(high);
        int highT = sizeAbove(low);
        int c = lowT + highT - size();
        if(c < 0){
            
            return 0;
        }
        
        return(c);
        
    }

    /**
     *  Removes the given element from this tree if it is present.
     *
     * @param element the element to remove.
     */
    //assessed?
    public void remove(T element) {
        // implement this method from the lectures if you
        // want to do the extension exercises
    }

    /** The direction used when creating a representation of this tree. */
    enum Direction {LEFT, RIGHT, NO};
    
    /**
     *  Recursively generates a representation of this tree.
     *
     * @param curr the current line being generated.
     * @param dir the direction of the last link followed.
     * @param result the representation generated so far.
     * @return a representation of this tree.
     */
    public StringBuilder str(String curr, Direction dir, StringBuilder result) {
        if(right != null) {
            right.str(curr + (dir == LEFT ? "│  " : "   "), RIGHT, result);
        }
        if (root != null) {
            result.append(curr + (dir == RIGHT ? "┌─ " :
                                  dir == LEFT ? "└─ " : "  ") + root + "\n");
        }
        if(left != null) {
            left.str(curr +  (dir == RIGHT ? "│  " : "   "), LEFT, result);
        }
        return result;
    }

    @Override
    public String toString() {
        return str("", NO, new StringBuilder()).toString();
    }

    /**
     *  Entry point of program (used for testing).
     *  Valid commands are:
     *  <pre>
     *  a (add) item(s)             - calls add with each item
     *  f (find) item               - calls search with item
     *  p (print)                   - calls toString
     *  h (height)                  - calls height
     *  s (size)                    - calls size
     *  sa (sizeabove) low          - calls sizeAbove(low)
     *  sb (sizebelow) high         - calls sizeBelow(high)
     *  si (sizeinbetween) low high - calls sizeBetween(low,high)
     *  </pre>
     *  Return values of methods are printed to stdout.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        LinkedBST<String> tree = new LinkedBST<>();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine());
            if (line.hasNext()) {
                String command = line.next();
                switch (command) {
                    case "a": case "add":
                        while (line.hasNext()) {
                            tree.add(line.next());
                        }
                        break;
                    case "f": case "find":
                        if (line.hasNext()) {
                            System.out.println(tree.search(line.next()));
                        }
                        break;
                    case "p": case "print":
                        System.out.print(tree);
                        break;
                    case "h": case "height":
                        System.out.println(tree.height());
                        break;
                    case "s": case "size":
                        System.out.println(tree.size());
                        break;
                    case "sa": case "sizeabove":
                        if (line.hasNext()) {
                            String low = line.next();
                            System.out.println(tree.sizeAbove(low));
                        }
                        break;
                    case "sb": case "sizebelow":
                        if (line.hasNext()) {
                            System.out.println(tree.sizeBelow(line.next()));
                        }
                        break;
                    case "si": case "sizeinbetween":
                        if (line.hasNext()) {
                            String low = line.next();
                            if (line.hasNext()) {
                                System.out.println(tree.sizeBetween
                                                   (low, line.next()));
                            }
                        }
                        break;
                    default:
                        System.err.println("Unknown command: " + command);
                }
            }
        }
    }

}


