//2023-10-08
//idea : Discuss 에 올라온 solution 을 이용해서 풀었음. 
//  1) List 를 만들고, intervals 의 end 가 newInterval의 start 보다 작은 값들을 list 에 넣음
//  2) newInterval 을 list 에 넣기전에 만약 newInterval 의 end 보다 남아있는 intervals 들의 start 가 작은게 있으면 merge 를 수행해서 update 된 'end' 를 찾아서 list 에 넣기 
//  3) 남은 intervals 값이 있으면 다 넣고, return 
//
//example: intervals = [], newIntervals = [5,7]  이경우도 커버해야함. 
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length <= 0){
            return new int[][]{newInterval};
        }
        
        List<int[]> insertedList = new ArrayList<>();
        
        int position = 0;
        int start = newInterval[0];
        int end = newInterval[1];
        //intervals-i's end < newInterval's start => non-overlapping
        while(position < intervals.length && intervals[position][1] < start){
            insertedList.add(intervals[position++]);
        }
        
        //intervals-i's start <= newInterval's end => overlapping
        while(position < intervals.length && intervals[position][0] <= end){
            start = Math.min(intervals[position][0], start);
            end = Math.max(intervals[position][1], end);
            position++;
        }
        
        insertedList.add(new int[]{start,end});
        
        while(position < intervals.length){
            insertedList.add(intervals[position++]);
        }
        
        return insertedList.toArray(new int[insertedList.size()][]);
    }
}

/*
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        if(intervals == null || intervals.length == 0){
            return new int[0][]; //return empty array
        }
        
        List<int[]> mergedList = new ArrayList<>();
        
        int count = 0; 
        while(count < intervals.length && intervals[count][0] < newInterval[0] && intervals[count][1] < newInterval[1]){
            mergedList.add(intervals[count]);
            count++;
        }
        
        count--;
        mergedList.add(newInterval);
        
        System.out.println(mergedList.get(0)[0] +","+mergedList.get(1)[0]);
        
        while(count < intervals.length){
            
            int[] pre = mergedList.get(mergedList.size() -1);
            if(pre[1] > intervals[count][0]){ //overlapping
                pre[1] = Math.max(pre[1], intervals[count][1]);
            }else{
                mergedList.add(intervals[count]);
            }
            
            count++;
        }
        
        int[][] mergedArray = mergedList.toArray(new int[mergedList.size()][]);
        return mergedArray;
    }
}
*/