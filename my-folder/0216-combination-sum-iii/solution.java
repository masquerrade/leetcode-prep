class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {

        //Total k numbers
        //Sum to N
        //[1-9]
        //No repeatation and no duplicates
        //For confirming the length I'll just add a simple check

        //Initial condition to break

        List<List<Integer>> finalList=new ArrayList<>();

        if(k<1||k>9||n<1||n>45){
            return finalList;
        }

        backTrack(n,k,new ArrayList<>(),finalList, 1);

        return finalList;
        
    }

    private void backTrack(int remain,int maxLen,List<Integer> currentList,List<List<Integer>> finalList, int start){

        //Base case
        if(currentList.size()==maxLen){
            if(remain == 0){
                finalList.add(new ArrayList<>(currentList));
            }
            return ;
        }

        //Always first instinct go with the easiest approach
        //Candidates are harcoded and sorted
        for(int i=start;i<=9;i++){
            //If the remaining is less than i then break

            if(remain<i){
                break;
            }

            //Still I can add to the list

            currentList.add(i);

            //Backtrack from the next starting point
            backTrack(remain-i,maxLen,currentList,finalList, i+1);

            currentList.remove(currentList.size()-1);


        }
    }
}


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
