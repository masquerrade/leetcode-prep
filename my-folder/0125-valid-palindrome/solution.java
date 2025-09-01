class Solution {
    public boolean isPalindrome(String s) {

        //reverse the string and clean using for loop
        StringBuilder sb= new StringBuilder();

        for(char c:s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
            }
        }
        String cleaned=sb.toString().toLowerCase();
        String reversed=sb.reverse().toString().toLowerCase();
        if(cleaned.equals(reversed)){
            return true;
        }
        else{
            return false;
        }



        //reverse the string and clean using replace function

        // String cleaned = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        // String reverse=new StringBuilder(cleaned).reverse().toString().toLowerCase();
        // if(cleaned.equals(reverse)){
        //     return true;
        // }
        // else{
        //     return false;
        // }
        // StringBuilder sb=new StringBuilder(s);
        // String rev=sb.reverse().toString();
        // if(rev.equals(s))


        //Compare with the original string
        // //use two pointer method
        // char[] cr=s.toCharArray();
        // int l=0;
        // int r=s.length()-1;
        // while(l<r){
        //     char lc=s.charAt(l);

        //     //This condition is important to check when you are changing the control variable
        //     while(!Character.isLetterOrDigit(lc) && l<r){
        //         l++;
        //         lc=s.charAt(l);
        //     }

        //     Character rc=s.charAt(r);
        //     while(!Character.isLetterOrDigit(rc)  && l<r){
        //         r--;
        //         rc=s.charAt(r);
        //     }

        //     if(Character.toLowerCase(rc)!=Character.toLowerCase(lc)){
        //         return false;
        //     }
        //     l++;
        //     r--;

        // }

        // return true;

    }
}
