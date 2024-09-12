package Enum;

/**
 *
 * @author PJ
 */
public enum DisasterDepartment {
    POLICE("POLICE"),
    FIRE("FIRE"),
    MEDICAL("MEDICAL"),
    FINANCE("FINANCE"),
    CYBER("CYBER"),
    FORENSIC("FORENSIC");
    
    private final String displayName;

    // Constructor
    DisasterDepartment(String displayName
    ) {
        this.displayName = displayName;
    }

    // Getter
    public String getDisplayName() {
        return displayName;
    }

    // Lookup by string value
    public static DisasterDepartment fromDisplayName(String displayName) {
        for (DisasterDepartment disasterDepartment : DisasterDepartment.values()) {
            if (disasterDepartment.getDisplayName().equalsIgnoreCase(displayName)) {
                return disasterDepartment;
            }
        }
        throw new IllegalArgumentException("No constant with text " + displayName + " found");
    }
}
