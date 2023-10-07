

//2023-10-06
//idea1: Array sort
//idea2: Priority Queue

//Idea1: Array Sort
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length <= 0 || k < 1){
            return -10001;
        }
        
        //1차원 배열을 입력으로 받는 경우, Comparator 를 따로 넣을수가 없다. Comparator 의 입력타입이 
        // object 이어야 함. int[] nums 가 아니라 Integer[] nums 이면 동작됨. 
        // 되게 하는 방법이 있을까? 나중에 고민해봐야할 듯. 
        //Arrays.sort(nums, (a,b)->(b-a)); <-- 동작 안함. 에러발생
        //Arrays.sort(nums, Collections.reverseOrder()); <--- 이동작 안함. 에러발생
        Arrays.sort(nums);
        
        return nums[nums.length -k];
    }
    
}