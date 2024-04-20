// AsteroidSize.java
public enum AsteroidSize {
    SMALL(50, 50),
    MEDIUM(75, 75),
    LARGE(100, 100);
    private final int width;
    private final int height;
    AsteroidSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
