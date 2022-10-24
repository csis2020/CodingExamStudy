//2022-10-24
// Limitation : if n is less than 1, return 0? 
//              if edges is null or size is zero, return all pairs???
// 계산할때 long 으로 바꾸어서 시작하지 않으면 아래 input 의 경우처럼 error 나옴. 
//input : 100000 , []   -> output : 704982704 (Expected: 4999950000 ) 로 값이 잘못나옴. 
//Time Complexity: O(V + E) <--V is the number of node, E is the number of dependencies (= edges). 
//Space Complexity: O(V + E) <--- same denotation as in the above time complexity
//좀더 간결하게 코드를 만드는 버전 ( Discuss 의 내용 참조)
class Solution{
    
    private List<List<Integer>> adjacentList = new ArrayList<List<Integer>>();
    boolean[] visited;
    public long countPairs(int n, int[][] edges){
        
        if(n <= 0){
            return 0;
        }
        
        long totalNode = n; 
        
        if(edges == null || edges.length == 0){
            return totalNode * (totalNode -1) / 2;
        }
        
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            adjacentList.add ( new ArrayList<Integer>());
        }
        
        //make the adjacentlist 
        for(int i = 0; i < edges.length; i++){
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            //undirected graph. 
            adjacentList.get(node1).add(node2);
            adjacentList.get(node2).add(node1); 
        }
        
        long totalPairs = 0; 
        for(int i = 0; i < adjacentList.size(); i++){
            int count = 0;
            if(!visited[i]){
                count = dfs(i, count);
                totalNode = totalNode - count; 
                totalPairs += count * totalNode;                
            }
        }
        
        //아래와 같은 식으로 풀어야 totalPairs 가 나오는 것인데 
        // 이렇게 하면 O(n^2) 으로 Time Limit Exceed 가 나옴. 그래서 위처럼 풀어서 O(n) 으로 계산해야함. 
        //for(int i=0; i<sizes.length; i++)
        //    for(int j=i+1; j<sizes.length; j++)
        //        res += sizes[i]*sizes[j];
        
        return totalPairs;
    }
    
    private int dfs(int node, int count){
        
        if(visited[node]){
            return count;
        }
        
        visited[node] = true;
        count++;
        for(int member : adjacentList.get(node) ){
            count = dfs(member, count);
        }
        
        return count;
    }
}

//아래도 동작은 잘 됨. 
//Time Complexity : (V + E) <-- 인접 리스트를 이용한 DFS 
//Space Complexity: (V + E) <-- 리스트 구성에 든 space
/*
class Solution {
    
    private HashMap<Integer, List<Integer>> adjacentList = new HashMap<>();
    private List<List<Integer>> nodeGroup = new ArrayList<>();
    boolean[] checked; 
    boolean[] visited;
    public long countPairs(int n, int[][] edges) {
        
        if(n <= 0){
            return 0;
        }
        //아래처럼 바꾸지 않으면 
        //input : 100000 , []   -> output : 704982704 (Expected: 4999950000 ) 로 값이 잘못나옴. 
        if(edges == null || edges.length == 0){
            long result = n; 
            return (result*(result-1)/2);
        }
        
        checked = new boolean[n];
        visited = new boolean[n];
        
        //make adjacent list map. important: it is undirected graph. 
        for(int i = 0; i < edges.length; i++){
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if(adjacentList.containsKey(node1)){
                adjacentList.get(node1).add(node2);
            }else{
                List<Integer> list = new ArrayList<Integer>(Arrays.asList(node2));
                adjacentList.put(node1, list);
            }
            
            if(adjacentList.containsKey(node2)){
                adjacentList.get(node2).add(node1);
            }else{
                List<Integer> list = new ArrayList<Integer>(Arrays.asList(node1));
                adjacentList.put(node2, list);
            }
            
        }
        
        for(int i = 0; i < n ; i++){
            if(!checked[i]){
                List<Integer> newGroup = new ArrayList<>();
                nodeGroup.add(newGroup);
                dfs(i, newGroup);
            }
        }
        
        long totalPairs = 0; 
        
        //아래처럼 하면 Time Limit Exceed 나옴. 
       
        //for(int i = 0; i < nodeGroup.size(); i++){
        //    int size = nodeGroup.get(i).size();
        //    for(int j = i+1; j < nodeGroup.size(); j++){
        //        totalPairs += size * nodeGroup.get(j).size();
        //    }
       // }
       
        
        //수정결과. 
        long sum = n;
        for(int i = 0; i < nodeGroup.size(); i++){
            int size = nodeGroup.get(i).size();
            sum = sum - size; 
            totalPairs += sum * size;
        }
        
        return totalPairs;
    }
    
    private void dfs(int node, List<Integer> group){
        
        if(checked[node]){ //already checked
            return;
        }
        
        
        if(visited[node]){ //avoid to visit again
            return;
        }
        
        group.add(node); 
        
        if(!adjacentList.containsKey(node)){ // this node doesn't have other group member. 
            checked[node] = true;
            return;
        }
        
        List<Integer> adjacent = adjacentList.get(node);
        
        visited[node] = true;
        for(int member: adjacent){
            dfs(member, group);
        }
        visited[node] = false;
        
        checked[node] = true;
    }
}
*/