class TimeMap {
    private final Map<String, List<String[]>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<String[]> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new String[] {value, String.valueOf(timestamp)});
        map.put(key, list);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<String[]> values = map.get(key);
        int left = 0;
        int right = values.size() - 1;
        String res = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Integer.parseInt(values.get(mid)[1]) < timestamp) {
                left = mid + 1;
                res = values.get(mid)[0];
            } else if (Integer.parseInt(values.get(mid)[1]) > timestamp) {
                right = mid - 1;
            } else {
                res = values.get(mid)[0];
                break;
            }
        }

        return res;
    }
}
