// class Solution {
//     public int uniquePaths(int m, int n) {
//         int [][] dp=new int [m][n];
        
//         for(int i=0;i<m;i++){
//             dp[i][0]=1;
//         }

//          for(int i=0;i<n;i++){
//             dp[0][i]=1;
//         }

//         for(int i=1;i<m;i++){
//             for(int j=1;j<n;j++){
//                dp[i][j]=dp[i-1][j]+dp[i][j-1];
//             }
//         }

//         return dp[m-1][n-1];
//     }
// }

class Solution {
    public int uniquePaths(int m, int n) {
        // The problem can be solved using combinatorics.
        // To reach the bottom-right corner (m-1, n-1) from the top-left (0,0),
        // the robot must make a total of (m-1) down moves and (n-1) right moves.
        // The total number of moves is (m-1) + (n-1) = m + n - 2.

        // The problem is equivalent to choosing (m-1) positions for the 'down' moves
        // out of a total of (m+n-2) available move slots.
        // This is a combination problem: "N choose K", or C(N, K).
        // Here, N = m + n - 2 and K = m - 1 (or n - 1, it's symmetric).

        // Formula: C(N, K) = N! / (K! * (N-K)!)
        // Which simplifies to: C(m+n-2, m-1)

        int N = m + n - 2;
        int K = m - 1; // Or n - 1, we can choose the smaller one to optimize calculation
        
        // We can choose the smaller of (m-1) and (n-1) for K to reduce iterations.
        if (K > N - K) {
            K = N - K;
        }

        long result = 1; // Use long to prevent overflow during intermediate calculations

        // Calculate C(N, K) iteratively:
        // C(N,K) = (N * (N-1) * ... * (N-K+1)) / K!
        for (int i = 1; i <= K; i++) {
            result = result * (N - i + 1) / i;
        }

        return (int) result;
    }
}
