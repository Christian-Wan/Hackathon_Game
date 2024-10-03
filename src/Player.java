import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private Engine engine;
    private Rectangle hitBox;
    private int speed, xCord, yCord;

    public Player(Engine engine) {

    }

    public void updatePlayer() {
        ArrayList<Integer> input = engine.getInput().getInputs();

        if (input.contains(KeyEvent.VK_W)) {

        }
        else if (input.contains(KeyEvent.VK_A)) {

        }
        else if (input.contains(KeyEvent.VK_S)) {

        }
        else if (input.contains(KeyEvent.VK_D)) {

        }
        else if (input.contains(KeyEvent.VK_E)) {

        }
    }
}
