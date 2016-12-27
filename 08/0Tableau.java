package week08;

import java.util.Scanner;
import java.util.function.Function;

/**
 * Implementation of Young's tableau using linked cells.
 *
 * @author James Cross
 */
public class Tableau {

    /** The smallest value (or root) of this Tableau. */
    private Cell smallest;

    /**
     * Adds the given value to this tableau.
     * 
     * @param value the value to be added to this tableau.
     *            
     */

    public void addValue(Integer value) {
        // tableau is empty
        // smallest = top left square
        if (smallest == null) {
            smallest = new Cell(value);
            return;
        }

        Cell currentCell = smallest;
        //get the return value from addToRow
        Integer addtoRowReturnedValue = addToRow(currentCell, value);
        // while addtoRow has returned a value
        while (addtoRowReturnedValue != null) {    
            //deals with pointers when in last row
            if (currentCell.below == null) {
                Cell addCell = new Cell(addtoRowReturnedValue);
                currentCell.below = addCell;
                addCell.above = currentCell;
                addCell.right = null;
                addCell.left = null;
                addCell.below = null;
                return;

            } else {
                //iterate position down the column
                currentCell = currentCell.below;
            }
            //set result to the new currentCell and enter the returned value
            addtoRowReturnedValue=addToRow(currentCell,addtoRowReturnedValue);
        }

    }

    /**
     * Adds the given value to the row beginning with <code>curr</code>, keeping
     * the row in ascending order. If the value gets added to the end of the row
     * <code>null</code> is returned, otherwise the bumped value is returned.
     * 
     * @param curr
     *            the first cell in the current row.
     * @param value
     *            the value to be added to the row.
     * @return the bumped value, or null if the value was added to the end of
     *         the row.
     */

    protected Integer addToRow(Cell curr, int value) {

        //While current cell has something in it
        while (curr != null) {
            /*case 1: value found is greater than current value
             *if the value of current 
             *cell is greater than the value entered
             *swap the value found with value entered and
             *return the value found
             */
            if (curr.value >= value) {
                int returnValue = curr.value;
                curr.value = value;
                return returnValue;

            }
            /*case 2: the end of the row
             *if the value of current 
             *cell is greater than the value entered
             *swap the value found with value entered and
             *return the value found
             */
            if (curr.value < value) {// curr.value < value-----start
                // if we are at the end of the row
                if (curr.right == null) {// end of row-----start
                    //add a new cell to the right
                    Cell addCell = new Cell(value);
                    curr.right = addCell;
                    addCell.left = curr;
                    addCell.right = null;
                    // check above row pointers------start
                    if (curr.above == null) {
                        addCell.above = null;

                    } else if (curr.above.right == null) {
                        addCell.above = null;

                    } else {
                        addCell.above = curr.above.right;
                        curr.above.right.below = addCell;
                    }
                    // check above row pointers-------finish
                    //fix pointers for end of row
                    
                    return null;
                    // end of row-------finish
                } else {
                    //iterate current position to the right
                    curr = curr.right;
                }

            }

        } 

        return null;
    }

    /**
     * Interate through every cell in the tableau printing them using the given
     * function.
     * 
     * @param f
     *            a function which when applied to a cell should return an
     *            integer.
     */
    protected void print(Function<Cell, Integer> f) {
        for (Cell i = smallest; i != null; i = i.below) {
            for (Cell j = i; j != null; j = j.right) {
                System.out.printf("[%2d]", f.apply(j));
            }
            System.out.println();
        }
    }

    /**
     * Entry point of the program. Reads numbers from stdin and adds them to a
     * Tableau. If <code>p</code> is input then the tableau is printed. If
     * <code>c</code> is input then a count of the neighbours of each cell is
     * printed.
     * 
     * @param args
     *            command line arguments are not used.
     */
    public static void main(String[] args) {
        Tableau t = new Tableau();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                t.addValue(input.nextInt());
            } else {
                String command = input.next();
                if ("p".equals(command)) {
                    t.print(cell -> cell.value);
                } else if ("c".equals(command)) {
                    t.print(cell -> cell.neighbours());
                }
            }
        }
    }

    /**
     * A cell which holds a value and links to neighbouring cells.
     */
    protected static class Cell {
        /** The value held by this cell. */
        int value;
        /** The cell above this cell. */
        Cell above;
        /** The cell below this cell. */
        Cell below;
        /** The cell to the left of this cell. */
        Cell left;
        /** The cell to the right of this cell. */
        Cell right;

        /**
         * Creates a new cell with the given value.
         * 
         * @param value
         *            the value contained in this cell.
         */
        Cell(int value) {
            this.value = value;
        }

        /**
         * Returns how many horizontal and vertical (but not diagonal)
         * neighbours this cell has.
         * 
         * @return how many neighbours this cell has.
         */
        int neighbours() {
            int count = left != null ? 1 : 0;
            count += right != null ? 1 : 0;
            count += above != null ? 1 : 0;
            count += below != null ? 1 : 0;
            return count;
        }
    }
}
