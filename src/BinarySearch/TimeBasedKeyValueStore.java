package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Time - O(log n)
// Space - O(n)
public class TimeBasedKeyValueStore {
    private final Map<String, ArrayList<String[]>> store;

    public TimeBasedKeyValueStore() {
        store = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!store.containsKey(key)) {
            store.put(key, new ArrayList<>());
        }

        store.get(key).add(new String[]{value, String.valueOf(timestamp)});
    }

    public String get(String key, int timestamp) {
        String res = "";
        if (!store.containsKey(key)) {
            return res;
        }

        ArrayList<String[]> values = store.get(key);
        int left = 0;
        int right = values.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midTimeStamp = Integer.parseInt(values.get(mid)[1]);
            String midValue = values.get(mid)[0];

            if (timestamp == midTimeStamp) {
                res = midValue;
                break;
            }

            if (timestamp > midTimeStamp) {
                res = midValue;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }
}
