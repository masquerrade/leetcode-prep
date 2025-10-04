//In scenario my charaters will only contain ascii characters I can use an array of lenth 128
class Solution {
    public String minWindow(String s, String t) {

        //I have to track my substring 

        int c=t.length();

        if(c==0){
            return "";
        }


        int [] mp= new int[128];

        //First I need to map how many are needed
        //I have all the counts of what all characters are needed
        for(int l=0;l<t.length();l++){
            mp[t.charAt(l)]++;
        }

        int l=0;
        int currLeft=0;
        int minLen=Integer.MAX_VALUE;
        //I have to go through entire string 
        for(int r=0;r<s.length();r++){

            //Get current char in this substring
            char currentChar=s.charAt(r);

            //If this character count is greater than 1 recduce the count by 1

            if(mp[currentChar]>0){
                c--;
            }

            mp[currentChar]--;

            //When c==0 and all the requird char ==0
            while(c==0){

                //Check the current length of the substring 
                if(r-l+1<minLen){
                    minLen=r-l+1;
                    currLeft=l;
                }

                //Then decrease the window

                // The trick is that I have already gone through the characters once so its value will be negative .Untill we remove all from the window we won't get a 0 value 
                //And since my substring characters we have added extra value it will always become positive first
                 char currLeftChar=s.charAt(l);
                 mp[currLeftChar]++;
                 l++;

                 if(mp[currLeftChar]>0){
                    c++;
                 }



            }

        }

          if(minLen==Integer.MAX_VALUE){
                return "";
            }

            return s.substring(currLeft,currLeft+minLen);
        
    }
}



// //Using Hashmap
// class Solution {
//     public String minWindow(String s, String t) {

//         //Make map of taarget sstring 

//         //Keep a counter
//         //It should be equal to the length of the substring and not equal to length of the string
//         int c=t.length();

//         //I can make a character array or map

//         Map<Character,Integer> cm=new HashMap<>();


//         int minVal=Integer.MAX_VALUE;
//         //This is not a feasible approach
//         //String minString=s+" ";
//         int finalLeft=0;

//         //I don't need to make a map of the string but of the substring because I'm counting its characters
//         // for(int i=0;i<s.length();i++){
//         //     cm.compute(s.charAt(i),(k,v)->(v==null)?1:v+1);
//         // }

//         for(int i=0;i<t.length();i++){
//             cm.compute(t.charAt(i),(k,v)->(v==null)?1:v+1);
//         }


//         //Now take left and right pointers for the window

//         int l=0;

//         for(int r=0;r<s.length();r++){

//             // System.out.println(cm);
//             // System.out.println("c ="+c);
            


//             char currChar=s.charAt(r);
//              //a

//             if(cm.getOrDefault(currChar,-1)>0){
//                 c--;
//             }

//             //ADOBECODEBANC
//             cm.computeIfPresent(currChar,(k,v)->{
//                 // if(v>0){
//                 //     //We can't modify local variable inside lambda expression
//                 //     c--;
//                 // }
                
//                 return v-1;
//                 });

            


//             while(c==0){
//                 //My window has all the required chars
//                 if(r-l+1<minVal){
//                     minVal=r-l+1;
//                     //This should also be fine or we can just keep the left and min length and finally calculate the substring.
//                     //minString=s.substring(l,r+1); //Not used
//                     finalLeft=l;
//                 }
//                 char leftChar=s.charAt(l);

//                 l++;
//                 cm.computeIfPresent(leftChar,(k,v)->v+1);
//                  if(cm.getOrDefault(leftChar,-1)>0){
//                     c++;
//                 }

                             
                
//             }
//         }

//         //System.out.println(minVal);

//         if(minVal==Integer.MAX_VALUE){
//             return "";
//         }

//         return s.substring(finalLeft,finalLeft+minVal);

        
//     }
// }

//Gemini Soln

// class Solution {
//     public String minWindow(String s, String t) {
//         // Edge case check
//         if (s == null || t == null || s.length() < t.length()) {
//             return "";
//         }

//         // 1. Create a frequency map for characters in t
//         int[] map = new int[128]; // For ASCII characters
//         for (char c : t.toCharArray()) {
//             map[c]++;
//         }

//         // 2. Initialize window pointers and counters
//         int left = 0;
//         int right = 0;
//         int count = t.length(); // Number of characters from t we need to find
//         int minLength = Integer.MAX_VALUE;
//         int minStart = 0; // Starting index of the minimum window

//         // 3. Slide the window by expanding with the 'right' pointer
//         while (right < s.length()) {
//             char charRight = s.charAt(right);

//             // If this character is one we need, decrement the count
//             if (map[charRight] > 0) {
//                 count--;
//             }
//             // Decrement the character's frequency. If it's not in t, it becomes negative.
//             map[charRight]--;
//             right++;

//             // 4. When count is 0, we have a valid window. Try to contract it.
//             while (count == 0) {
//                 // Check if the current window is the smallest we've seen
//                 if (right - left < minLength) {
//                     minLength = right - left;
//                     minStart = left;
//                 }

//                 // Move the 'left' pointer to shrink the window
//                 char charLeft = s.charAt(left);
                
//                 // As we remove the left character, we "return" it to the map
//                 map[charLeft]++;

//                 // If the count of that character becomes positive, it means
//                 // this was a necessary character. Our window is now invalid.
//                 if (map[charLeft] > 0) {
//                     count++;
//                 }
//                 left++;
//             }
//         }

//         // 5. Return the result
//         if (minLength == Integer.MAX_VALUE) {
//             return "";
//         }
//         return s.substring(minStart, minStart + minLength);
//     }
// }
