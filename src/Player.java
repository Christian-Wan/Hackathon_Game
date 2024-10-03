import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private Engine engine;
    private Rectangle hitBox;
    private int speed, xCord, yCord;

    public Player(Engine engine) {
        this.engine = engine;
        hitBox = new Rectangle(10, 10);
        speed = 2;
        xCord = 10;
        yCord = 10;
    }

    public void updatePlayer() {
        movePlayer();
    }

    public void movePlayer() {
        ArrayList<Integer> input = engine.getInput().getInputs();
        int up = input.indexOf(KeyEvent.VK_W);
        int left = input.indexOf(KeyEvent.VK_A);
        int down = input.indexOf(KeyEvent.VK_S);
        int right = input.indexOf(KeyEvent.VK_D);

        if (up > left && up > down && up > right) {
            System.out.println("up");
            yCord -= speed;
        }
        else if (left > up && left > down && left > right) {
            System.out.println("left");
            xCord -= speed;
        }
        else if (down > left && down > up && down > right) {
            System.out.println("down");
            yCord += speed;
        }
        else if (right > left && right > down && right > up) {
            System.out.println("right");
            xCord += speed;
        }
    }

    public void interact() {

    }

    public void draw(Graphics2D g) {
        g.drawRect(xCord, yCord, hitBox.width, hitBox.height);
    }
}
