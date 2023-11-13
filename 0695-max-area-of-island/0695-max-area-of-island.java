
//2023-11-12
//DFS
//Time Complexity: O(MxN) 이라고 하네. 나는 처음에 O(MxNx4^K) 이라고 생각했는데, 아무래도 '0'으로 세팅하는것이 있으니까 이것으로인해 체크된것은 다시 체크하지 않으니 결국 MxN 이라는건가보다. 
//Space Complexity: O(K), recursive call stack: k is maximum area size
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length <= 0 ){
            return 0;
        }

        int maxNumber = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                int number = bt(grid, i, j);
                maxNumber = Math.max(number, maxNumber);
            }
        }

        return maxNumber;
    }

    
    int bt(int[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length){
            return 0;
        }

        if(grid[i][j] != 1){
            return 0;
        }

        grid[i][j] = '0';
        int number = bt(grid, i-1, j) + bt(grid, i, j-1) + bt(grid, i+1, j) + bt(grid, i, j+1) + 1;

        return number;
    }

}