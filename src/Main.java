import TwoPointers.ThreeSum;

import java.util.*;

public class Main {
    public static void main(String[] args) {
      int[] input = new int[4];
      input[0] = 0; input[1] = 0;
      input[2] = 0; input[3] = 0;

      List<List<Integer>> ans = new ThreeSum().threeSum(input);
      System.out.println(ans.toString());
    }
}
