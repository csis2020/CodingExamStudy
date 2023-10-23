
//2023-10-22

//Idea : dynamic programming
//n개의 node들이 나열되어 있다고 할때, 
// a) i번째 node 가 root 인경우의 가능한 tree 개수는 =  (0~ i-1) 은 left sub tree, (i+1 ~ n) 노드는 right subtree 가 되는 경우의 수를 곱한 것 
// b) i 를 1부터 해서 n 까지 a)를 구해 다 더하면 이것이 최종구하려는 n개 노드에 대한 binary search tree 구성가능한 경우이다. 
// ==> 정리하면 
//  f(0) = 1, f(1) = 1 (즉, tree 가 null 노드일 경우는 1가지, 1개 노드가질 경우 1가지)
//  f(2) = f(0)*f(1) + f(1)*f(0)
//  f(3) = f(0)*f(2) + f(1)*f(1) + f(2)*f(0)
//  ...
//  f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

class Solution {
    public int numTrees(int n) {
        if(n <= 0){
            return 0;
        }
        
        int[] totalTrees = new int[n+1];
        totalTrees[0] = 1;
        totalTrees[1] = 1;
        for(int i = 2; i <= n; i++){
            int total = 0;
            for(int j = 0; j < i; j++){
                total += totalTrees[j]*totalTrees[i-j-1];
            }
            totalTrees[i] = total;
        }
        return totalTrees[n];
    }
}