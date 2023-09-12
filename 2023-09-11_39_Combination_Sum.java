//2023-September-11
//limitation: Are All of candidates distinct? -> yes
//            the candidates are ascending order? -> maybe not. 
//              target is bigger than zero? -> yes 
// 이 문제의 핵심은 
//  1) candidates 숫자들이 distinct 하다는 것 
//  2) candidates 숫자들을 반복사용할수 있다
//  3) candidates 숫자들로 만들어낸 조합이 unique 해야 한다
// ==> 그러다보니 사용할수 있는 솔루션이 recursive 가 되네...
// ==> iterative 한 솔루션도 가능할수 있겠으나 나는 모르겠음... recursive 만 해봄. 
// for 문으로 candidates 첫번째인자부터 recursive 들어가고 더이상 없으면 return. 그다음 recursive는 다음 인자부터 ( 이렇게 하면 이미 체크가 끝난 인자는 제외되기때문에 만들어낸 조합들이 unique 해짐)
// example : candidates = [2,3,6,7],  target = 7
// [2, 3, 6, 7]  2 (x), 2->2 (x), 2 -> 2-> 2 (x), 2 -> 2-> 3 (O), ... , 
//               2->3 (x), 2->3->6 (x),  2->3->7(x)
//               2->6 (x), 2-7 (x)
// [3,6,7 ] 3 (x), 3->3(x), 3-3-3(x), ...
//          3->6(x), ...
// [6, 7] 6(x), 6-6(x), ...
// [7] -> 7(O) 
// ==> [2, 2,3], [7]  

//Input: candidates = [2,3,5], target = 8 
//[2,3,5] : 2, 2-2, 2-2-2 (O), 2-2-3, ....
//          2-3, 2-3-3        


//Time Complexity: O(N^(t/m + 1)) : 'N' is the number of candidates. 't' is the target, 'm' is the minimal value among the candidates. 
//Space Complexity: O(t /m) : heights of tree
/*
Let NNN be the number of candidates, TTT be the target value, and MMM be the minimal value among the candidates.
Time Complexity: O(N^(T/M+1))

As we illustrated before, the execution of the backtracking is unfolded as a DFS traversal in a n-ary tree.
The total number of steps during the backtracking would be the number of nodes in the tree.
At each node, it takes a constant time to process, except the leaf nodes which could take a linear time to make a copy of combination. So we can say that the time complexity is linear to the number of nodes of the execution tree.

Here we provide a loose upper bound on the number of nodes.

First of all, the fan-out of each node would be bounded to NNN, i.e. the total number of candidates.

The maximal depth of the tree, would be T/M, where we keep on adding the smallest element to the combination.
As we know, the maximal number of nodes in N-ary tree of T/M height would be N^(t/m + 1)

Note that, the actual number of nodes in the execution tree would be much smaller than the upper bound, since the fan-out of the nodes are decreasing level by level.
*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length <= 0 || target <= 0){
            return result; //return empty list
        }

        List<Integer> combination = new ArrayList<>();
        combination(candidates, target, 0, combination, 0, result);

        return result;
    }

    //Input: candidates = [2,3,5], target = 8
    //
    //     [3, 5 ] 
    //sum: 2, 4, 6, 8, ..., 4, 7, 10, 4,  9, 5, 9, 
    //result:  [2, 2, 2, 2] , [2, 3, 3], [3, 5]
    void combination(int[] candidates, int target, int start, List<Integer> combination, int sum, List<List<Integer>> result){

        if(sum == target){
            result.add(new ArrayList(combination));
            return;
        }else if(sum > target){
            return;
        }

        for(int i = start; i < candidates.length; i++){
            combination.add(candidates[i]);
            combination(candidates, target, i, combination, sum + candidates[i], result);
            combination.remove(new Integer(candidates[i]));
        }
    }
}


// 아래 솔루션을 iteration 으로는 푸는 방법을 모르겠다. 풀수 있는지조차 모르겠다. 
// leetcode 솔루션에도 recursive 만 나와있음. 

//2022-12-04 - recursive : DP-Top down
//Time Complexity:  O(N^(target/min + 1)) <- N is candidates.length, 
//        min is the minimum number of candidates.  target/min + 1 = tree height
//      매 트리마다 sub tree 에서 다시 candidates 를  체크함. ( 정확히는 1개씩 줄어든 candidates 이긴 하다.) 
// Space Complexity: O(target/min)  <-- recursive calls stack 은 tree의 height
/*
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0 || target <= 0){
            return result;
        }   

        LinkedList<Integer> comb = new LinkedList<>();
        findCombination(candidates, target, comb, 0, result);     

        return result;
    }

    private void findCombination(int[] candidates, int target, LinkedList<Integer> comb, int start, List<List<Integer>> result){

        if(target < 0){
            return;
        }
        if(target == 0){ //find combination
            result.add(new ArrayList<>(comb));
        }

        for(int i = start; i < candidates.length; i++){
            comb.add(candidates[i]);
            findCombination(candidates, target - candidates[i], comb, i, result);
            comb.removeLast();
        }
    }
}
*/