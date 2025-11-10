// class Solution {
//     public int findJudge(int n, int[][] trust) {

//         //I will create adjacency list and indegree array
//         //Indegree array for the judge should be equal to number of people-1
//         //And it should not be present in the adjacency list

//         Map<Integer,Integer> adj=new HashMap<>();
//         int [] indegree=new int[n];
//         Set<Integer> trst=new HashSet<>();

//         for(int [] tr:trust){
//             int t1=tr[0];
//             int t2=tr[1];

//             // List<Integer> lst=adj.getOrDefault(t1,new ArrayList<>());
//             // lst.add(t2);
//             // adj.put(t1,lst);

//             //Set of people who trusts someone and create indegree
//             trst.add(t1);
//             indegree[t2-1]++;


//         }

//         //Iterate through all the courses and check if it is a judge or return -1
//         for(int i=1;i<=n;i++){
//             if(!trst.contains(i) && indegree[i-1]==n-1){
//                 return i;
//             }
//         }

//         return -1;

        
//     }
// }

//Gemini solution
/**
 * Solves LeetCode 997: Find the Town Judge
 * This solution is based on the single-array "trust delta" approach.
 */
// class Solution {
//     public int findJudge(int n, int[][] trust) {
        
//         // Use a single array to track the "trust score" for each person.
//         // We use n + 1 to accommodate 1-based indexing (people are 1 to n).
//         int[] trustScores = new int[n + 1];

//         // Iterate through all trust relationships
//         for (int[] relation : trust) {
//             int personA = relation[0];
//             int personB = relation[1];
            
//             // personA trusts someone, so they lose a "point".
//             // This disqualifies them from being the judge.
//             trustScores[personA]--;
            
//             // personB is trusted by someone, so they gain a "point".
//             trustScores[personB]++;
//         }

//         // Check the final scores to find the judge
//         for (int i = 1; i <= n; i++) {
            
//             // The judge is the only person who will have a final score of n - 1.
//             // (Trusted by n-1 people and trusts 0 people)
//             if (trustScores[i] == n - 1) {
//                 return i;
//             }
//         }

//         // If no person has a score of n-1, no judge exists.
//         return -1;
//     }
// }

//Two array solution

/**
 * Solves LeetCode 997: Find the Town Judge
 * This solution uses the "first approach" from the video:
 * tracking incoming and outgoing edges (trust) in two separate arrays.
 */
class Solution {
    public int findJudge(int n, int[][] trust) {
        
        // Per the video, we count incoming and outgoing edges separately.
        
        // incomingEdges[i] = how many people trust person i
        int[] incomingEdges = new int[n + 1];
        
        // outgoingEdges[i] = how many people person i trusts
        int[] outgoingEdges = new int[n + 1];

        // 1. Populate the counts based on the trust relationships
        for (int[] relation : trust) {
            int personA = relation[0];
            int personB = relation[1];
            
            // personA trusts someone
            outgoingEdges[personA]++;
            
            // personB is trusted by someone
            incomingEdges[personB]++;
        }

        // 2. Check each person to see if they satisfy BOTH conditions
        for (int i = 1; i <= n; i++) {
            
            // Condition 1: The judge trusts nobody (out-degree is 0)
            boolean trustsNobody = (outgoingEdges[i] == 0);
            
            // Condition 2: Everybody trusts the judge (in-degree is n-1)
            boolean trustedByEveryone = (incomingEdges[i] == n - 1);

            if (trustsNobody && trustedByEveryone) {
                return i; // This person is the judge
            }
        }
        
        // If no one satisfied both conditions
        return -1;
    }
}
