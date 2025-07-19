class Solution {
    public int[][] merge(int[][] intervals) {

        if(intervals.length==0){
            return new int[0][];
        }

        //Custom sorting of 2d array

        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));

        //Using list of Int array 

        List<int []> merged= new ArrayList<>();

        //Add one element to the list
        int [] current=intervals[0];

        merged.add(current);

        //Iterating through the intervals and add overlapping intervals to the list

        for (int [] e: intervals){
            //Always take care of the last element

            if(current[1]>=e[0]){
                current[1]=Math.max(current[1],e[1]);
            }
            else{
                current=e;
                merged.add(current);
            }

        }


        //Convert the List to an array

        int [][] finMerged=merged.toArray(new int[merged.size()][]);

        return finMerged;
                
    
}
}
