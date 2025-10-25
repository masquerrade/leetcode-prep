class Solution {
    public int numDecodings(String s) {

        //"11106"
        //1->T
        //No of ways we can decode the previous string ,if current number is valid + number of ways we can decode the last 2nd string if previous two numbers are valid
        //If any invalid string is present then the path is blocked
        //Two back for decode ways for 2 back
        //One back for decode ways for 1 back

        int n2=0;
        int n1=1;

        //Extract the first letter
        //Check valid and assign this to n1 and update n2
        //Then check the second letter. 

        //How to extract a single caharacter
        for(int i=0;i<s.length();i++){
            int c=0;
            int c1=s.charAt(i)-'0';
            //If the current char is valid take one back
            //Then check for 2 chars
            //No of decodings possible
            if(c1>0 ){
                c+=n1;
            }
            
            int c2=(i-1>=0)?Integer.parseInt(s.substring(i-1,i+1)):0;
            if(c2>9&c2<=26){
                c+=n2;
            }

            n2=n1;
            n1=c;
        }
        
        return n1;
    }
}



// class Solution {
//     public int numDecodings(String s) {

//         int n=s.length();

//         if(s.charAt(0)=='0'){
//             return 0;
//         }

//         int i1=1;
//         int i2=1;

//         for(int i=1;i<n;i++){
//             int curr=0;

//             //last char
//             int n1=s.charAt(i)-'0';

//             if(n1>0){
//                 curr+=i1;
//             }

//             int n2=Integer.parseInt(s.substring(i-1,i+1));
//             //This eliminates 01,02 like that
//             if(n2<=26 &&n2>9){
//                 curr+=i2;
//             }
//             i2=i1;
//             i1=curr;
//         }




//         return i1;
        
//     }
// }


//My first solution
// class Solution {
//     public int numDecodings(String s) {
//         //226

        
//         //n=3
//         int n=s.length();

//         int c=0,n1,n2,n21;
//         int i2=0;

//         int i1=0;
//         //n1=2
//         n1=Integer.parseInt(s.substring(0,1));

//         if(n1==0){
//             return i1;
//         }
//         else{
//             i1=1;
//         }

//         if(n==1){
//             return i1+i2;
//         }
//         else{
//             i2=1;
//         }

     
        
//         for(int i=2;i<=n;i++){
//                 //220
//                 //n2=2
//                 //2
//                 n2=Integer.parseInt(s.substring(i-2,i-1));//1st char, last 2nd  ->2

//                 //22
//                 //20
//                 n21=Integer.parseInt(s.substring(i-2,i));//1st 2 char, last 2->25
                
//                 //2
//                 //0
//                 n1=Integer.parseInt(s.substring(i-1,i));//2nd char ,last->5

//                 //0<n<26 is not valid in java
                
                
//                 if(n21>26 || n2==0){
//                     i2=0;
//                 }
//                 if(n1==0){
//                     //0
//                     i1=0;
//                 }
                
//                 //0

//                 int temp=i1;
//                 //2
//                 //1
//                 i1=i1+i2;
//                 //1
//                 //0
//                 i2=temp;

//                 if(i1==0){
//                     return 0;
//                 }
                

//         }

//         return i1;
//     }
// }

//Gemini solution
// class Solution {
//     public int numDecodings(String s) {
//         // Edge case: If the string is empty or starts with '0', no decoding is possible.
//         if (s == null || s.length() == 0 || s.charAt(0) == '0') {
//             return 0;
//         }

//         // two_back stores the number of ways to decode up to s[i-2]
//         // Corresponds to dp[i-2]
//         int two_back = 1;
        
//         // one_back stores the number of ways to decode up to s[i-1]
//         // Corresponds to dp[i-1]
//         int one_back = 1;

//         // Iterate from the second character of the string
//         for (int i = 1; i < s.length(); i++) {
//             int current = 0;

//             // Case 1: Single-digit decoding
//             // The current digit s[i] can be decoded by itself if it's not '0'.
//             int singleDigit = s.charAt(i) - '0';
//             if (singleDigit >= 1 && singleDigit <= 9) {
//                 current += one_back;
//             }

//             // Case 2: Two-digit decoding
//             // The previous digit s[i-1] and current digit s[i] can be decoded together.
//             // Forms a number between 10 and 26.
//             int twoDigits = Integer.parseInt(s.substring(i - 1, i + 1));
//             if (twoDigits >= 10 && twoDigits <= 26) {
//                 current += two_back;
//             }

//             // Update pointers for the next iteration
//             two_back = one_back;
//             one_back = current;
//         }

//         // The final result is stored in one_back after the loop finishes.
//         return one_back;
//     }
// }
