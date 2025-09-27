class Solution {
    public int candy(int[] ratings) {

        //[1,1,1]
        //1>0  n to 1 , i>i+1
        //0>2
        //[1,1,1]
        //1>0
        //[1+1,1,1]
        //1 to n-1
        //i>i-1 -> add one to i
        //0>1
        //2,1,1
        //2>1
        //[2,1,Math.max(1,1+1)]
        //
        int n=ratings.length;
        int [] costArr=new int[ratings.length];

        Arrays.fill(costArr,1);
        //Direction of addition is important . Don't add to the values which is going to be modified int he further steps
        for(int i=1;i<n;i++){
            if(ratings[i]>ratings[i-1]){
                costArr[i]=costArr[i-1]+1;
            }
        }

        //System.out.println(Arrays.toString(costArr));

        for(int i=n-2;i>=0;i--){
            //System.out.println(Arrays.toString(costArr));
            if(ratings[i]>ratings[i+1]){
                 costArr[i]=Math.max(costArr[i],(costArr[i+1]+1));
                 //costArr[i]=costArr[i+1]+1;
            }
        }
        //System.out.println(Arrays.toString(costArr));

        int total=Arrays.stream(costArr).sum();

        // int total=0;
        // for(int elm:costArr){
        //     total+=elm;
        // }

        return total;

        
    }
}
