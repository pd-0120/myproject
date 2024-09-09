package Enum;

/**
 *
 * @author PJ
 */
public enum Role {
    ADMIN("Administrator"),
    USER("User"),
    GUEST("Guest"),
    SUPPORT("SupportOrganisation");
    
    private final String displayName;

    // Constructor
    Role(String displayName) {
        this.displayName = displayName;
    }

    // Getter
    public String getDisplayName() {
        return displayName;
    }

    // Lookup by string value
    public static Role fromDisplayName(String displayName) {
        for (Role role : Role.values()) {
            if (role.getDisplayName().equalsIgnoreCase(displayName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No constant with text " + displayName + " found");
    }
}
