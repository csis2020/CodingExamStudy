/*
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
output: 1
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
output: 3
*/

//2022.09.18
//limitation: if grid is null or size is zero, return 0
//
// Time Complexity: O(MxN), Space Complexity: O(MxN) -> 왜지? 
// leetCode 설명: Space Complexty :worst case O(MxN)  in case that the grid map is filled with lands where DFS goes by MxN deep.
// 위 설명이 잘 이해가 가지 않는다. 
//    
//  내가 이해하기로는 제일 깊게간 recursive 함수 depth 가 아래 경우인거 같은데.. 그리고 return 되지 않나? 그런경우  (M+N-1) 가 Space complexity 가 아닌가?  
//  ["1","1","1","1","1"],           ["0","0","0","0","0"],
//  ["1","1","1","1","1"],  visit => ["1","1","1","1","0"],
//  ["1","1","1","1","1"],           ["1","1","1","1","0"],
//  ["1","1","1","1","1"]            ["1","1","1","1","0"],
class Solution{
    
    
    public int numIslands(char[][] grid){
        
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return -1;
        }
        
        int numOfIslands = 0; 
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                
                if(grid[i][j] != '0'){//there is an island
                    visitIsland(grid, i, j);
                    numOfIslands++;
                }
            }
        }
        
        return numOfIslands;
    }
    
    //Visit Island -> if the grid is visited, reset it as "0"
    private void visitIsland(char[][] grid, int i, int j){
        
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length){ //out of grid
            return;
        }
        
        if(grid[i][j] == '0'){
            return; 
        }
        
        grid[i][j] = '0'; // "1" -> "0" : visited
        
        visitIsland(grid, i-1, j); //left
        visitIsland(grid, i+1, j); //right
        visitIsland(grid, i, j-1); //up
        visitIsland(grid, i, j+1); //down
        
    } 
}

//leetcode solution 
//Time Complexity: O(MxN) <--- 왜 이건지 모르겠다.  recursive 함수 호출하는 빈도수도 함께 체크되어야 하는거 아닌가??  '0' 으로 체크되고나면 recursive 안돌게 되니까 ... 딱 MxN 만큼만 움직이는건가???? 여전히 헷갈린다. 
//Space Complexity: O(MxN)  <--- in case that the grid map is filled with lands where DFS goes by MxN deep.
/*
class Solution {
    
    int output = 0;
    public int numIslands(char[][] grid) {
        //if the value is '1', check 4 directions and make them '0', make the value to '1' also
        int total = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                   
                    checkGrid(grid, i, j);
                    total++;
                }else{
                    // System.out.print("["+i+"]["+j+"] ");
                }
            }
        }
        
        return total;
    }
    
    private void checkGrid(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length){
            return; 
        }
        if(grid[i][j] == '1'){
            grid[i][j] = '0';
            checkGrid(grid, i-1, j);
            checkGrid(grid, i+1, j);
            checkGrid(grid, i, j-1);
            checkGrid(grid, i, j+1);
        }
    }
}
*/
/*  it doesn't work. not solved.
class Solution {
    
    int output = 0;
    public int numIslands(char[][] grid) {
       
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                
                if(visitGrid(grid, i, j)){
                       System.out.println("["+i+", "+j+"]_return true");
                    output++;
                }else{
                     System.out.println("["+i+", "+j+"]_return false");
                }
            }
        }
        
        return output;
    }
     
    private boolean visitGrid(char[][] grid, int i, int j){
        
        if(i < 0 || i >= grid.length || j < 0  || j >= grid[0].length){ //out of area
            //System.out.println("["+i+", "+j+"]_out of area");
            return true;
        }         
        
        if(grid[i][j] == 0){ //water: true
            //System.out.println("["+i+", "+j+"]_water");
            return true;
        }

        if(grid[i][j] == 2){ //already visited
            //System.out.println("["+i+", "+j+"]_already visited");
            return false;
        }
        
        grid[i][j] = 2;        
        boolean right = visitGrid(grid, i+1, j); //check left direction         
        boolean bottom = visitGrid(grid, i, j+1); //check right direction
    
       //visited     
        boolean temp = right && bottom;
        //System.out.println("["+i+", "+j+"]_[right:"+right+", bottom:"+bottom+"]_return " + temp);
        return right && bottom; 
        
    }

}
*/