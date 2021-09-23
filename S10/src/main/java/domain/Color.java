package domain;

public enum Color {
    YELLOW, RED;

    /**
     * a private copy of values in order to prevent creating clones
     */
    private static Color[] allColors = values();

    /**
     * easily loop through colors
     *
     * @return Color the next color in the list or the first when end is reached
     */
    public Color nextColor()
    {
        int nextIndex = (this.ordinal()+1) % allColors.length;
        return allColors[nextIndex];
    }
}
