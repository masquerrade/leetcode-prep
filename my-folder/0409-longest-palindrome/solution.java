// class Solution {
//     public int longestPalindrome(String s) {
//         Map<Character,Integer> mp=new HashMap<>();
//         int l=0;

//         for(int i=0;i<s.length();i++){
//             mp.compute(s.charAt(i),(k,v)->(v==null)?1:v+1);
//         }
//         //The trick here is to count all the even number of letters and take one odd letter if available
//         for(Map.Entry<Character,Integer> e:mp.entrySet()){
//             if(e.getValue()%2==0){
//                 l+=e.getValue();
//             }
//             else{
//                 l+=(e.getValue()-1);
//             }
//         }

//         System.out.println(mp);

//         if(l<s.length()){
//             return l+1;
//         }

//         return l;

//     }
// }

//Using Hashset
class Solution {
    /**
     * Calculates the length of the longest palindrome that can be built from the characters of the input string.
     *
     * @param s The input string consisting of lowercase or uppercase letters.
     * @return The length of the longest possible palindrome.
     */
    public int longestPalindrome(String s) {
        // A set to keep track of characters that have appeared an odd number of times.
        Set<Character> charSet = new HashSet<>();
        int length = 0;

        // Iterate through each character in the string.
        for (char c : s.toCharArray()) {
            // If the character is already in our set, it means we've now seen it twice.
            // This forms a pair that can be used in the palindrome.
            if (charSet.contains(c)) {
                length += 2; // Add 2 to our palindrome length.
                charSet.remove(c); // Remove the character as it's now paired up.
            } else {
                // If this is the first time we're seeing this character (or the 3rd, 5th, etc.),
                // add it to the set to mark it as "unpaired".
                charSet.add(c);
            }
        }

        // After the loop, the set contains characters that appeared an odd number of times.
        // If there are any unpaired characters left, we can use one of them as the center of the palindrome.
        if (!charSet.isEmpty()) {
            length += 1; // Add 1 for the center character.
        }

        return length;
    }
}
