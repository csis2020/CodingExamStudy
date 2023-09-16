
//2023-09-15
// idea : 
//       from top-left cornner to bottom-right corner : right /bottom
//         from bottom-right corner to top-left cornner : left /up
// Time Complexity: O(MxN)
// Space Complexity: O(MxN)
class Solution{
    public int uniquePaths(int m, int n){
        if(m <= 0 || n <= 0){
            return 0;
        }

        int[][] paths = new int[m+1][n+1]; //default values are '0'

        //from top-left cornner to bottom-right corner : right /bottom
        //from bottom-right corner to top-left cornner : left /up
        paths[m][n-1] = 1; //for initial value
        for(int i = m-1; i >= 0; i--){
            for(int j = n-1; j >=0; j--){
                paths[i][j] = paths[i+1][j] + paths[i][j+1];
            }
        }
        return paths[0][0];
    }
}


//2022-12-04
//Time Complexity: O(MxN)
//Space Complexity: O(MxN)
/*
class Solution {
    public int uniquePaths(int m, int n) {
        if(m <=0 || n <= 0){
            return 0;
        }

        int[][] maxPath = new int[m][n];

        for(int i = 0; i < m; i++){
            maxPath[i][0] = 1;
        }

        for(int j = 0; j < n; j++){
            maxPath[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j <n; j++){
                maxPath[i][j] = maxPath[i-1][j] + maxPath[i][j-1];
            }
        }

        return maxPath[m-1][n-1];
    }
}
*/