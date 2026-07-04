class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        //Two optimisation
        //Instead of adding the word in the set , I can use a boolean array to track the subword till now 
        //Instead of interating through all the words in the dictionary to check if the prefix till now is valid ,I take out max max word length from my wordDict and then for each substring look from the end to the max word length to check if the substring is valid

        //Convert list to set for fast lookup
        Set<String> wordSet =new HashSet<>(wordDict);

        //Boolean array to track which all sustrings are vaLid
        boolean[] dp=new boolean[s.length()+1];

        //Empty string can always be formed
        dp[0]=true;

        //Need to find max word length 
        int maxLen=0,limit=0;
        for(String word:wordDict){
            maxLen=Math.max(maxLen,word.length());
        }
        
        for(int i=1;i<=s.length();i++){

            if(i>maxLen){
                limit=i-maxLen;
                //2
                //1 ->0
                //2 ->0
                //2 ->
            }
            else{
                limit=0;
            }

            for(int j=i-1;j>=limit;j--){
                //0->1 1st letter from the word (0,1) dp[j]==true
                //(j,i) (1,2) && dp[j]  ; (0,2)&&dp[0]
                //i=3
                //limit=1
                //(2,3)&& dp[2]; (1,3)&&dp[1]

                if(wordSet.contains(s.substring(j,i)) && dp[j]){
                    dp[i]=true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}

// //Gemini solution

// class Solution {
//     public boolean wordBreak(String s, List<String> wordDict) {
//         // Convert list to a HashSet for O(1) average time complexity lookups
//         Set<String> wordSet = new HashSet<>(wordDict);
        
//         // Find the maximum word length in the dictionary for our optimization
//         int maxWordLength = 0;
//         for (String word : wordDict) {
//             maxWordLength = Math.max(maxWordLength, word.length());
//         }
        
//         // dp[i] represents whether s.substring(0, i) can be segmented into dictionary words
//         boolean[] dp = new boolean[s.length() + 1];
        
//         // Base case: an empty string is always theoretically segmentable 
//         dp[0] = true; 
        
//         // Iterate through the string to build our DP array
//         for (int i = 1; i <= s.length(); i++) {
//             // Check previous substrings, but limit the look-back to the max word length
//             // This prevents the inner loop from running N times unnecessarily
//             for (int j = i - 1; j >= Math.max(0, i - maxWordLength); j--) {
                
//                 // If the string up to 'j' is valid AND the substring from 'j' to 'i' is in the dictionary
//                 if (dp[j] && wordSet.contains(s.substring(j, i))) {
//                     dp[i] = true;
//                     break; // Move to the next 'i' since we found a valid segmentation for this prefix
//                 }
//             }
//         }
        
//         // The last element contains the boolean result for the entire string
//         return dp[s.length()];
//     }
// }

//Gemini optimised solution
// class Solution {
//     public boolean wordBreak(String s, List<String> wordDict) {
//         // Instead of a Set of Strings, we use a boolean array of size N + 1.
//         // dp[i] being true means the prefix of length 'i' is valid.
//         boolean[] dp = new boolean[s.length() + 1];
        
//         // Base case: equivalent to dp.add("")
//         dp[0] = true; 
        
//         for (int i = 1; i <= s.length(); i++) {
//             // We can still look at the current prefix conceptually, but no need to 
//             // constantly substring it in the inner loop.
            
//             for (String word : wordDict) {
//                 // To avoid string slicing, we just check lengths and characters
//                 int wordLen = word.length();
                
//                 // 1. Is the current prefix long enough to even contain this word?
//                 // 2. Does the prefix end with this word?
//                 // 3. Was the string VALID right before this word started? (equivalent to dp.contains(subString))
//                 if (i >= wordLen) {
//                     // Calculate where the substring would have started
//                     int previousIndex = i - wordLen;
                    
//                     if (dp[previousIndex] && s.substring(previousIndex, i).equals(word)) {
//                         dp[i] = true;
//                         break; // We found a valid breakdown for this prefix ending at the index i, skip other words
//                         //Since this has already been marked as true , we don't care if any other words can also form this prefix
//                     }
//                 }
//             }
//         }
        
//         // equivalent to dp.contains(s)
//         return dp[s.length()]; 
//     }
// }


// class Solution {
//     public boolean wordBreak(String s, List<String> wordDict) {

//         //Since same word can be used multiple times in the squence it is a permutation or combination
//         //The words can be used multiple times in any order so it is unbound knapsack and will come in the inner loop



//         Set<String> dp=new HashSet<>();

//         //Add empty string to the set
//         dp.add("");

//         for(int i=0;i<=s.length();i++){
//             String currentString=s.substring(0,i);

//             for(String word:wordDict){
//                 if(currentString.endsWith(word)){
                    
//                     //Finally I've figured out the trick .I need to find out if my current string ends with the current word , then check if the remaining string is present in the dp set
//                     int wordIndex=currentString.lastIndexOf(word);
//                     String subString=currentString.substring(0,wordIndex);
//                     if(dp.contains(subString)){
//                         dp.add(currentString);
//                     }

//                 }
//             }

//         }

//         // System.out.println(dp);

//         if(dp.contains(s)){
//             return true;
//         }

//         return false;

        
//     }
// }


// class Solution {
//     public boolean wordBreak(String s, List<String> wordDict) {

//         //The trick is go throught the string and mark the indexes as true for which the substring is present in the dictionary
//         //Then reiterate through the string for every new string and if you find true try to find if the reamining string is present in the dictionary
//         //Finally check if the current string as the whole is present in the dictionary

//         int n=s.length();

//         boolean w[]=new boolean[n+1];

//         // for(int i=0;i<n;i++){

//         //     if(wordDict.contains(s.substring(0,i+1))){
//         //             w[i]=true;
//         //     }
//         //     else{
//         //         for(int j=0;j<=i;j++){
//         //             //If I keep w[0] as true it will check the previous if condition in the first iteration
//         //             if(w[j]==true&&wordDict.contains(s.substring(j+1,i+1))){
//         //                 w[i]=true;
//         //                 break;
//         //             }
//         //         }
//         //     } 
            
//         // }

//         w[0]=true;

//         for(int i=1;i<=n;i++){
//             for(int j=0;j<=i;j++){
//                 if(w[j]&&wordDict.contains(s.substring(j,i))){
//                     w[i]=true;
//                     break;
//                 }
//             }
//         }


//          return w[n];
        
//     }
// }
