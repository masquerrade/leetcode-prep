class Solution {
    public int balancedString(String s) {

        //I just need to remove he wxtra char
        int n=s.length();

        int [] mp=new int[128];

        int t=n/4;

        for(int i=0;i<n;i++){
            mp[s.charAt(i)]++;
        }

        mp['Q']-=t;
        mp['W']-=t;
        mp['E']-=t;
        mp['R']-=t;

        if( mp['Q']==0&&mp['W']==0&&mp['E']==0&&mp['R']==0){
            return 0;

        }

        int minLen=Integer.MAX_VALUE;

        //Now I hve the extra .Iw ant to fien the window which conrains thes cahrss
        int l=0;
        for(int r=0;r<n;r++){

            
            mp[s.charAt(r)]--;

             while( mp['Q']<=0&&mp['W']<=0&&mp['E']<=0&&mp['R']<=0){
                minLen=Math.min(r-l+1,minLen);
                mp[s.charAt(l)]++;
                l++;
               
             }

        }

        return minLen;


        }
        
}

//Gemini solution

// class Solution {
//     /**
//      * Finds the length of the shortest substring that can be replaced to make the string balanced.
//      * A string is balanced if each of 'Q', 'W', 'E', 'R' appears n/4 times.
//      *
//      * @param s The input string consisting of 'Q', 'W', 'E', 'R'.
//      * @return The length of the shortest substring to replace.
//      */
//     public int balancedString(String s) {
//         int n = s.length();
//         int targetCount = n / 4;
        
//         // Step 1: Count character frequencies and determine the "excess" characters required in the window.
//         // We use a map to store the counts of characters that are over the targetCount.
//         Map<Character, Integer> required = new HashMap<>();
//         Map<Character, Integer> initialCounts = new HashMap<>();
        
//         for (char c : s.toCharArray()) {
//             initialCounts.put(c, initialCounts.getOrDefault(c, 0) + 1);
//         }

//         boolean isBalanced = true;
//         for (Map.Entry<Character, Integer> entry : initialCounts.entrySet()) {
//             if (entry.getValue() > targetCount) {
//                 required.put(entry.getKey(), entry.getValue() - targetCount);
//                 isBalanced = false;
//             }
//         }

//         // If the string is already balanced, no replacement is needed.
//         if (isBalanced) {
//             return 0;
//         }

//         // Step 2 & 3: Use a sliding window to find the shortest substring containing all excess characters.
//         int left = 0;
//         int minLength = n;
//         Map<Character, Integer> windowCounts = new HashMap<>();
        
//         for (int right = 0; right < n; right++) {
//             char charRight = s.charAt(right);
//             windowCounts.put(charRight, windowCounts.getOrDefault(charRight, 0) + 1);

//             // While the current window is valid (contains all required excess characters),
//             // try to shrink it from the left to find a smaller valid window.
//             while (isWindowValid(windowCounts, required)) {
//                 minLength = Math.min(minLength, right - left + 1);
                
//                 char charLeft = s.charAt(left);
//                 windowCounts.put(charLeft, windowCounts.get(charLeft) - 1);
//                 left++;
//             }
//         }
        
//         return minLength;
//     }

//     /**
//      * Helper function to check if the current window contains enough excess characters.
//      */
//     private boolean isWindowValid(Map<Character, Integer> windowCounts, Map<Character, Integer> required) {
//         for (Map.Entry<Character, Integer> entry : required.entrySet()) {
//             char c = entry.getKey();
//             int requiredCount = entry.getValue();
//             if (windowCounts.getOrDefault(c, 0) < requiredCount) {
//                 return false; // This window is missing some required characters.
//             }
//         }
//         return true; // All required characters are present in sufficient amounts.
//     }
// }
