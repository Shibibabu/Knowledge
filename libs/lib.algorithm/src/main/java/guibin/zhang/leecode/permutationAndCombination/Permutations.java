package guibin.zhang.leecode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Given a collection of numbers, return all possible permutations.
 * 
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 * 
 * http://yucoding.blogspot.com/2013/04/leetcode-question-69-permutations.html
 * http://blog.csdn.net/tuantuanls/article/details/8717262
 * http://discuss.leetcode.com/questions/224/permutations
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Permutations {
    
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (num == null) {
            return null;
        }
        return permute(num, num.length - 1);
    }
    
    public ArrayList<ArrayList<Integer>> permute(int[] num, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (k < 0) {
            result.add(new ArrayList<Integer>());
        } else {
            for (ArrayList<Integer> x : permute(num, k - 1)) {
                for (int i = 0; i <= x.size(); i++) {
                    ArrayList<Integer> tmp = (ArrayList<Integer>) x.clone();
                    tmp.add(i, num[k]);
                    result.add(tmp);
                }
            }
        }
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> permute_v2(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(num == null) return null;
        Integer[] n = new Integer[num.length];
        for (int i = 0; i < num.length; i++) {
            n[i] = num[i];
        }
        return perm(n, 0, num.length - 1);
        
    }
    
    public ArrayList<ArrayList<Integer>> perm(Integer[] num, int start, int end) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (start > end) {
            result.add(new ArrayList<Integer>(Arrays.asList(num)));
        } else {
            for (int i = start; i <= end; i++) {
                swap(num, start, i);
                result.addAll(perm(num, start + 1, end));
                swap(num, start, i);
            }
        }
        
        return result;
    }
    
    public void swap(Integer[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    
    /**
     * The idea of this classic problem is to use backtracking.
     * We want to get permutations, which is mainly about swap values in the list.
     * Consider:
     * a --> a
     * ab --> ab, ba
     * abc --> abc, acb, bac, bca, cab, cba.
     * ...
     * where for the length of n, the permutations can be generated by
     * (1) Swap the 1st element with all the elements, including itself.
     * (2) Then the 1st element is fixed, go to the next element.
     * (3) Until the last element is fixed. Output.
     * It's more clear in the figure above. The key point is to make the big problem into smaller problem, 
     * here is how to convert the length n permutation into length n-1 permutation problem.
     * 
     * @param num
     * @return 
     */
    public ArrayList<ArrayList<Integer>> permute_v3(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(num == null) return null;
        Integer[] n = new Integer[num.length];
        for (int i = 0; i < num.length; i++) {
            n[i] = num[i];
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        perm(n, 0, num.length - 1, result);
        return result;
        
    }
    
    /**
     * swap the elements from start to end, one by one.
     * @param num
     * @param start
     * @param end
     * @param result 
     */
    public void perm(Integer[] num, int start, int end, ArrayList<ArrayList<Integer>> result) {
        if (start == end) {
            result.add(new ArrayList<Integer>(Arrays.asList(num)));
        } else {
            for (int i = start; i <= end; i++) {
                //Swap the first element with the rest of the element, including itself.
                swap(num, start, i);
                perm(num, start + 1, end, result);
                //Swap back
                swap(num, start, i);
            }
        }
    }
}