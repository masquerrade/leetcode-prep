class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        //Since same word can be used multiple times in the squence it is a permutation or combination
        //The words can be used multiple times in any order so it is unbound knapsack and will come in the inner loop


        //First my stringbuilder will be empty

        //I'm not sure whether this will be correct but I'll make a set of all the words made till now

        Set<String> dp=new HashSet<>();

        //Add empty string to the set
        dp.add("");

        for(int i=0;i<=s.length();i++){
            String currentString=s.substring(0,i);

            for(String word:wordDict){
                if(currentString.endsWith(word)){
                    
                    //Finally I've figured out the trick .I need to find out if my current string ends with the current word , then check if the remaining string is present in the dp set
                    int wordIndex=currentString.lastIndexOf(word);
                    String subString=currentString.substring(0,wordIndex);
                    if(dp.contains(subString)){
                        dp.add(currentString);
                    }

                }
            }

        }

        System.out.println(dp);

        if(dp.contains(s)){
            return true;
        }

        return false;

        
    }
}


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
