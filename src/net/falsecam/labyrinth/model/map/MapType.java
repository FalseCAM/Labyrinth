package net.falsecam.labyrinth.model.map;

/**
 *
 * @author FalseCAM
 */
public enum MapType {

    START("S", "Start", true, true, true, true), //
    TARGET("T", "Target", true, true, true, true), //
    HORIZONTAL("-", "Horizontal", false, false, true, true), //
    VERTICAL("|", "Vertical", true, true, false, false), //
    TOPLEFT(")", "Top Left", true, false, true, false), //
    TOPRIGHT("(", "Top Right", true, false, false, true), //
    BOTTOMLEFT("\\", "Bottom Left", false, true, true, false), //
    BOTTOMRIGHT("/", "Bottom Right", false, true, false, true), //
    TOPHORIZONTAL("1", "Top Horizontal", true, false, true, true), //
    BOTTOMHORIZONTAL("2", "Bottom Horizontal", false, true, true, true), //
    LEFTVERTICAL("3", "Left Vertical", true, true, true, false), //
    RIGHTVERTICAL("4", "Right Vertical", true, true, false, true), //
    FREE("+", "Free", true, true, true, true), //
    STAR("*", "Star", true, true, true, true), //
    CLOSED("O", "Closed", false, false, false, false);
    private String chr;
    private String name;
    private boolean top;
    private boolean bottom;
    private boolean left;
    private boolean right;

    private MapType(String chr, String name, boolean top, boolean bottom, boolean left, boolean right) {
        this.chr = chr;
        this.name = name;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return name + "(" + chr + ")";
    }

    public String getName() {
        return name;
    }

    public boolean isTopFree() {
        return top;
    }

    public boolean isBottomFree() {
        return bottom;
    }

    public boolean isLeftFree() {
        return left;
    }

    public boolean isRightFree() {
        return right;
    }

    public String getChr() {
        return chr;
    }

    public static MapType get(String chr) {
        for (MapType t : MapType.values()) {
            if (chr.equals(t.getChr())) {
                return t;
            }
        }
        return MapType.CLOSED;
    }
}
