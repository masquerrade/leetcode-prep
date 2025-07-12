class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> fL=new ArrayList<>();
        combSum(target,0,candidates,new ArrayList<Integer>(),fL);

        return fL;

        
    }

    public static void combSum(int remsum, int currI, int[] orgA, List<Integer> currL, List<List<Integer>> finalL){
        System.out.println("Call par "+remsum+","+currI+","+currL);
        if(remsum<0 || currI>=orgA.length){
            return;
        }

        if(remsum==0){
            finalL.add(new ArrayList<>(currL));
            return;
        }

        remsum=remsum-orgA[currI];
        currL.add(orgA[currI]);
        combSum(remsum,currI,orgA,currL,finalL);

        currL.remove(currL.size()-1);
        remsum=remsum+orgA[currI];
        System.out.println("RemSum "+ remsum);
        combSum(remsum,currI+1,orgA,currL,finalL);


    }

}
