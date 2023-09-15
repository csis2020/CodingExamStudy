//2023-09-13
//Dynamic programming 방식으로 풀었음. 
//경계값 체크를 위해 new int[row+1][col+1] 의 저장배열을 선언하고 
// [row-1][col-1] -> [0][0] 순서로 끝에서부터 처음으로 거꾸로 누적길을 계산해 나감. 
// [0][0] -> [row-1][col-1] 순서로 풀면. 왼쪽끝, 위쪽끝 라인의 초기화를 위해 따로 코드를 넣거나, 아니면 index 를 [i][j] -> [i+1][j+1] 로 공간을 shift 해서 사용해야하니 index 가 헷갈릴수도 있다. 

//Time Complexity: O(n*m)
//Space Complexity: O(n*m)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length <= 0 || obstacleGrid[0].length <= 0){
            return 0;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[][] ways = new int[row+1][col+1]; //default is '0'
        ways[row][col-1] = 1; //initial
        for(int i = row-1; i >= 0; i--){
            for(int j = col-1; j >= 0; j--){
                if(obstacleGrid[i][j] == 0){ //existed path
                    ways[i][j] = ways[i+1][j] + ways[i][j+1];
                }
            }
        }

        return ways[0][0];
    }
}