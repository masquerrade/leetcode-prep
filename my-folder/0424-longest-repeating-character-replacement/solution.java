class Solution {
    public int characterReplacement(String s, int k) {
        //I can simply keep track of the freq
        int [] mp=new int[128];

        //Iterate through the string and keep on increating the char count
         int maxFreq=0,maxLen=0,l=0;

        for(int r=0;r<s.length();r++){

            char currChar=s.charAt(r);

            mp[currChar]++;

            // if(mp[currChar]>maxFreq){
            //     maxFreq=
            // }

            maxFreq=Math.max(maxFreq,mp[currChar]);

            if(r-l+1-maxFreq>k){
                mp[s.charAt(l)]--;
                l++;
            }

            maxLen=Math.max(maxLen,r-l+1);

        }
        return maxLen;
    }
}

// class Solution {
//     public int characterReplacement(String s, int k) {

//         //Keep track of the current substring and the freq of the max char
//         //If diff greater than k reduce the window
//         //Else keep on  expanding the window

//         //If ascii characters are there instead of using map I can use array

//         int []map=new int[128];

//         //Iterate and keep incr right 
//         //diff
//         //maxChar
//         //AABABBA
//     int l=0,maxDiff=0,maxWin=0,maxFreq=0;
//     char maxChar=' ';

//     for(int r=0;r<s.length();r++){
//         //int r-l+1=r-l+1; 
//         System.out.println("Current window ="+s.substring(l,r+1));
        
//         char currChar=s.charAt(r);
//         //A
//         map[currChar]++;
//         //A=1

//         //
//         System.out.println("currChar :"+currChar+"="+map[currChar]);
//         if(map[currChar]>map[maxChar]){
//             //1
//             maxChar=currChar;
//             System.out.println("Max Char: "+maxChar+" = "+map[maxChar]);
//             //A
//         }
        
//         //I need maxDiff for each iteration 
//         maxDiff=r-l+1-map[maxChar];
//         System.out.println("Max Diff "+maxDiff);
//         //0

//         if(maxDiff>k){
//             //Shrink from left
//             map[s.charAt(l)]--;
//             System.out.println("Reduce Window :"+s.charAt(l)+" ="+map[s.charAt(l)]);
//             l++;
//         }
//         // else{
//         //     //This is necessary if I am reducing the character
//         //     maxWin=Math.max(maxWin,r-l+1);
//         //     System.out.println("Max Win "+maxWin);
//         // }
//         //I need to calculate the new window size here
//         maxWin=Math.max(maxWin,r-l+1);
//         System.out.println("Max Win "+maxWin);        
//     }

//     return maxWin;


        
//     }
// }

//Gemini soltion

// class Solution {
//     public int characterReplacement(String s, int k) {
//         int n = s.length();
//         int left = 0;
//         int maxFreq = 0;
//         int maxLength = 0;
//         Map<Character, Integer> freqMap = new HashMap<>();

//         for (int right = 0; right < n; right++) {
//             char currentChar = s.charAt(right);
//             freqMap.put(currentChar, freqMap.getOrDefault(currentChar, 0) + 1);
            
//             // Update the maximum frequency of any character in the current window
//             maxFreq = Math.max(maxFreq, freqMap.get(currentChar));

//             // Calculate the number of replacements needed for the current window
//             int replacementsNeeded = (right - left + 1) - maxFreq;

//             // If replacements needed exceed k, shrink the window from the left
//             if (replacementsNeeded > k) {
//                 char leftChar = s.charAt(left);
//                 freqMap.put(leftChar, freqMap.get(leftChar) - 1);
//                 left++;
//             }
            
//             // The length of the longest valid window is our answer
//             maxLength = Math.max(maxLength, right - left + 1);
//         }

//         return maxLength;
//     }
// }
