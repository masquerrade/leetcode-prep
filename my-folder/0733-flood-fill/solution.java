// class Solution {
//     public int[][] floodFill(int[][] image, int sr, int sc, int color) {


//         //Directly call dfs for the current node 
//         int m=image.length;
//         int n=image[0].length;



//         int inC=image[sr][sc];
//         int [][]visited=new int[m][n];

//         dfs(image,sr,sc,inC,color,visited);
        


//         return image;
        
//     }

//     public void dfs(int[][] image, int sr, int sc, int inC,int fnC,int [][]visited) {

//         // System.out.println(sr);

//         int m=image.length;
//         int n=image[0].length;

//         //Directly call dfs for the current node 

//         //Here nowhere I'm marking as visited that is why this issue
//         //Here the need of visited comes only in the case new colour is equal to the original colour
//         //In this case the cells with the current colour is never marked as visited

//         if(sr<0||sr>=m||sc<0||sc>=n||image[sr][sc]!=inC ||visited[sr][sc]==1){
//             // System.out.println("Base condition called");
//             return;
//         }

//         image[sr][sc]=fnC;
//         visited[sr][sc]=1;

//         dfs(image,sr-1,sc,inC,fnC,visited);
//         dfs(image,sr+1,sc,inC,fnC,visited);
//         dfs(image,sr,sc-1,inC,fnC,visited);
//         dfs(image,sr,sc+1,inC,fnC,visited);              

        
//     }
// }

//Gemini solution

// class Solution {
//     public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
//         // Get the color of the starting pixel.
//         int originalColor = image[sr][sc];
        
//         // 1. Edge Case: If the original color is already the new color,
//         // we'll get stuck in an infinite loop. So, we return early.
//         if (originalColor == newColor) {
//             return image;
//         }
        
//         // 2. Call the recursive helper function to do the painting.
//         dfs(image, sr, sc, originalColor, newColor);
        
//         return image;
//     }
    
//     /**
//      * Helper function to perform the Depth-First Search (DFS).
//      *
//      * @param image         The image grid.
//      * @param r             The current row.
//      * @param c             The current column.
//      * @param originalColor The color we are looking to replace.
//      * @param newColor      The color to replace it with.
//      */
//     private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        
//         // 3. Base Cases (Stop conditions):
//         //    a) Row is out of bounds
//         //    b) Column is out of bounds
//         //    c) The current pixel is NOT the original color we're trying to replace.
//         //       (This also handles pixels we've already visited, since they
//         //        will now have the `newColor` and fail this check).
//         if (r < 0 || r >= image.length || 
//             c < 0 || c >= image[0].length || 
//             image[r][c] != originalColor) {
//             return;
//         }
        
//         // 4. "Visit" the pixel: Paint the current pixel with the new color.
//         image[r][c] = newColor;
        
//         // 5. Recursive Step: Call DFS for all 4 neighbors.
//         dfs(image, r + 1, c, originalColor, newColor); // Down
//         dfs(image, r - 1, c, originalColor, newColor); // Up
//         dfs(image, r, c + 1, originalColor, newColor); // Right
//         dfs(image, r, c - 1, originalColor, newColor); // Left
//     }
// }

//BFS solution
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        
        if (originalColor == newColor) {
            return image;
        }
        
        int R = image.length;
        int C = image[0].length;
        
        // Queue for BFS, storing {row, col} pairs
        Queue<int[]> queue = new LinkedList<>();
        
        // Add the starting pixel to the queue and paint it
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        
        // Define the 4 directions (up, down, left, right)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        while (!queue.isEmpty()) {
            // Get the current pixel
            int[] pixel = queue.poll();
            int r = pixel[0];
            int c = pixel[1];
            
            // Check all 4 neighbors
            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                
                // Check if the neighbor is valid
                if (newR >= 0 && newR < R && 
                    newC >= 0 && newC < C && 
                    image[newR][newC] == originalColor) { // Must be the original color
                    
                    // If valid, paint it and add it to the queue to process its neighbors
                    image[newR][newC] = newColor;
                    queue.offer(new int[]{newR, newC});
                }
            }
        }
        
        return image;
    }
}
