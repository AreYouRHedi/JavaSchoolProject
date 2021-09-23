package domain;

class Column {
    public static int MAX_SIZE = 6;

    private int currentStackSize;
    private Color[] tokenStack;

    public Column() {
        this.currentStackSize = 0;
        this.tokenStack = new Color[MAX_SIZE];
    }

    public Color[] getTokenStack() {
        return tokenStack;
    }

    public void play(Color color) {
        tokenStack[currentStackSize] = color;
        currentStackSize++;
    }

    public int getCurrentStackSize() {
        return currentStackSize;
    }

    public boolean isPlayable(){
        return !isFull();
    }

    public boolean isFull(){
        return this.currentStackSize == MAX_SIZE;
    }
}
