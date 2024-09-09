package Enum;

/**
 *
 * @author PJ
 */
public enum DisasterStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String displayName;

    // Constructor
    DisasterStatus(String displayName
    ) {
        this.displayName = displayName;
    }

    // Getter
    public String getDisplayName() {
        return displayName;
    }

    // Lookup by string value
    public static DisasterStatus fromDisplayName(String displayName) {
        for (DisasterStatus disasterStatus : DisasterStatus.values()) {
            if (disasterStatus.getDisplayName().equalsIgnoreCase(displayName)) {
                return disasterStatus;
            }
        }
        throw new IllegalArgumentException("No constant with text " + displayName + " found");
    }
}
