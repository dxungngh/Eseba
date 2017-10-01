package com.eseba.jp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danielnguyen on 8/21/17.
 */

public class ServiceRegistry {
    private static Map<String, Object> references = new HashMap<String, Object>();

    public static void registerService(String id, Object reference) {
        references.put(id, reference);
    }

    public static Object getService(String id) {
        return references.get(id);
    }
}
