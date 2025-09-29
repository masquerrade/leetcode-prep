class Trie {

    //I need to create a trie node inside the trie class
    //Trie starts with a root node -> What is this root node
    //It consists of trie nodes 

    class TrieNode{

        //What does a trie node have 
        // Each trie node has an array of trie nodes whose index will correspond to the character and whose values will be the next trie node
        // And one more flag to indicate whether the current node is the last node
        private TrieNode [] children;
        public boolean isEndOfWord;

        public TrieNode(){
            children=new TrieNode[26];
            isEndOfWord=false;
        }


    }

     // I'm making trie node as final so that it is only initialised during the object creation
    private final TrieNode root;

    public Trie() {
        root=new TrieNode();
    }

   
    
    public void insert(String word) {

        //In insert what I need to do is I will get a word and I'll iterate through all the letters
        //If I find the letter present in the children move pointer to next trie node 
        //If the letter is not present add a trie node
        //With each word we start with the root and keep on updating the children 
        TrieNode currentNode=root;
        for(char c:word.toCharArray()){
            //Go thorough the 
            int i=c-'a';
            if(currentNode.children[i]==null){
                currentNode.children[i]=new TrieNode();
            }

            currentNode=currentNode.children[i];
        }
        //If you have reached the end of the word don't forget to mark true

        currentNode.isEndOfWord=true;
    }
    
    public boolean search(String word) {

        //I will go matching each letter letter by letter till I reach the end of the word
        // TireNode currentNode=root;
        // for(char c:word.toCharArray()){
        //     int i=c-'a';
        //     if(cuurentNode[i]==null){
        //         return false;
        //     }
        //     currentNode=cuurentNode[i];
        // }
        // return true;
        //Above code is not required as I'll put that code in the searchPrefix

        //So what I need to do here is search for the word .
        //If I get null the word is not present completely or as a prefix
        TrieNode node=searchPrefix(word);
        return node!=null&&node.isEndOfWord;
        
    }
    
    public boolean startsWith(String prefix) {

        return searchPrefix(prefix)!=null;

        
    }
    // This will return the last matching trienode if the full word exists or it will return null
    public TrieNode searchPrefix(String word){
        //The trick is that the last node of any path will have all its children as null and isEndOfWord as true
        TrieNode currentNode=root;//no one can change the root
        for(char c:word.toCharArray()){
            //If the children of cuurrent node is all null of the required index is not present return null
            int i=c-'a';
            if(currentNode.children[i]==null){
                return null;
            }
            currentNode=currentNode.children[i];
        }
        //At the end of the loop I will be on the last matched node 
        return currentNode;

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//  class Trie {

//     /**
//      * Inner class to represent each node in the Trie.
//      */
//     private class TrieNode {
//         private TrieNode[] children;
//         private boolean isEndOfWord;

//         public TrieNode() {
//             // Since we are dealing with lowercase English letters ('a'-'z'),
//             // each node can have up to 26 children.
//             children = new TrieNode[26];
//             isEndOfWord = false;
//         }
//     }

//     private final TrieNode root;

//     /**
//      * Initializes the Trie data structure.
//      */
//     public Trie() {
//         root = new TrieNode();
//     }
    
//     /**
//      * Inserts a word into the Trie.
//      * Time Complexity: O(L), where L is the length of the word.
//      * Space Complexity: O(L) in the worst case (if no part of the word is already in the trie).
//      */
//     public void insert(String word) {
//         TrieNode currentNode = root;
//         for (char ch : word.toCharArray()) {
//             int index = ch - 'a';
//             // If the node for this character does not exist, create it.
//             if (currentNode.children[index] == null) {
//                 currentNode.children[index] = new TrieNode();
//             }
//             // Move to the next node.
//             currentNode = currentNode.children[index];
//         }
//         // Mark the end of the inserted word.
//         currentNode.isEndOfWord = true;
//     }
    
//     /**
//      * Searches for a complete word in the Trie.
//      * Time Complexity: O(L), where L is the length of the word.
//      * Space Complexity: O(1).
//      */
//     public boolean search(String word) {
//         TrieNode node = searchPrefix(word);
//         // The word exists if the path is found AND it's marked as the end of a word.
//         return node != null && node.isEndOfWord;
//     }
    
//     /**
//      * Checks if there is any word in the Trie that starts with the given prefix.
//      * Time Complexity: O(L), where L is the length of the prefix.
//      * Space Complexity: O(1).
//      */
//     public boolean startsWith(String prefix) {
//         TrieNode node = searchPrefix(prefix);
//         // The prefix exists as long as the path is found.
//         return node != null;
//     }

//     /**
//      * Helper method to traverse the Trie for a given string (word or prefix).
//      * Returns the last node in the path, or null if the path doesn't exist.
//      */
//     private TrieNode searchPrefix(String word) {
//         TrieNode currentNode = root;
//         for (char ch : word.toCharArray()) {
//             int index = ch - 'a';
//             if (currentNode.children[index] == null) {
//                 return null; // Path does not exist.
//             }
//             currentNode = currentNode.children[index];
//         }
//         return currentNode;
//     }
// }

// /**
//  * Your Trie object will be instantiated and called as such:
//  * Trie obj = new Trie();
//  * obj.insert(word);
//  * boolean param_2 = obj.search(word);
//  * boolean param_3 = obj.startsWith(prefix);
//  */
