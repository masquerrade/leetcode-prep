// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {

//         //Start with the begin word and go through all the words in the word list
//         //If the difference is 1 there is a path
//         //Make a taken array here and start the traversal
//         //Keep updating the minimum distance array 
//         //I don't see any use of any specific graph algorithm for now

//         //There is actually an undirected graph with words differing by one letter as neighbours
//         //Here we will use bfs to find shortest distance to the target node

//         HashSet<String> wordSet=new HashSet<>(wordList);

//         if(!wordSet.contains(endWord)){
//             return 0;
//         }

//         //If the wordSet contains endWord then I need to form the neighbours' adjacency map
//         //For forming the neighbours adjacency map I need not scan whole list for each node but I can generate all possible neighbours from each word and check which ones are valid.

//         //I have to start bfs from the startWord

//         //1st I will traverse the first level by travelling all the neighbours of the first word
//         //and adding all the neighbours of them to the queue for the second round of traversal

        
//         //Adj List will be map of List
//         // Map<List<String>> adj=new HashMap<>(); We don't need this map as we will generate the neighbours dynamically.
//         //To do -- I need to populate the adjacency list

//         //Now I have the list first  I will initialze the queue 


//         Queue<String> bfs=new ArrayDeque<>();

//         Set<String> visited=new HashSet<>();

//         int currentLevel=1;

//         //The start word I need to add to the queue and need to mark it as visited
        
//         bfs.offer(beginWord);
//         visited.add(beginWord);

//         while(!bfs.isEmpty()){
//             int currentLen=bfs.size();

//             //When we come out of the  loop means there is no more connected neighbours left and we did not find the end word till then.We need to return 0
//             //If the last element of the queue is the endWord,we need to return the current level

//             for(int i=0;i<currentLen;i++){
//                 String currentWord=bfs.poll();
//                 //If the current popped element is the endword then I need to return the current level
//                 //When I have traversed through all the neighbours of the cuurent node then I need to mark that node as visited.
//                 if(currentWord.equals(endWord)){
//                     return currentLevel;
//                 }

//                 //I need to generate all the neighbours
//                 char[] currArray= currentWord.toCharArray();
//                 for(int j=0;j<currArray.length;j++){
//                     char currentChar=currArray[j];
//                     for(char c='a';c<='z';c++){
//                          //Here we cannot change the original word because I need to use it later
//                          currArray[j]=c;
//                          String currString=String.valueOf(currArray);
//                          //I got my new string and I need to check if this string is present in the visited and if not I need to add it to the queue
//                          if(wordSet.contains(currString) && !visited.contains(currString)){
//                             bfs.offer(currString);
//                             visited.add(currString);
//                          }
//                     }
//                     currArray[j]=currentChar;
//                 }
                
//                 // for(String neighbour:adj.get(currentWord)){
//                 //     if(!visited.contains(neighbour)){
//                 //         queue.offer(neighbour);
//                 //         //As soon as we put the node in the queue we need to mark it as visited
//                 //     }
//                 // }
//             }
            
//             //Now all the elements of this level are visited 
//             currentLevel++;

//         }

//         //Now there is nothing in the queue and End word is not found
//         return 0;


        
        
//     }
// }

//Bidirectional BFS first try
//What my strategy is , I'll track neighbours of begin node and end node as a set
//But I'll find all the neighbours only of the set which has less nodes


//Gemini optimesed solution

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);

        // Edge case: If endWord is not in the dictionary, a path is impossible.
        if (!dictionary.contains(endWord)) {
            return 0;
        }

        // We use Sets for the BFS layers to allow O(1) containment checks
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        // While we don't strictly need to track visited for the very first word
        // in this specific logic, it's good practice.
        visited.add(beginWord);
        visited.add(endWord);

        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Optimization: Always expand the smaller set to minimize growth
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevel = new HashSet<>();

            for (String word : beginSet) {
                char[] charArray = word.toCharArray();

                // Try changing every character to 'a'-'z'
                for (int i = 0; i < charArray.length; i++) {
                    char originalChar = charArray[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;

                        charArray[i] = c;
                        String nextWord = new String(charArray);

                        // 1. Check if we met the other search front
                        if (endSet.contains(nextWord)) {
                            return level + 1;
                        }

                        // 2. If valid neighbor and not visited, add to next level
                        if (dictionary.contains(nextWord) && visited.add(nextWord)) {
                            nextLevel.add(nextWord);
                        }
                    }
                    // Restore original char for next iteration
                    charArray[i] = originalChar;
                }
            }
            
            // Move to the next level
            beginSet = nextLevel;
            level++;
        }

        return 0;
    }
}
