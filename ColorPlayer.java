import java.awt.Color;

public abstract class ColorPlayer {
    private final Color color;
    private final int id;

    public ColorPlayer(Color color, int id) {
        this.color = color;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public abstract void next(Simulator simulator);

}
