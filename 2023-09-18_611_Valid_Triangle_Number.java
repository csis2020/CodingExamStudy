 //2023-09-18
//limitation : Is the input array sorted? No

//아래는 leetcode Solutions 에 있던 것중 제일 이해가 잘 되었던 풀이 내용
//기본 idea: sorting 후 3sum 이용
//  기존 3sum 과의 차이는 여기는 제일 큰값부터 고정시켜서 나머지 2개의 작은 값을 찾아야 한다는 것이다.  그리고 삼각형을 만들기 위해서 nums[i] < nums[j] < nums[k] 일때, 
//  nums[i] + nums[j] > nums[k] 인지만 확인하면 됨. 왜냐하면 nums[k]가 제일 큰 값이기 때문에 nums[k] + nums[i] > nums[j]  와  nums[k] + nums[j] > nums[i] 는 무조건 성립하게 됨.( nums[k] > nums [j] > nums[i] 이니까. nums[k] 에 뭘 더하기 도 전에 이미 부등호가 > 로 성립.)
/*
[A similar O(n^2) solution to 3-Sum]
This problem is very similar to 3-Sum, in 3-Sum, we can use three pointers (i, j, k and i < j < k) to solve the problem in O(n^2) time for a sorted array, the way we do in 3-Sum is that we first lock pointer i and then scan j and k, if nums[j] + nums[k] is too large, k--, otherwise j++, once we complete the scan, increase pointer i and repeat.

For this problem, once we sort the input array nums, the key to solve the problem is that given nums[k], count the combination of i and j where nums[i] + nums[j] > nums[k] (so that they can form a triangle). If nums[i] + nums[j] is larger than nums[k], we know that there will be j - i combination.

Let's take the following array for example, let's mark the three pointers:
 i                  j   k
[3, 19, 22, 24, 35, 82, 84]

because 3 + 82 > 84 and the numbers between 3 and 82 are always larger than 3, so we can quickly tell that there will be j - i combination which can form the triangle, and they are:
3,  82, 84
19, 82, 84
22, 82, 84
24, 82, 84
35, 82, 84

Now let's lock k and point to 35:
 i          j   k
[3, 19, 22, 24, 35, 82, 84]

because 3 + 24 < 35, if we move j to the left, the sum will become even smaller, so we have to move pointer i to the next number 19, and now we found that 19 + 24 > 35, and we don't need to scan 22, we know that 22 must be ok!

class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int k = nums.length-1; k>1; k--) {
            int i = 0;
            int j = k-1;
            while (i<j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += j-i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }
}
*/

//리트코드 풀이대로 풀어보자. 
//Time Complexity: O(N^2)
//Space Complexity: O(logn) <--- sorting
// idea: nums[i] < nums[j] < nums[k] -> nums[i] + nums[j] > nums[k]
//  I will use 2-pointer way -> need to sort the array
// example: Input: nums = [2,2,3,4]
//      [2,2,3][4] -> [2,2][3]
//      nums[k]: 4 , 3
//      nums[i]:2 , 2
//      nums[j]:3 -> 2, 2
//      triplets: 2 -> 3
class Solution{
    public int triangleNumber(int[] nums){
        if(nums == null || nums.length <= 0){
            return 0;
        }

        Arrays.sort(nums);

//Input: nums = [4,2,3,4] -> [2,3,4,4]
// [2,3,4][4] -> [2,3][4]
// nums[k]: 4(3) , 4(2) , 3(1)
// nums[j]: 4(2) -> 3(1) -> 2(0), 3(1) -> 2(0), 2(0)
// nums[i]: 2(0) , 2(0), 2(0)
// triplets: 0, 2, 3, 4
        int triplets = 0;
        for(int i = nums.length -1; i >= 0; i--){
            int start = 0;
            int end = i -1;
            while(start < end){
                if(nums[start] + nums[end] > nums[i]){
                    triplets += end - start;
                    end--;
                }else{
                    start++;
                }
            }
        }
        return triplets;
    }
}

//---------------------------------------------------------
//아래 idea 는 Time Limit Exceeded 
//idea: recursive  check
//          => if (nums[i] + nums[j] > nums[k]) && (nums[i] + nums[k] > nums[j]) &&
//              (nums[k] + nums[j] > nums[i])

//Time Complexity: O(N^3) // N*(N-1)*(N-2) : 각 요소들에 대해서 모든 case 에 대해서 계산
//Space Complexity: O(1) //size of triplet is 3 (fixed value)
/*
class Solution {
    int count = 0;
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length <= 0){
            return 0;
        }

        //Arrays.sort(nums);

        List<Integer> triplet = new ArrayList<>();
        findTriplets(nums, 0, triplet);
        return count;
    }

    void findTriplets(int[] nums, int start, List<Integer> triplet){

        if(triplet.size() == 3){
            int first = triplet.get(0);
            int second = triplet.get(1);
            int third = triplet.get(2);
            if( (first + second) > third && (first + third) > second && (second + third) > first){
                count++;
            }
            return;
        }

        for(int i = start; i < nums.length; i++){
            triplet.add(nums[i]);
            findTriplets(nums, i+1, triplet);
            triplet.remove(triplet.size() -1);
        }
    }

}
*/