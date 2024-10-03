import java.awt.*;

public class Interactable {

    private Engine engine;
    private Rectangle interactBox;

    public Interactable(int x, int y, int height, int width) {
        interactBox = new Rectangle(x, y, width, height);
    }

    public Rectangle getInteractBox() {
        return interactBox;
    }

    public void setInteractBox(Rectangle interactBox) {
        this.interactBox = interactBox;
    }
}
