
//2023-11-13
//Backtracking
//Time Complexity: O(n!/(k!(n-k)!)) <-- 조합(Combination)의 개수 일까??
//Space Complexity: O(K) , recursive call stack's max : K 
/*
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
        if(n <= 0 || k <=0 || k > n){
            return result;
        }
        
        //boolean[] checked = new boolean[n+1]; //필요없음. 
        List<Integer> list = new ArrayList<>(); 
        backTracking(1, n, k, list, result);
        return result;
    }
    
    void backTracking(int start, int end, int k, List<Integer> list, List<List<Integer>> result){
        if(list.size() == k){
            result.add(new ArrayList(list));
            return;
        }
        
        
        
        for(int i = start; i <= end; i++){
            //if(!checked[i]){
            //    checked[i] = true;
                list.add(i);                
                backTracking(i+1, end, k, list, result);  
                list.remove(list.size() -1);
            //    checked[i] = false;
            //}
        }
    }
}
*/

class Solution {
    private int n;
    private int k;
    
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), 1, ans);
        return ans;
    }
    
    public void backtrack(List<Integer> curr, int firstNum, List<List<Integer>> ans) {
        if (curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        
        int need = k - curr.size();
        int remain = n - firstNum + 1;
        int available = remain - need;
        
        for (int num = firstNum; num <= firstNum + available; num++) {
            curr.add(num);
            backtrack(curr, num + 1, ans);
            curr.remove(curr.size() - 1);
        }

        return;
    }
}