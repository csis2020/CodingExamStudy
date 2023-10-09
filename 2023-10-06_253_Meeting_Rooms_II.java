//Limitations: intervals' size can be 0 
// Input: intervals = [[0,30],[5,10],[15,20]] ==> Output: 2
// Input: intervals = [[7,10],[2,4]] ==> Output: 1

//Leetcode solution
//Time complexity: O(NlogN) -> array sorting time, Space complexity: O(N) ->PriorityQueue's worst case size

//2023-10-06
//Idea: Array Sorting + PriorityQueue<Integer>  <--Priority queue 에는 end time 이 들어감 
//Time Complexity: O(NlogN)=> sorting:O(NlogN), min-heap의 offer/peek/poll operation:O(logN) 인데 
//                  이걸 input array 사이즈만큼 반복하니까 O(N * logN) 
//                 min-heap에서 임의숫자 search, remove 의 경우 O(N)인데 여기선 이 연산이 쓰이진 않았음.  
//Space Complexity:O(N) => max(sorting:O(logN)~O(N), min-heap size: O(N))
class Solution{
    public int minMeetingRooms(int[][] intervals){
        if(intervals == null || intervals.length <= 0){
            return -1; 
        }
        
        Arrays.sort(intervals, (a,b)->(a[0]- b[0]));//sort by the start time -ascending order
        PriorityQueue<Integer> meetingRooms = new PriorityQueue<>(); //default - min heap
        
        meetingRooms.offer(intervals[0][1]); //meetingRooms.add(intervals[0][1]); 이라고 써도 됨. 
        
        //intervals = [[0,30],[5,10],[15,20]]       intervals = [[7,10],[2,4]] => [[2,4],[7,10]]
        //PQ:(30)->(10,30)->(30)->(20,30) => size:2         PQ:(4)->()->(7) => size:1
        for(int i = 1; i < intervals.length; i++){
            int[] meeting = intervals[i];
            if(meeting[0] >= meetingRooms.peek()){
                meetingRooms.poll();
            }
            meetingRooms.offer(meeting[1]);
        }
        
        return meetingRooms.size();
    }
}

/*
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }
            
        //sort arrays ( ascending order of start time)
        Arrays.sort(intervals, (a,b)->(a[0] - b[0]));
        
        //using default order ( ascending)
        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();
        //add first end time. 
        endTimeQueue.add(intervals[0][1]);
        
        //check end time 
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= endTimeQueue.peek()){
                endTimeQueue.poll();
            }
            endTimeQueue.add(intervals[i][1]);
        }
        
        // The size of the heap tells us the minimum rooms required for all the meetings.
        return endTimeQueue.size();
    }
}
*/