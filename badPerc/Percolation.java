//import java.util.Random;
//
//public class Percolation {
//    static String BLUE = "\u001B[34m\u2588\u2588\u001B[0m";
//    static String WHITE = "\u2588\u2588";
//    static String RED = "\u001B[31m\u2588\u2588\u001B[0m";
////    String[] colorBlocks = { RED, WHITE, BLUE};
////    int FULL = 2;
////    int OPEN = 1;
////    int BLOCKED = 0;
//    static Random rand = new Random();
//
//    /**
//     * prints out a 20x20 grid showing percolation from the top
//     * @param grid the UnionFindArray to show in grid form (first cell is empty for coloring)
//     */
//    public static void printGrid(UnionFind grid) {
//        for (int cell = 1; cell < 401; cell++) {
//            if ((cell - 1) % 20 == 0) System.out.println();
//            if (grid.arr[cell] == 0) { //leave grid.arr[0] empty so that nothing will point to it
//                System.out.print(RED + " ");
//            }
//            else if (grid.find(cell) == grid.find(401)) {
//                System.out.print(BLUE + " ");
//            }
//            else System.out.print(WHITE + " ");
//        }
//    }
//
//    /**
//     * unblock a single random cell
//     * @param grid a UnionFindArray of blocked (0) cells
//     */
//    public static int getRandomBlocked(UnionFind grid) {
//        int randomCellIdx = rand.nextInt(grid.arr.length) + 1;
//        System.out.println("randCellIdx: " + randomCellIdx);
//        int randomCellVal = grid.arr[randomCellIdx];
//        if (randomCellVal == 0) {
//            return randomCellIdx;
//        }
//        else return getRandomBlocked(grid); //if randomCell was already unblocked, try again
//    }
//
//    /**
//     * iterate removing random blocks in the grid until the top(401) and bottom(402) are
//     * connected
//     * @param grid
//     */
//    public static void percolate(UnionFind grid) {
//        int count = 0;
//        boolean found = false;
//        while (!found) {
//            count++;
//            int randBlockedIdx = getRandomBlocked(grid);
//            System.out.println("randBlocked = " + randBlockedIdx);
//            grid.arr[randBlockedIdx] = -1; //unblocks one cell
//            int x = (randBlockedIdx - 1) % 20; //might need to switch mod and floor div
//            int y = (randBlockedIdx - 1) / 20;// have to - 1 to account for extra cell bc otherwise it looks like 0 is a parent
//
//            System.out.println("Unblocked Cell: (" + x + "," + y + ")");
//
////            int xLeft = (randBlockedIdx - 1) % 20 - 1;
////            int xRight = (randBlockedIdx - 1) % 20 + 1;
////            int yUp = (randBlockedIdx - 1) / 20 - 1;
////            int yDown = (randBlockedIdx - 1) / 20 + 1;
//
//            int aboveCellIdx = randBlockedIdx -20;
//            int belowCellIdx = randBlockedIdx + 20;
//            int leftCellIdx = randBlockedIdx - 1;
//            int rightCellIdx = randBlockedIdx + 1;
//
//            //check if on top row
//            if (y == 0) {
//
//                //401 is considered the top that will percolate down
//                grid.union(randBlockedIdx, 401);
//
//                //check for open cells around top left corner
//                if (x == 0) {
//                    if (grid.arr[rightCellIdx] != 0) grid.union(rightCellIdx, randBlockedIdx);
//                    if (grid.arr[belowCellIdx] != 0) grid.union(belowCellIdx, randBlockedIdx);
//                }
//
//                //check for open cells around top right corner
//                else if (x == 19) {
//                    if (grid.arr[leftCellIdx] != 0) grid.union(leftCellIdx, randBlockedIdx);
//                    if (grid.arr[belowCellIdx] != 0) grid.union(belowCellIdx, randBlockedIdx);
//                }
//
//                //check middle cells
//                else {
//                    if (grid.arr[leftCellIdx] != 0) grid.union(leftCellIdx, randBlockedIdx);
//                    if (grid.arr[rightCellIdx] != 0) grid.union(rightCellIdx, randBlockedIdx);
//                    if (grid.arr[belowCellIdx] != 0) grid.union(belowCellIdx, randBlockedIdx);
//                }
//            }
//
//            //check if bottom row
//            else if (y == 19) {
//
//                //combine with bottom
//                grid.union(randBlockedIdx, 402);
//
//                //check if bottom left corner
//                if (x == 0) {
//                    if (grid.arr[rightCellIdx] != 0) grid.union(rightCellIdx, randBlockedIdx);
//                    if (grid.arr[aboveCellIdx] != 0) grid.union(aboveCellIdx, randBlockedIdx);
//                }
//
//                //check if bottom right corner
//                else if (x == 19) {
//                    if (grid.arr[leftCellIdx] != 0) grid.union(leftCellIdx, randBlockedIdx);
//                    if (grid.arr[aboveCellIdx] != 0) grid.union(aboveCellIdx, randBlockedIdx);
//                }
//
//                //check middle cells
//                else {
//                    if (grid.arr[leftCellIdx] != 0) grid.union(leftCellIdx, randBlockedIdx);
//                    if (grid.arr[aboveCellIdx] != 0) grid.union(aboveCellIdx, randBlockedIdx);
//                    if (grid.arr[rightCellIdx] != 0) grid.union(rightCellIdx, randBlockedIdx);
//                }
//            }
//
//            if (y == 19) { //bottom row
//                grid.union(randBlockedIdx, 402);
//            }
//
//            //check middle rows
//            else {
//
//                //check if on left edge
//                if (x == 0) {
//                    if (grid.arr[aboveCellIdx] != 0) grid.union(aboveCellIdx, randBlockedIdx);
//                    if (grid.arr[rightCellIdx] != 0) grid.union(rightCellIdx, randBlockedIdx);
//                    if (grid.arr[belowCellIdx] != 0) grid.union(belowCellIdx, randBlockedIdx);
//                }
//
//                //check on right edge
//                else if (x == 19) {
//                    if (grid.arr[aboveCellIdx] != 0) grid.union(aboveCellIdx, randBlockedIdx);
//                    if (grid.arr[leftCellIdx] != 0) grid.union(leftCellIdx, randBlockedIdx);
//                    if (grid.arr[belowCellIdx] != 0) grid.union(belowCellIdx, randBlockedIdx);
//                }
//
//                //check middle cells
//                else {
//                    if (grid.arr[aboveCellIdx] != 0) grid.union(aboveCellIdx, randBlockedIdx);
//                    if (grid.arr[leftCellIdx] != 0) grid.union(leftCellIdx, randBlockedIdx);
//                    if (grid.arr[belowCellIdx] != 0) grid.union(belowCellIdx, randBlockedIdx);
//                    if (grid.arr[rightCellIdx] != 0) grid.union(rightCellIdx, randBlockedIdx);
//                }
//            }
//
//
//            if (grid.find(401) == grid.find(402)) found = true;
//
//            //print out grid every 50 unblocked cells
//            if (count % 1 == 0) {
//                System.out.println("Number of cells unblocked: " + count);
//                printGrid(grid);
//                System.out.println();
//            }
//        }
//        System.out.println("Number of cells unblocked until percolation: " + count);
//        printGrid(grid);
//    }
//
////
////    private int[] initArray(int size) {
////        int[] arr = new int[size];
////        for (int i=0; i<size; i++) {
////            arr[i] = -1;
////        }
////        return arr;
////    }
//
//
//    public static void main(String[] args) {
//        UnionFind testArray = new UnionFind(403);
//        testArray.arr[401] = -1;
//        testArray.arr[402] = -1;
//
//        Percolation.printGrid(testArray);
//        System.out.println();
//
//        Percolation.percolate(testArray);
//    }
//
//}
