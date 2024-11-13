class Solution {
    public int rodPoints(String s) {
        int len = s.length();
        int[] red = new int[10];
        int[] green = new int[10];
        int[] blue = new int[10];

        for (int i = 0; i < len; i += 2) {
            char c = s.charAt(i);
            int num = s.charAt(i + 1) - '0';

            switch (c) {
                case 'R' -> red[num]++;
                case 'G' -> green[num]++;
                case 'B' -> blue[num]++;
                default -> {
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 10; i++) {
            if (red[i] > 0 && green[i] > 0 && blue[i] > 0) {
                ++res;
            }
        }

        return res;
    }
}
