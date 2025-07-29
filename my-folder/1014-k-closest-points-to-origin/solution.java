class Solution {
    public int[][] kClosest(int[][] points, int k) {

        kClose(points,0,points.length-1, k);

        //System.out.println(Arrays.deepToString(points));
        //Here k os the size
        return Arrays.copyOf(points,k);
        
    }

    public void kClose(int [][]points,int s,int e,int k){

        //Partition to find the closest k
        int pivot=partition(points,s,e);
       
        if(pivot>k-1){
            kClose(points,s,pivot-1,k);
        }
        else if(pivot<k-1){
            kClose(points,pivot+1,e,k);
        }
        else{
            return;
        }

        // return temporary 2d array
        
        
    }

    public int partition(int [][]points,int s, int e){
        //Random int
        Random rnd=new Random();
        int pivotIndex=s+rnd.nextInt(e-s+1);
        // System.out.println(pivotIndex);
        // System.out.println(Arrays.deepToString(points));
        //Choosing a random index as pivot and bringing that element to the last
        swap(points,pivotIndex,e);
        //System.out.println(Arrays.deepToString(points));
        int [] pivot=points[e];
        int pivotDist=dist(pivot);
        int j=s-1;
        for(int i=s;i<e;i++){
            if(dist(points[i])<pivotDist){
                j++;
                swap(points,i,j);
            }
        }

        swap(points,e,j+1);

        return j+1;

    }

    public void swap(int [][]points,int s, int e){
        int [] tmp=points[s];
        points[s]=points[e];
        points[e]=tmp;
    }

    public int dist(int [] p){
        return p[0]*p[0]+p[1]*p[1];
    }
}
