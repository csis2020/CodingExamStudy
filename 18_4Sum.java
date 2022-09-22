//2022-09-22
//Input: [2, 2, 2, 2, 2] , target = 8 -> output: [2, 2, 2, 2]
//Input: [1000000000,1000000000,1000000000,1000000000], target= -294967296  -> output: []
//          -> sum = nums[i] + nums[j] + nums[min] + nums[max] 로 하면 integer 범위 초과
//          -> 1000,000,000 를 4번 더하면 4,000,000,000 인데 
//          -> integer 범위는 -2,147,483,648 ~ 2,147,483,647
//          -> 4,000,000,000 를 integer 연산으로 더하면 결과값이 -294967296 가 됨.
//          -> long 을 이용해야함. 

// 내가 짠 해법은 4sum 만 구현 (3Sum 을 이용해 outer loop 을 하나 더)
// leetcode 해법에서는 4sum 뿐아니라 5sum, 6sum, .... k-sum 까지(outer loop 증가) 대응할수 있도록 코드를 짜야한다고 그런 방식으로 코딩이 되어있음. 그런데 코드가 살짝 복잡해져서 ... 더 깊게 보지는 않았음.

//Time Complexty : O(n^3), Space Complexity : O(logn)~O(n) <-- sorting 알고리즘에 따라 


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> totalLists = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 4){
            return totalLists;
        }
       
        Arrays.sort(nums);
        
        long longTarget = Long.valueOf(target);
        
       
        for(int i = 0; i < nums.length -1 ; i++){
            //int threeSumTarget = target - nums[i];
            
            if( i > 0 && nums[i] == nums[i-1]){
                //to avoid duplicated cases
                continue;
            }
            
            for(int j = i+1 ; j < nums.length -1 ; j++){
               //int twoSumTarget = threeSumTarget - nums[j];
               if(j > (i+1) && nums[j] == nums[j-1]){
                    //to avoid duplicated cases
                    continue;
               }
                
                int min = j + 1; 
                int max = nums.length - 1;
                
                while(min < max){
                    //int twoSum = nums[min] + nums[max];
                    //long tempSum = nums[i]+nums[j]+nums[min]+nums[max]; <-- int 연산으로 계산후 그 결과값인 -294967296 가 저장됨.
                    System.out.println(tempSum);
                    long sum = Long.valueOf(nums[i]) + Long.valueOf(nums[j]) + Long.valueOf(nums[min]) + Long.valueOf(nums[max]);
                    if( sum == longTarget){
                        //find the quadruplets
                        List<Integer> eachList = Arrays.asList(nums[i], nums[j], nums[min], nums[max]);
                        totalLists.add(eachList);
                        //move the positions
                        min++;
                        max--;
                        //if the nums[min] or nums[max] are same value as its previous value, move the positons again to avoid the dupulicated cases.
                        while((min < (nums.length -1)) && nums[min-1] == nums[min]){
                            min++;
                        }
                        while(min < max && nums[max] == nums[max+1]){
                            max--;
                        }
                        //System.out.println("["+i+","+j+"]:min="+min+", max="+max);
                    }else if(sum < longTarget){
                        min++;
                    }else{//sum > (long)target
                        max--;
                    }
                }
            }
        }
        
        return totalLists;
        
    }
}