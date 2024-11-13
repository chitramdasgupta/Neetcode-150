class Solution {
    public int minDaysBloom(int[] roses, int k, int n) {
        int left = Arrays.stream(roses).min().getAsInt();
        int right = Arrays.stream(roses).max().getAsInt();

        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(roses, k, n, mid)) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean isValid(int[] harvest, int k, int n, int day) {
        int curr = 0;
        int bouquets = 0;

        boolean flag = false;
        for (int h : harvest) {
            curr = h <= day ? curr + 1 : 0;

            if (curr == k) {
                bouquets++;
                curr = 0;
            }

            if (bouquets == n) {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
