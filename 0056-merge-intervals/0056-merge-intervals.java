//similar Question
//  252. Meeting Rooms (premium)
//  763. Partition Labels
//  57. Insert Interval (premium)

//2022-10-24
//limitation: [[s1,e1],[s2,e2],[s3,e3],...] <--- s1<=s2<=s3<=... 이순서가 보장되는건가? => 보장이 안되는구만...
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]] -> output: [[1,6],[8,10],[15,18]]
//Input: intervals = [[1,4],[4,5]] -> output: [[1,5]]
//Input: intervals = [[1,4],[0,4]] -> output: [[0,4]]
//Input: intervals = [[1,4],[2,3]] -> output: [[1,4]]

//Idea: 
//  0) 순서가 보장이 안되면 ... Sort 부터 해야함. 
//  => 중요! start 를 기준으로 하는경우 ascending 으로 sort, end 를 기준으로 하는경우 descending으로 sort해야함! 
//     그이유는, merge 하는 것이기때문에 boundary 가 더 바깥쪽인 것부터 안으로 계산해 나가야함.  
//      end 를 기준으로 ascending order 로 sort 하고 계산하면 아래 예제의 경우 문제 발생
//      ex) input: [[2,3],[4,5],[6,7],[8,9],[1,10]]  -> output 이 [[1,10]] 이어야 하는데 [[2,3],[4,5],[6,7],[1,10]] 가 나옴. 
//  1) List<List<Integer>>() 생성  
//  2) i 번째 e 와 i+1 의 s 를 비교, 겹치면 합치기 
//  3) 더이상 겹치지 않으면 1)번 list 에 넣기

//2023-10-05-- start 를 기준으로 ascending order 로 계산한 경우 
// Time Complexity: O(NlogN) <--sorting
// Space Complexity: O(N) <-- sorting 할때 든 메모리 
/*
class Solution{
    public int[][] merge(int[][] intervals){
        if(intervals == null || intervals.length <= 0){
            return new int[0][]; //empty array
        }
        
        Arrays.sort(intervals, (a, b)-> (a[0] - b[0])); //sorted by the start - ascending order

        
        List<int[]> mergedPoints = new ArrayList<>();
        mergedPoints.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int[] prevPoint = mergedPoints.get(mergedPoints.size() -1);
            int[] currPoint = intervals[i];
            int start = currPoint[0]; //because of (Point[0] <= intervals[i][0])
            int end = Math.min(prevPoint[1], currPoint[1]);
            if(start <= end){
                prevPoint[1] = Math.max(prevPoint[1], currPoint[1]); 
            }else{
                mergedPoints.add(intervals[i]);
            }
        }
        
        return mergedPoints.toArray(new int[mergedPoints.size()][]);
    }
}
*/

//2023-10-05-- end 를 기준으로 descending order 로 계산한 경우 
// Time Complexity: O(NlogN) <--sorting
// Space Complexity: O(N) <-- sorting 할때 든 메모리 
class Solution{
    public int[][] merge(int[][] intervals){
        if(intervals == null || intervals.length <= 0){
            return new int[0][]; //empty array
        }
        
        Arrays.sort(intervals, (a, b)-> (b[1] - a[1])); //sorted by the end - desscending order

        
        List<int[]> mergedPoints = new ArrayList<>();
        mergedPoints.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int[] prevPoint = mergedPoints.get(mergedPoints.size() -1);
            int[] currPoint = intervals[i];
            int start = Math.max(prevPoint[0], currPoint[0]); 
            int end = currPoint[1];//because of (prevPoint[1] >= intervals[i][1])
            if(start <= end){
                prevPoint[0] = Math.min(prevPoint[0], currPoint[0]); 
            }else{
                mergedPoints.add(intervals[i]);
            }
        }
        
        return mergedPoints.toArray(new int[mergedPoints.size()][]);
    }
}
//2022-12-05
//Time Complexity: O(nlogn) <--sorting
//Space Complexity: O(n) or o(logn)
/*
class Solution{
    public int[][] merge(int[][] intervals){
        if(intervals == null || intervals.length == 0){
            return new int[0][]; //return empty array;
        }

        // 첫번째 데이터 기준으로 sort
        Arrays.sort(intervals, (a,b)->(a[0] - b[0]));

        List<int[]> mergeList = new ArrayList<>();
        mergeList.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int[] interval = mergeList.get(mergeList.size() -1);
            if(interval[1] >= intervals[i][0]){
                interval[1] = Math.max(interval[1], intervals[i][1]);
            }else{
                mergeList.add(intervals[i]);
            }
        }

        int[][] result = mergeList.toArray(new int[mergeList.size()][]);
        return result;
    }
}
*/
// Time Complexity : O(nlogn) <--- sort 시간 
// Space Complexity : O(logn) or O(n) <--- sort 에서 쓴 space
/*
class Solution{
    public int[][] merge(int[][] intervals){
        if(intervals == null || intervals.length == 0){
            return new int[0][]; //return empty array;
        }
        
        
        //sort by the ascending order of start position
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]));
        
        List<int[]> mergedList = new ArrayList<>();
        mergedList.add(intervals[0]);
        
        for(int i = 1; i < intervals.length; i++){
            int[] pre = mergedList.get(mergedList.size() -1);
            int[] curr = intervals[i];
            if(pre[1] >= curr[0]){ //if (i-1)th end is equal or bigger than (i)th start, merge it
                pre[1] = Math.max(pre[1], curr[1]);
            }else{
                mergedList.add(curr);
            }       
        }
        
        int[][] mergedArray = mergedList.toArray(new int[mergedList.size()][]);
        return mergedArray;
    }
}
*/

//leetcode 풀이보고 수정
/*
class Solution{
    public int[][] merge(int[][] intervals){
        
        if(intervals.length == 0){
            return new int[0][]; //return empty array
        }
        
        //sort by acending order of first value
        Arrays.sort(intervals, (a,b)->(a[0] - b[0])); // a:int[2], b:int[2]
        
        List<int[]> mergedList = new ArrayList<int[]>();
        
        mergedList.add(intervals[0]);
        
        for(int i = 1; i < intervals.length; i++){
            int[] last = mergedList.get(mergedList.size()-1); 
            if(intervals[i][0] <= last[1]){ //overlapping
                last[1] = Math.max(last[1], intervals[i][1]);
            }else{ // no overapping
                mergedList.add(intervals[i]);
            }                                                       
        }
        
        //make 2d array from list
        int[][] mergedArray = mergedList.toArray(new int[mergedList.size()][]);
        
        return mergedArray;
    }    
}
*/
/*
class Solution{
    public int[][] merge(int[][] intervals){
        
        List<int[]> merged = new ArrayList<int[]>();
        
        if(intervals.length == 0){
            return new int[0][]; //return empty array 
        }
        
        //sorting by ascending order of 'start'
        Arrays.sort(intervals, (a,b)->(a[0] - b[0])); //a = int[2] , b = int[2]
        
        int start = intervals[0][0];
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] <= end){ //overlapping
                end = Math.max(end, intervals[i][1]); //choose max end.
            }else{
                merged.add(new int[]{start,end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        
        //add last intervals
        merged.add(new int[]{start,end});
        
        //int[][] resultArray = new int[merged.size()][2];
        //for(int i = 0; i < merged.size(); i++){
        //    int[] interval = merged.get(i);
        //    resultArray[i][0] = interval[0];
        //    resultArray[i][1] = interval[1];
        //}
        
        int[][] resultArray = merged.toArray(new int[merged.size()][]);

        
        return resultArray;
    }
}
*/

//leetcode solution 
/*
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }
        
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0],b[0]));
        
        LinkedList<int[]> mergedList = new LinkedList<>();
        
        for(int[] value : intervals){
            if(mergedList.isEmpty()){
                mergedList.add(value);
            }else{
                int[] lastList = mergedList.getLast();
                if(lastList[1] < value[0]){
                    mergedList.add(value);
                }else{
                    lastList[1] = Math.max(lastList[1], value[1]);
                }
            }
            
        }
        
        int[][] mergedArray = mergedList.toArray(new int[mergedList.size()][]);
        
        return mergedArray;
    }
}
*/
//My solution - 2022-04-13
/*
class Solution {
    public int[][] merge(int[][] intervals) {
        
        int count = 1;
        int pos = 0;
        int temp0, temp1;
    
        Arrays.sort(intervals, new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2){
               if(o1[0] == o2[0]){
                   return o1[1] - o2[1];
               }else{
                   return o1[0] - o2[0];
               }
           }
        });
        
        while(count < intervals.length ){
            if(intervals[pos][1] >= intervals[count][0]){
                if(intervals[pos][0] > intervals[count][1]){
                    temp0 = intervals[pos][0];
                    temp1 = intervals[pos][1];
                    intervals[pos][0] = intervals[count][0];
                    intervals[pos][1] = intervals[count][1];
                    pos++;
                    intervals[pos][0] = temp0;
                    intervals[pos][1] = temp1;
                }else{
                    intervals[pos][0] = (intervals[pos][0] < intervals[count][0]) ? intervals[pos][0] : intervals[count][0];
                    intervals[pos][1] = Math.max(intervals[pos][1], intervals[count][1]);
                }

            }else{
                pos++;                
                intervals[pos][0] = intervals[count][0];
                intervals[pos][1] = intervals[count][1];                
            }
            
            count++;
        }
    
        //System.out.println("length__: " + length);     
        int[][] output = new int[pos+1][2];
        
        for(int i = 0; i <= pos ; i++){
            output[i][0] = intervals[i][0];
            output[i][1] = intervals[i][1];
        }
        
        return output;
    }
}
*/
