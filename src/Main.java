import BinarySearch.BinarySearch;

public class Main {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5};
        int target = 3;
        System.out.println(new BinarySearch().search(nums, target));
    }
}