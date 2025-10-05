class Solution {
    public int countSubstrings(String s) {
        
        //I will use helper function to count all possible palindromic substrings in it
        int count=0;

        for(int i=0;i<s.length();i++){
            count+=palindromicSubstrings(i,i,s);

            //I don't want to miss the first character
            //It doesn't matter as even if I pass the wrong values it will be filtered
            // if(i>0){
            //     count+=palindromicSubstrings(i-1,i,s);
            // }
            count+=palindromicSubstrings(i-1,i,s);
            
        }

        return count;
        
    }

    public int palindromicSubstrings(int l,int r,String s){
        int c=0;

        while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
            c++;
            l--;
            r++;
        }

        return c;
    }
}


// class Solution {
//     /**
//      * Counts the total number of palindromic substrings in a given string.
//      *
//      * @param s The input string.
//      * @return The total count of palindromic substrings.
//      */
//     public int countSubstrings(String s) {
//         // Edge case for null or empty string.
//         if (s == null || s.length() == 0) {
//             return 0;
//         }

//         int count = 0;
//         // Iterate through the string to consider every possible center.
//         for (int i = 0; i < s.length(); i++) {
//             // Case 1: Odd-length palindromes (center is the character at i).
//             // Example: "racecar", center is 'e'.
//             count += expandAndCount(s, i, i);

//             // Case 2: Even-length palindromes (center is between i and i+1).
//             // Example: "aabbaa", center is between the two 'b's.
//             count += expandAndCount(s, i, i + 1);
//         }

//         return count;
//     }

//     /**
//      * Helper function to expand from a center and count palindromes.
//      *
//      * @param s The input string.
//      * @param left The left pointer of the center.
//      * @param right The right pointer of the center.
//      * @return The number of palindromes found for this center.
//      */
//     private int expandAndCount(String s, int left, int right) {
//         int palindromesFound = 0;
//         // Expand outwards as long as pointers are in bounds and characters match.
//         while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//             // Each valid expansion finds a new palindromic substring.
//             palindromesFound++;
            
//             // Move pointers outward for the next check.
//             left--;
//             right++;
//         }
//         return palindromesFound;
//     }
// }

//Gemini dp approach
// class Solution {
//     public int countSubstrings(String s) {
//         int n = s.length();
//         if (n == 0) {
//             return 0;
//         }

//         // dp[i][j] will be true if the substring s[i...j] is a palindrome.
//         boolean[][] dp = new boolean[n][n];
//         int count = 0;

//         // Base case: all substrings of length 1 are palindromes.
//         for (int i = 0; i < n; i++) {
//             dp[i][i] = true;
//             count++;
//         }

//         // Check for substrings of length 2.
//         for (int i = 0; i < n - 1; i++) {
//             if (s.charAt(i) == s.charAt(i + 1)) {
//                 dp[i][i + 1] = true;
//                 count++;
//             }
//         }

//         // Check for substrings of length 3 or more.
//         // We iterate by the length of the substring.
//         for (int len = 3; len <= n; len++) {
//             // Iterate by the starting index 'i'.
//             for (int i = 0; i <= n - len; i++) {
//                 // Calculate the ending index 'j'.
//                 int j = i + len - 1;

//                 // Check if the outer characters match and the inner substring is a palindrome.
//                 if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
//                     dp[i][j] = true;
//                     count++;
//                 }
//             }
//         }

//         return count;
//     }
// }
