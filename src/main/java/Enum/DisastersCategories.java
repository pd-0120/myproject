package Enum;

/**
 *
 * @author PJ
 */
public enum DisastersCategories {
    CHEMICAL_SPILL("CHEMICAL_SPILL"),
    NUCLEAR_ACCIDENT("NUCLEAR_ACCIDENT"),
    OIL_SPILL("OIL_SPILL"),
    WAR("WAR"),
    TERRORISM("TERRORISM"),
    FIRE("FIRE"),
    EARTHQUAKE("EARTHQUAKE"),
    FLOOD("FLOOD"),
    CYBER("CYBER"),
    HURRICANE("HURRICANE"),
    VOLCANIC_ERUPTION("VOLCANIC_ERUPTION"),
    TORNADO("TORNADO"),
    TSUNAMI("TSUNAMI");
    
    private final String displayName;

    // Constructor
    DisastersCategories(String displayName
    ) {
        this.displayName = displayName;
    }

    // Getter
    public String getDisplayName() {
        return displayName;
    }

    // Lookup by string value
    public static DisastersCategories fromDisplayName(String displayName) {
        for (DisastersCategories disasterCategory : DisastersCategories.values()) {
            if (disasterCategory.getDisplayName().equalsIgnoreCase(displayName)) {
                return disasterCategory;
            }
        }
        throw new IllegalArgumentException("No constant with text " + displayName + " found");
    }
}
