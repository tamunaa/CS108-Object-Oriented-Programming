package Data;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    private Map<String, String> mp;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_ERROR = -1;
    public static final int CODE_IN_USE = 1;

    public AccountManager() {
        mp = new HashMap<String, String>();
        mp.put("Patric", "1234");
        mp.put("Molly", "FloPup");
    }

    public int add(String key, String value) {
        if (key.isEmpty() || value.isEmpty()) return CODE_ERROR;
        if (mp.containsKey(key))return CODE_IN_USE;
        mp.put(key, value);
        return CODE_SUCCESS;
    }

    public boolean contains(String key, String value) {
        return mp.containsKey(key) && mp.get(key).equals(value);
    }

}