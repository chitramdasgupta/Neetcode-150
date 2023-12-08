package Greedy;

// Time - O(n)
// Space - O(1)
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        // The min and max of the valid range of opening parentheses
        int openMin = 0;
        int openMax = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                openMin += 1;
                openMax += 1;
            } else if (s.charAt(i) == ')') {
                openMin -= 1;
                openMax -= 1;
            } else {
                // Here we process for the wildcard character
                openMin -= 1; // Assuming an open parenthesis
                openMax += 1; // Assuming a closed parenthesis
                // For an empty space the range remains unchanged
            }
            if (openMax < 0) {
                return false;
            }
            if (openMin < 0) {
                // We reset the openMin because the choices we made caused our
                // openMin to become invalid
                openMin = 0;
            }
        }

        return openMin == 0;
    }
}
