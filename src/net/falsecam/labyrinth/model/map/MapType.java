package net.falsecam.labyrinth.model.map;

/**
 *
 * @author FalseCAM
 */
public enum MapType {

    START("S", "Start", true, true, true, true), //
    TARGET("T", "Target", true, true, true, true), //
    HORIZONTAL("H", "Horizontal", false, false, true, true), //
    VERTICAL("V", "Vertical", true, true, false, false), //
    TOPLEFT("1", "Top Left", true, false, true, false), //
    TOPRIGHT("2", "Top Right", true, false, false, true), //
    BOTTOMLEFT("3", "Bottom Left", false, true, true, false), //
    BOTTOMRIGHT("4", "Bottom Right", false, true, false, true), //
    TOPHORIZONTAL("5", "Top Horizontal", true, false, true, true), //
    BOTTOMHORIZONTAL("6", "Bottom Horizontal", false, true, true, true), //
    LEFTVERTICAL("7", "Left Vertical", true, true, true, false), //
    RIGHTVERTICAL("8", "Right Vertical", true, true, false, true);
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
        return chr;
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
}
