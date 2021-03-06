package guibin.zhang.leetcode.matrix;

/**
 * 
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up:
 * Could you do this in-place?
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RotateImage {
    
    /**
     * http://joycelearning.blogspot.com/2013/10/leetcode-rotate-image.html
     * 
     * @param matrix 
     */
    public void rotate_v3(int[][] matrix) {
        
        if (matrix == null) return;
        int n = matrix.length;
        if (n <= 1) return;
        
        // rule: i = j'; j = n-1 - i';
        // that is: i' = n-1 -j; j' = i;
        // loop through 1/4 of the matrix
        // a | b
        // c | d
        // four parts
        // 
        // a = matrix[i][j]; d = matrix[n - 1 - i][n - 1 - j]
        // b = matrix[j][n - 1 - i];
        // c = matrix[n - 1 - j][i]
        //
        // Math.ceil : returns the smallest integer >= a number.
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < Math.ceil(((double)n)/2.0); j++) {
                int tmp = matrix[i][j];
                //c -> a
                matrix[i][j] = matrix[n - 1 - j][i];
                //d -> c
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                //b -> d
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                //a -> b
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }
    
    
    /**
     * The key idea is to rotate the matrix according to layers. 
     * For the nth layer(the out layer), rotate 90 degree is to move all the elements n times in a circle. 
     * In each layer, the rotation can be performed by first swap 4 corners, 
     * then swap 4 elements next to corner until the end of each line.
     * 
     * @param matrix 
     */
    public void rotate(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = matrix.length;
        if (n == 0 || n == 1) {
            return;
        }
        
        /**
         *      (layer, i)          ∆           (i, n-layer-1)
         *          ∆               ∆                ∆
         *      (n-i-1, layer)      ∆           (n-layer-1, n-i-1)
         */
        //n-i-1 = moving last index, n-layer-1 = fixed last index
        //layer : divide the square into different layers like an earth
        for (int layer = 0; layer < n / 2; layer++) {
            for (int i = layer; i < n - layer - 1; i++) {
                //save top
                int temp = matrix[layer][i];
                //left to top
                matrix[layer][i] = matrix[n - i - 1][layer];
                //bottom to left
                matrix[n - i - 1][layer] = matrix[n - layer - 1][n - i - 1];
                //right to bottom
                matrix[n - layer - 1][n - i - 1] = matrix[i][n - layer - 1];
                //top to right
                matrix[i][n - layer - 1] = temp;
            }
        }
    }
    
    /**
     * http://fisherlei.blogspot.com/2013/01/leetcode-rotate-image.html
     * 
     * @param matrix 
     */
    public void rotate_v2(int[][] matrix) {
        int n = matrix.length;
        if (n == 0 || n == 1) {
            return;
        }
        
        //首先沿逆对角线翻转一次
        for (int i = 0; i < n -1 ; i++) {
            for (int j = 0; j < n - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][n-i-1];
                matrix[n-j-1][n-i-1] = tmp;
            }
        }
        
        //然后按x轴中线翻转一次
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = tmp;
            }
        }
    }
}
