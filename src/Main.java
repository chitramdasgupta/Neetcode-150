import BinarySearch.FindMinimumInRotatedSortedArray;
import SlidingWindow.LongestRepeatingCharReplacement;
import SlidingWindow.LongestSubstrWithoutRepeatingChars;

public class Main {
    public static void main(String[] args) {
        String input = "AABABBA";
        int k = 1;
        System.out.println(new LongestRepeatingCharReplacement().characterReplacement(input, k));
    }
}
