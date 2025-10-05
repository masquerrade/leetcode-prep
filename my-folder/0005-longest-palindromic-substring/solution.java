//DP Solution
class Solution {
    public String longestPalindrome(String s) {

        //Create a 2 d array of t
        int n=s.length();
        boolean [][] dp=new boolean[n][n];

        int l=0;
        int r=0;

        //This won't work as smaller subproblems need to be solved first
        // for(int i=0;i<n-1;i++){
        //     dp[i][i]=true;

        //     for(int j=i+1;j<n;j++){
        //         boolean isPalin=(s.charAt(i)==s.charAt(j));
        //         //babad
        //         System.out.println("isPalin="+isPalin);
        //         System.out.println("j-i+1 ="+(j-i+1));
        //         System.out.println(Arrays.deepToString(dp));
        //         if(isPalin && (j-i+1==2 || dp[i+1][j-1])){
        //             dp[i][j]=true;
        //             if(j-i+1>r-l+1){
        //                 l=i;
        //                 r=j;
        //             }
        //         }

                
        //     }

        // }

        //Take j as the outer loop and solve till j in each iteration

        for(int j=0;j<n;j++){
            dp[j][j]=true;

            for(int i=0;i<j;i++){
                boolean isPalin=(j-i+1)<=2||dp[i+1][j-1];

                if(s.charAt(i)==s.charAt(j)&&isPalin){
                   dp[i][j]=true;
                    if(j-i+1>r-l+1){
                        l=i;
                        r=j;
                    }

                }
            }
        }

        return s.substring(l,r+1);
        
    }
}


// class Solution {
//     public String longestPalindrome(String s) {

//         if(s.length()==1){
//             return s;
//         }

        
//         int r=0;
//         int l=0;

//         for(int i=0;i<s.length();i++){
//             int currLen1=findPalinLen(i,i,s);
//             int currLen2=findPalinLen(i,i+1,s);

//             //Here I'm getting the length of the current palindrome . 
//             int len=Math.max(currLen1,currLen2);
            
//             // If the current lenght is greater than the length of the current window then only update the window
//             //odd -> 1,2,3 l= 2-(3-1)/2=1    r= 2+(3/2)=3
//             if(len>(r-l+1)){
//                 l=i-((len-1)/2);
//                 r=i+(len/2);                
//             }
//         }

//         return s.substring(l,r+1);
        
//     }

//     public int findPalinLen(int l,int r,String s){
//         //caba
//         //2,2
//         //1,3
//         //0,4
//         while(l>=0 && r<s.length() &&s.charAt(l)==s.charAt(r)){
//             l--;
//             r++;
//         }

//         return r-l-1;

//     }
// }

//Gemini solution
// class Solution {
//     public String longestPalindrome(String s) {
//         // Handle edge cases: null or empty string
//         if (s == null || s.length() < 1) {
//             return "";
//         }

//         int start = 0;
//         int end = 0;

//         for (int i = 0; i < s.length(); i++) {
//             // Case 1: Odd length palindrome, with s[i] as the center.
//             // Example: "racecar"
//             int len1 = expandAroundCenter(s, i, i);

//             // Case 2: Even length palindrome, with s[i] and s[i+1] as the center.
//             // Example: "aabbaa"
//             int len2 = expandAroundCenter(s, i, i + 1);

//             // Find the maximum length from the two cases
//             int len = Math.max(len1, len2);

//             // If we found a new longest palindrome, update our pointers
//             if (len > end - start) {
//                 start = i - (len - 1) / 2;
//                 end = i + len / 2;
//             }
//         }

//         // Return the longest palindromic substring
//         return s.substring(start, end + 1);
//     }

//     /**
//      * Helper function to expand from a center and find the palindrome's length.
//      * It works by moving two pointers, L (left) and R (right), outwards.
//      */
//     private int expandAroundCenter(String s, int left, int right) {
//         int L = left;
//         int R = right;
//         // Expand while the pointers are within bounds and characters match
//         while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//             L--;
//             R++;
//         }
//         // The final length is R - L - 1. For example, if s="aba", L goes to -1 and R to 3.
//         // The length is 3 - (-1) - 1 = 3.
//         return R - L - 1;
//     }
// }

//Gemini DP solution

// class Solution {
//     public String longestPalindrome(String s) {
//         int n = s.length();
//         if (n <= 1) {
//             return s;
//         }

//         // dp[i][j] is true if the substring from index i to j is a palindrome.
//         boolean[][] dp = new boolean[n][n];

//         int start = 0;
//         int maxLength = 1;

//         // Iterate through all substrings. The order is important.
//         // We compute dp[i][j] based on dp[i+1][j-1], so we need to ensure
//         // the smaller subproblems are solved first.
//         for (int j = 0; j < n; j++) {
//             // All single-character substrings are palindromes.
//             dp[j][j] = true;
//             for (int i = 0; i < j; i++) {

//                 // To be a palindrome, the outer characters must match AND
//                 // the inner substring must also be a palindrome.
//                 boolean isInnerPalindrome = (j - i < 2) || dp[i + 1][j - 1];
//                 // The j - i < 2 condition handles substrings of length 2 (e.g., "aa").
//                 // For these, there is no inner substring, so isInnerPalindrome is true.
                
//                 if (s.charAt(i) == s.charAt(j) && isInnerPalindrome) {
//                     dp[i][j] = true;

//                     int currentLength = j - i + 1;
//                     if (currentLength > maxLength) {
//                         maxLength = currentLength;
//                         start = i;
//                     }
//                 }
//             }
//         }
//         return s.substring(start, start + maxLength);
//     }
// }
