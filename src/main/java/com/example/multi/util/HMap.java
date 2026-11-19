package com.example.multi.util;

import org.apache.commons.collections4.map.ListOrderedMap;

import java.util.Objects;

public class HMap extends ListOrderedMap<String, Object> {
    /**
     * serial version UID
     **/
    private static final long serialVersionUID = 1L;

    public String getString(String key) {
        String result = "";

        Object value = get(key);
        if (Objects.nonNull(value)) {
            result = (String) value;
        }

        return result;
    }


    public Object put(String key, Object value) {
        return super.put(convert2CamelCase(key), value);
    }

    public Object put2(String key, Object value) {
        return super.put(convert3CamelCase(key), value);
    }

    public Object put3(String key, Object value) {
        return super.put(key, value);
    }

    public static String convert2CamelCase(String underScore) {
        if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
            return underScore;
        }

        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();
        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
                continue;
            }
            if (nextUpper) {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else {
                result.append(Character.toLowerCase(currentChar));
            }
        }

        return result.toString();
    }

    /**
     *
     *
     * @param underScore
     * @return
     */
    public static String convert3CamelCase(String underScore) {
        if (underScore.indexOf('_') < 0) {
            return underScore;
        }

        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();
        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
                continue;
            }
            if (nextUpper) {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

}
