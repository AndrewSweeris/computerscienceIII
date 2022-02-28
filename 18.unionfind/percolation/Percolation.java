import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
    private int openSites;
    private WeightedQuickUnionUF union;
    private WeightedQuickUnionUF bwunion;
    // virtual top is 0, virtual bottom is 1
	
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
       if (n<=0) throw new IllegalArgumentException();
       openSites = 0;
       grid = new boolean[n][n];
       union = new WeightedQuickUnionUF(n*n + 2);
       bwunion = new WeightedQuickUnionUF(n*n + 1);
       for (int i = 0; i < grid.length; i++) {
           for (int j = 0; j < grid[i].length; j++) {
               grid[i][j]=false;
           }
       }
       // Fills array w/blocked locations
   }
   public void open(int row, int col)       // open site (row, col) if it is not open already
   {
       // TODO
       row--;
       col--;
       checkRange(row, col);
       if (!grid[row][col]) {
           openSites++;
           grid[row][col]=true;
           int loc = getID(row,col);
           if (row==0) {
               union.union(0, loc);
               bwunion.union(0, loc-1);
           }
           if (row == grid.length-1) {
               union.union(1, loc);
           }
           if (row>0) {
               int left = getID(row-1, col);
               if (isOpenInt(row-1,col)) {
                   union.union(loc, left);
                   bwunion.union(left-1, loc-1);
               }
           }
           if (row+1<grid.length) {
               int right = getID(row+1, col);
               if (isOpenInt(row+1,col)) {
                   union.union(loc, right);
                   bwunion.union(right-1, loc-1);
               }
           }
           if (col>0) {
               int top = getID(row, col-1);
               if (isOpenInt(row,col-1)) {
                   union.union(loc, top);
                   bwunion.union(top-1, loc-1);
               }
           }
           if (col+1<grid.length) {
               int bot = getID(row, col + 1);
               if (isOpenInt(row,col+1)) {
                   union.union(loc, bot);
                   bwunion.union(bot-1, loc-1);
               }
           }
       }
   }
   private int getID(int r, int c){			// helper method to calculate ID
       checkRange(r, c);
       return grid.length * r + c + 2;
   }
   
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       row--;
       col--;
       checkRange(row, col);
       return grid[row][col];
   }
    private boolean isOpenInt(int row, int col)  // is site (row, col) open? (INTERNAL FUNC
    {
        checkRange(row, col);
        return grid[row][col];
    }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       row--;
       col--;
       checkRange(row, col);
       return bwunion.find(0)==bwunion.find(getID(row,col)-1);
   }
   private void checkRange(int row, int col){	// validate input
       if (row<0||row>=grid.length||col<0||col>=grid[row].length) throw new IllegalArgumentException("" + row + " " + col);
   }
   public boolean percolates()              // does the system percolate?
   {
       return union.find(0)==union.find(1);
   }
   private void print(){				   // prints boolean[][] called mat
   	for(boolean[] row: grid){
   		for(boolean col: row){
   			System.out.print((col ? 1: 0) + " ");
   		}
   		System.out.println();
   	}
   	System.out.println();
   }
   public int numberOfOpenSites() {return openSites;}
   public static void main(String[] args)   // test client (optional){
   {
   		Percolation test = new Percolation(2);	// simple test case
   		test.print();
   		test.open(1,1);
   		test.print();
   		test.open(2,1);
   		test.print();
   		System.out.println(test.percolates());
   }
}