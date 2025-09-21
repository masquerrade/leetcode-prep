class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        //There are two tricks when we find any match then we do dp[i-1][j-1] to find longest common for the string without the current character

        //If we don't find match we take the max of dp[i][j-1] and dp[i-1][j]

        int m=text1.length();
        int n=text2.length();

        int [][] dp=new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
