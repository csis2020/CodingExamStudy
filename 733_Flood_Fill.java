//2022.09.20
//Limitation: if image is null or size is zero, return null???

//Time Complexity: O(n),  Space Complexity: O(N) <-The size of the implicit call stack when calling dfs.
// 만약 left -> down -> right -> up 순으로 방문한다면 아래처럼 나선형을 그리며 depth 가 image 안의 pixel개수 n 만큼 들어감.
//  ["1","1","1","1","1"],           ["0"->"0"->"0"->"0"-> "0"],
//                                                         아래 
//  ["1","1","1","1","1"],  visit => ["0"->"0"->"0"->"0"   "0"],
//                                    위             아래   아래
//  ["1","1","1","1","1"],           ["0"  "0"<-"0"<-"0"   "0"],
//                                    위                    아래 
//  ["1","1","1","1","1"]            ["0"<-"0"<-"0"<-"0"<- "0"],
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        
        if(image == null || image.length == 0 || image[0].length == 0){
            return null;
        }
        
        int width = image.length;
        int height = image[0].length;
        
        if( sr < 0 || sr >= width || sc < 0 || sr >= height){
            return null;
        }
        
        int original = image[sr][sc];
        
        //The starting pixel is already colored
        if(original == color){
            return image;
        }
        
        //change the color
        image[sr][sc] = color;
        
        //up
        if((sc-1 >= 0 ) && (image[sr][sc-1] == original)){
            image = floodFill(image, sr, sc -1, color);
        }
        //down
        if((sc+1 < height ) && (image[sr][sc+1] == original)){
            image = floodFill(image, sr, sc+1, color);
        }
        //left
        if((sr-1 >= 0 ) && (image[sr-1][sc] == original)){
            image = floodFill(image, sr-1, sc, color);
        }
        //right
        if((sr+1 < width ) && (image[sr+1][sc] == original)){
            image = floodFill(image, sr+1, sc, color);
        }
        
        return image;

    }

}
