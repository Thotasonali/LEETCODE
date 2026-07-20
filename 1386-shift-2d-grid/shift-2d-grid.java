class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m=grid.length;
        int n= grid[0].length;
        int total=m*n;
        k=k%total;
        List<List<Integer>> result=new ArrayList<>();
        for(int r=0;r<m;r++){
            List<Integer> row=new ArrayList<>();
            for(int c=0;c<n;c++){
                int present=r*n+c;
                int old =(total-k+present) % total;
                int oldrow=old/n;
                int oldcol=old%n;
                row.add(grid[oldrow][oldcol]);
            }
            result.add(row);

        }
        return result; 
    }
}