
//2028-09-15
//limitation: Arrays has distinct numbers? -> No.
//idea: time complexity 를 O(N)으로 해야하기때문에 sorting 방식 (O(NlogN))은 사용할 수 없다. 따라서 HashSet을 사용하는 방법을 이용 -> 이미 check 한 것을 다시 check 하지 않기 위해서  nums[i] 를 체크할때 nums[i] -1 의 숫자가 있으면 skip 하는 방식으로  증가하는 값이 있는 경우만 체크하도록 만든다. 
//I will use HashSet to get the time compleixty O(N)

//Time Complexity : O(N)
//Space Complexity: O(N)
//HashSet 의 단점은  nums[] 배열에서 같은 값이 존재하면  (ex . Input: nums = [0,3,7,2,5,8,4,6,0,1] <-- '0' 이 2번 존재 )  '0' 부터 시작하는 증가하는 값을 2번 check 하게 됨. 
//만약 HashMap 으로 Map<Integer, Boolean> 으로 해서 이미 check 된 값인지 보면... 메모리는 늘어나겠지만 반복 check 하는 경우를 피할수 있음. 
class Solution{
    public int longestConsecutive(int[] nums){
        if(nums == null || nums.length <= 0){
            return 0;
        }

        //Set<Integer> elements = new HashSet<>();
        Map<Integer, Boolean> elements = new HashMap<>();

        for(int num: nums){
            //elements.add(num);
            elements.put(num, false);
        }

        //Input: nums = [100,4,200,1,3,2]   
        //max   : 1 , 4   ===> 4
        //count : 1 , 2 ->3 -> 4
        //consec : 101,  201, 2-> 3 -> 4 -> 5
        int max = 0;
        int startNum = 0; // to save longest consecutive sequence array
        for(int num: nums){
            //if(!elements.contains(num -1)){
            if(!elements.containsKey(num-1) && !elements.get(num)){ // it should be start number and never checked before
                
                elements.put(num, true);
                int count = 1;
                int consecutive = num +1;
                //while(elements.contains(consecutive)){
                while(elements.containsKey(consecutive)){
                    count++;
                    consecutive++;
                }

                max = Math.max(count, max);
                if(count > max){
                    startNum = num;
                }
            }

        }

        //for saving the longest consecutive sequence array
        /*
        int[] longestArray = new int[max];
        int i = 0;
        while(elements.contains(startNum)){
            longestArray[i++] = startNum;
            startNum++; 
        }
        */

        return max;
    }
}


//2023-01-18
//input: [100, 4, 200, 1, 3, 2] -> output: 4
//input: [0, 3, 7, 2, 5, 8, 4, 6, 0, 1] -> output: 9
//[idea] -> Add all nums to HashSet , then check one by one element in nums[i]
// if there is (nums[i]-1) number, skip. if not, check whether there is consecutive 
// increasing number and save the count
//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution{
    public int longestConsecutive(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        HashSet<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }

        int longestSize = 0; 
        List<Integer> result = new ArrayList<>(); // longest 배열을 저장해야 하는 경우 
        int startElement = 0; // 제일긴 배열을 return 해야할 경우 시작지점 저장을 위해 
        for(int num: nums){
            if(!set.contains(num -1)){ // to check only once. to avoid cyclic checking
                int currentSize = 1;
                int element = num + 1;
                while(set.contains(element)){
                    currentSize++;
                    element++;
                }
                //longestSize = math.Max(longestSize, currentSize);
                if(longestSize < currentSize){
                    startElement = num;
                    longestSize = currentSize;
                }
            }
        }

       
       // while(set.contains(startElement)){
        //    result.add(startElement);
        //    startElement++;
       // }
       // Integer[] resultArray1 = new Integer[result.size()]; //이방식은 int[] 는 안되네. 
       // result.toArray(resultArray1);
       // int[] resultArray = new int[result.size()];

       // for(int i = 0; i < resultArray.length; i++){
       //         resultArray[i] = result.get(i);
       //         System.out.print(resultArray[i] + " ");
       // }
       

        return longestSize;
    }
}
*/


//Similar question: 
//  2274. Maximum Consecutive Floors Without Special Floors
//  298. Binary Tree Longest Consecutive Sequence

//2022.10.24
//Limitation : if nums is null or size is zero, return 0?

//input: nums = [100,4,200,1,3,2] -> output : 4
//input: nums = [0,3,7,2,5,8,4,6,0,1] -> output: 9

//Idea : Using HashSet (no sorting algorithm. )

//Time Complexity : O(n)
//Space Complexity: O(n)
/*
class Solution {
    public int longestConsecutive(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        //make HashSet to find some number with O(n) time complexity
        HashSet<Integer> numSet = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            numSet.add(nums[i]);
        }
        
        int longest = 1;
        //check the consecutive sequence
        for(int num : nums){

            if(!numSet.contains(num-1)){ //first number of some consecutive sequence
                int consecutive = 1;
                int next = num+1;
                while(numSet.contains(next)){
                    consecutive++;
                    next++;
                }
                
                longest = Math.max(consecutive, longest);
            }
        }
        
        return longest;
    }
}
*/