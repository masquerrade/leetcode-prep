class Solution {
    /**
     * Finds the longest common prefix string amongst an array of strings.
     *
     * @param strs The array of strings.
     * @return The longest common prefix. Returns an empty string "" if there is none.
     */
    public String longestCommonPrefix(String[] strs) {
        // Handle the edge case where the input array is null or empty.
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Iterate through the characters of the first string.
        for (int i = 0; i < strs[0].length(); i++) {
            // Get the character at the current position (i) from the first string.
            char currentChar = strs[0].charAt(i);

            // Compare this character with the character at the same position
            // in all the other strings.
            for (int j = 1; j < strs.length; j++) {
                // If the current string is shorter than the index 'i' OR
                // the character doesn't match, we have found the end of the LCP.
                if (i == strs[j].length() || strs[j].charAt(i) != currentChar) {
                    // Return the substring of the first string from the beginning
                    // up to the current index 'i'.
                    return strs[0].substring(0, i);
                }
            }
        }

        // If the outer loop completes, it means the entire first string
        // is a common prefix.
        return strs[0];
    }
}
