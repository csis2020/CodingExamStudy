

//2023-10-05
// idea: First, sort the intervals by the start time. 
//      check if [i-1]'s end  <= [i]'s start
// 
// Time Complexity: O(NlogN) <-- by sorting
// Space Complexity: O(N) <--- by sorting
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
       if(intervals == null || intervals.length <= 0 ){
           return true;
       } 
        
        Arrays.sort(intervals, (a,b)->(a[0]-b[0])); //sort by the start, ascending order
        
        
        for(int i = 1; i < intervals.length; i++){
            int[] prevMeeting = intervals[i-1];
            int[] currMetting = intervals[i];
            if(prevMeeting[1] > currMetting[0]){
                return false;
            }
        }
        
        return true;
    }
}