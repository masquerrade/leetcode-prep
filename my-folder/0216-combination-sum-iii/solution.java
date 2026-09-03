// //Attempt 1
// class Solution {
//     public List<List<Integer>> combinationSum3(int k, int n) {
//         // if(k==0 || n==0 || n>=45 || k>10){
//         //     return Collections.emptyList();
//         // }
//         // List<List<Integer>> result=new ArrayList<>();
//         // backtrack(k,n,1,);

//         //TC
//     }
// }


/**
 * Production-grade solution for LC 216: Combination Sum III.
 * Thread-safe, garbage-minimized backtracking with mathematical branch-and-bound pruning.
 */
public final class Solution {

    private static final int MIN_DIGIT = 1;
    private static final int MAX_DIGIT = 9;

    /**
     * Finds all valid combinations of k distinct digits (1-9) that sum to n.
     *
     * @param k number of distinct digits required.
     * @param n target sum.
     * @return unmodifiable list of valid combinations.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        // Defensive validation and boundary pruning
        if (k < 1 || k > MAX_DIGIT) {
            return Collections.emptyList();
        }

        // Global arithmetic feasibility checks:
        // Min possible sum for k digits: k * (k + 1) / 2
        // Max possible sum for k digits: k * (19 - k) / 2
        int minPossibleSum = (k * (k + 1)) >>> 1;
        int maxPossibleSum = (k * (19 - k)) >>> 1;

        if (n < minPossibleSum || n > maxPossibleSum) {
            return Collections.emptyList();
        }

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>(k);

        backtrack(MIN_DIGIT, k, n, currentPath, results);
        return Collections.unmodifiableList(results);
    }

    private void backtrack(
            int currentDigit,
            int remainingK,
            int remainingSum,
            List<Integer> currentPath,
            List<List<Integer>> results) {

        // Base Success Case: exact count and target reached simultaneously
        if (remainingK == 0) {
            if (remainingSum == 0) {
                results.add(new ArrayList<>(currentPath));
            }
            return;
        }

        // Structural upper bound: ensure enough digits remain in [currentDigit, MAX_DIGIT]
        // Example: if remainingK = 3, currentDigit cannot exceed 9 - 3 + 1 = 7.
        int maxFeasibleDigit = MAX_DIGIT - remainingK + 1;

        for (int digit = currentDigit; digit <= maxFeasibleDigit; ++digit) {
            // Prune 1: Digit exceeds remaining sum
            if (digit > remainingSum) {
                break; // Since digits are ascending, subsequent digits will also exceed remainingSum
            }

            // Prune 2: Minimum achievable sum with remaining elements exceeds remainingSum
            int minSumRemaining = (remainingK * digit) + ((remainingK * (remainingK - 1)) >>> 1);
            if (minSumRemaining > remainingSum) {
                break; // Higher digits will only produce larger sums
            }

            // Prune 3: Maximum achievable sum with remaining elements cannot reach remainingSum
            int maxSumRemaining = (remainingK * MAX_DIGIT) - ((remainingK * (remainingK - 1)) >>> 1);
            if (maxSumRemaining < remainingSum) {
                continue; // Larger start digits may close the gap, so continue rather than break
            }

            // State Transition: Push
            currentPath.add(digit);

            // Recurse: strictly increasing digit eliminates duplicates
            backtrack(digit + 1, remainingK - 1, remainingSum - digit, currentPath, results);

            // Backtrack: Pop last element
            currentPath.remove(currentPath.size() - 1);
        }
    }
}


// class Solution {
//     public List<List<Integer>> combinationSum3(int k, int n) {

//         //Total k numbers
//         //Sum to N
//         //[1-9]
//         //No repeatation and no duplicates
//         //For confirming the length I'll just add a simple check

//         //Initial condition to break

//         List<List<Integer>> finalList=new ArrayList<>();

//         if(k<1||k>9||n<1||n>45){
//             return finalList;
//         }

//         backTrack(n,k,new ArrayList<>(),finalList, 1);

//         return finalList;
        
//     }

//     private void backTrack(int remain,int maxLen,List<Integer> currentList,List<List<Integer>> finalList, int start){

//         //Base case
//         if(currentList.size()==maxLen){
//             if(remain == 0){
//                 finalList.add(new ArrayList<>(currentList));
//             }
//             return ;
//         }

//         //Always first instinct go with the easiest approach
//         //Candidates are harcoded and sorted
//         for(int i=start;i<=9;i++){
//             //If the remaining is less than i then break

//             if(remain<i){
//                 break;
//             }

//             //Still I can add to the list

//             currentList.add(i);

//             //Backtrack from the next starting point
//             backTrack(remain-i,maxLen,currentList,finalList, i+1);

//             currentList.remove(currentList.size()-1);


//         }
//     }
// }


// class Solution {
//     public List<List<Integer>> combinationSum3(int k, int n) {

//         List<List<Integer>> finalCom=new ArrayList<>();
//         combSumHelper(k,n,finalCom,new ArrayList<>(),1);

//         return finalCom;
        
//     }
//     public void combSumHelper(int k, int n,List<List<Integer>> finalCom,List<Integer> currCom,int currCan){
//         //System.out.println(currCom);
//         if(n==0 && currCom.size()==k){
//             finalCom.add(new ArrayList<>(currCom));
//             return;
//         }

//         if(n<0 || currCom.size()==k){
//             return ;
//         }

//         for(int i=currCan;i<=9;i++){
//             //currCan
//             currCom.add(i);
//             //[1,2]
//             //currCan will be same for all the iteration so can't pass that
//             combSumHelper(k,n-i,finalCom,currCom,i+1);

//             currCom.remove(currCom.size()-1);


//         }
//     }
// }
