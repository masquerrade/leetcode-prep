class Solution {
    public int[][] merge(int[][] intervals) {

        //Sort the intervals
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));

        List<int []> merged =new ArrayList<>();

        merged.add(intervals[0]);

        //System.out.println(Arrays.deepToString(intervals));
        int cI=0;
        for (int [] e: intervals){
            cI=merged.size()-1;
            if(e[0]<=merged.get(cI)[1]){
                int[] nE=new int []{Math.min(e[0],merged.get(cI)[0]),Math.max(e[1],merged.get(cI)[1])};
                merged.remove(cI);
                merged.add(nE);
            }
            else{
                merged.add(e);
            }
        }

        int [][] finalMerged=new int[merged.size()][2];
        int nI=0;
        for(int []e:merged){
            finalMerged[nI]=e;
            nI++;
        }

        return finalMerged;
        //return new int[0][0];
        
    }
}
