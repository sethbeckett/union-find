import java.util.Random;

public class PercolationGrid {
    private int[][] percolationGrid;
    private int size;
    static String BLUE = "\u001B[34m\u2588\u2588\u001B[0m";
    static String WHITE = "\u2588\u2588";
    static String RED = "\u001B[31m\u2588\u2588\u001B[0m";
    String [] colorSet = {RED, WHITE, BLUE};
    int BLOCKED = 0;
    int OPEN = 1;
    int FULL = 2;
    int TOP;
    int BOTTOM;
    Random rand = new Random();

    /**
     * creates a square grid that can percolate
     * @param size the dimension of one side of the square
     */
    PercolationGrid(int size) {
        this.percolationGrid = new int[size][size];
        this.size = size;
        TOP = size * size;
        BOTTOM = size * size + 1;
    }

    /**
     * iterates through the grid, removing blocks until a path from top to bottom is found
     * prints out the grid once percolated as if water is poured from top
     */
    public void percolate() {
        UnionFind unionArray = new UnionFind(size * size + 2);
        //while loop to check if TOP and BOTTOM are connected
        boolean connected = false;
        int count = 0;
        while (!connected) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if (percolationGrid[x][y] == OPEN) continue;

            count++;
            percolationGrid[x][y] = OPEN;
            int cellIdx = getIdx(x,y);

            //check if on top row
            if (y == 0) unionArray.union(cellIdx, size*size);

            //check if on bottom row
            if (y == size - 1) unionArray.union(cellIdx, size*size+1);

            //check if there is cell above it's open
            if (y > 0 && percolationGrid[x][y-1] != 0) unionArray.union(cellIdx, getIdx(x, y-1));

            //check if has left neighbor and it's open
            if (x > 0 && percolationGrid[x-1][y] != 0) unionArray.union(cellIdx, getIdx(x-1,y));

            //check if has cell below and it's open
            if (y < size - 1 && percolationGrid[x][y+1] != 0) unionArray.union(cellIdx, getIdx(x, y+1));
            if (y < size - 1 && percolationGrid[x][y+1] != 0) unionArray.union(cellIdx, getIdx(x, y+1));

            //check if has right neighbor and it's open
            if (x < size - 1 && percolationGrid[x+1][y] != 0) unionArray.union(cellIdx, getIdx(x+1,y));

            //check if percolates
            if (unionArray.find(size * size) == unionArray.find(size * size + 1)) {

                //loop through and update the grid to show full cells
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (unionArray.find(getIdx(i,j)) == unionArray.find(TOP)) percolationGrid[i][j] = 2;
                    }
                }
                System.out.println("Iteration count for percolation: " + count);
                printGrid();
                connected = true;
            }

            //print every 50 iterations
            if (count % 50 == 0) {
                System.out.println("Current iteration count: " + count);
                printGrid();
            }
        }
    }

    /**
     * prints the grid with colors specified by the colorset indices
     */
    private void printGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(colorSet[percolationGrid[j][i]]); //switched bc x y are whack
            }
            System.out.println();
        }
    }

    /**
     * returns the array index based off of an x
     * @param x
     * @param y
     * @return
     */
    private int getIdx(int x, int y) {
        return size * x + y;
    }

    public static void main(String[] args) {
        PercolationGrid griddy = new PercolationGrid(20);
        griddy.percolate();
    }


}
