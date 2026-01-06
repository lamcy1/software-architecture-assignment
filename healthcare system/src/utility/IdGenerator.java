package utility;

import java.util.List;

/**
 * Utility class for generating unique IDs with a specific prefix.
 *
 * IDs follow a sequential numeric pattern with 3-digit padding.
 * Examples:
 *   - Prefix "A"  -> A001, A002, A003, ...
 *   - Prefix "RX" -> RX001, RX002, RX003, ...
 *   - Prefix "R"  -> R001, R002, R003, ...
 */
public class IdGenerator {

    /**
     * Generates the next unique ID based on the given prefix and list of existing IDs.
     *
     * Scans existing IDs, extracts the numeric part, and finds the maximum value.
     * The next ID is then the maximum + 1, padded to 3 digits.
     *
     * @param prefix      The prefix for the ID (e.g., "A", "RX", "R")
     * @param existingIds List of existing IDs to avoid duplicates
     * @return The next generated ID with the same prefix
     */
    public static String nextId(String prefix, List<String> existingIds) {

        int max = 0;

        for (String id : existingIds) {
            if (id == null) continue;
            if (!id.startsWith(prefix)) continue;

            String numberPart = id.substring(prefix.length());

            try {
                int value = Integer.parseInt(numberPart);
                if (value > max) {
                    max = value;
                }
            } catch (NumberFormatException ignored) {
                // Ignore malformed IDs
            }
        }

        int next = max + 1;

        // Return ID with 3-digit padding
        return prefix + String.format("%03d", next);
    }
}
