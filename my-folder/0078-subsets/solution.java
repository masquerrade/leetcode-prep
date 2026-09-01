class Solution {

    //Bitmasking approach
    public List<List<Integer>> subsets(int[] nums) {
        //I've to use all the 2^n numbers as mask
        int n= nums.length;
        int totalSubsets=1<<n;

        List<List<Integer>> result=new ArrayList<>(totalSubsets);

        //Generate all the masks 
        for(int i=0;i<totalSubsets;i++){
            //For each mask iterate through the nums
            //Create a new list for each mask
            List<Integer> currentList=new ArrayList<>();
            for(int j=0;j<n;j++){
                //Shift 1 to jth index and do & with the mask
                // if(((1<<j)&i)==1){ Don't use 1 use 0
                if(((1<<j)&i)!=0){
                    currentList.add(nums[j]);
                }
            }
            result.add(currentList);
        }

        return result;
    }
}



// class Solution {
//     public List<List<Integer>> subsets(int[] nums) {

//         //Common sense tells 
//         //0 at a time
//         //1 a time looking forward
//         //2 at a time 
//         //3 at a time 
        
//         //Pretty good question
//         //If array is null return empty list \
//         if(nums==null ){
//            return  Collections.emptyList();
//         }

//         int n=nums.length;

//         //Now I know the total number of subsets
//         if(n>=31){
//             throw new IllegalArgumentException("Total number of elements can't be greater than 30");
//         }

//         int size=1<<n;

//         List<List<Integer>> subsetList=new ArrayList<>(size);

//         //There will be a list of current subset
//         //Max possible depth of every list is n so use it
//         List<Integer> currentSubset=new ArrayList<>(n);

//         //I need to pass by reference the curent list and the final list to the backtrack function.
//         //Since I need to avoid duplicates and look strictly forward I'll pass the start Index

//         // backtrackDecisionTree(nums,0,currentSubset,subsetList);
//         backtrackLoopDfs(nums,0,currentSubset,subsetList);

//         return subsetList;


//     }

//     private void backtrackDecisionTree(int[] nums,int startIndex,List<Integer> currentSubset,List<List<Integer>> subsetList){

//         //What would be my base case
//         if(nums.length<=startIndex){
//             //Fucking caught the error> I need to insert the snapshot not the reference to the list
//             // subsetList.add(currentSubset);
//             subsetList.add(new ArrayList<>(currentSubset));
//             return;
//         }
//         //When do I realize that I need to add currentSubset to the sebsetList


//         //The start Index is the index I need to start making my subset
//         //In the current iteration I need to include the current element and then find all the subsets which include the current element

//         currentSubset.add(nums[startIndex]);
//         // I chose the current element and moved forward 
//         backtrackDecisionTree(nums,startIndex+1,currentSubset,subsetList);

//         //currentSubset.remove(nums[startIndex]); To avoid ambiguity whther to remove by value or index remove the last most element
//         //Now I've to unchoose the current element and move forward
//         currentSubset.remove(currentSubset.size()-1);
//         backtrackDecisionTree(nums,startIndex+1,currentSubset,subsetList);

//     }

//     //Backtrack loop dfs
//     private void backtrackLoopDfs(int[] nums,int startIndex,List<Integer> currentSubset,List<List<Integer>> subsetList){

//         //Add the current valid prefix to the list
//         subsetList.add(new ArrayList<>(currentSubset));

//         //Now go on making all the possible combinations with the current prefix in the dfs way

//         for(int i=startIndex;i<nums.length;i++){

//             currentSubset.add(nums[i]);
//             //Major issue here. I need to call backtrack with i+1 and not start+1
//             // backtrackLoopDfs(nums,startIndex+1,currentSubset,subsetList);
//              backtrackLoopDfs(nums,i+1,currentSubset,subsetList);
//             currentSubset.remove(currentSubset.size()-1);
            
//         }
//     }

// }
