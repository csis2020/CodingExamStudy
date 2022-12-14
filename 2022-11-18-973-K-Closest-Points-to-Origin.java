//2022-11-17
//Time Complexity: O(NlogN) <--- sorting 시간
//Space Complexity: O(N) <--- Sorting 할때 드는 메모리
/*
class Solution{
    public int[][] kClosest(int[][] points, int k){

        Arrays.sort(points, (a,b) -> (distance(a[0], a[1]) - distance(b[0], b[1])));

        return Arrays.copyOf(points, k);

    }

    private int distance(int x, int y){
        return x*x + y*y;
    }

}
*/
//개선된 방법 - Max Heap 사용 
//Time Complexity: O(NlogK) // tree의 최대노드수가 k 개임 
//Space Complexity: O(K) // tree 의 최대 노드수가 k 개
class Solution{
    public int[][] kClosest(int[][] points, int k){

        //타입: int[] arr = new int[2] 
        //    arr[0]:distance, arr[1]: row value of 'points' array 
        PriorityQueue<int[]> minPQ = new PriorityQueue<int[]>((a,b)->(b[0] - a[0]));
        
        for(int i = 0; i < points.length ; i++){
            int distance = getDistance(points[i]);
            /*
            if(minPQ.size() < k){
                minPQ.add(new int[]{distance, i});
            }else{
                if(distance < minPQ.peek()[0]){
                    minPQ.remove(); //minPQ.poll();와 동일
                    minPQ.add(new int[]{distance, i});
                }
            }
            */
            //위 구현과 방식만 살짝 다르지 동일함. 
            minPQ.add(new int[]{distance, i});
            if(minPQ.size() > k){
                minPQ.remove(); //minPQ.poll(); 과 동일 //PQ안에서 제일 큰값이 빠짐.
            }
        }
        int[][] result = new int[k][2];
        for(int i = 0; i < k; i++){
            int index = minPQ.remove()[1];
            result[i] = points[index];
        }

        return result;

    }
    
    private int getDistance(int[] point){
        return point[0]*point[0] + point[1]*point[1];
    }
}

//LeetCode-Solution#1
//Time Complexity: O(NlogN)  <--- by sorting
// Space complexity: O(N) <--- 이해를 잘 못했음.  Sorting 에 필요한 extra space 가 worst case 에 O(N)이라고 함...  그럼 다른 알고리즘에서도 Arrays.sort 를 썼으면 O(N) 이였던걸까???
/*
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        //Sort by distance
        Arrays.sort(points, (a,b)->(squaredDistance(a) - squaredDistance(b)));
        
        return Arrays.copyOf(points, k);
    }
    
    private int squaredDistance(int[] point){
        return (point[0]*point[0] + point[1]*point[1]);
    }
}
*/
//LeetCode-Solution#2
//Time Complexity: O(NlogK)
//Space Complexity: O(K)
/*
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        //[0]: distance
        //[1]: frist index of points array
        // descending order of distance
        PriorityQueue<int[]> closestSet = new PriorityQueue<int[]>((a,b)->(b[0] - a[0]));
        
        for(int i = 0; i < points.length; i++){
            int distance = squaredDistance(points[i]);
            int[] point = new int[]{distance, i};
            if(closestSet.size() < k){
                closestSet.add(point);
            }else{
                if(distance < closestSet.peek()[0]){
                    closestSet.poll(); //remove
                    closestSet.add(point);
                }
            }
        }
        
        int[][] output = new int[k][2];
        for(int i = 0; i < k; i++){
            int index = closestSet.poll()[1];
            output[i] = points[index];
        }
        return output;
    }
    
    private int squaredDistance(int[] point){
        return (point[0]*point[0] + point[1]*point[1]);
    }
}
*/

//Input: points = [[1,3],[-2,2]], k = 1  ==> Output: [[-2,2]]
//Input: points = [[3,3],[5,-1],[-2,4]], k = 2 ==> Output: [[3,3],[-2,4]]

//Time complexity: O(N) <--Wrong , Space complexity: O(K)
//According to Leetcode ==>  Time complexity: O(NlogK), Space complexity: O(K) <-- It is right (The heap (or priority queue) will contain at most kk elements.)
/*
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        //List<int[]> minDistPnt = new ArrayList<int[]>();
        PriorityQueue<int[]> minDistPnt = new PriorityQueue<int[]>(
            (a,b)->((b[0]*b[0]+b[1]*b[1])-(a[0]*a[0]+a[1]*a[1]))
                   );
        int currentDist = 0;
         
        for(int i = 0; i < points.length; i++){
           
            
            if(minDistPnt.size() < k){
                minDistPnt.add(points[i]);
                //System.out.println("["+i+"]:("+points[i][0]+","+points[i][1]+")");
            }else{
                currentDist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                int[] minPoint = minDistPnt.peek();
                int savedMaxDistance = minPoint[0]*minPoint[0] + minPoint[1]*minPoint[1];
                if(currentDist < savedMaxDistance){
                    minDistPnt.poll(); // remove max distance
                    minDistPnt.add(points[i]);
                    //System.out.println("["+i+"]:("+points[i][0]+","+points[i][1]+")");
                }
            }
        }
        
        int size = minDistPnt.size();
        int[][] outputPoints = new int[size][points[0].length];
        //System.out.println("Size= " + size);
        for(int i = 0; i <size ; i++){
            
            outputPoints[i] = minDistPnt.poll();
            //System.out.println("Result["+i+"]:("+outputPoints[i][0]+","+outputPoints[i][1]+")");
        }
        
        return outputPoints;
    }
}
*/