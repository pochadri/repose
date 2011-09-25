package com.rackspace.papi.commons.util;

import java.util.regex.Pattern;

/**
 * Local StringUtils implementation...this is written because we don't want a dependency on
 * apache commons...it's too big for what we need.
 * 
 */
public final class StringUtilities {
    private StringUtilities() {
    }
    
    private static final Pattern IS_BLANK_PATTERN = Pattern.compile("[\\s]*");

    public static boolean isEmpty(String st) {
        return st == null || st.length() == 0;
    }

    public static boolean isBlank(String st) {
        return isEmpty(st) || IS_BLANK_PATTERN.matcher(st).matches();
    }

    public static String trim(String st, String trim) {
        if (st.length() < trim.length()) {
            return st;
        }

        final String next = st.startsWith(trim) ? st.substring(trim.length()) : st;
        return next.endsWith(trim) ? next.substring(0, next.length() - trim.length()) : next;
    }

    public static String join(Object... args) {
        final StringBuilder builder = new StringBuilder();

        for (Object a : args) {
            builder.append(a);
        }

        return builder.toString();
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
    
    public static String getValue(String string, String defaultValue) {
      if (string != null) {
        return string;
      }
      
      return defaultValue;
    }

    public static boolean nullSafeEqualsIgnoreCase(String one, String two) {
        return one == null ? (two == null) : (two != null) && one.equalsIgnoreCase(two);
    }
    
    /**
     * Formats a URI by adding a forward slash and removing the last forward
     * slash from the URI.
     * 
     * e.g. some/random/uri/    -> /some/random/uri
     * e.g. some/random/uri     -> /some/random/uri
     * e.g. /some/random/uri/   -> /some/random/uri
     * 
     * @param uri
     * @return 
     */
    public static String formatUri(String uri) {
        if (StringUtilities.isBlank(uri)) {
            return "";
        }

        final StringBuilder externalName = new StringBuilder(uri);

        if (externalName.charAt(0) != '/') {
            externalName.insert(0, "/");
        }

        if (externalName.charAt(externalName.length() - 1) == '/') {
            externalName.deleteCharAt(externalName.length() - 1);
        }

        return externalName.toString();
    }
}
