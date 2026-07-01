//Gemini solution
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        
        // Sorting is strictly required to easily skip duplicates and prune the search space.
        Arrays.sort(candidates);
        
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        
        return results;
    }

    private void backtrack(int[] candidates, int remain, int startIndex, 
                           List<Integer> currentCombination, List<List<Integer>> results) {
        // Base Case: We found a valid combination
        if (remain == 0) {
            // Deep copy the current combination before adding to results
            results.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // Pruning step 1: Skip duplicates at the same depth of the recursion tree.
            // If i > startIndex, we have already branched on this exact value at this level.
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Pruning step 2: Early termination.
            // Because the array is sorted, if the current element exceeds the remaining target,
            // all subsequent elements will also exceed it.
            if (candidates[i] > remain) {
                break;
            }

            // Choose
            currentCombination.add(candidates[i]);
            
            // Explore (Note: we pass i + 1 because we cannot reuse the exact same element)
            backtrack(candidates, remain - candidates[i], i + 1, currentCombination, results);
            
            // Un-choose (Backtrack)
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}

// class Solution {
//     public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
//         //Sort the candidates to remove duplicates
//         Arrays.sort(candidates);
//         //It's mandatory to initialize list before using it 
//         // List<List<Integer>> finalList;
//         List<List<Integer>> finalList=new ArrayList<>();

//         backtrack(candidates,target,0,new ArrayList<>(),finalList);

//         return finalList;
//     }

//     private void backtrack(int[] candidates,int remain,int startIndex,List<Integer> currentList,List<List<Integer>> finalList){
        
//         if(remain==0){
//             finalList.add(new ArrayList<>(currentList));
//             return;
//         }

//         for(int i=startIndex; i<candidates.length; i++){
            
//             //i>startIndex is there just to allow the first call in this level
//             if(i>startIndex && candidates[i]==candidates[i-1]){
//                 continue;
//             }

//             //Now I got a new candidate . Check whether the new candidaate is greater than target and break
//             if(candidates[i]>remain){
//                 break;
//             }

//             //Add current candidate to the currentList
//             currentList.add(candidates[i]);

//             //Call backtrack
//             backtrack(candidates,remain-candidates[i],i+1,currentList,finalList);

//             currentList.remove(currentList.size()-1);

//         }



//     }
// }


// class Solution {
//     public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//         //Only one tine sort
//         Arrays.sort(candidates);
 
//         List<List<Integer>> finalList=new ArrayList<>();
//         combinationSum2Helper(candidates,target,new ArrayList<Integer>(),0,finalList);
//         return finalList;
        
//     }

//     public void combinationSum2Helper(int[] candidates, int target, List<Integer> crrCmb, int canInd,List<List<Integer>> finalList) {

//         //Base case
//         if(target==0){
//             finalList.add(new ArrayList<>(crrCmb));
//             return ;
//         }

//         if(target<0){
//             return ;
//         }
        
//         //No need to sort candidates again and again
//         //Arrays.sort(candidates);

//         for(int i=canInd;i<candidates.length;i++){

//             //We can take duplicates for the first time 
//             crrCmb.add(candidates[i]);
//             //i+1 is passed as starting index so that each candidate is used only once
//             combinationSum2Helper(candidates,target-candidates[i],crrCmb,i+1,finalList);

//             //This check is necessary first to avoid index out of bounds exception
//             //This is used to skip duplicates
//             while((i+1)<candidates.length && candidates[i]==candidates[i+1]){
//                 i++;
//             }

//             //Removing the last index to explore other possibilities
//             crrCmb.remove(crrCmb.size()-1);

//         }

//         return ;
        
//     }
// }
