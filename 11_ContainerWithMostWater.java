//2022-09-07
//limitation: height is null or size is zero, -> return ???? 
// Input: height = [1,8,6,2,5,4,8,3,7] -> Output: 49
// Input: height = [1,1] -> Output: 1
// Time Complexity: O(N) , Space Complexity: O(1)

class Solution {
    public int maxArea(int[] height) {
        
        if(height == null || height.length == 0){
            return 0;
        }
        
        int leftX = 0;
        int rightX = height.length -1;
        int maximumArea = 0;
        
        while(leftX < rightX){
                        
            int currentArea = (rightX - leftX) * (Math.min(height[leftX], height[rightX]));
            maximumArea = ( currentArea > maximumArea) ? currentArea : maximumArea;
            
            if(height[leftX] <= height[rightX]){
                
                leftX++;
                while( (leftX < rightX) && height[leftX] < height[leftX-1]){ //find bigger height[leftX] 
                    leftX++;
                }
                
            }else{ //height[leftX] > height[rightX]
                rightX--;
                while( (leftX < rightX) && height[rightX] < height[rightX+1]){ //find bigger height[rightX] 
                    rightX--;
                }
            }
        }
        
        return maximumArea;
    }
}