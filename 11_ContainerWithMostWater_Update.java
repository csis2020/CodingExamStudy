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
                        
            maximumArea = Math.max(maximumArea, (rightX - leftX) * Math.min(height[leftX], height[rightX]));
        
            if(height[leftX] <= height[rightX]){
                
                leftX++;
                // 여기서는 while 문을 넣는것이 이득이 없음. 원래 위 계산 자체가 단순하기때문. 아래 while 문을 넣은게 코드만 더 복잡해짐.  
                //while( (leftX < rightX) && height[leftX] < height[leftX-1]){ //find bigger height[leftX] 
                //   leftX++;
                //}
                
            }else{ //height[leftX] > height[rightX]
                
                rightX--;
                // 여기서는 while 문을 넣는것이 이득이 없음. 원래 위 계산 자체가 단순하기때문. 아래 while 문을 넣은게 코드만 더 복잡해짐. 
                //while( (leftX < rightX) && height[rightX] < height[rightX+1]){ //find bigger height[rightX] 
                //    rightX--;
                //}
            }

        }
        
        return maximumArea;
    }
}

/*
class Solution {
    public int maxArea(int[] height) {
        
        if(height == null || height.length == 0){
            return 0;
        }
        
        int leftX = 0;
        int rightX = height.length -1;
        int maximumArea = 0;
        int currentArea = 0; 
        int length = 0;
        
        while(leftX < rightX){
                        
            //int currentArea = (rightX - leftX) * (Math.min(height[leftX], height[rightX]));
            //maximumArea = ( currentArea > maximumArea) ? currentArea : maximumArea;
            length = rightX - leftX;
            
            if(height[leftX] <= height[rightX]){
                
                currentArea = length * height[leftX];                
                
                leftX++;
                //while( (leftX < rightX) && height[leftX] < height[leftX-1]){ //find bigger height[leftX] 
                //   leftX++;
                //}
                
            }else{ //height[leftX] > height[rightX]
                
                currentArea = length * height[rightX]; 
                
                rightX--;
                //while( (leftX < rightX) && height[rightX] < height[rightX+1]){ //find bigger height[rightX] 
                //    rightX--;
                //}
            }
            
            maximumArea = ( currentArea > maximumArea) ? currentArea : maximumArea;
        }
        
        return maximumArea;
    }
}
*/