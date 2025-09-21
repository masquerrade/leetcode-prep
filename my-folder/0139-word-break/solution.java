class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        //The trick is go throught the string and mark the indexes as true for which the substring is present in the dictionary
        //Then reiterate through the string for every new string and if you find true try to find if the reamining string is present in the dictionary
        //Finally check if the current string as the whole is present in the dictionary

        int n=s.length();

        boolean w[]=new boolean[n+1];

        // for(int i=0;i<n;i++){

        //     if(wordDict.contains(s.substring(0,i+1))){
        //             w[i]=true;
        //     }
        //     else{
        //         for(int j=0;j<=i;j++){
        //             //If I keep w[0] as true it will check the previous if condition in the first iteration
        //             if(w[j]==true&&wordDict.contains(s.substring(j+1,i+1))){
        //                 w[i]=true;
        //                 break;
        //             }
        //         }
        //     } 
            
        // }

        w[0]=true;

        for(int i=1;i<=n;i++){
            for(int j=0;j<=i;j++){
                if(w[j]&&wordDict.contains(s.substring(j,i))){
                    w[i]=true;
                    break;
                }
            }
        }


         return w[n];
        
    }
}
