// class Solution {
//     public int minAddToMakeValid(String s) {
//         int opB=0;
//         int clB=0;
//         //This will not work if opening and closing brackets are not in the correct order
//         for(int i=0;i<s.length();i++){
//             if(s.charAt(i)=='('){
//                 opB++;
//             }
//             if(s.charAt(i)==')'){
//                 opB--;
//             }
//             //Unmatched closing bracket count it
//             if(opB<0){
//                 clB++;
//                 opB=0;
//             }

//         }

//        return opB+clB;
//     }
// }

class Solution {
    public int minAddToMakeValid(String s) {
        // 'openCount' tracks the number of open parentheses '(' that are waiting for a match.
        int openCount = 0;

        // 'additions' tracks the number of parentheses we need to add.
        int additions = 0;

        // Iterate through each character of the string.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If we see an open parenthesis, it needs a closing one.
                openCount++;
            } else if (c == ')') {
                // If we see a closing parenthesis:
                if (openCount > 0) {
                    // There is a matching open parenthesis available. Use it.
                    openCount--;
                } else {
                    // This closing parenthesis has no matching open one.
                    // We must add an open parenthesis '(' to balance it.
                    additions++;
                }
            }
        }

        // After the loop, if 'openCount' is greater than 0, it means we have
        // that many open parentheses that were never closed.
        // We need to add a closing parenthesis ')' for each of them.
        // The total additions will be the closing parentheses we needed plus the open ones.
        return additions + openCount;
    }
}
