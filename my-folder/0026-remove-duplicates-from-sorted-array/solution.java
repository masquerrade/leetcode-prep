class Solution {
    public int removeDuplicates(int[] array) {
        int j=0;
        for(int i=1;i<array.length;i++){
            if(array[j]!=array[i]){
            j++;
            array[j]=array[i];
            }
        }
        //System.out.println(Arrays.toString(array));
        return j+1;
    }
}
