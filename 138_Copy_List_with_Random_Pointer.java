/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

//2022.09.14
//Time Complexity: O(n), Space Complexity: O(n)
class Solution {
    public Node copyRandomList(Node head) {
        
        if(head == null){
            return null;
        }
        
        Node copiedLists = new Node(0);
        Node copiedNodeHead = copiedLists;
       // Node inputNodeHead = head;
        
        
        HashMap<Node, Node> nodeMap = new HashMap<Node, Node>(); //key: org node, value: copied node
        
        //First, construct a deep copy of the list based on (val , next)
        while(head != null){
            
            Node copiedNode = null;
            Node copiedRandomNode = null;
            //check whether there is copied node 
            if(nodeMap.containsKey(head)){
                copiedNode = nodeMap.get(head); 
                
            }else{// there is no copied node
                copiedNode = new Node(head.val);
                nodeMap.put(head, copiedNode);
            }

            //Check whether there is copied random node
            if(head.random != null){
                if(nodeMap.containsKey(head.random)){
                    copiedRandomNode = nodeMap.get(head.random); 

                }else{// there is no copied random node
                    copiedRandomNode = new Node(head.random.val);
                    nodeMap.put(head.random, copiedRandomNode);
                }
            }
            
            copiedLists.next = copiedNode;
            copiedLists.next.random = copiedRandomNode;     
            
            head = head.next;
            copiedLists = copiedLists.next;
        }
        
        return copiedNodeHead.next;
    }
}
/*
class Solution {
    public Node copyRandomList(Node head) {
        
        if(head == null){
            return null;
        }
        
        Node copiedLists = new Node(0);
        Node copiedNodeHead = copiedLists;
        Node inputNodeHead = head;
        
        
        HashMap<Node, Node> nodeMap = new HashMap<Node, Node>(); //key: org node, value: copied node
        
        //First, construct a deep copy of the list based on (val , next)
        while(head != null){
            Node tempNode = new Node(head.val); //deep copy of the Node.
            nodeMap.put(head, tempNode);
            copiedLists.next = tempNode;
            copiedLists = copiedLists.next;            
            head = head.next;
        }
        
        //Second, connect the random nodes
        head = inputNodeHead; 
        copiedLists = copiedNodeHead.next;
        while(head != null){
                       
            if(head.random != null){
                
                copiedLists.random = nodeMap.get(head.random);
            }
            copiedLists = copiedLists.next;
            head = head.next;
        }
        
        return copiedNodeHead.next;
    }
}
*/