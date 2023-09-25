/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

//2023-09-24
//Idea: Using HashMap => Map<Node, Node> = new HashMap<>();
//          'Key' is original node, 'Value' is clone node.
//Time Complexity: O(N+E) , N = 노드수, E = edge 수 (간선수)
/*  
DFS가 총 N번 호출되긴 하지만 인접행렬과 달리 인접 리스트로 구현하게 되면 DFS하나당 각 정점에 연결되어 있는 간선의 개수만큼 탐색을 하게 되므로 예측이 불가능 하다. 하지만 DFS가 다 끝난 후를 생각하면, 모든 정점을 한번씩 다 방문하고, 모든 간선을 한번씩 모두 검사했다고 할 수 있으므로 O(n+e)의 시간이 걸렸다고 할 수 있다.
*/
//Space Complexity: O(N)
따라서 시간복잡도는 O(n+e)이다.
class Solution{
    public Node cloneGraph(Node node){
        if(node == null){
            return null;
        }
        
        Map<Node, Node> cloneMap = new HashMap<>(); // <original node, copied node>
        
        Node cloneNode = makeClone(cloneMap, node);
        
        return cloneNode;
    }
    
    // 
    //HashMap: <1,(2,4)> <2, (1, 3)> <3,(2, 4)> <4,(1,3)>
    Node makeClone(Map<Node, Node> cloneMap, Node node){
        
        if(cloneMap.containsKey(node)){
            return cloneMap.get(node);
        }
        
        Node cloneNode = new Node(node.val);
        cloneMap.put(node, cloneNode); // neighbors 를 recursive 로 찾기전에 HashMap 에 먼저 넣어야, 무한 루프가 안생긴다. 
        for(int i = 0; i < node.neighbors.size(); i++){
            Node neighbor = node.neighbors.get(i);
            Node cloneNeighbor = makeClone(cloneMap, neighbor);
            cloneNode.neighbors.add(cloneNeighbor);
        }
        //cloneMap.put(node, cloneNode); // HashMap 에 나중에 넣으면, cycle 이 생기고 무한반복 -> cloneMap 안에 1번노드 ->2번노드 ->1 번노드 -> ...
        
        return cloneNode;        
    }
}

//2022.10.21
//Limitations: if node is null, return null
//Time Complexity : O(N + M) <-- 이게 왜 N + M 인지 모르겠따.  (dfs 알고리즘의 time complexity 가 원래 그렇다는데???)
//Space Complexity: O(N)
/*
class Solution{
    
    private HashMap<Node, Node> cloneNodeMap = new HashMap<Node, Node>();
    
    public Node cloneGraph(Node node){
        
        if(node == null){
            return null;
        }
        
        return makeCloneNode(node);
    }
    
    private Node makeCloneNode(Node node){
        
        if(cloneNodeMap.containsKey(node)){
            return cloneNodeMap.get(node);
        }
        
        Node newNode = new Node(node.val, new ArrayList<Node>());
        cloneNodeMap.put(node, newNode);
        
        for(Node neighbor: node.neighbors){
            Node clone = makeCloneNode(neighbor);
            newNode.neighbors.add(clone);
        }
        
        return newNode;
    }
}
*/

/*
class Solution {
    
    private HashMap<Node, Node> visited = new HashMap<Node, Node>();
    
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        
        Node cloneNode = new Node(node.val, new ArrayList<Node>());
        visited.put(node, cloneNode);

        for(Node neighbor : node.neighbors){
            Node nbNode = cloneNeighbor(neighbor);
            cloneNode.neighbors.add(nbNode);
        }
        
        return cloneNode;
    }
    
    private Node cloneNeighbor(Node node){
        
        if(visited.containsKey(node)){
            return visited.get(node); //return cloneNode;    
        }
        
        Node cloneNode = new Node(node.val, new ArrayList<Node>());
        visited.put(node, cloneNode);  
        
        for(Node neighbor : node.neighbors){
            Node nbNode = cloneNeighbor(neighbor);
            cloneNode.neighbors.add(nbNode);
        }
        
        return cloneNode;
    }
    
}
*/