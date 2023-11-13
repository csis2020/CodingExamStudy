
//2023-11-13
//Backtracking
//Time Complexity: O(k*n!/(k!*(n-k)!)) <-- 조합(Combination)의 개수 n!/(k!*(n-k)!) * k => 여기서 k 는 => because at each of the leaves, we need to perform O(k) work to create a copy of current list to add to the answer.
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
            list.add(i);                
            backTracking(i+1, end, k, list, result);  
            list.remove(list.size() -1);
        }
    }
}
*/

//아래 Pruning 으로 속도개선이 많이 됨. <--- 신기하다... 보통은 저정도로 개선이 안되던거 같은데... 이 문제의 case 같은 경우 속도개선이 많이 되나보다...
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
        
        int need = k - list.size();
        int updatedEnd = end - need + 1;
        for(int i = start; i <= updatedEnd ; i++){
            list.add(i);                
            backTracking(i+1, end, k, list, result);  
            list.remove(list.size() -1);
        }
    }
}

// pruning 에 의한 optimization 으로  leetcode 방법.
/*
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
*/