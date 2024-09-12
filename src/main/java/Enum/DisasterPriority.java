package Enum;

/**
 *
 * @author PJ
 */
public enum DisasterPriority {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String displayName;

    // Constructor
    DisasterPriority(String displayName
    ) {
        this.displayName = displayName;
    }

    // Getter
    public String getDisplayName() {
        return displayName;
    }

    // Lookup by string value
    public static DisasterPriority fromDisplayName(String displayName) {
        for (DisasterPriority disasterPriority : DisasterPriority.values()) {
            if (disasterPriority.getDisplayName().equalsIgnoreCase(displayName)) {
                return disasterPriority;
            }
        }
        throw new IllegalArgumentException("No constant with text " + displayName + " found");
    }
}
