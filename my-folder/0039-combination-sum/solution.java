class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {    
        	
		// int [] candidates=[1,4,2,6];
		// int target=7;
        List<List<Integer>> rs=new ArrayList<>();


		comSum(candidates, target, 0, new ArrayList<>(),rs);

		//System.out.println(rs);

        return rs;
	
    }

    public static void comSum(int [] can, int remSum, int currI, List<Integer> currC, List<List<Integer>> rs){

		//remSum==0 
		if(remSum==0){
            //System.out.println("Current C "+currC);
			rs.add(new ArrayList<>(currC));
            //System.out.println("Current Final "+rs);
			return;
		}
		if(remSum<0 || currI>=can.length){
			return;
		}

		// for(int i=currI;i<can.length;i++){
		// 	currC.add(can[i]);
        //     //It will add all the possible combinations where cuurent element is the first element
		// 	comSum(can,remSum-can[i],i,currC,rs);
		// 	currC.remove(currC.size()-1);
		// }
        currC.add(can[currI]);
        comSum(can,remSum-can[currI],currI,currC,rs);

        currC.remove(currC.size()-1);

        comSum(can,remSum,currI+1,currC,rs);

	}
}
