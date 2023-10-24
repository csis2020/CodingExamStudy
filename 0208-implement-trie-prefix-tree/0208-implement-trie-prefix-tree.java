//====Question==================================================================
/*
class Trie {

    public Trie() {
        
    }
    
    public void insert(String word) {
        
    }
    
    public boolean search(String word) {
        
    }
    
    public boolean startsWith(String prefix) {
        
    }
}
*/
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//============================================================================

//2023-10-23

//HashMap 의 함수 모양을 참조해서 TrieNode 만듬. 
class TrieNode{
    
    private final int LENGTH = 26; // number of character
    private TrieNode[] link;
    private boolean isEnd;
    
    public TrieNode(){
        link = new TrieNode[LENGTH];
        isEnd = false;
    }
    
    public boolean containsKey(char key){
        return link[key - 'a'] != null;
    }
    
    public void put(char key, TrieNode node){
        link[key - 'a'] = node;
    }
    
    public TrieNode get(char key){
        return link[key - 'a'];
    }
    
    public void setEnd(){
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
    
}

class Trie {

    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char key = word.charAt(i);
            if(!node.containsKey(key)){
                node.put(key, new TrieNode());
            }
            node = node.get(key);
        }
        node.setEnd();
    }
    
    public boolean search(String word) {

        TrieNode lastNode = getLastNode(word);
        
        return lastNode == null ? false : lastNode.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode lastNode = getLastNode(prefix);
        return  lastNode != null;
    }
    
    //If the input word is not contained, return null. 
    //else, return the last linked node.
    private TrieNode getLastNode(String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char key = word.charAt(i);
            if(node.containsKey(key)){
                node = node.get(key);
            }else{
                return null;
            }
           
        }
        return node;
    }
}



//input example
/*
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
*/

//2022-12-1  
//leetcode 솔루션을 보니 내가 이해를 완전 잘못했다.  
// 나는 하나의 node 에 string 을 value 로 넣었는데 leetcode 에서는 한 노드에 char 하나 저장
//insert 때 같은 값이 올수 있는지???
// Time Complexity: insert 와 search 모두 O(logN)
// Space Complexity: O(입력으로 들어온 string 의 길이 합) 
/*
class Trie {

    class TreeNode{
        String val;
        TreeNode left;
        TreeNode right;
        TreeNode(){

        }
        TreeNode(String val){
            this.val = val;
        }
    }

    TreeNode root;

    public Trie() {

    }
    
    public void insert(String word) {
        if(root == null){
            root = new TreeNode(word);
        }else{
            TreeNode node = root;
            while(node != null){
                int result = node.val.compareTo(word);
                if(result < 0){ // left tree
                    if(node.left == null){
                        node.left = new TreeNode(word);
                        break;
                    }else{
                        node = node.left;
                    }
                }else if(result > 0){ //right tree
                    if(node.right == null){
                        node.right = new TreeNode(word);
                        break;
                    }else{
                        node = node.right;
                    }                  
                }else{ //already existed
                    break;
                }                
            }
        }
    }
    
    public boolean search(String word) {
        TreeNode node = root;
        while(node != null){
            int result = node.val.compareTo(word);
            if(result == 0){
                return true;
            }else if(result < 0){
                node = node.left;
            }else{ //result > 0
                node = node.right;
            }
        }

        return false;
    }
    
    public boolean startsWith(String prefix) {
        TreeNode node = root;
        while(node != null){
            if(node.val.startsWith(prefix)){
                return true;
            }

            int result = node.val.compareTo(prefix);
            if(result < 0){
                node = node.left;
            }else{ //result > 0
                node = node.right;
            }
        }

        return false;
    }
}
*/
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */