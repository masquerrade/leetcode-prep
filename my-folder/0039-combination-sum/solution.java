//Using for loop
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        //What is the general steps I'm going to follow as I've solved this q so many times
        //Remaining 
        //Final array
        //Only way here is to explore all the paths
        //Start with the full 
        //Ierate through the whole candidates array and fire off the combination one by one starting with each entry in the loop by removing the current entry after firing off
        //In the loop variant I fire the current index as the starting for the candidates

        //Final array I'll pass as a member variable

        List<List<Integer>> finalList=new ArrayList<>();
        backTrack(candidates,0,target,new ArrayList<>(),finalList);

        return finalList;

        
    }

    private void backTrack(int[] candidates ,int start, int remaining,List<Integer> currentList, List<List<Integer>> finalList){

        //Base case
        if(remaining==0){
            finalList.add(new ArrayList<>(currentList));
            return;
        }

        if(remaining<0){
            return;
        }

        //Iterate all the candidate from start and fire the backtrack
        for(int i=start;i<candidates.length;i++){
            currentList.add(candidates[i]);
            backTrack(candidates,i,remaining-candidates[i],currentList,finalList);

            currentList.remove(currentList.size()-1);
        }
        

    }
}

// class Solution {
//     public List<List<Integer>> combinationSum(int[] candidates, int target) {

//         List<List<Integer>> fL=new ArrayList<>();
//         combSum(target,0,candidates,new ArrayList<Integer>(),fL);

//         return fL;

        
//     }

//     public static void combSum(int remsum, int currI, int[] orgA, List<Integer> currL, List<List<Integer>> finalL){
//         System.out.println("Call par "+remsum+","+currI+","+currL);
//         if(remsum<0 || currI>=orgA.length){
//             return;
//         }

//         if(remsum==0){
//             finalL.add(new ArrayList<>(currL));
//             return;
//         }

//         remsum=remsum-orgA[currI];
//         currL.add(orgA[currI]);
//         combSum(remsum,currI,orgA,currL,finalL);

//         currL.remove(currL.size()-1);
//         remsum=remsum+orgA[currI];
//         System.out.println("RemSum "+ remsum);
//         combSum(remsum,currI+1,orgA,currL,finalL);


//     }

// }


// class Solution {
//     public List<List<Integer>> combinationSum(int[] candidates, int target) {    
        	
// 		// candidates=new int[] {4,2,8};
// 		// target=8;
//         List<List<Integer>> rs=new ArrayList<>();

        
// 		combSum(target,new ArrayList<>(),rs,0,candidates);

// 		//System.out.println(rs);

//         return rs;
	
//     }

//     public static void combSum(int target, List<Integer> currC, List<List<Integer>> finList, int currI, int[] orgA){            

//         if(currI>orgA.length-1 || target<0){
//             return;
//         }

//         if(target==0){
//             finList.add(new ArrayList<>(currC));
//             return;

//         }        

        
//         int remTar=target-orgA[currI];
//         currC.add(orgA[currI]);
//         combSum(remTar, currC,finList,currI,orgA);

        
//         currC.remove(currC.size()-1);
//         combSum(target, currC,finList,currI+1,orgA);

//         }


// }
