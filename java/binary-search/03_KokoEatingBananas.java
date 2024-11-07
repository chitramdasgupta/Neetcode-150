class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canEat(piles, h, mid)) {
                right = mid - 1;
                res = Math.min(mid, res);
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean canEat(int[] piles, int h, int k) {
        double hours = 0;
        for (double pile : piles) {
            hours += Math.ceil(pile / k);

            if (hours > h) {
                return false;
            }
        }

        return true;
    }
}
