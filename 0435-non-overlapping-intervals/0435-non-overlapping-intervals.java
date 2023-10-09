//Similar questions
//  452. Minimum Number of Arrows to Burst Balloons  (premium)
//  이문제에서는 [1,2][2,3] -> 이게 overlapping 관계가 아닌가보다. 
// 어떤식으로 overlapping 되지? 
//  [[1,2],[1,3]] <-- 이런식으로 완전히 포함관계의 overlapping 인지 
//  [[1,5],[3,6]] <-- 이런식으로 걸치는 overlapping 도 있는건지 -> 이경우도 존재. 
//                  합치는건 아니고 overlapping 되는게 있으면 remove 만 함. 그럼 앞에것과 뒤엣것중 아무거나???
//  input: [[1,3],[4,7],[1,7]] -> output: 1
//  input: [[1,5],[3,8],[5,8]] -> output: 1
//  input: [[0,2],[1,3],[2,4],[3,5],[4,6]] -> output: 2

// 1) end 를 기준으로 sorting
//    : 이렇게 하는 이유는 겹치는것 제거할때, end 가 더 큰 것을 제거해야 
//      그뒤에 나올 세트들이 겹칠 확률이 줄어듬. 
//      ex) [3,5] [2,7] [5,8] <-- 이런경우, 0번 1번중, end 가 더큰 2번을 제거해야 2번과 겹치지 않게됨. 
//      ex) [3,5] [4,7] [5,8] <-- 이경우도 역시, 0, 1번중 end가 더 큰 1번을 삭제해야 유리. 
// 2) 0부터 n-1 까지 비교시작. 
//     2-1) (i-1)의 end <= (i)의 start 이면 겹치지 않으면 count 증가,
//          그리고 end = (i)의 end 로 업데이트
// 3) (원래 배열 길이 - count)구하면 => 제거한 개수      
//

//2023-10-04 
//idea: end 값을 기준으로 sorting 한뒤 겹치면 앞에 있는것 말고 뒤에 있는 것을 지우는게 확률상 최소로 지우며 non-overlapping 을 만들수 있다 =>  몰랐었음... 이걸 기억해야함. 
//Time Complexity: O(NlogN) <-- sort time 
//Space Complexity: O(N)<-- the space for sorting
/*
class Solution{
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals == null || intervals.length <= 0 || intervals[0].length <= 0){
            return 0;
        }
        
        Arrays.sort(intervals, (a,b)->(a[1]-b[1]) ); //sort by 'end' element
        
        int remove = 0;
        //int prevEnd = Integer.MIN_VALUE;
        //만약-2^31 <= xstart < xend <= 2^31 - 1 라고 boundary 가 정해진경우
        // prevEnd = Integer.MIN_VALUE 로 놓고 for 문을 0 부터 시작하면 
        //input: [[-2147483648,2147483647]] 인 경우 문제 생김. 
        int prevEnd = intervals[0][1];
        //for(int i = 0; i < intervals.length; i++){
        for(int i = 1; i < intervals.length; i++){
            if(prevEnd > intervals[i][0]){
                remove++;
            }else{
                prevEnd = intervals[i][1];
            }
        }
        return remove;
    }
}
*/

//intervals = [[1,2],[2,3],[3,4],[1,3]] => [[1,2],[2,3],[1,3],[3,4]]
class Solution{
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals == null || intervals.length <= 0){
            return 0;
        }
        
        Arrays.sort(intervals, (a,b)->(a[1]- b[1])); // ordered by the end position - ascending order
        int remove = 0;
        int pos = 0;
        for(int i = 1; i < intervals.length; i++){
            int[] current = intervals[i];
            int[] previous = intervals[pos];
            if(current[0] < previous[1]){
                remove++;
            }else{
                pos = i;
            }            
        }
        
        return remove;
    }
}

// [아래는 start 를 기준으로 sorting 한 경우 풀이 방법]
// end 로 sorting 한 경우는 prevEnd 가 당연히 더 작으나, 
// start로 sorting 한 경우는 어느쪽 end 가 더 작은지 알수가 없어서 prevEnd 가 curEnd 보다 크면 
// 더 작은 end 를 가르키도록 prevEnd 를 옮겨주어야 한다. 
/*
class Solution{
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals == null || intervals.length <= 0 || intervals[0].length <= 0){
            return 0;
        }
        
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]) ); //sort by 'start' element
        
        int remove = 0;
        //int prevEnd = Integer.MIN_VALUE;
        //만약-2^31 <= xstart < xend <= 2^31 - 1 라고 boundary 가 정해진경우
        // prevEnd = Integer.MIN_VALUE 로 놓고 for 문을 0 부터 시작하면 
        //input: [[-2147483648,2147483647]] 인 경우 문제 생김. 
        int prevEnd = intervals[0][1];
        //for(int i = 0; i < intervals.length; i++){
        for(int i = 1; i < intervals.length; i++){
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            if(prevEnd > curStart){
                remove++;
                // end 가 더 큰쪽을 remove 해야 한다.  (실제로 remove 하는게 아니라 check 대상에서 제외하는 효과 )
                // end 로 sorting 한 경우는 prevEnd 가 당연히 더 작으나, 
                // start로 sorting 한 경우는 어느쪽 end 가 더 작은지 알수가 없어서 prevEnd 가 curEnd 보다 크면 더 작은 end 를 가르키도록 prevEnd 를 옮겨주어야 한다. 
                if(prevEnd > curEnd){
                    prevEnd = intervals[i][1];
                } 
            }else {
                prevEnd = intervals[i][1];
            }            
            
        }
        return remove;
    }
}
*/
    
//2022-12-05
//Time Complexity: O(nlon) <-- sort time 
//Space Complexity: O(logn) or O(n) <-- the space for sorting
        /*
class Solution{
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals, (a,b)->(a[1] - b[1]));

        int count = 1; //제거없이 살아 남은 element 개수
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(end <= intervals[i][0]){
                end = intervals[i][1];
                count++;
            }
        }

        return (intervals.length -count);
    }
}
*/
/*
class Solution{
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        
        Arrays.sort(intervals, (a,b)->(a[1]-b[1]));
        
        int count = 1; //non-removed intervals
        int end = intervals[0][1];
        
        for(int i = 1; i < intervals.length; i++){
            if(end <= intervals[i][0]){
                end = intervals[i][1];
                count++;
            }
        }
        
        //removed intervals = total size - non-removed interval
        return (intervals.length - count);
    }
}
*/

//Time Complexity: O(nlon) <-- sort time 
//Space Complexity: O(logn) or O(n) <-- the space for sorting
/*
class Solution{
    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        
        //sort by ascendgin order of 'end'
        Arrays.sort(intervals, (a,b)->(a[1] - b[1]));
        
        int size = intervals.length;
        int count = 1;
        int end = intervals[0][1];
        
        //find non-overlapping 
        for(int i = 1; i < size; i++){
            if(intervals[i][0] >= end){
                end = intervals[i][1]; //update end value
                count++;
            }
        }
        
        // size -count = the number of overlapping that need to remove 
        return size - count;
    }
}
*/
/*
//idea: 
//1) sort by the ascending order of 'start'
//2) set 'each nonOverlap' as 1
//3) compare previous 'end' continuously to find 'end' <= 'start' -> then count++
//4) total number - count = minimum number of intervals to remove

//Time Complexity: O(n^2)
//Space Complexity: O(n)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        
        //sort by ascending order of 'start'
        Arrays.sort(intervals, (a,b)->(a[0] - b[0]));
        
        int nonOverlap = 1;
        int[] maxNonOverlap = new int[intervals.length];
        maxNonOverlap[0] = 1;
        
        for(int i = 1; i < intervals.length; i++){
            maxNonOverlap[i] = 1;
               for(int j = i-1; j >=0; j--){
                   if(intervals[i][0] >= intervals[j][1]){ //non-overlapping
                       
                        maxNonOverlap[i] = maxNonOverlap[j] +1;
                        break;                 
                   }
               }
        }
        
        return intervals.length - maxNonOverlap[intervals.length-1];
        
    }
    
}
*/